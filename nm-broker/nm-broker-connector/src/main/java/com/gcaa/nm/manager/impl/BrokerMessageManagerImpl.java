package com.gcaa.nm.manager.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gcaa.commons.model.MatchedGroup;
import com.gcaa.commons.utils.GcaaUtil;
import com.gcaa.nm.BrokerConstants;
import com.gcaa.nm.entity.NmMessageEntity;
import com.gcaa.nm.entity.NmSubscriptionTypeEntity;
import com.gcaa.nm.manager.BrokerMessageManager;
import com.gcaa.nm.producer.GcaaNmMessageProducer;
import com.gcaa.nm.repository.NmMessageRepository;
import com.gcaa.nm.repository.NmSubscriptionTypeRepository;
import com.gcaa.nm.types.NmMessageType;
import com.gcaa.nm.types.NmSubscriptionType;

@Component
public class BrokerMessageManagerImpl implements BrokerMessageManager, InitializingBean {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(BrokerMessageManagerImpl.class);

	private Long lastUpdated = null;

	@Autowired
	private NmMessageRepository msgRepo;

	@Autowired
	NmSubscriptionTypeRepository subRepo;

	@Autowired
	GcaaNmMessageProducer msgProducer;

	@Value("${gcaa.broker.house-keeping-days}")
	private int daysToKeepData;

	private Map<String, NmSubscriptionType> nmSubTypesMap = new HashMap<String, NmSubscriptionType>();

	@Override
	public boolean processReceivedMessage(String rcvdMsg) {
		if (null == rcvdMsg || rcvdMsg.length() == 0) {
			return false;
		}

		NmMessageType msgType = identifyMessageType(rcvdMsg);
		NmMessageEntity msgEntity = new NmMessageEntity();

		msgEntity.setMessageType(msgType);

		msgEntity.setReceivedDate(new Date());
		msgEntity.setReceivedMessage(rcvdMsg);

		msgRepo.save(msgEntity);

		return true;
	}

	public NmMessageType identifyMessageType(String message) {
		if (null == message) {
			return null;
		}

		if (message.length() >= 100) {
			return message.substring(0, 100).contains(BrokerConstants.TECHNICAL_MESSAGE_HEADER)
					? NmMessageType.TECHNICAL_MESSAGE : NmMessageType.BUSINESS_MESSAGE;
		}

		List<MatchedGroup> mgrp = GcaaUtil.matchByGroups("(<\\w*:?" + BrokerConstants.TECHNICAL_MESSAGE_HEADER+")",
				message);
		if (null != mgrp && mgrp.size() > 0) {
			return NmMessageType.TECHNICAL_MESSAGE;
		} else {
			return NmMessageType.BUSINESS_MESSAGE;
		}

	}

	public NmSubscriptionType identifyMessageSubscriptionType(String message) {
		if (null == message) {
			return null;
		}

		for (Map.Entry<String, NmSubscriptionType> subTypEntry : nmSubTypesMap.entrySet()) {
			List<MatchedGroup> mgrp = GcaaUtil.matchByGroups("(<\\w*:?" + subTypEntry.getKey().trim() + ")", message);
			if (null != mgrp && mgrp.size() > 0) {
				return subTypEntry.getValue();
			}
		}

		return nmSubTypesMap.get(BrokerConstants.DEFAULT_SUBSCRIPTION_NAME);
	}

	/**
	 * Publishes messages to GCAA Broker. Messages are read from nm_messages table and then published
	 */
	@Scheduled(fixedDelay = 10000)
	@Override
	@Transactional
	public void initiateMessagePublishing() {
		List<NmMessageEntity> nmMessages = msgRepo
				.findByProcessedDateIsNullAndMessageTypeEquals(NmMessageType.BUSINESS_MESSAGE);

		if (null == nmMessages || nmMessages.size() == 0) {
			return;
		}
		msgProducer.publishMessages(nmMessages);
	}

	/**
	 * House Keeping. Deletes Messages that are 10 days old.
	 */
	@Scheduled(cron = "0 0 0 * * ?") // Every day midnight /*@Scheduled(cron="0
										// * * * * ?")*/ //Every minute. For
										// testing purpose
	@Transactional
	public void cleanUpMessages() {
		GregorianCalendar gc = new GregorianCalendar();
		gc.add(Calendar.DAY_OF_MONTH, -daysToKeepData);

		List<NmMessageEntity> nmMessages = msgRepo.findByProcessedDateNotNullAndProcessedDateBefore(gc.getTime());
		if (null != nmMessages && nmMessages.size() > 0) {
			msgRepo.delete(nmMessages);
		}

		nmMessages = null;
		nmMessages = msgRepo.findByMessageTypeAndReceivedDateBefore(NmMessageType.TECHNICAL_MESSAGE, gc.getTime());
		if (null != nmMessages && nmMessages.size() > 0) {
			msgRepo.delete(nmMessages);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		loadNmSubscriptionTypeMap();
	}

	private void loadNmSubscriptionTypeMap() {
		Iterable<NmSubscriptionTypeEntity> nmSubTypeEnts = subRepo.findAll();

		for (NmSubscriptionTypeEntity nmSubEnt : nmSubTypeEnts) {
			NmSubscriptionType subType = new NmSubscriptionType();
			BeanUtils.copyProperties(nmSubEnt, subType);
			nmSubTypesMap.put(subType.getSubscriptionTypeName(), subType);
		}
	}

	public Map<String, NmSubscriptionType> getNmSubTypesMap() {
		if (null == nmSubTypesMap || nmSubTypesMap.size() == 0 || lastUpdated == null
				|| lastUpdated + 600000 > System.currentTimeMillis()) {
			loadNmSubscriptionTypeMap();
		}
		return nmSubTypesMap;
	}
}
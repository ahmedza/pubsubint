
package com.gcaa.nm.manager.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gcaa.nm.entity.SubscriptionEntity;
import com.gcaa.nm.eurocontrol._2_5_0.Subscription;
import com.gcaa.nm.manager.NmSubscriptionManager;
import com.gcaa.nm.repository.SubscriptionRepository;
import com.gcaa.nm.types.SubscriptionStatus;
import com.gcaa.nm.wss.client.NmSubscriptionClient;

@Component
public class NmSubscriptionManagerImpl implements NmSubscriptionManager {

	private static final Logger logger = LoggerFactory.getLogger(NmSubscriptionManagerImpl.class);

	@Autowired
	NmSubscriptionClient nmWssClient;

	@Autowired
	SubscriptionRepository subRepo;

	@Override
	public void syncNmSubscriptions() {
		List<Subscription> subscriptions = nmWssClient.getSubscriptionList(null);
		syncLocalSubscriptions(subscriptions);
	}

	@Override
	public void syncLocalSubscriptions(List<Subscription> subscriptions) {
		for (Subscription subscription : subscriptions) {
			if (subscription.getState().equals(SubscriptionStatus.PAUSED.getCode())
					|| subscription.getState().equals(SubscriptionStatus.SUSPENDED_PAUSE.getCode())) {
				resumeAndUpdateStatus(subscription);
			} else if (subscription.getState().equals(SubscriptionStatus.DELETED.getCode())) {
				disableDeletedSubscriptions(subscription);
			} else if (subscription.getState().equals(SubscriptionStatus.ACTIVE.getCode())) {
				updateSubscriptionStateToActive(subscription);
			}
		}
	}

	public void updateSubscriptionStateToActive(Subscription subscription) {
		SubscriptionEntity subEntity = subRepo.findByUuidEquals(subscription.getUuid());
		if (null == subEntity) {
			subEntity = creatEntityFromNmSubscription(subscription);
		} else if (!subEntity.getIsActive()) {
			logger.warn(
					"Broker listener for Subscription {} will be Stopped as, subscription is not active in system. Activate subscription in system to consume messages for this subscription. This may result cause the NM subscription to be PAUSED by NM Admin as messages could get expired in NM queue", subscription.getUuid());
			return;
		}
		
		if(subEntity.getId() != null && subEntity.getSubscriptionStatus() == SubscriptionStatus.ACTIVE && 
		subEntity.getIsActive()){
			return;
		}

		subEntity.setSubscriptionStatus(SubscriptionStatus.ACTIVE);
		subEntity.setIsActive(true);
		subRepo.save(subEntity);
	}

	public void disableDeletedSubscriptions(Subscription subscription) {
		SubscriptionEntity subEntity = subRepo.findByUuidEquals(subscription.getUuid());

		if (null == subEntity) {
			subEntity = creatEntityFromNmSubscription(subscription);
		}

		if(subEntity.getSubscriptionStatus() == SubscriptionStatus.DELETED && !subEntity.getIsActive()){
			return;
		}
		
		subEntity.setSubscriptionStatus(SubscriptionStatus.DELETED);
		subEntity.setIsActive(false);
		subRepo.save(subEntity);
		/* } */
	}

	public void resumeAndUpdateStatus(Subscription subscription) {

		SubscriptionEntity subEntity = subRepo.findByUuidEquals(subscription.getUuid());
		if (null == subEntity) {
			subEntity = creatEntityFromNmSubscription(subscription);
		} else if (!subEntity.getIsActive()) {
			logger.warn(
					"Failed in trying to resume a Paused NM subscription {}. Subscription is not active in system. Activate subscription in system before sending resume request. This may result cause the NM subscription to be PAUSED by NM Admin as messages could get expired in NM queue", subscription.getUuid());
			return;
		}
		
		nmWssClient.resumeSubscription(subscription.getUuid());

		if(subEntity.getId() != null && subEntity.getSubscriptionStatus() == SubscriptionStatus.ACTIVE){
			return;
		}
		subEntity.setSubscriptionStatus(SubscriptionStatus.ACTIVE);
		subEntity.setIsActive(true);
		subRepo.save(subEntity);
		
//		updateSubscriptionStateToActive(subscription);

	}

	public void updatePauseStatus(Subscription subscription) {
		/*
		 * if
		 * (subscription.getState().equals(SubscriptionStatus.PAUSE.getCode())
		 * || subscription.getState().equals(SubscriptionStatus.SUSPENDED_PAUSE.
		 * getCode())) {
		 * 
		 * nmWssClient.pauseSubscription(subscription.getUuid());
		 */

		SubscriptionEntity subEntity = subRepo.findByUuidEquals(subscription.getUuid());
		subEntity.setSubscriptionStatus(SubscriptionStatus.PAUSED);
		subEntity.setIsActive(true);
		subRepo.save(subEntity);
		/* } */
	}

	private SubscriptionEntity creatEntityFromNmSubscription(Subscription subscription) {

		SubscriptionEntity subEntity = new SubscriptionEntity();
		if (null == subscription) {
			return subEntity;
		}

		subEntity.setCreationDate(new Date());
		subEntity.setDescription(subscription.getDescription());
		subEntity.setIsActive(true);
		subEntity.setName(subscription.getDescription());
		subEntity.setQueueName(subscription.getQueueName());
		subEntity.setSubscriptionStatus(SubscriptionStatus.getSubscriptionStatus(subscription.getState()));
		subEntity.setTopicName(subscription.getTopic());
		subEntity.setUuid(subscription.getUuid());

		return subEntity;
	}

}
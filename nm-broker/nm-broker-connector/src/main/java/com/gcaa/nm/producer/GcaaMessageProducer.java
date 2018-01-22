package com.gcaa.nm.producer;

import java.util.Date;
import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gcaa.nm.entity.NmMessageEntity;
import com.gcaa.nm.repository.NmMessageRepository;
import com.gcaa.nm.types.NmMessageType;



@Component
public class GcaaMessageProducer {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(GcaaMessageProducer.class);

	@Autowired
	JmsTemplate gcaaJmsTemplate;

	@Autowired
	NmMessageRepository msgRepo;

	@Value("${gcaa.broker.flight-plans-queue}")
	public String gcaaFlightPlansQNme;

	@Value("${gcaa.broker.insert-flight-plans-in-topic}")
	public boolean insertFplansInTopic;

	@Value("${gcaa.broker.msg-time-to-live}")
	public int msgTimeToLive;

	@Value("${gcaa.broker.qos-enabled}")
	public boolean qosEnabled;

	@Scheduled(fixedDelay = 10000)
	@Transactional
	public void scheduledRead() {
		List<NmMessageEntity> nmMessages = msgRepo
				.findByProcessedDateIsNullAndMessageTypeEquals(NmMessageType.BUSINESS);

		if (null == nmMessages || nmMessages.size() == 0) {
			return;
		}

		logger.debug("Messages to Be Sent to GCAA Broker = {}", nmMessages.size());
		for (NmMessageEntity nmMessage : nmMessages) {
			try {
				sendToGcaaBroker(nmMessage);
				udateProcessedInDB(nmMessage);
			} catch (JMSException e) {
				logger.error(e.getMessage());
			}
		}
	}

	public void sendToGcaaBroker(NmMessageEntity nmMessage) throws JMSException {
		Destination dest = null;
		if (insertFplansInTopic) {
			dest = new ActiveMQTopic(gcaaFlightPlansQNme);
		} else {
			dest = new ActiveMQQueue(gcaaFlightPlansQNme);
		}
		gcaaJmsTemplate.setExplicitQosEnabled(qosEnabled);
		gcaaJmsTemplate.setTimeToLive(msgTimeToLive);
		gcaaJmsTemplate.send(dest/* "gcaanm-msg-q" */, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {

				return session.createTextMessage(nmMessage.getReceivedMessage());
			}
		});
	}

	public void udateProcessedInDB(NmMessageEntity nmMessage) {
		nmMessage.setProcessedDate(new Date());
		msgRepo.save(nmMessage);
	}
}

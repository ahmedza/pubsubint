package com.gcaa.nm.producer.impl;

import java.util.Date;
import java.util.List;

import javax.jms.DeliveryMode;
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
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gcaa.nm.entity.NmMessageEntity;
import com.gcaa.nm.manager.BrokerMessageManager;
import com.gcaa.nm.producer.GcaaNmMessageProducer;
import com.gcaa.nm.repository.NmMessageRepository;
import com.gcaa.nm.types.NmMessageType;
import com.gcaa.nm.types.NmSubscriptionType;

@Component
public class GcaaMessageProducerImpl implements GcaaNmMessageProducer {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(GcaaMessageProducerImpl.class);

	@Autowired
	JmsTemplate gcaaJmsTemplate;

	@Autowired
	NmMessageRepository msgRepo;

	@Autowired
	BrokerMessageManager msgManager;

	@Value("${gcaa.broker.insert-flight-plans-in-topic}")
	public boolean insertFplansInTopic;

	@Value("${gcaa.broker.qos-enabled}")
	public boolean qosEnabled;

	@Override
	@Transactional
	public void publishMessages(List<NmMessageEntity> nmMessages) {
		if (null == nmMessages || nmMessages.size() == 0) {
			return;
		}
		logger.debug("Messages to Be Sent to GCAA Broker = {}", nmMessages.size());
		for (NmMessageEntity nmMessage : nmMessages) {
			try {
				// if message is a business type, then check subscription type
				// i.e. Check if its a flight-plan or flight-data message
				NmSubscriptionType subType = null;
				if (nmMessage.getMessageType() != NmMessageType.TECHNICAL_MESSAGE) {
					subType = msgManager.identifyMessageSubscriptionType(nmMessage.getReceivedMessage());
					if (null == subType || subType.getDestinationName().length() == 0) {
						logger.warn(
								"Destination name unknown for PSI-Message-Id {}. Message is not published and will remain in PSI.",
								nmMessage.getId());
						return;
					}
				}
				sendToGcaaBroker(nmMessage, subType);
				udateProcessedInDB(nmMessage);
			} catch (JMSException e) {
				logger.error(e.getMessage());
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}

	public void sendToGcaaBroker(NmMessageEntity nmMessage, NmSubscriptionType subType) throws JMSException {
		Destination dest = null;
		if (insertFplansInTopic) {
			dest = new ActiveMQTopic(subType.getDestinationName());
		} else {
			dest = new ActiveMQQueue(subType.getDestinationName());
		}
		gcaaJmsTemplate.setExplicitQosEnabled(qosEnabled);

		gcaaJmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);
		gcaaJmsTemplate.send(dest, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				
				Message message = session.createTextMessage(nmMessage.getReceivedMessage());
				try{
				// setting time to live based on message type
				message.setJMSExpiration((subType.getMsgTimeToLiveMins())*60000);
				
				if (insertFplansInTopic) {
					message.setJMSDestination(new ActiveMQTopic(subType.getDestinationName()/* gcaaFlightPlansQNme */));
				} else {
					message.setJMSDestination(new ActiveMQQueue(subType.getDestinationName()));
				}
				
				message.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
				
				}catch (Exception e) {
					logger.error(e.getMessage());
				}
				return message;
			}
		});
		logger.debug("Message Id {} Sent to GCAA Broker Destination {}", nmMessage.getId(), subType.getDestinationName());
		
	}

	public void udateProcessedInDB(NmMessageEntity nmMessage) {
		nmMessage.setProcessedDate(new Date());
		msgRepo.save(nmMessage);
	}
}

package com.gcaa.nm.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import com.gcaa.nm.manager.BrokerMessageManager;

@Component
public class QpidConsumer implements SessionAwareMessageListener<Message> {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(QpidConsumer.class);

	@Autowired
	private BrokerMessageManager msgManager;

	@Override
	public void onMessage(Message message, Session session) throws JMSException {
		if (null == message) {
			return;
		}
		if (message instanceof TextMessage) {
			try {
				if(((TextMessage) message).getText().length() >= 50){
					logger.debug("New Message received= {}", ((TextMessage) message).getText().substring(0, 32));
				}else{
					logger.debug("Non-Text Message received= {}", ((TextMessage) message).getText().substring(0, ((TextMessage) message).getText().length()));
				}
				msgManager.processReceivedMessage( ((TextMessage) message).getText());
			} catch (JmsException jmsExcep) {
				logger.error("Error in Message. CorrelationId {}. \nExecption= {}", message.getJMSCorrelationID(),
						jmsExcep.getMessage());
				throw jmsExcep;
			} catch (Exception ex) {
				logger.error("Error in Message. CorrelationId {}. \nExecption= {}", message.getJMSCorrelationID(),
						ex.getMessage());
				throw ex;
			}
		}
	}
}
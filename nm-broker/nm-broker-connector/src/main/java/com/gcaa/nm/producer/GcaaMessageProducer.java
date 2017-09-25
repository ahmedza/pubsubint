package com.gcaa.nm.producer;

import java.util.Date;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	JmsTemplate gcaaJmsTemplate;
	
	@Autowired
	NmMessageRepository msgRepo;
	
	@Scheduled(fixedDelay=10000)
	@Transactional
	public void scheduledRead(){
		List<NmMessageEntity> nmMessages= msgRepo.findByProcessedDateIsNullAndMessageTypeEquals(NmMessageType.BUSINESS);
		
		if(null == nmMessages){return;}
		
		for (NmMessageEntity nmMessage : nmMessages) {
			try{
				sendToGcaaBroker(nmMessage);
				udateProcessedInDB(nmMessage);
			}catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendToGcaaBroker(NmMessageEntity nmMessage) throws JMSException {
		gcaaJmsTemplate.send("gcaanm-msg-q",new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {

				return session.createTextMessage(nmMessage.getReceivedMessage());
			}
		});
	}
	
	public void udateProcessedInDB(NmMessageEntity nmMessage){
		nmMessage.setProcessedDate(new Date());
		msgRepo.save(nmMessage);
	}
}

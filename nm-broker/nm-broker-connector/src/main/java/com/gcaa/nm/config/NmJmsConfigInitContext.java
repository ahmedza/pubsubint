package com.gcaa.nm.config;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.gcaa.nm.consumer.QpidConsumer;

@Configuration
public class NmJmsConfigInitContext {
	private static final Logger logger = LoggerFactory.getLogger(NmJmsConfigInitContext.class);
	@Bean
	@Primary
	public ConnectionFactory nmConnectionFactory(InitialContext initialContext) throws JMSException{
		ConnectionFactory nmConnectionfactory = null;
		try {
			nmConnectionfactory = (ConnectionFactory) initialContext.lookup("nmConnectionfactory");
			logger.debug("--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- CREATED NM CONNECITON -*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*");
		} catch (NamingException e) {
			logger.error("*-*-*-*- NNNOTTTT CREATED NM CONNECITON -*-*-*\n"+e.getMessage());
		}
		return nmConnectionfactory;
	}

	@Bean
	public QpidConsumer messageConsumer(){
		return new QpidConsumer();
	}


	@Value("${gcaa.nm.jmsrecovery-interval}")
	private Long jmsRecInterval;
	
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Lazy
	public DefaultMessageListenerContainer nmMessageContainer(ConnectionFactory nmConnectionFactory, QpidConsumer messageConsumer, String destinationName){
		DefaultMessageListenerContainer listenerContainer = new DefaultMessageListenerContainer();
		listenerContainer.setConcurrency("5-20");
		listenerContainer.setRecoveryInterval(jmsRecInterval);
		listenerContainer.setConnectionFactory(nmConnectionFactory);
		listenerContainer.setMessageListener(messageConsumer);
		listenerContainer.setDestinationName(destinationName);
		logger.info("Starting DefaultMessageListenerContainer for Queue {}", destinationName);
		return listenerContainer;
	}
	
}

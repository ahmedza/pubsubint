package com.gcaa.nm.config.adhoc;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.gcaa.nm.consumer.QpidConsumer;

/*@Profile(value="adhoc")
@Configuration*/
public class QueueListenerConfig {

	@Value("${nm.destination}")
	private String destinationName;
	
	@Bean
	public DefaultMessageListenerContainer nmMessageContainer(ConnectionFactory nmConnectionFactory, QpidConsumer messageConsumer){
		DefaultMessageListenerContainer listenerContainer = new DefaultMessageListenerContainer();
		listenerContainer.setConcurrency("5-20");
		listenerContainer.setConnectionFactory(new CachingConnectionFactory(nmConnectionFactory));
		listenerContainer.setMessageListener(messageConsumer);
		listenerContainer.setDestinationName(destinationName);
		return listenerContainer;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	
}

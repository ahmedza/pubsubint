package com.gcaa.nm.config.adhoc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Configuration
public class QueueListenerConfig {

	private static final Logger logger = LoggerFactory.getLogger(QueueListenerConfig.class);
	/*@Value("${nm.destination}")
	private String destinationName;
	
	@Value("${nm.jmsrecovery-interval}")
	private Long jmsRecInterval;
	
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Lazy
	public DefaultMessageListenerContainer nmMessageContainer(ConnectionFactory nmConnectionFactory, QpidConsumer messageConsumer, String destName){
		DefaultMessageListenerContainer listenerContainer = new DefaultMessageListenerContainer();
		listenerContainer.setConcurrency("5-20");
		listenerContainer.setRecoveryInterval(jmsRecInterval);
		listenerContainer.setConnectionFactory(nmConnectionFactory);
		listenerContainer.setMessageListener(messageConsumer);
		listenerContainer.setDestinationName(destName);
		logger.debug("Starting DefaultMessageListenerContainer for Queue {}", destinationName);
		return listenerContainer;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}*/
	
}

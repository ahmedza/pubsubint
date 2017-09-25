package com.gcaa.nm.config;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class GcaaJmsConfigInitContext {

	@Autowired
	InitialContext initialContext;
	
	@Bean
	public JmsTemplate gcaaJmsTemplate() throws NamingException, JMSException {
		JmsTemplate template = null;
		template = new JmsTemplate(gcaaConnectionFactory());
		template.setTimeToLive(21600000);
		return template;
	}

	@Bean(name="gcaConnectionFactory")
	public ConnectionFactory gcaaConnectionFactory() throws JMSException {
		ConnectionFactory gcaaConnectionfactory = null;
		try {
			gcaaConnectionfactory = (ConnectionFactory) initialContext.lookup("gcaaConnectionfactory");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return gcaaConnectionfactory;
	}

	/*
	 * @Bean public QpidConsumer messageConsumer(){ return new QpidConsumer(); }
	 */

}

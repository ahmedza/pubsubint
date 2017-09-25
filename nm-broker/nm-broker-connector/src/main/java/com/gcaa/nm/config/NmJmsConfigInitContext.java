package com.gcaa.nm.config;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.gcaa.nm.consumer.QpidConsumer;

@Configuration
public class NmJmsConfigInitContext {
	
	@Bean
	public InitialContext initialContext() throws NamingException{
		InitialContext context = null;
		try {
			context = new InitialContext(); 
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return context;
	}
	
	@Bean
	@Primary
	public ConnectionFactory nmConnectionFactory(InitialContext initialContext) throws JMSException{
		ConnectionFactory nmConnectionfactory = null;
		try {
			nmConnectionfactory = (ConnectionFactory) initialContext.lookup("nmConnectionfactory");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return nmConnectionfactory;
	}

	@Bean
	public QpidConsumer messageConsumer(){
		return new QpidConsumer();
	}

}

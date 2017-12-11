package com.gcaa.nm.config;

import org.springframework.context.annotation.Configuration;

@Configuration
//@Profile("dev") //java -jar myapp.jar --spring.profiles.active=profile1,profile2
public class NmJmsConfigInitContext {
		
	/*@Bean
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
			System.out.println("--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- CREATED NM CONNECITON -*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*");
		} catch (NamingException e) {
			System.out.println("--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- NNNOTTTT CREATED NM CONNECITON -*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*");
			e.printStackTrace();
		}
		return nmConnectionfactory;
	}

	@Bean
	public QpidConsumer messageConsumer(){
		return new QpidConsumer();
	}
*/
}

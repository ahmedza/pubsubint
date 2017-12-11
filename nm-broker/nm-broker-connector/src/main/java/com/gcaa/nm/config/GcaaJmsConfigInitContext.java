package com.gcaa.nm.config;

import java.util.Hashtable;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.core.JmsTemplate;

import com.gcaa.nm.consumer.QpidConsumer;

@Configuration
public class GcaaJmsConfigInitContext {

	/*@Autowired
	InitialContext initialContext;*/
	
	@Bean
	public InitialContext initialContext() throws NamingException{
		//InitialContext context = null;
		javax.naming.Context context = null;
		try {
			Hashtable<Object, Object> env = new Hashtable<Object, Object>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.qpid.jms.jndi.JmsInitialContextFactory");
			env.put("java.naming.factory.initial", "org.apache.qpid.jms.jndi.JmsInitialContextFactory");
			env.put("connectionfactory.nmConnectionfactory", "amqps://publish.preops.nm.eurocontrol.int:5671?transport.keyStoreLocation=D:/work/projects/workspce/nm/cefi/cefitest/CC0000009204_500.jks&transport.keyStorePassword=Qq3zlmvD0MTT&transport.trustStoreLocation=D:/work/projects/workspce/nm/cefi/cefitest/CC0000009204_500.jks&transport.trustStorePassword=Qq3zlmvD0MTT&transport.trustAll=false&transport.verifyHost=true&transport.keyAlias=1");
//			env.put("connectionfactory.nmConnectionfactory", "amqps://193.58.21.68:5671?transport.keyStoreLocation=D:/work/projects/workspce/nm/cefi/cefitest/CC0000009204_500.jks&transport.keyStorePassword=Qq3zlmvD0MTT&transport.trustStoreLocation=D:/work/projects/workspce/nm/cefi/cefitest/CC0000009204_500.jks&transport.trustStorePassword=Qq3zlmvD0MTT&transport.trustAll=false&transport.verifyHost=true&transport.keyAlias=1");
			env.put("connectionfactory.gcaaConnectionfactory","amqp://localhost:5672?jms.username=admin&jms.password=admin");
			context = new javax.naming.InitialContext(env);
			
			//context = new InitialContext(); 

		} catch (NamingException e) {
			e.printStackTrace();
		}
		return (InitialContext)context;
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

	
	@Bean
	public JmsTemplate gcaaJmsTemplate(InitialContext initialContext) throws NamingException, JMSException {
		JmsTemplate template = null;
		if(null != initialContext){
			System.out.println("+*+*+*+*+*+**+*+*+*+*+*+*+*+*+*+*+Initiial COntext != null +*+*+*+*+*+**+*+*+*+*+*+*+*+*+*+*+" );
		}
		
		template = new JmsTemplate(gcaaConnectionFactory(initialContext));
		template.setTimeToLive(21600000);
		return template;
	}

	@Bean(name="gcaConnectionFactory")
	public ConnectionFactory gcaaConnectionFactory(InitialContext initialContext) throws JMSException {
		ConnectionFactory gcaaConnectionfactory = null;
		try {
			gcaaConnectionfactory = (ConnectionFactory) initialContext.lookup("gcaaConnectionfactory");
			System.out.println("--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- CREATED GCAA CONNECITON -*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*");
		} catch (NamingException e) {
			System.out.println("--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*- NOTTTT    CREATED GCAA CONNECITON -*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*");
			e.printStackTrace();
		}
		return gcaaConnectionfactory;
	}

	/*
	 * @Bean public QpidConsumer messageConsumer(){ return new QpidConsumer(); }
	 */

}

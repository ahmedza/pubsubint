package com.gcaa.nm.config;

import java.util.Hashtable;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class GcaaJmsConfigInitContext {

	private static final Logger logger = LoggerFactory.getLogger(GcaaJmsConfigInitContext.class);
	
	@Value("${gcaa.security.ssl.key-store}")
	public String keyStorePath;
	@Value("${gcaa.security.ssl.key-store-password}")
	public String keyStorePswd;
	@Value("${gcaa.security.ssl.trust-store}")
	public String trustStorePath;
	@Value("${gcaa.security.ssl.trust-store-password}")
	public String trustStorePswd;
	@Value("${gcaa.nm.broker-url}")
	public String nmBrokerEndPointUrl;
	
	@Value("${gcaa.broker.url}")
	public String gcaaBrokerUrl;
	@Value("${gcaa.broker.user}")
	public String gcaaBrokerUser;
	@Value("${gcaa.broker.password}")
	public String gcaaBrokerPswd;
	
	@Bean
	public InitialContext initialContext() throws NamingException{
		javax.naming.Context context = null;
		try {
			Hashtable<Object, Object> env = new Hashtable<Object, Object>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.qpid.jms.jndi.JmsInitialContextFactory");
			env.put("java.naming.factory.initial", "org.apache.qpid.jms.jndi.JmsInitialContextFactory");
			env.put("connectionfactory.nmConnectionfactory", nmBrokerEndPointUrl+"?transport.keyStoreLocation="+keyStorePath+"&transport.keyStorePassword="+keyStorePswd+"&transport.trustStoreLocation="+trustStorePath+"&transport.trustStorePassword="+trustStorePswd+"&transport.trustAll=false&transport.verifyHost=true&transport.keyAlias=1");
			env.put("connectionfactory.gcaaConnectionfactory",gcaaBrokerUrl+"?jms.username="+gcaaBrokerUser+"&jms.password="+gcaaBrokerPswd);
			context = new javax.naming.InitialContext(env);
		} catch (NamingException e) {
			logger.error(e.getMessage());
		}
		return (InitialContext)context;
	}
	
	@Bean
	public JmsTemplate gcaaJmsTemplate(InitialContext initialContext) throws NamingException, JMSException {
		JmsTemplate template = null;
		template = new JmsTemplate(gcaaConnectionFactory(initialContext));
		template.setTimeToLive(21600000);
		return template;
	}

	@Bean(name="gcaConnectionFactory")
	public ConnectionFactory gcaaConnectionFactory(InitialContext initialContext) throws JMSException {
		ConnectionFactory gcaaConnectionfactory = null;
		try {
			gcaaConnectionfactory = (ConnectionFactory) initialContext.lookup("gcaaConnectionfactory");
			logger.info("GCAA Connection Factory Created");
		} catch (NamingException e) {
			logger.error("GCAA Connection Factory Not created \n"+e.getMessage());
		}
		return gcaaConnectionfactory;
	}
}

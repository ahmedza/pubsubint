package com.gcaa.nm.connector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gcaa.nm.config.adhoc.QueueListenerConfig;
import com.gcaa.nm.entity.SubscriptionEntity;
import com.gcaa.nm.repository.SubscriptionRepository;
import com.gcaa.nm.types.SubscriptionStatus;

@Component
public class NmListenerInitializer implements /* CommandLineRunner, */ApplicationContextAware {
	@Autowired
	private SubscriptionRepository subRepo;

	private ApplicationContext applicationContext;

	Map<String, ConfigurableApplicationContext> listenerHandles = new HashMap<String, ConfigurableApplicationContext>();
	
	@Scheduled(fixedDelayString = "${gcaa.nm.listener.fixedDelay}", initialDelayString = "${gcaa.nm.listener.initDelay}")
	public void initListeners() throws Exception {
		Iterable<SubscriptionEntity> subs = subRepo.findAll();
		for (SubscriptionEntity sub : subs) {
			processSubscriptionInit(sub);
		}
	}

	private void processSubscriptionInit(SubscriptionEntity sub) {
		if (!sub.getIsActive() || sub.getSubscriptionStatus() == SubscriptionStatus.PAUSED
				|| sub.getSubscriptionStatus() == SubscriptionStatus.DELETED) {
			stopListenerIfRunning(sub);
		} else if (sub.getSubscriptionStatus() == SubscriptionStatus.ACTIVE) {
			prepareListenerForSubscription(sub);
		}

	}

	private void stopListenerIfRunning(SubscriptionEntity sub) {
		ConfigurableApplicationContext ctx = listenerHandles.get(sub.getQueueName());
		if (null != ctx) {
			ctx.close();
			listenerHandles.remove(ctx);
		}
	}

	private void prepareListenerForSubscription(SubscriptionEntity sub) {
		if (null == sub) {
			return;
		}
		/*
		 * if (applicationContext.containsBean("nmMessageContainer")) {
		 * DefaultMessageListenerContainer listener =
		 * (DefaultMessageListenerContainer) applicationContext
		 * .getBean("nmMessageContainer");
		 * 
		 * } else {
		 */

		ConfigurableApplicationContext ctx = listenerHandles.get(sub.getQueueName());
		if (null == ctx || !(null != ctx.getBean("nmMessageContainer")
				&& ((DefaultMessageListenerContainer) ctx.getBean("nmMessageContainer")).isActive())) {
			ctx = startMessageListener(sub.getQueueName());
			listenerHandles.put(sub.getQueueName(), ctx);
		}
		/* } */
	}

	private AnnotationConfigApplicationContext startMessageListener(String destinationName) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		StandardEnvironment env = new StandardEnvironment();
		Properties props = new Properties();
		props.setProperty("nm.destination", destinationName); /*
																 * "EH009204.c5009204.FLIGHT_PLANS.20.5.0.32dc2c62-0b98-419b-81d7-64b1b6b712a8"
																 * );
																 */

		PropertiesPropertySource pps = new PropertiesPropertySource("systemProperties", props);
		env.getPropertySources().addLast(pps);
		env.setActiveProfiles("adhoc");

		context.setDisplayName("NM-Q-CTXT-" + destinationName);
		context.setId("NM-Q-CTXT-" + destinationName);
		;
		context.setEnvironment(env);
		context.register(QueueListenerConfig.class);

		context.setParent(applicationContext);
		context.refresh();
		return context;
	}

	// @Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}
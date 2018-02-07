package com.gcaa.nm.connector;

import java.util.HashMap;
import java.util.Map;

import javax.jms.ConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gcaa.nm.consumer.QpidConsumer;
import com.gcaa.nm.entity.SubscriptionEntity;
import com.gcaa.nm.repository.SubscriptionRepository;
import com.gcaa.nm.types.SubscriptionStatus;

@Component
public class NmListenerInitializer implements ApplicationContextAware {

	private static final Logger logger = LoggerFactory.getLogger(NmListenerInitializer.class);

	@Autowired
	private SubscriptionRepository subRepo;

	@Autowired
	private QpidConsumer messageConsumer;

	private ApplicationContext applicationContext;

	Map<String, DefaultMessageListenerContainer> listenerHandles = new HashMap<String, DefaultMessageListenerContainer>();

	@Scheduled(fixedDelayString = "${gcaa.nm.listener.fixedDelay}", initialDelayString = "${gcaa.nm.listener.initDelay}")
	public void initListeners() throws Exception {
		// Get all the subscriptions available in system. Returns(Active,
		// Paused, Deleted)
		Iterable<SubscriptionEntity> subs = subRepo.findAll();

		for (SubscriptionEntity sub : subs) {
			processSubscriptionInit(sub);
		}
	}

	/**
	 * This method processes each subscription according to its status 1. Active
	 * Queues are started (If not running) 2. Paused Queues are resumed ot in
	 * PSI JVM and in Eurocontrol (A soap request is ent to NMOC to Activate a
	 * paused subscription) 3. Deleted queue is stopped, i.e. connection to the
	 * queue is disconnected.
	 * 
	 * @param sub
	 *            - Subscription entity that conatins complete detail of
	 *            subscription available in PSI DB
	 */
	private void processSubscriptionInit(SubscriptionEntity sub) {

		if (!sub.getIsActive() || sub.getSubscriptionStatus() == SubscriptionStatus.PAUSED
				|| sub.getSubscriptionStatus() == SubscriptionStatus.DELETED
				|| sub.getSubscriptionStatus() == SubscriptionStatus.SUSPENDED_PAUSE
				|| sub.getSubscriptionStatus() == SubscriptionStatus.SUSPENDED_DELETE) {

			stopListenerIfRunning(sub);

		} else if (sub.getSubscriptionStatus() == SubscriptionStatus.ACTIVE) {

			prepareListenerForSubscription(sub);
		}
	}

	private void stopListenerIfRunning(SubscriptionEntity sub) {
		final DefaultMessageListenerContainer dmlc = listenerHandles.get(sub.getQueueName());
		if (null != dmlc) {
			if (!dmlc.isRunning()) {
				return;
			}
			logger.debug("Stoping Listener for Queue {}", sub.getQueueName());
			dmlc.stop(new Runnable() {
				@Override
				public void run() {

					logger.info("Closed Listener Container for Connection {}", sub.getQueueName());

					/*
					 * Incase of deleted Queue, no need to hold the reference to
					 * application context. But in case of a paused queue, keep
					 * the reference as same context will be needed to resume
					 * the connection
					 */
					if (!sub.getIsActive() || sub.getSubscriptionStatus() == SubscriptionStatus.DELETED
							|| sub.getSubscriptionStatus() == SubscriptionStatus.SUSPENDED_DELETE) {
						listenerHandles.remove(sub.getQueueName());
					}
				}
			});
			dmlc.destroy();
			dmlc.shutdown();

		}
	}

	private void prepareListenerForSubscription(SubscriptionEntity sub) {
		if (null == sub) {
			return;
		}

		DefaultMessageListenerContainer dmlc = listenerHandles.get(sub.getQueueName());
		if (null == dmlc) {
			logger.info("Starting Listener for Queue {}", sub.getQueueName());
			dmlc = startMessageListener(sub.getQueueName());
			dmlc.start();
			listenerHandles.put(sub.getQueueName(), dmlc);
		} else {

			if (null != dmlc && !dmlc.isRunning()) {
				logger.debug("Sub={}, IsActive = {}, IsRunning={}", sub.getName(), dmlc.isActive(), dmlc.isRunning());
				logger.info("Reconnecting queue={}", sub.getQueueName());
				dmlc.start();
				dmlc.afterPropertiesSet();
			}
		}
	}

	@Autowired
	private ConnectionFactory nmConnectionFactory;

	private DefaultMessageListenerContainer startMessageListener(String destinationName) {
		DefaultMessageListenerContainer dmlc = null;

		dmlc = (DefaultMessageListenerContainer) applicationContext.getBean("nmMessageContainer", nmConnectionFactory,
				messageConsumer, destinationName);

		return dmlc;
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}
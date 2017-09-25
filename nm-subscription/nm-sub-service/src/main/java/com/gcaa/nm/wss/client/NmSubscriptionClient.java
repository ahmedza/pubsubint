
package com.gcaa.nm.wss.client;

import java.util.List;

import com.gcaa.nm.eurocontrol._2_5_0.Subscription;

public interface NmSubscriptionClient{

	public List<Subscription> getSubscriptionList(List<String> subStates);	
	
	public void deleteSubscription(String subscriptionUuid);
	
	public void createSubscription();

	void resumeSubscription(String subUuid);

	void pauseSubscription(String subUuid);
}
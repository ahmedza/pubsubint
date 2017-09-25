
package com.gcaa.nm.manager;

import java.util.List;

import com.gcaa.nm.eurocontrol._2_5_0.Subscription;

public interface NmSubscriptionManager {
	public void syncNmSubscriptions();
	public void syncLocalSubscriptions(List<Subscription> pausedSubs);
}
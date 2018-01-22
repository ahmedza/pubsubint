package com.gcaa.nm.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NmStatusChecker{

	@Autowired
	NmSubscriptionManagerImpl nmSubManager;
	
	@Scheduled(fixedDelayString ="${gcaa.nm.subchecker.fixedDelay}", initialDelayString="${gcaa.nm.subchecker.initDelay}")
	public void check(){
		
		if(null != nmSubManager){
			nmSubManager.syncNmSubscriptions();
		}
	}

}

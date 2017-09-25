package com.gcaa.nm.types;

import java.util.HashMap;

public enum SubscriptionStatus {

	ACTIVE("ACTIVE"),PAUSED("PAUSED"), DELETED("DELETED"), SUSPENDED_PAUSE("SUSPENDED_PAUSED"), SUSPENDED_DELETE("SUSPENDED_DELETED");
	
	private String code;

	static HashMap<String, SubscriptionStatus> statusMap = new HashMap<String, SubscriptionStatus>();
	
	SubscriptionStatus(String code)
	{
		this.code = code;
	}
	
	static     
	{         
		for (SubscriptionStatus  type : SubscriptionStatus.values()) 
		{             
			statusMap.put(type.code, type);         
		}     
	}      
	
	public static SubscriptionStatus getSubscriptionStatus(String code)     
	{         
		return statusMap.get(code);     
	}
	
	public String getCode()     
	{         
		return code;     
	}
	
}

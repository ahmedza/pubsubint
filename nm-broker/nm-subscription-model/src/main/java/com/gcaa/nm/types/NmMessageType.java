package com.gcaa.nm.types;

import java.util.HashMap;

public enum NmMessageType {

	TECHNICAL("T"),BUSINESS("B"), OTHER("O");
	
	private String code;

	static HashMap<String, NmMessageType> codeValueMap = new HashMap<String, NmMessageType>();
	
	NmMessageType(String code)
	{
		this.code = code;
	}
	
	static     
	{         
		for (NmMessageType  type : NmMessageType.values()) 
		{             
			codeValueMap.put(type.code, type);         
		}     
	}      
	
	public static NmMessageType getNmMessageType(String code)     
	{         
		return codeValueMap.get(code);     
	}
	
	public String getCode()     
	{         
		return code;     
	}
	
}

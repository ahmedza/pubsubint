package com.gcaa.nm.types;

public class NmSubscriptionType {
	private Long Id;
	private String subscriptionTypeName;
	private String destinationName;
	private int msgTimeToLiveMins;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getSubscriptionTypeName() {
		return subscriptionTypeName;
	}
	public void setSubscriptionTypeName(String subscriptionTypeName) {
		this.subscriptionTypeName = subscriptionTypeName;
	}
	public String getDestinationName() {
		return destinationName;
	}
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	public int getMsgTimeToLiveMins() {
		return msgTimeToLiveMins;
	}
	public void setMsgTimeToLiveMins(int msgTimeToLiveMins) {
		this.msgTimeToLiveMins = msgTimeToLiveMins;
	}
}

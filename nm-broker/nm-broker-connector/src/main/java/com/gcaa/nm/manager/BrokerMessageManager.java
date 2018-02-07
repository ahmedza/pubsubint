package com.gcaa.nm.manager;

import com.gcaa.nm.types.NmMessageType;
import com.gcaa.nm.types.NmSubscriptionType;

public interface BrokerMessageManager {
	NmMessageType identifyMessageType(String text);
	boolean processReceivedMessage(String message);
	NmSubscriptionType identifyMessageSubscriptionType(String receivedMessage);
	void initiateMessagePublishing();
}

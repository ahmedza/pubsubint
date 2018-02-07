package com.gcaa.nm.producer;

import java.util.List;

import com.gcaa.nm.entity.NmMessageEntity;

public interface GcaaNmMessageProducer {
	void publishMessages(List<NmMessageEntity> nmMessages);
}

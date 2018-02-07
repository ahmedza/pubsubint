package com.gcaa.nm.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gcaa.nm.entity.NmMessageEntity;
import com.gcaa.nm.types.NmMessageType;

@Repository
public interface NmMessageRepository extends CrudRepository<NmMessageEntity, Long> {
	public List<NmMessageEntity> findByProcessedDateIsNullAndMessageTypeEquals(NmMessageType msgType);
	public int deleteByProcessedDateBefore(Date dte);
	public List<NmMessageEntity> findByProcessedDateNotNullAndProcessedDateBefore(Date time);
	public List<NmMessageEntity> findByMessageTypeAndReceivedDateBefore(NmMessageType technicalMessage, Date time);
}

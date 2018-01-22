package com.gcaa.nm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gcaa.nm.entity.NmMessageEntity;
import com.gcaa.nm.types.NmMessageType;

@Repository
public interface NmMessageRepository extends CrudRepository<NmMessageEntity, Long> {
	public List<NmMessageEntity> findByProcessedDateIsNullAndMessageTypeEquals(NmMessageType msgType);
}

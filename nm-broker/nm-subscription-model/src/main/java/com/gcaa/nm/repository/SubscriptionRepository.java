package com.gcaa.nm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gcaa.nm.entity.SubscriptionEntity;
import com.gcaa.nm.types.SubscriptionStatus;

@Repository
public interface SubscriptionRepository extends CrudRepository<SubscriptionEntity, Long> {
	public SubscriptionEntity findByUuidEquals(String uuid);
	public List<SubscriptionEntity> findBySubscriptionStatusIn(List<SubscriptionStatus> statuses);
}

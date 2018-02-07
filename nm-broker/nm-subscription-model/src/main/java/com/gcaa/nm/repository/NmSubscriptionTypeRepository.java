package com.gcaa.nm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gcaa.nm.entity.NmSubscriptionTypeEntity;

@Repository
public interface NmSubscriptionTypeRepository extends CrudRepository<NmSubscriptionTypeEntity, Long> {
}

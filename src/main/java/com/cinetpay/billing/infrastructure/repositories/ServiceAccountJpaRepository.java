package com.cinetpay.billing.infrastructure.repositories;

import com.cinetpay.billing.infrastructure.entities.ServiceAccountModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceAccountJpaRepository extends JpaRepository<ServiceAccountModel, String> {

    public ServiceAccountModel findWithService(String vendor, String product, String country, String currency, String owner);
    
}

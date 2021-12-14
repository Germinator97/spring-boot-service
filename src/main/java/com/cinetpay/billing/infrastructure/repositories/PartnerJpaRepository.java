package com.cinetpay.billing.infrastructure.repositories;

import com.cinetpay.billing.infrastructure.entities.PartnerModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerJpaRepository extends JpaRepository<PartnerModel, String> {

    public PartnerModel findByName(String name);
    
}

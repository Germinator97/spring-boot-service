package com.cinetpay.billing.infrastructure.repositories;

import com.cinetpay.billing.infrastructure.entities.BillingServiceModel;
import com.cinetpay.billing.infrastructure.entities.CommissionServiceModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommissionServiceJpaRepository extends JpaRepository<CommissionServiceModel, String> {

    public CommissionServiceModel findByInterval(BillingServiceModel billing, Double min, Double max);
    
}

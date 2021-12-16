package com.cinetpay.billing.infrastructure.repositories;

import com.cinetpay.billing.infrastructure.entities.BillingServiceModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingServiceJpaRepository extends JpaRepository<BillingServiceModel, String> {

    public BillingServiceModel findWithService(String vendor, String product, String country, String partner, String currency, String owner);

    public BillingServiceModel findWithOutService(String vendor, String product, String country, String partner, String currency);

}

package com.cinetpay.billing.domain.billings.service.repository;

import com.cinetpay.billing.domain.billings.service.entity.BillingService;

public interface BillingServiceRepository {

    public BillingService findWithService(String vendor, String product, String country, String partner, String currency, String owner);

    public BillingService findWithOutService(String vendor, String product, String country, String partner, String currency);

    public BillingService create(BillingService billingService);
    
}

package com.cinetpay.billing.infrastructure.adapters;

import com.cinetpay.billing.application.mapper.Mapper;
import com.cinetpay.billing.domain.billings.service.entity.BillingService;
import com.cinetpay.billing.domain.billings.service.repository.BillingServiceRepository;
import com.cinetpay.billing.infrastructure.entities.BillingServiceModel;
import com.cinetpay.billing.infrastructure.repositories.BillingServiceJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BillingServiceRepositoryAdapter implements BillingServiceRepository  {

    @Autowired
    private BillingServiceJpaRepository billingServiceJpaRepository;

    @Autowired
	private Mapper mapper;

    @Override
    public BillingService findWithService(String vendor, String product, String country, String partner, String currency, String owner) {
        BillingServiceModel commission = billingServiceJpaRepository.findWithService(vendor, product, country, partner, currency, owner);
        return mapper.mapper(commission, BillingService.class);
    }

    @Override
    public BillingService findWithoutService(String vendor, String product, String country, String partner, String currency) {
        BillingServiceModel commission = billingServiceJpaRepository.findWithoutService(vendor, product, country, partner, currency);
        return mapper.mapper(commission, BillingService.class);
    }

    @Override
    public BillingService create(BillingService data) {
        BillingServiceModel model = mapper.mapper(data, BillingServiceModel.class);
        BillingServiceModel commission = billingServiceJpaRepository.save(model);
        return mapper.mapper(commission, BillingService.class);
    }
    
}

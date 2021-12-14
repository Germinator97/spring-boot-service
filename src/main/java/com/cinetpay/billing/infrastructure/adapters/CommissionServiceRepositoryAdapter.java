package com.cinetpay.billing.infrastructure.adapters;

import com.cinetpay.billing.application.mapper.Mapper;
import com.cinetpay.billing.domain.billings.service.entity.BillingService;
import com.cinetpay.billing.domain.commissions.service.entity.CommissionService;
import com.cinetpay.billing.domain.commissions.service.repository.CommissionServiceRepository;
import com.cinetpay.billing.infrastructure.entities.BillingServiceModel;
import com.cinetpay.billing.infrastructure.entities.CommissionServiceModel;
import com.cinetpay.billing.infrastructure.repositories.CommissionServiceJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommissionServiceRepositoryAdapter implements CommissionServiceRepository {

    @Autowired
    private CommissionServiceJpaRepository commissionServiceJpaRepository;

    @Autowired
	private Mapper mapper;

    @Override
    public CommissionService findByInterval(BillingService billing, Double min, Double max) {
        BillingServiceModel model = mapper.mapper(billing, BillingServiceModel.class);
        CommissionServiceModel commission = commissionServiceJpaRepository.findByInterval(model, min, max);
        return mapper.mapper(commission, CommissionService.class);
    }

    @Override
    public CommissionService create(CommissionService data) {
        CommissionServiceModel model = mapper.mapper(data, CommissionServiceModel.class);
        CommissionServiceModel commission = commissionServiceJpaRepository.save(model);
        return mapper.mapper(commission, CommissionService.class);
    }
    
}
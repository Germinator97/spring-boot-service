package com.cinetpay.billing.domain.commissions.service.repository;

import com.cinetpay.billing.domain.billings.service.entity.BillingService;
import com.cinetpay.billing.domain.commissions.service.entity.CommissionService;

public interface CommissionServiceRepository {

    public CommissionService findByInterval(BillingService billing, Double min, Double max);

    public CommissionService findInInterval(BillingService billing, Double amount);

    public CommissionService findForOne(BillingService billing);

    public CommissionService create(CommissionService countryService);

	// public CommissionService update(CommissionService countryService);
    
}

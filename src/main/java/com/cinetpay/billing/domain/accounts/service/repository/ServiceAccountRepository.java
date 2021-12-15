package com.cinetpay.billing.domain.accounts.service.repository;

import com.cinetpay.billing.domain.accounts.service.entity.ServiceAccount;

public interface ServiceAccountRepository {

    public ServiceAccount findWithService(String vendor, String product, String country, String currency, String owner);

    public ServiceAccount create(ServiceAccount serviceAccount);
    
}

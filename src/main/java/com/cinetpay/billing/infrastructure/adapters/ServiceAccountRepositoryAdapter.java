package com.cinetpay.billing.infrastructure.adapters;

import com.cinetpay.billing.application.mapper.Mapper;
import com.cinetpay.billing.domain.accounts.service.entity.ServiceAccount;
import com.cinetpay.billing.domain.accounts.service.repository.ServiceAccountRepository;
import com.cinetpay.billing.infrastructure.entities.ServiceAccountModel;
import com.cinetpay.billing.infrastructure.repositories.ServiceAccountJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceAccountRepositoryAdapter implements ServiceAccountRepository {

    @Autowired
    private ServiceAccountJpaRepository serviceAccountJpaRepository;

    @Autowired
	private Mapper mapper;

    @Override
    public ServiceAccount findWithService(String vendor, String product, String country, String currency, String owner) {
        ServiceAccountModel account = serviceAccountJpaRepository.findWithService(vendor, product, country, currency, owner);
        return mapper.mapper(account, ServiceAccount.class);
    }

    @Override
    public ServiceAccount create(ServiceAccount data) {
        ServiceAccountModel model = mapper.mapper(data, ServiceAccountModel.class);
        ServiceAccountModel account = serviceAccountJpaRepository.save(model);
        return mapper.mapper(account, ServiceAccount.class);
    }
    
}

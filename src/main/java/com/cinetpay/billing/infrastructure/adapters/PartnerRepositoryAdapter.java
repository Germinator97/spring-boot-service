package com.cinetpay.billing.infrastructure.adapters;

import com.cinetpay.billing.application.mapper.Mapper;
import com.cinetpay.billing.domain.partner.entity.Partner;
import com.cinetpay.billing.domain.partner.repository.PartnerRepository;
import com.cinetpay.billing.infrastructure.entities.PartnerModel;
import com.cinetpay.billing.infrastructure.repositories.PartnerJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PartnerRepositoryAdapter implements PartnerRepository {

    @Autowired
    private PartnerJpaRepository partnerJpaRepository;

    @Autowired
	private Mapper mapper;

    @Override
    public Partner findByName(String name) {
        PartnerModel country = partnerJpaRepository.findByName(name);
        return mapper.mapper(country, Partner.class);
    }

    @Override
    public Partner create(Partner data) {
        PartnerModel model = mapper.mapper(data, PartnerModel.class);
        PartnerModel product = partnerJpaRepository.save(model);
        return mapper.mapper(product, Partner.class);
    }
    
}

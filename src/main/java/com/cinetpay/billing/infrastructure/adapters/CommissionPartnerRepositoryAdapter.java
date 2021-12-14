package com.cinetpay.billing.infrastructure.adapters;

import com.cinetpay.billing.application.mapper.Mapper;
import com.cinetpay.billing.domain.commissions.partner.entity.CommissionPartner;
import com.cinetpay.billing.domain.commissions.partner.repository.CommissionPartnerRepository;
import com.cinetpay.billing.domain.country.entity.Country;
import com.cinetpay.billing.infrastructure.entities.CommissionPartnerModel;
import com.cinetpay.billing.infrastructure.entities.CountryModel;
import com.cinetpay.billing.infrastructure.repositories.CommissionPartnerJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CommissionPartnerRepositoryAdapter implements CommissionPartnerRepository {

    @Autowired
    private CommissionPartnerJpaRepository commissionPartnerJpaRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public CommissionPartner findById(String id) {
        Optional<CommissionPartnerModel> commissionPartnerModel = commissionPartnerJpaRepository.findById(id);
        return mapper.mapper(commissionPartnerModel, CommissionPartner.class);
    }

    @Override
    public CommissionPartner findByVendorAndProductAndCurrencyAndPartnerAndCountry(String vendor, String product, String currency, String partner, String country) {
        CommissionPartnerModel commissionPartnerModel = commissionPartnerJpaRepository.
                findByVendorAndProductAndCurrencyAndPartnerAndCountry(vendor,product, currency, partner, country);
        return mapper.mapper(commissionPartnerModel, CommissionPartner.class);
    }

    @Override
    public CommissionPartner create(CommissionPartner commissionPartner) {
        CommissionPartnerModel model = mapper.mapper(commissionPartner, CommissionPartnerModel.class);
        CommissionPartnerModel commissionPartnerModel = commissionPartnerJpaRepository.save(model);
        return mapper.mapper(commissionPartnerModel, CommissionPartner.class);
    }

    @Override
    public CommissionPartner update(CommissionPartner commissionPartner) {
        CommissionPartnerModel model = mapper.mapper(commissionPartner, CommissionPartnerModel.class);
        CommissionPartnerModel commissionPartnerModel = commissionPartnerJpaRepository.save(model);
        return mapper.mapper(commissionPartnerModel, CommissionPartner.class);
    }
}
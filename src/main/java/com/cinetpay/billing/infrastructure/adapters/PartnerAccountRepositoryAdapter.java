package com.cinetpay.billing.infrastructure.adapters;

import com.cinetpay.billing.application.mapper.Mapper;
import com.cinetpay.billing.domain.accounts.partner.entity.PartnerAccount;
import com.cinetpay.billing.domain.accounts.partner.repository.PartnerAccountRepository;
import com.cinetpay.billing.domain.partner.entity.Partner;
import com.cinetpay.billing.infrastructure.entities.PartnerAccountModel;
import com.cinetpay.billing.infrastructure.entities.PartnerModel;
import com.cinetpay.billing.infrastructure.repositories.PartnerAccountJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PartnerAccountRepositoryAdapter implements PartnerAccountRepository {

    @Autowired
    private PartnerAccountJpaRepository partnerAccountJpaRepository;


    @Autowired
    private Mapper mapper;

    @Override
    public PartnerAccount findByAccount(String code) {
        PartnerAccountModel partnerAccountModel = partnerAccountJpaRepository.findByAccount(code);
        return mapper.mapper(partnerAccountModel, PartnerAccount.class);
    }

    @Override
    public PartnerAccount create(PartnerAccount partnerAccount) {
        PartnerAccountModel model = mapper.mapper(partnerAccount, PartnerAccountModel.class);
        PartnerAccountModel partnerAccountModel = partnerAccountJpaRepository.save(model);
        return mapper.mapper(partnerAccountModel, PartnerAccount.class);
    }

    @Override
    public PartnerAccount update(PartnerAccount vendorAccount) {
        return null;
    }

    @Override
    public PartnerAccount debitAccount(PartnerAccount vendorAccount) {
        return null;
    }

    @Override
    public PartnerAccount creditAccount(PartnerAccount vendorAccount) {
        return null;
    }
}

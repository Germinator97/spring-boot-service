package com.cinetpay.billing.infrastructure.adapters;

import com.cinetpay.billing.application.mapper.Mapper;
import com.cinetpay.billing.domain.accounts.vendor.entity.VendorAccount;
import com.cinetpay.billing.domain.accounts.vendor.repository.VendorAccountRepository;
import com.cinetpay.billing.infrastructure.entities.VendorAccountModel;
import com.cinetpay.billing.infrastructure.repositories.VendorAccountJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VendorAccountRepositoryAdapter implements VendorAccountRepository {

    @Autowired
    private VendorAccountJpaRepository vendorAccountJpaRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public VendorAccount findByAccount(String code) {
        VendorAccountModel vendorAccountModel = vendorAccountJpaRepository.findByAccount(code);
        return mapper.mapper(vendorAccountModel, VendorAccount.class);
    }

    @Override
    public VendorAccount create(VendorAccount vendorAccount) {
        return null;
    }

    @Override
    public VendorAccount update(VendorAccount vendorAccount) {
        return null;
    }

    @Override
    public VendorAccount debitAccount(VendorAccount vendorAccount) {
        return null;
    }

    @Override
    public VendorAccount creditAccount(VendorAccount vendorAccount) {
        return null;
    }
}

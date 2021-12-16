package com.cinetpay.billing.domain.commissions.partner.repository;

import com.cinetpay.billing.domain.commissions.partner.entity.CommissionPartner;


public interface CommissionPartnerRepository {

    public CommissionPartner findById(String id);

    public CommissionPartner findByVendorAndProductAndCurrencyAndPartnerAndCountry(String vendor, String product, String currency, String partner, String country);


    public CommissionPartner create(CommissionPartner commissionPartner);


    public CommissionPartner update(CommissionPartner commissionPartner);
}

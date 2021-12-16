package com.cinetpay.billing.domain.accounts.partner.repository;

import com.cinetpay.billing.domain.accounts.partner.entity.PartnerAccount;
import com.cinetpay.billing.domain.accounts.vendor.entity.VendorAccount;

public interface PartnerAccountRepository {

    public PartnerAccount findByAccount(String code);

    public PartnerAccount create(PartnerAccount partnerAccount);

    public PartnerAccount update(PartnerAccount partnerAccount);

    public PartnerAccount debitAccount(PartnerAccount partnerAccount);

    public PartnerAccount creditAccount(PartnerAccount partnerAccount);
}

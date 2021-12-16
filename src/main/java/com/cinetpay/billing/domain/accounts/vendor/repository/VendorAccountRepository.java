package com.cinetpay.billing.domain.accounts.vendor.repository;

import com.cinetpay.billing.domain.accounts.vendor.entity.VendorAccount;

public interface VendorAccountRepository {

    public VendorAccount findByAccount(String code);

    public VendorAccount create(VendorAccount vendorAccount);

    public VendorAccount update(VendorAccount vendorAccount);

    public VendorAccount debitAccount(VendorAccount vendorAccount);

    public VendorAccount creditAccount(VendorAccount vendorAccount);
}

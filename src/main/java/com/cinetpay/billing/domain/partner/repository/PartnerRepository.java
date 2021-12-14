package com.cinetpay.billing.domain.partner.repository;

import com.cinetpay.billing.domain.partner.entity.Partner;

public interface PartnerRepository {

    public Partner findByName(String name);

	public Partner create(Partner partner);

	public Partner update(Partner partner);
    
}

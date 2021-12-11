package com.cinetpay.billing.domain.country.repository;

import com.cinetpay.billing.domain.country.entity.Country;

public interface CountryRepository {
    public Country findByCode(String code);

	public Country findByName(String name);

	public Country create(Country country, String sequence);

	public Country update(Country data);
}

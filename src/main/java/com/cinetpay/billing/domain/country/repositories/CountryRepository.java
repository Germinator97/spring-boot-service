package com.cinetpay.billing.domain.country.repositories;

import com.cinetpay.billing.domain.country.entities.Country;

public interface CountryRepository {
    public Country findByCode(String code);

	public Country findByName(String name);

	public Country create(Country country);

	public Country update(Country data);
}

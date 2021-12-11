package com.cinetpay.billing.use_cases.country;

import com.cinetpay.billing.entities.Country;

public class UpdateCountry {
    private CountryAdapter adapter;
	
	public UpdateCountry(CountryAdapter adapter) {
		this.adapter = adapter;
	}
	
	public Country update(Country country) {
		return adapter.update(country);
	}
}

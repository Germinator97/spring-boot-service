package com.cinetpay.billing.use_cases.country;

import com.cinetpay.billing.entities.Country;

public class FindLastCountry {
    private CountryAdapter adapter;
	
	public FindLastCountry(CountryAdapter adapter) {
		this.adapter = adapter;
	}
	
	public Country find() {
		return adapter.findLast();
	}
}

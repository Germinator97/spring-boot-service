package com.cinetpay.billing.use_cases.country;

import com.cinetpay.billing.application.dto.CountryDto;
import com.cinetpay.billing.domain.country.entity.Country;

public class CreateCountry {

    private CountryAdapter adapter;
	
	public CreateCountry(CountryAdapter adapter) {
		this.adapter = adapter;
	}
	
	public Country create(CountryDto country, String sequence) {
		return adapter.create(country, sequence);
	}

}

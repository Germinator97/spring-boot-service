/**
 * 
 */
package com.cinetpay.billing.use_cases.country;

import com.cinetpay.billing.domain.country.entity.Country;

/**
 * @author mac
 *
 */
public class FindCountryByName {
	
	private CountryAdapter adapter;
	
	public FindCountryByName(CountryAdapter adapter) {
		this.adapter = adapter;
	}
	
	public Country find(String name) {
		return adapter.findByName(name);
	}


}

/**
 * 
 */
package com.cinetpay.billing.use_cases.country;

import com.cinetpay.billing.entities.Country;

/**
 * @author mac
 *
 */
public class FindCountryByCode {
	
	private CountryAdapter adapter;
	
	public FindCountryByCode(CountryAdapter adapter) {
		this.adapter = adapter;
	}
	
	public Country find(String code) {
		return adapter.findByCode(code);
	}

}

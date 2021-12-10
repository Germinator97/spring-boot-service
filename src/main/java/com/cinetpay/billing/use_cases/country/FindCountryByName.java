/**
 * 
 */
package com.cinetpay.billing.use_cases.country;

import com.cinetpay.billing.entities.Country;

/**
 * @author mac
 *
 */
public class FindCountryByName {
	
	private CountryAdapter adapter;
	
	public FindCountryByName(CountryAdapter adapter) {
		this.adapter = adapter;
	}
	
	public Country find(String code) {
		return adapter.findByName(code);
	}


}

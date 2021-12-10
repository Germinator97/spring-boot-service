/**
 * 
 */
package com.cinetpay.billing.use_cases.country;

import com.cinetpay.billing.entities.Country;
import com.cinetpay.billing.infrastructure.country.dto.CountryDto;

/**
 * @author mac
 *
 */
public interface CountryAdapter {

	Country findByCode(String code);

	Country findByName(String name);

	// Country create(CountryDto country);

}

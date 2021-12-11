/**
 * 
 */
package com.cinetpay.billing.use_cases.country;

import com.cinetpay.billing.application.dto.CountryDto;
import com.cinetpay.billing.domain.country.entity.Country;

/**
 * @author mac
 *
 */
public interface CountryAdapter {

	Country findByCode(String code);

	Country findByName(String name);

	Country create(CountryDto country, String sequence);

	Country update(Country country);

}

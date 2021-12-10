/**
 * 
 */
package com.cinetpay.billing.use_cases.country;

import com.cinetpay.billing.entities.Country;

/**
 * @author mac
 *
 */
public interface CountryAdapter {

	Country findByCode(String code);

	Country findByName(String name);

}

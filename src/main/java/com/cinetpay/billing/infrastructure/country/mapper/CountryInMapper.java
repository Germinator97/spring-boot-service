/**
 * 
 */
package com.cinetpay.billing.infrastructure.country.mapper;

import com.cinetpay.billing.database.country.model.CountryModel;
import com.cinetpay.billing.entities.Country;

/**
 * @author mac
 *
 */
public class CountryInMapper {

    public static Country toEntity(CountryModel model) {
		if (model == null) {
			return null;
		}
 
		Country country = new Country();
		country.setId(model.getId());
		country.setCode(model.getCode());
		country.setName(model.getName());
		country.setIs_active(model.getIs_active());
		country.setCreated_at(model.getCreated_at());
		country.setUpdated_at(model.getUpdated_at());
 
		return country;
    }

}

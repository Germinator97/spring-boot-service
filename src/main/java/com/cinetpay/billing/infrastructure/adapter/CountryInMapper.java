/**
 * 
 */
package com.cinetpay.billing.infrastructure.adapter;

import com.cinetpay.billing.domain.country.entity.Country;
import com.cinetpay.billing.infrastructure.entities.CountryModel;

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
		country.setIsActive(model.getIsActive());
		country.setCreatedAt(model.getCreatedAt());
		country.setUpdatedAt(model.getUpdatedAt());
 
		return country;
    }

	public static CountryModel toModel(Country country) {
		if (country == null) {
			return null;
		}
 
		CountryModel model = new CountryModel();
		model.setId(country.getId());
		model.setCode(country.getCode());
		model.setName(country.getName());
		model.setIsActive(country.getIsActive());
		model.setCreatedAt(country.getCreatedAt());
		model.setUpdatedAt(country.getUpdatedAt());
 
		return model;
    }

}

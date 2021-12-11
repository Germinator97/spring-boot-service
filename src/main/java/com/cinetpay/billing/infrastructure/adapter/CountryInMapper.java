/**
 * 
 */
package com.cinetpay.billing.infrastructure.adapter;

import com.cinetpay.billing.application.dto.CountryDto;
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
		country.setIs_active(model.getIs_active());
		country.setCreated_at(model.getCreated_at());
		country.setUpdated_at(model.getUpdated_at());
 
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
		model.setIs_active(country.getIs_active());
		model.setCreated_at(country.getCreated_at());
		model.setUpdated_at(country.getUpdated_at());
 
		return model;
    }

	public static CountryModel toCreateModel(CountryDto dto, String sequence) {
		if (dto == null) {
			return null;
		}
 
		CountryModel model = new CountryModel();
		model.setName(dto.getName());
		model.generateId();
		model.passCode(sequence);
 
		return model;
    }

	public static CountryModel toUpdateModel(Country country) {
		if (country == null) {
			return null;
		}
 
		CountryModel model = new CountryModel();
		model.setId(country.getId());
		model.setCode(country.getCode());
		model.setName(country.getName());
		model.setIs_active(country.getIs_active());
 
		return model;
    }

}

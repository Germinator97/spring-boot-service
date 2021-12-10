/**
 * 
 */
package com.cinetpay.billing.infrastructure.country.mapper;

import com.cinetpay.billing.database.country.model.CountryModel;
import com.cinetpay.billing.entities.Country;
import com.cinetpay.billing.infrastructure.country.dto.CountryDto;

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

	public static CountryModel toModel(CountryDto dto, String sequence) {
		if (dto == null) {
			return null;
		}
 
		CountryModel model = new CountryModel();
		model.setName(dto.getName());
		model.generateId();
		model.passCode(sequence);
 
		return model;
    }

}

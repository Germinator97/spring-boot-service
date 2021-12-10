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

	// public static CountryModel toModel(CountryDto dto) {
	// 	if (dto == null) {
	// 		return null;
	// 	}
 
	// 	CountryModel model = new CountryModel();
	// 	model.setCode(dto.getCode());
	// 	model.setName(dto.getName());
	// 	model.setIs_active(dto.getIs_active());
 
	// 	return model;
    // }

}

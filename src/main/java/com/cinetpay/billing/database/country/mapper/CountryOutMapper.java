/**
 * 
 */
package com.cinetpay.billing.database.country.mapper;

import com.cinetpay.billing.entities.Country;
import com.cinetpay.billing.infrastructure.country.dto.CountryDto;

/**
 * @author mac
 *
 */
public class CountryOutMapper {

	public static CountryDto toDto(Country entity) {
		if (entity == null) {
			return null;
		}
 
		CountryDto countryDto = new CountryDto();
		countryDto.setCode(entity.getCode());
		countryDto.setName(entity.getName());
		countryDto.setIs_active(entity.getIs_active());
 
		return countryDto;
    }

}

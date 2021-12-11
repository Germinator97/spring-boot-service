/**
 * 
 */
package com.cinetpay.billing.application.adapter;

import com.cinetpay.billing.application.dto.CountryDto;
import com.cinetpay.billing.domain.country.entity.Country;

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
		countryDto.setId(entity.getId());
		countryDto.setCode(entity.getCode());
		countryDto.setName(entity.getName());
		countryDto.setIs_active(entity.getIs_active());
		countryDto.setCreated_at(entity.getCreated_at());
		countryDto.setUpdated_at(entity.getUpdated_at());
 
		return countryDto;
    }

	public static Country toDto(CountryDto countryDto) {
		if (countryDto == null) {
			return null;
		}
 
		Country country = new Country();
		country.setId(countryDto.getId());
		country.setCode(countryDto.getCode());
		country.setName(countryDto.getName());
		country.setIs_active(countryDto.getIs_active());
		country.setCreated_at(countryDto.getCreated_at());
		country.setUpdated_at(countryDto.getUpdated_at());
 
		return country;
    }

}

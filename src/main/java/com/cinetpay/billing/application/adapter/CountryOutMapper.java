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
		countryDto.setIsActive(entity.getIsActive());
		countryDto.setCreatedAt(entity.getCreatedAt());
		countryDto.setUpdatedAt(entity.getUpdatedAt());
 
		return countryDto;
    }

	public static Country toEntity(CountryDto countryDto) {
		if (countryDto == null) {
			return null;
		}
 
		Country country = new Country();
		country.setId(countryDto.getId());
		country.setCode(countryDto.getCode());
		country.setName(countryDto.getName());
		country.setIsActive(countryDto.getIsActive());
		country.setCreatedAt(countryDto.getCreatedAt());
		country.setUpdatedAt(countryDto.getUpdatedAt());
 
		return country;
    }

}

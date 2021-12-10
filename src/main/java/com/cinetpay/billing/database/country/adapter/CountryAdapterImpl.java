/**
 * 
 */
package com.cinetpay.billing.database.country.adapter;

import com.cinetpay.billing.database.country.model.CountryModel;
import com.cinetpay.billing.database.country.repository.CountryRepository;
import com.cinetpay.billing.entities.Country;
import com.cinetpay.billing.infrastructure.country.dto.CountryDto;
import com.cinetpay.billing.infrastructure.country.mapper.CountryInMapper;
import com.cinetpay.billing.use_cases.country.CountryAdapter;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author mac
 *
 */
public class CountryAdapterImpl implements CountryAdapter {

	@Autowired
	private CountryRepository countryRepository;

	public Country findByCode(String code){
		CountryModel country = countryRepository.findByCode(code);

		return CountryInMapper.toEntity(country);
	}

	public Country findByName(String name){
		CountryModel country = countryRepository.findByName(name);

		return CountryInMapper.toEntity(country);
	}

	// public Country create(CountryDto dto) {
	// 	CountryModel model = CountryInMapper.toModel(dto);

	// 	CountryModel country = countryRepository.save(model);

	// 	return CountryInMapper.toEntity(country);
	// }

}

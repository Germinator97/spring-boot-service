package com.cinetpay.billing.infrastructure.adapters;

import com.cinetpay.billing.application.mapper.Mapper;
import com.cinetpay.billing.domain.country.entities.Country;
import com.cinetpay.billing.domain.country.repositories.CountryRepository;
import com.cinetpay.billing.infrastructure.entities.CountryModel;
import com.cinetpay.billing.infrastructure.repositories.CountryJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryRepositoryAdapter implements CountryRepository{

    @Autowired
    private CountryJpaRepository countryJpaRepository;

    @Autowired
	private Mapper mapper;

    @Override
    public Country findByCode(String code) {
        CountryModel country = countryJpaRepository.findByCode(code);
        return mapper.mapper(country, Country.class);
    }

    @Override
    public Country findByName(String name) {
        CountryModel country = countryJpaRepository.findByName(name);
        return mapper.mapper(country, Country.class);
    }

    @Override
    public Country create(Country data) {
        CountryModel model = mapper.mapper(data, CountryModel.class);
        CountryModel country = countryJpaRepository.save(model);
        return mapper.mapper(country, Country.class);
    }

    @Override
    public Country update(Country data) {
        CountryModel model = mapper.mapper(data, CountryModel.class);
        CountryModel country = countryJpaRepository.save(model);
        return mapper.mapper(country, Country.class);
    }
    
}
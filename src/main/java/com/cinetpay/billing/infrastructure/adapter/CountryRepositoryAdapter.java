package com.cinetpay.billing.infrastructure.adapter;

import com.cinetpay.billing.domain.country.entity.Country;
import com.cinetpay.billing.domain.country.repository.CountryRepository;
import com.cinetpay.billing.infrastructure.entities.CountryModel;
import com.cinetpay.billing.infrastructure.repository.CountryJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryRepositoryAdapter implements CountryRepository{

    @Autowired
    private CountryJpaRepository countryJpaRepository;

    @Override
    public Country findByCode(String code) {
        CountryModel country = countryJpaRepository.findByCode(code);
        return CountryInMapper.toEntity(country);
    }

    @Override
    public Country findByName(String name) {
        CountryModel country = countryJpaRepository.findByCode(name);
        return CountryInMapper.toEntity(country);
    }

    @Override
    public Country create(Country data) {
        CountryModel model = CountryInMapper.toModel(data);
        CountryModel country = countryJpaRepository.save(model);
        return CountryInMapper.toEntity(country);
    }

    @Override
    public Country update(Country data) {
        CountryModel model = CountryInMapper.toModel(data);
        CountryModel country = countryJpaRepository.save(model);
        return CountryInMapper.toEntity(country);
    }
    
}

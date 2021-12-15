package com.cinetpay.billing.infrastructure.adapters;

import com.cinetpay.billing.application.mapper.Mapper;
import com.cinetpay.billing.domain.currency.entity.Currency;
import com.cinetpay.billing.domain.currency.repository.CurrencyRepository;
import com.cinetpay.billing.infrastructure.entities.CurrencyModel;
import com.cinetpay.billing.infrastructure.repositories.CurrencyJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurrencyRepositoryAdapter implements CurrencyRepository {

    @Autowired
    private CurrencyJpaRepository currencyJpaRepository;

    @Autowired
	private Mapper mapper;

    @Override
    public Currency findByName(String name) {
        CurrencyModel currency = currencyJpaRepository.findByName(name);
        return mapper.mapper(currency, Currency.class);
    }

    @Override
    public Currency create(Currency data) {
        CurrencyModel model = mapper.mapper(data, CurrencyModel.class);
        CurrencyModel currency = currencyJpaRepository.save(model);
        return mapper.mapper(currency, Currency.class);
    }
    
}

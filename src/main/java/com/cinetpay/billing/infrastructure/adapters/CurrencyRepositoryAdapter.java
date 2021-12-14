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
    private CurrencyJpaRepository currencyRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public Currency findByCode(String code) {
        CurrencyModel currency = currencyRepository.findByCode(code);
        return mapper.mapper(currency, Currency.class);
    }

    @Override
    public Currency findByName(String name) {
        CurrencyModel currency = currencyRepository.findByName(name);
        return mapper.mapper(currency, Currency.class);
    }

    @Override
    public Currency create(Currency data) {
        CurrencyModel model = mapper.mapper(data, CurrencyModel.class);
        CurrencyModel currencyModel = currencyRepository.save(model);
        return mapper.mapper(currencyModel, Currency.class);
    }

    @Override
    public Currency update(Currency data) {
        CurrencyModel model = mapper.mapper(data, CurrencyModel.class);
        CurrencyModel currencyModel = currencyRepository.save(model);
        return mapper.mapper(currencyModel, Currency.class);
    }
}

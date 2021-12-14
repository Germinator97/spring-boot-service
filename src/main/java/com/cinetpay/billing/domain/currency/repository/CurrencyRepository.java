package com.cinetpay.billing.domain.currency.repository;
import com.cinetpay.billing.domain.currency.entity.Currency;

public interface CurrencyRepository {
    public Currency findByCode(String code);

    public Currency findByName(String name);

    public Currency create(Currency currency);

    public Currency update(Currency data);
}

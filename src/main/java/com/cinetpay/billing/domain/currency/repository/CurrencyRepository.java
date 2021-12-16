package com.cinetpay.billing.domain.currency.repository;
import com.cinetpay.billing.domain.currency.entity.Currency;

public interface CurrencyRepository {

    public Currency findByName(String name);

	public Currency create(Currency currency);
}

package com.cinetpay.billing.application.utils;

import com.cinetpay.billing.domain.country.entity.Country;
import com.cinetpay.billing.domain.country.repository.CountryRepository;
import com.cinetpay.billing.domain.currency.entity.Currency;
import com.cinetpay.billing.domain.currency.repository.CurrencyRepository;
import com.cinetpay.billing.domain.product.entity.Product;
import com.cinetpay.billing.domain.product.repository.ProductRepository;
import com.cinetpay.billing.domain.sequence.entity.Sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NextSequence {

    @Autowired
	private CountryRepository countryRepository;

    @Autowired
	private ProductRepository productRepository;

    @Autowired
	private CurrencyRepository currencyRepository;

    public String nexCode(Sequence sequence, String code) {
        String[] array = code.split("\\.");
        String prefix = array[0];
        String suffix = array[1];
        Integer newSuffix = Integer.parseInt(suffix) + 1;
        String nextCode = prefix + "." + newSuffix.toString();

        return nextCode;
    }

    public String account(String code, String prefix, String country, String currency, String product) {
        String prefixAccount = prefix(prefix, country, currency, product);

        if (prefixAccount == null) {
            return null;
        }

        String value = prefixAccount + "." + code;

        return value;
    }

    public String nexAccount(Sequence sequence, String code) {
        Integer next = Integer.parseInt(code) + 1;
        String nextAccount = next.toString();

        return nextAccount;
    }

    public String clean(String sequence) {
        String[] array = sequence.split("\\.");
        String value = array[1];

        return value;
    }

    public String prefix(String prefix, String country, String currency, String product) {
        Country countryFind = countryRepository.findByName(country);
        Currency currencyFind = currencyRepository.findByName(currency);
        Product productFind= productRepository.findByName(product);

        if ((countryFind == null) || (currencyFind == null) || (productFind == null)) {
            return null;
        }

        String value = prefix + "." + clean(productFind.getCode()) + "." + clean(countryFind.getCode()) + "." + clean(currencyFind.getCode());

        return value;
    }
    
}

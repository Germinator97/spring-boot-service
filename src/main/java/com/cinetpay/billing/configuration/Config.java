/**
 * 
 */
package com.cinetpay.billing.configuration;

import com.cinetpay.billing.database.country.adapter.CountryAdapterImpl;
import com.cinetpay.billing.use_cases.country.CountryAdapter;
import com.cinetpay.billing.use_cases.country.FindCountryByCode;
import com.cinetpay.billing.use_cases.country.FindCountryByName;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mac
 *
 */
@Configuration
public class Config {

    @Bean
    public FindCountryByCode findCountryByCode(CountryAdapter countryAdapter) {
        return new FindCountryByCode(countryAdapter);
    }

    @Bean
    public FindCountryByName findCountryByName(CountryAdapter countryAdapter) {
        return new FindCountryByName(countryAdapter);
    }
 
    @Bean
    public CountryAdapter countryAdapter() {
        return new CountryAdapterImpl();
    }

}

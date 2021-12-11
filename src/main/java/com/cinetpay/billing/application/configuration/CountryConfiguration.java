/**
 * 
 */
package com.cinetpay.billing.application.configuration;

import com.cinetpay.billing.database.country.adapter.CountryAdapterImpl;
import com.cinetpay.billing.use_cases.country.CountryAdapter;
import com.cinetpay.billing.use_cases.country.CreateCountry;
import com.cinetpay.billing.use_cases.country.FindCountryByCode;
import com.cinetpay.billing.use_cases.country.FindCountryByName;
import com.cinetpay.billing.use_cases.country.UpdateCountry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mac
 *
 */
@Configuration
public class CountryConfiguration {

    // @Bean
    // public FindCountryByCode findCountryByCode(CountryAdapter countryAdapter) {
    //     return new FindCountryByCode(countryAdapter);
    // }

    // @Bean
    // public FindCountryByName findCountryByName(CountryAdapter countryAdapter) {
    //     return new FindCountryByName(countryAdapter);
    // }
 
    // @Bean
    // public CreateCountry countryCountry(CountryAdapter countryAdapter) {
    //     return new CreateCountry(countryAdapter);
    // }

    // @Bean
    // public UpdateCountry updateCountry(CountryAdapter countryAdapter) {
    //     return new UpdateCountry(countryAdapter);
    // }

    // @Bean
    // public CountryAdapter countryAdapter() {
    //     return new CountryAdapterImpl();
    // }

}

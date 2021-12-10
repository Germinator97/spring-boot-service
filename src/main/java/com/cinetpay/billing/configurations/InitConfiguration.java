package com.cinetpay.billing.configurations;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitConfiguration {
    @Bean
    public ApplicationRunner initializer(MarvelCharacterRepository repository) {
        return args -> repository.saveAll();
    }
}

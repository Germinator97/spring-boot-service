package com.cinetpay.billing.configurations;

import com.cinetpay.billing.models.Sequence;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitConfiguration {
    @Bean
    public ApplicationRunner initializer(SequenceRepository repository) {
        Sequence sequence = new Sequence();
        sequence.setCountry("03.0001");
        sequence.setCurrency("04.01");
        sequence.setProduct("02.001");
        return args -> repository.save(sequence);
    }
}

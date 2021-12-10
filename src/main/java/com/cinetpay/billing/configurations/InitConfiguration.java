package com.cinetpay.billing.configurations;

import java.util.Optional;

import com.cinetpay.billing.models.Sequence;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitConfiguration {
    
    @Bean
    public ApplicationRunner initializer(SequenceRepository repository) {

        Optional<Sequence> exist = repository.findById(1);

        if (exist.isPresent()) {
            return null;
        }

        else {
            Sequence sequence = new Sequence();
            sequence.setCountry("03.0001");
            sequence.setCurrency("04.01");
            sequence.setProduct("02.001");
            return args -> repository.save(sequence);
        }

    }
}

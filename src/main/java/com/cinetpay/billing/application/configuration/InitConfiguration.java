package com.cinetpay.billing.application.configuration;

import java.util.Optional;

import com.cinetpay.billing.application.SequenceRepository;
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
            sequence.setCountry("03.1000");
            sequence.setCurrency("04.10");
            sequence.setProduct("02.100");
            return args -> repository.save(sequence);
        }

    }
}

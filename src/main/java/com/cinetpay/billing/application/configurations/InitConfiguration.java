package com.cinetpay.billing.application.configurations;

import com.cinetpay.billing.application.mapper.Mapper;
import com.cinetpay.billing.domain.sequence.entity.Sequence;
import com.cinetpay.billing.domain.sequence.repository.SequenceRepository;

import org.modelmapper.ModelMapper;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitConfiguration {

    @Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

    @Bean
	public Mapper mapper() {
		return new Mapper();
	}
    
    @Bean
    public ApplicationRunner initializer(SequenceRepository repository) throws Exception {

        Sequence exist = repository.find();

        if (exist != null) {
            return null;
        }

        else {
            Sequence sequence = new Sequence();
            sequence.setCountry("03.1000");
            sequence.setCurrency("04.10");
            sequence.setProduct("02.100");
            return args -> repository.update(sequence);
        }
    }
}

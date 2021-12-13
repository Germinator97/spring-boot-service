package com.cinetpay.billing.infrastructure.adapters;

import java.util.List;

import com.cinetpay.billing.application.mapper.Mapper;
import com.cinetpay.billing.domain.country.entities.Sequence;
import com.cinetpay.billing.domain.country.repositories.SequenceRepository;
import com.cinetpay.billing.infrastructure.entities.SequenceModel;
import com.cinetpay.billing.infrastructure.repositories.SequenceJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SequenceRepositoryAdapter implements SequenceRepository {
    
    @Autowired
    private SequenceJpaRepository sequenceJpaRepository;

    @Autowired
	private Mapper mapper;

    @Override
    public Sequence find() {
        List<SequenceModel> sequences = sequenceJpaRepository.findAll();
        return mapper.mapperList(sequences.toArray(), Sequence.class);
    }

    @Override
    public Sequence update(Sequence data) {
        SequenceModel model = mapper.mapper(data, SequenceModel.class);
        SequenceModel sequence = sequenceJpaRepository.save(model);
        return mapper.mapper(sequence, Sequence.class);
    }

}

package com.cinetpay.billing.domain.sequence.repository;

import com.cinetpay.billing.domain.sequence.entity.Sequence;

public interface SequenceRepository {

    public Sequence find();

    public Sequence update(Sequence data);
    
}

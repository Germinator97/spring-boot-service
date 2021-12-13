package com.cinetpay.billing.domain.country.repositories;

import com.cinetpay.billing.domain.country.entities.Sequence;

public interface SequenceRepository {

    public Sequence find();

    public Sequence update(Sequence data);
    
}

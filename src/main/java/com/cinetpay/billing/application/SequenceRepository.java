package com.cinetpay.billing.application;

import com.cinetpay.billing.models.Sequence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SequenceRepository extends JpaRepository<Sequence, Integer> {
    
}

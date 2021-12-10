package com.cinetpay.billing.configurations;

import com.cinetpay.billing.models.Sequence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SequenceRepository extends JpaRepository<Sequence, String> {
    
}

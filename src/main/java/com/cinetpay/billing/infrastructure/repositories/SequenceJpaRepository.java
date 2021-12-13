package com.cinetpay.billing.infrastructure.repositories;

import com.cinetpay.billing.infrastructure.entities.SequenceModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SequenceJpaRepository extends JpaRepository<SequenceModel, String> {
    
}

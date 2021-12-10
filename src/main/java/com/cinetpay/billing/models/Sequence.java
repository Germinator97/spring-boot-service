package com.cinetpay.billing.models;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "sequences")
@EntityListeners(AuditingEntityListener.class)
public class Sequence {
    
}

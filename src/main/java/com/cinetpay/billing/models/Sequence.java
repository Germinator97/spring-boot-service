package com.cinetpay.billing.models;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "sequences")
@EntityListeners(AuditingEntityListener.class)
public class Sequence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	protected int id;

    @Column(columnDefinition = "varchar(255)", name = "billing", nullable = false)
    private String billing;

    @Column(columnDefinition = "varchar(255)", name = "billing_service", nullable = false)
    private String billing_service;

    @Column(columnDefinition = "varchar(255)", name = "commission_partner", nullable = false)
    private String commission_partner;

    @Column(columnDefinition = "varchar(255)", name = "commission_service", nullable = false)
    private String commission_service;

    @Column(columnDefinition = "varchar(255)", name = "country", nullable = false)
    private String country;

    @Column(columnDefinition = "varchar(255)", name = "currency", nullable = false)
    private String currency;

    @Column(columnDefinition = "varchar(255)", name = "customer_account", nullable = false)
    private String customer_account;

    @Column(columnDefinition = "varchar(255)", name = "merchant_account", nullable = false)
    private String merchant_account;

    @Column(columnDefinition = "varchar(255)", name = "partner_account", nullable = false)
    private String partner_account;

    @Column(columnDefinition = "varchar(255)", name = "partner", nullable = false)
    private String partner;

    @Column(columnDefinition = "varchar(255)", name = "product", nullable = false)
    private String product;

    @Column(columnDefinition = "varchar(255)", name = "vendor_account", nullable = false)
    private String vendor_account;

    @Column(columnDefinition = "varchar(255)", name = "service_account", nullable = false)
    private String service_account;
    
}

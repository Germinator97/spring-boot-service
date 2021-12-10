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

    @Column(columnDefinition = "varchar(255)", name = "country", nullable = false)
    private String country;

    @Column(columnDefinition = "varchar(255)", name = "currency", nullable = false)
    private String currency;

    @Column(columnDefinition = "varchar(255)", name = "product", nullable = false)
    private String product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
    
}

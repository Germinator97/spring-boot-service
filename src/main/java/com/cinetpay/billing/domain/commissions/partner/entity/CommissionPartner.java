package com.cinetpay.billing.domain.commissions.partner.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class CommissionPartner {

    protected String id;

    private String vendor;

    private Double commissionFixe;

    private Double commissionVariable;

    private Boolean isActive = true;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String product;

    private String country;

    private String partner;

    private String currency;

    public void generateId() {
        this.id = UUID.randomUUID().toString();
        System.out.println(this.id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Double getCommissionFixe() {
        return commissionFixe;
    }

    public void setCommissionFixe(Double commissionFixe) {
        this.commissionFixe = commissionFixe;
    }

    public Double getCommissionVariable() {
        return commissionVariable;
    }

    public void setCommissionVariable(Double commissionVariable) {
        this.commissionVariable = commissionVariable;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

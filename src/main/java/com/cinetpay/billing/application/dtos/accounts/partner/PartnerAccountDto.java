package com.cinetpay.billing.application.dtos.accounts.partner;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class PartnerAccountDto {

    @Schema(hidden = true)
    protected String id;

    @Schema(hidden = true)
    private String vendor;

    @NotNull(message = "The product name must not be null.")
    @NotEmpty(message = "The product name must not be empty.")
    @Schema(description = "The product name",  type = "string", required = true, example ="PAYIN")
    @Column(columnDefinition = "varchar(255)", name = "product", nullable = false)
    private String product;

    @NotNull(message = "The country name must not be null.")
    @NotEmpty(message = "The country name must not be empty.")
    @Schema(description = "The country name",  type = "string", required = true, example ="CI")
    @Column(columnDefinition = "varchar(255)", name = "country", nullable = false)
    private String country;

    @NotNull(message = "The partner name must not be null.")
    @NotEmpty(message = "The partner name must not be empty.")
    @Schema(description = "The partner name",  type = "string", required = true, example ="OM")
    @Column(columnDefinition = "varchar(255)", name = "partner", nullable = false)
    private String partner;

    @NotNull(message = "The currency name must not be null.")
    @NotEmpty(message = "The currency name must not be empty.")
    @Schema(description = "The currency name",  type = "string", required = true, example ="XOF")
    @Column(columnDefinition = "varchar(255)", name = "currency", nullable = false)
    private String currency;


    @Schema(hidden = true)
    private String account;

    @Schema(hidden = true)
    private Double balance;

    @Schema(hidden = true)
    private Boolean isBlocked = false;

    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP", name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP", name = "updated_at")
    @CreationTimestamp
    @LastModifiedDate
    private Timestamp updatedAt;


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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}

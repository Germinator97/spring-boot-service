package com.cinetpay.billing.application.dtos.accounts.vendor;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UpdateVendorAccountDto {
    @Schema(hidden = true)
    protected String id;

    @Schema(description = "The vendor name",  type = "string", example ="1")
    private String vendor;

    @Schema(description = "The product name",  type = "string", example ="PAYIN")
    private String product;

    @Schema(description = "The country name",  type = "string", example ="CI")
    private String country;

    @Schema(description = "The currency name",  type = "string", example ="XOF")
    private String currency;


    @Schema(description = "The account",  type = "string", example ="01.1000")
    private String account;

    @Schema(description = "The balance",  type = "string", example ="500000")
    private Double balance;


    @Schema(description = "The is_blocked name",  type = "boolean", example ="false")
    private Boolean isBlocked;

    @Schema(hidden = true)
    private Timestamp createdAt;

    @Schema(hidden = true)
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

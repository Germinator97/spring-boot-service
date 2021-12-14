package com.cinetpay.billing.application.dtos.accounts.vendor;


import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class VendorAccountDto {
    @Schema(hidden = true)
    protected String id;

    @NotNull(message = "The vendor name must not be null.")
    @NotEmpty(message = "The vendor name must not be empty.")
    @Schema(description = "The vendor name",  type = "string", required = true, example ="1")
    private String vendor;


    @NotNull(message = "The product name must not be null.")
    @NotEmpty(message = "The product name must not be empty.")
    @Schema(description = "The product name",  type = "string", required = true, example ="PAYIN")
    private String product;

    @NotNull(message = "The country name must not be null.")
    @NotEmpty(message = "The country name must not be empty.")
    @Schema(description = "The country name",  type = "string", required = true, example ="CI")
    private String country;

    @NotNull(message = "The currency name must not be null.")
    @NotEmpty(message = "The currency name must not be empty.")
    @Schema(description = "The currency name",  type = "string", required = true, example ="XOF")
    private String currency;


    @NotNull(message = "The account name must not be null.")
    @NotEmpty(message = "The account name must not be empty.")
    @Schema(description = "The account name",  type = "string", required = true, example ="01.1000")
    private String account;

    @NotNull(message = "The balance name must not be null.")
    @NotEmpty(message = "The balance name must not be empty.")
    @Schema(description = "The balance name",  type = "double", required = true, example ="800000")
    private Double balance;

    @NotNull(message = "The isBlocked name must not be null.")
    @NotEmpty(message = "The isBlocked name must not be empty.")
    @Schema(description = "The isBlocked name",  type = "boolean", required = true, example ="true")
    private Boolean isBlocked = false;

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

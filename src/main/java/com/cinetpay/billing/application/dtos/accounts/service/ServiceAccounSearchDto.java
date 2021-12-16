package com.cinetpay.billing.application.dtos.accounts.service;

import java.time.LocalDateTime;

import javax.validation.constraints.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author mac
 *
 */
@Data
public class ServiceAccounSearchDto {
    
    @Schema(hidden = true)
	protected String id;
	
    @Schema(hidden = true)
    private String vendor;

    @NotNull(message = "The product name must not be null.")
	@NotEmpty(message = "The product name must not be empty.")
    @Schema(description = "The product name",  type = "string", required = true, example ="PAYIN")
    private String product;

    @NotNull(message = "The country name must not be null.")
	@NotEmpty(message = "The country name must not be empty.")
    @Schema(description = "The country name ISO2",  type = "string", required = true, example ="CI")
    private String country;

    @NotNull(message = "The currency name must not be null.")
	@NotEmpty(message = "The currency name must not be empty.")
    @Schema(description = "The currency name",  type = "string", required = true, example ="XOF")
    private String currency;
	
    @NotNull(message = "The owner service must not be null.")
	@NotEmpty(message = "The owner service must not be empty.")
    @Schema(description = "The owner service",  type = "string", required = true, example ="1xBet")
    private String owner;
    
    @Schema(hidden = true)
    private String account;
    
    @Schema(hidden = true)
    private Double balance;

    @Schema(hidden = true)
    private Boolean isBlocked;

	@Schema(hidden = true)
	private LocalDateTime createdAt;

	@Schema(hidden = true)
    private LocalDateTime updatedAt;

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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
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

}

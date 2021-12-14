package com.cinetpay.billing.application.dtos.commissions.partner;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class CommissionPartnerDto {

    @Schema(hidden = true)
    protected String id;

    @NotNull(message = "The vendor must not be null.")
    @NotEmpty(message = "The vendor must not be empty.")
    @Schema(description = "The vendor",  type = "double", required = true, example = "0.002")
    private String vendor;

    //@NotNull(message = "The commission fixe name must not be null.")
    //@NotEmpty(message = "The commission fixe name must not be empty.")
    @Schema(description = "The commission fixe name",  type = "double", required = true, example = "0.002")
    private Double commissionFixe;

    //@NotNull(message = "The commissionVariable name must not be null.")
    //@NotEmpty(message = "The commissionVariable name must not be empty.")
    @Schema(description = "The commissionVariable name",  type = "double", required = true, example ="0.03")
    private Double commissionVariable;

    @Schema(hidden = true)
    private Boolean isActive = true;

    @Schema(hidden = true)
    private Timestamp createdAt;

    @Schema(hidden = true)
    private Timestamp updatedAt;

    @NotNull(message = "The product name must not be null.")
    @NotEmpty(message = "The product name must not be empty.")
    @Schema(description = "The product name",  type = "string", required = true, example ="PAYIN")
    private String product;

    @NotNull(message = "The country name must not be null.")
    @NotEmpty(message = "The country name must not be empty.")
    @Schema(description = "The country name ISO2",  type = "string", required = true, example ="CI")
    private String country;


    @NotNull(message = "The partner name must not be null.")
    @NotEmpty(message = "The partner name must not be empty.")
    @Schema(description = "The partner name",  type = "string", required = true, example ="OM")
    private String partner;


    @NotNull(message = "The currency name must not be null.")
    @NotEmpty(message = "The currency name must not be empty.")
    @Schema(description = "The currency name",  type = "string", required = true, example ="XOF")
    private String currency;

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

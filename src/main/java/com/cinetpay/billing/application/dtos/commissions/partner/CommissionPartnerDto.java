package com.cinetpay.billing.application.dtos.commissions.partner;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CommissionPartnerDto {

    @Schema(hidden = true)
    protected String id;

    @Schema(hidden = true)
    private String vendor;

    @Schema(description = "The commissionFixe name",  type = "double", required = true, example ="0.012")
    private Double commissionFixe;

    @Schema(description = "The commissionVariable name",  type = "double", required = true, example ="0.025")
    private Double commissionVariable;

    @Schema(hidden = true)
    private Boolean isActive;

    @Schema(hidden = true)
    private LocalDateTime createdAt;

    @Schema(hidden = true)
    private LocalDateTime updatedAt;

    @Schema(description = "The product name",  type = "string", required = true, example ="PAYIN")
    private String product;

    @Schema(description = "The country name",  type = "string", required = true, example ="CI")
    private String country;

    @Schema(description = "The partner name",  type = "string", required = true, example ="OM")
    private String partner;

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
        this.isActive = active;
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

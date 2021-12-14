package com.cinetpay.billing.application.dtos.commissions.service;

import java.time.LocalDateTime;

import com.cinetpay.billing.application.dtos.billings.service.BillingServiceDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author mac
 *
 */
@Data
public class CommissionServiceDto {

    @Schema(hidden = true)
	protected String id;

    @Schema(hidden = true)
    private BillingServiceDto billingService;

    @Schema(description = "The min value only for INTERVAL mode",  type = "double", required = false, example = "0")
    private double min = 0;
    
    @Schema(description = "The max value only for INTERVAL mode",  type = "double", required = false, example = "0")
    private double max = 0;

    @Schema(description = "The amount for fixe billing",  type = "double", required = false, example = "0")
    private double commissionFixe;
    
    @Schema(description = "The pourcentage for variable billing",  type = "double", required = false, example = "0")
    private double commissionVariable;

	@Schema(hidden = true)
    private Boolean isActive;

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

    public BillingServiceDto getBillingService() {
        return billingService;
    }

    public void setBillingService(BillingServiceDto billingService) {
        this.billingService = billingService;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getCommissionFixe() {
        return commissionFixe;
    }

    public void setCommissionFixe(double commissionFixe) {
        this.commissionFixe = commissionFixe;
    }

    public double getCommissionVariable() {
        return commissionVariable;
    }

    public void setCommissionVariable(double commissionVariable) {
        this.commissionVariable = commissionVariable;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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

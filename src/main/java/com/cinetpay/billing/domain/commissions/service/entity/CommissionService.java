package com.cinetpay.billing.domain.commissions.service.entity;

import java.time.LocalDateTime;

import com.cinetpay.billing.domain.billings.service.entity.BillingService;

public class CommissionService {

	protected String id;
	
    private double min;
    
    private double max;

    private double commissionFixe;
    
    private double commissionVariable;

    private Boolean isActive = true;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    private BillingService billingService;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public BillingService getBillingService() {
        return billingService;
    }

    public void setBillingService(BillingService billingService) {
        this.billingService = billingService;
    }
    
}

package com.cinetpay.billing.domain.billings.service.entity;

import java.time.LocalDateTime;
import java.util.Set;

import com.cinetpay.billing.domain.commissions.service.entity.CommissionService;
import com.cinetpay.billing.infrastructure.enums.Mode;
import com.cinetpay.billing.infrastructure.enums.Option;
import com.cinetpay.billing.infrastructure.enums.Period;
import com.cinetpay.billing.infrastructure.enums.Type;

public class BillingService {

	protected String id;
	
    private String vendor;

    private String product;

    private String country;

    private String partner;

    private String currency;
	
    private String owner;
    
    private Option option;
    
    private Mode mode;
    
    private Type type;
    
    private Period period;
    
    private Integer frequency;

    private Boolean isActive = true;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Set<CommissionService> commissionsServices;

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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
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

    public Set<CommissionService> getCommissionsServices() {
        return commissionsServices;
    }

    public void setCommissionsServices(Set<CommissionService> commissionsServices) {
        this.commissionsServices = commissionsServices;
    }
    
}

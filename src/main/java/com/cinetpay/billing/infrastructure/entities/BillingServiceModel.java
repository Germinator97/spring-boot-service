package com.cinetpay.billing.infrastructure.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cinetpay.billing.infrastructure.enums.Mode;
import com.cinetpay.billing.infrastructure.enums.Option;
import com.cinetpay.billing.infrastructure.enums.Period;
import com.cinetpay.billing.infrastructure.enums.Type;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Table(name = "billings_services")
@EntityListeners(AuditingEntityListener.class)
@Data
@NamedQuery(
    name = "BillingServiceModel.findWithService",
    query = "SELECT t FROM BillingServiceModel t WHERE t.vendor = ?1 AND t.product = ?2 AND t.country = ?3 AND t.partner = ?4 AND t.currency = ?5 AND t.owner = ?6"
)
@NamedQuery(
    name = "BillingServiceModel.findWithOutService",
    query = "SELECT t FROM BillingServiceModel t WHERE t.vendor = ?1 AND t.product = ?2 AND t.country = ?3 AND t.partner = ?4 AND t.currency = ?5 AND t.option = 'DEFAULT'"
)
public class BillingServiceModel {

	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(columnDefinition = "varchar(255)", updatable = false, nullable = false, unique = true)
	protected String id;
	
    @Column(columnDefinition = "varchar(255)", name = "vendor", nullable = false)
    private String vendor;

    @Column(columnDefinition = "varchar(255)", name = "product", nullable = false)
    private String product;

    @Column(columnDefinition = "varchar(255)", name = "country", nullable = false)
    private String country;

    @Column(columnDefinition = "varchar(255)", name = "partner", nullable = false)
    private String partner;

    @Column(columnDefinition = "varchar(255)", name = "currency", nullable = false)
    private String currency;
	
    @Column(columnDefinition = "varchar(255)", name = "owner")
    private String owner;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "option", nullable = false)
    private Option option;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "mode", nullable = false)
    private Mode mode;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Type type;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "period")
    private Period period;
    
    @Column(columnDefinition = "integer default 0", name = "frequency")
    private Integer frequency;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive = true;

    @Column(updatable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP", name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
    
	@Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP", name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
	
	@OneToMany(mappedBy = "billingService")
    private List<CommissionServiceModel> commissionsServices;

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

    public List<CommissionServiceModel> getCommissionsServices() {
        return commissionsServices;
    }

    public void setCommissionsServices(List<CommissionServiceModel> commissionsServices) {
        this.commissionsServices = commissionsServices;
    }

    @Override
    public String toString() {
        return null;
    }

}

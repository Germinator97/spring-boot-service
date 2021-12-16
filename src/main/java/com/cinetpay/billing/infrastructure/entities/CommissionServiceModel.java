package com.cinetpay.billing.infrastructure.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Table(name = "commissions_services")
@EntityListeners(AuditingEntityListener.class)
@Data
@NamedQuery(
    name = "CommissionServiceModel.findByInterval",
    query = "SELECT t FROM CommissionServiceModel t WHERE t.billingService = ?1 GROUP BY t.id, t.billingService, t.min, t.max HAVING t.min <= ?2 AND ?2 <= t.max OR t.min <= ?3 AND ?3 <= t.max OR ?2 > t.min AND ?3 < t.max"
)
@NamedQuery(
    name = "CommissionServiceModel.findForOne",
    query = "SELECT t FROM CommissionServiceModel t WHERE t.billingService = ?1 AND t.isActive = true"
)
@NamedQuery(
    name = "CommissionServiceModel.findInInterval",
    query = "SELECT t FROM CommissionServiceModel t WHERE t.billingService = ?1 AND t.isActive = true AND t.min <= ?2 AND t.max >= ?2"
)
public class CommissionServiceModel {

	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(columnDefinition = "varchar(255)", updatable = false, nullable = false, unique = true)
	protected String id;
	
    @Column(columnDefinition = "decimal(20,2) default 0", name = "min", nullable = false)
    private Double min;
    
    @Column(columnDefinition = "decimal(20,2) default 0", name = "max", nullable = false)
    private Double max;
	
    @Column(columnDefinition = "decimal(12,2) default 0", name = "commission_fixe", nullable = false)
    private Double commissionFixe;
    
    @Column(columnDefinition = "decimal(5,4) default 0", name = "commission_variable", nullable = false)
    private Double commissionVariable;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive = true;

    @Column(updatable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP", name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
    
	@Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP", name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
	@ManyToOne
    @JoinColumn(name = "billingService")
    private BillingServiceModel billingService;

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

    public BillingServiceModel getBillingService() {
        return billingService;
    }

    public void setBillingService(BillingServiceModel billingService) {
        this.billingService = billingService;
    }

    @Override
    public String toString() {
        return null;
    }

}

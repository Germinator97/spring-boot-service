package com.cinetpay.billing.infrastructure.entities;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cinetpay.billing.models.BillingService;
import com.cinetpay.billing.models.CommissionPartner;
import com.cinetpay.billing.models.PartnerAccount;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Table(name = "partners")
@EntityListeners(AuditingEntityListener.class)
@Data
public class PartnerModel {

    @Id
	@Column(columnDefinition = "varchar(255)", nullable = false, unique = true)
	protected String id;
	
    @Column(columnDefinition = "varchar(255)", name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive = true;

    @Column(updatable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP", name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
    
	@Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP", name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "partner", cascade = CascadeType.ALL)
    private Set<CommissionPartner> commissionsPartners;
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "partner", cascade = CascadeType.ALL)
    private Set<BillingService> billingsServices;
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "partner", cascade = CascadeType.ALL)
    private Set<PartnerAccount> partnersAccounts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<CommissionPartner> getCommissionsPartners() {
        return commissionsPartners;
    }

    public void setCommissionsPartners(Set<CommissionPartner> commissionsPartners) {
        this.commissionsPartners = commissionsPartners;
    }

    public Set<BillingService> getBillingsServices() {
        return billingsServices;
    }

    public void setBillingsServices(Set<BillingService> billingsServices) {
        this.billingsServices = billingsServices;
    }

    public Set<PartnerAccount> getPartnersAccounts() {
        return partnersAccounts;
    }

    public void setPartnersAccounts(Set<PartnerAccount> partnersAccounts) {
        this.partnersAccounts = partnersAccounts;
    }
    
}

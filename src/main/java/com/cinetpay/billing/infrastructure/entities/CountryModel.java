/**
 * 
 */
package com.cinetpay.billing.infrastructure.entities;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

import com.cinetpay.billing.models.BillingService;
import com.cinetpay.billing.models.CommissionPartner;
import com.cinetpay.billing.models.MerchantAccount;
import com.cinetpay.billing.models.PartnerAccount;
import com.cinetpay.billing.models.ServiceAccount;
import com.cinetpay.billing.models.VendorAccount;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

/**
 * @author mac
 *
 */
@Entity
@Table(name = "countries")
@EntityListeners(AuditingEntityListener.class)
@Data
public class CountryModel {

	@Id
	@Column(columnDefinition = "varchar(255)", nullable = false, unique = true)
	protected String id;
	
	@Column(columnDefinition = "varchar(255)", name = "code", nullable = false, unique = true)
	private String code;
	
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country", cascade = CascadeType.ALL)
    private Set<CommissionPartner> commissionsPartners;
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country", cascade = CascadeType.ALL)
    private Set<BillingService> billingsServices;
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country", cascade = CascadeType.ALL)
    private Set<VendorAccount> vendorsAccounts;
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country", cascade = CascadeType.ALL)
    private Set<MerchantAccount> merchantsAccounts;
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country", cascade = CascadeType.ALL)
    private Set<ServiceAccount> servicesAccounts;
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country", cascade = CascadeType.ALL)
    private Set<PartnerAccount> partnersAccounts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Set<VendorAccount> getVendorsAccounts() {
        return vendorsAccounts;
    }

    public void setVendorsAccounts(Set<VendorAccount> vendorsAccounts) {
        this.vendorsAccounts = vendorsAccounts;
    }

    public Set<MerchantAccount> getMerchantsAccounts() {
        return merchantsAccounts;
    }

    public void setMerchantsAccounts(Set<MerchantAccount> merchantsAccounts) {
        this.merchantsAccounts = merchantsAccounts;
    }

    public Set<ServiceAccount> getServicesAccounts() {
        return servicesAccounts;
    }

    public void setServicesAccounts(Set<ServiceAccount> servicesAccounts) {
        this.servicesAccounts = servicesAccounts;
    }

    public Set<PartnerAccount> getPartnersAccounts() {
        return partnersAccounts;
    }

    public void setPartnersAccounts(Set<PartnerAccount> partnersAccounts) {
        this.partnersAccounts = partnersAccounts;
    }
}

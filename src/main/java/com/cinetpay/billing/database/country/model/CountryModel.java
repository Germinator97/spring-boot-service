/**
 * 
 */
package com.cinetpay.billing.database.country.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.*;

import com.cinetpay.billing.models.Billing_Service;
import com.cinetpay.billing.models.Commission_Partner;
import com.cinetpay.billing.models.Merchant_Account;
import com.cinetpay.billing.models.Partner_Account;
import com.cinetpay.billing.models.Service_Account;
import com.cinetpay.billing.models.Vendor_Account;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author mac
 *
 */
 @Entity
@Table(name = "countries")
@EntityListeners(AuditingEntityListener.class)
@Data
@Accessors(chain = true)
public class CountryModel {

	@Id
	@Column(columnDefinition = "varchar(255)", nullable = false, unique = true)
	protected String id;
	
	@Column(columnDefinition = "varchar(255)", name = "code", nullable = false, unique = true)
	private String code;
	
    @Column(columnDefinition = "varchar(255)", name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "is_active", columnDefinition = "boolean default true", nullable = false)
    private Boolean is_active;

    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP", name = "created_at", nullable = false)
    @CreatedDate
    private Timestamp created_at;
    
	@Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP", name = "updated_at", nullable = false)
    @LastModifiedDate
    private Timestamp updated_at;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country", cascade = CascadeType.ALL)
    private Set<Commission_Partner> commissions_partners;
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country", cascade = CascadeType.ALL)
    private Set<Billing_Service> billings_services;
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country", cascade = CascadeType.ALL)
    private Set<Vendor_Account> vendors_accounts;
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country", cascade = CascadeType.ALL)
    private Set<Merchant_Account> merchants_accounts;
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country", cascade = CascadeType.ALL)
    private Set<Service_Account> services_accounts;
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country", cascade = CascadeType.ALL)
    private Set<Partner_Account> partners_accounts;

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

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Set<Commission_Partner> getCommissions_partners() {
        return commissions_partners;
    }

    public void setCommissions_partners(Set<Commission_Partner> commissions_partners) {
        this.commissions_partners = commissions_partners;
    }

    public Set<Billing_Service> getBillings_services() {
        return billings_services;
    }

    public void setBillings_services(Set<Billing_Service> billings_services) {
        this.billings_services = billings_services;
    }

    public Set<Vendor_Account> getVendors_accounts() {
        return vendors_accounts;
    }

    public void setVendors_accounts(Set<Vendor_Account> vendors_accounts) {
        this.vendors_accounts = vendors_accounts;
    }

    public Set<Merchant_Account> getMerchants_accounts() {
        return merchants_accounts;
    }

    public void setMerchants_accounts(Set<Merchant_Account> merchants_accounts) {
        this.merchants_accounts = merchants_accounts;
    }

    public Set<Service_Account> getServices_accounts() {
        return services_accounts;
    }

    public void setServices_accounts(Set<Service_Account> services_accounts) {
        this.services_accounts = services_accounts;
    }

    public Set<Partner_Account> getPartners_accounts() {
        return partners_accounts;
    }

    public void setPartners_accounts(Set<Partner_Account> partners_accounts) {
        this.partners_accounts = partners_accounts;
    }

    public String generateId() {
        return null;
    }

}

/**
 * 
 */
package com.cinetpay.billing.models;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author Germinator
 *
 */
@Entity
@Table(name = "currencies")
@EntityListeners(AuditingEntityListener.class)
public class Currency {
	
	@Id
	@Column(columnDefinition = "varchar(255)", nullable = false, unique = true)
	protected String id;
	
	@Column(columnDefinition = "varchar(255)", name = "code", nullable = false, unique = true)
	protected String code;
	
    @Column(columnDefinition = "varchar(255)", name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive = true;

    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP", name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;
    
	@Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP", name = "updated_at")
    @CreationTimestamp
    @LastModifiedDate
    private Timestamp updatedAt;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "currency", cascade = CascadeType.ALL)
    private Set<CommissionPartner> commissions_partners;
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "currency", cascade = CascadeType.ALL)
    private Set<BillingService> billingsServices;
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "currency", cascade = CascadeType.ALL)
    private Set<VendorAccount> vendorsAccounts;
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "currency", cascade = CascadeType.ALL)
    private Set<MerchantAccount> merchantsAccounts;
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "currency", cascade = CascadeType.ALL)
    private Set<ServiceAccount> servicesAccounts;
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "currency", cascade = CascadeType.ALL)
    private Set<PartnerAccount> partnersAccounts;
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "currency", cascade = CascadeType.ALL)
    private Set<CustomerAccount> customersAccounts;

}

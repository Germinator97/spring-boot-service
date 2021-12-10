/**
 * 
 */
package com.cinetpay.billing.models;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.cinetpay.billing.enumerations.*;

/**
 * @author Germinator
 *
 */
@Entity
@Table(name = "billings_services")
@EntityListeners(AuditingEntityListener.class)
public class Billing_Service {
	
	@Id
	@Column(columnDefinition = "varchar(255)", nullable = false, unique = true)
	protected String id;
	
    @Column(columnDefinition = "varchar(255)", name = "vendor", nullable = false)
    private String vendor;
	
    @Column(columnDefinition = "varchar(255)", name = "owner", nullable = false)
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
    
    @Column(columnDefinition = "integer", name = "frequency")
    private Integer frequency;

    @Column(name = "is_active", columnDefinition = "boolean default true", nullable = false)
    private Boolean is_active;

    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP", name = "created_at", nullable = false)
    @CreatedDate
    private Timestamp created_at;
    
	@Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP", name = "updated_at", nullable = false)
    @LastModifiedDate
    private Timestamp updated_at;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product")
    private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "country")
    private Country country;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "partner")
    private Partner partner;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "currency")
    private Currency currency;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "billing_service", cascade = CascadeType.ALL)
    private Set<Commission_Service> commissions_services;

	/**
	 * 
	 */
	public Billing_Service() {
		// TODO Auto-generated constructor stub
	}

}

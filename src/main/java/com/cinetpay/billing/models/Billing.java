/**
 * 
 */
package com.cinetpay.billing.models;

import java.sql.Timestamp;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.cinetpay.billing.enumerations.*;

/**
 * @author Germinator
 *
 */
@Entity
@Table(name = "billings")
@EntityListeners(AuditingEntityListener.class)
public class Billing {
	
	@Id
	@Column(columnDefinition = "varchar(255)", nullable = false, unique = true)
	protected String id;
	
    @Column(columnDefinition = "varchar(255)", name = "vendor", nullable = false)
    private String vendor;
	
    @Column(columnDefinition = "Decimal(20,2) default 0", name = "amount", nullable = false)
    private Double amount;
    
    @Column(columnDefinition = "Decimal(20,2) default 0", name = "price", nullable = false)
    private Double price;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "operation", nullable = false)
    private Operation operation;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "action", nullable = false)
    private Action action;
    
    @Column(columnDefinition = "varchar(255)", name = "account", nullable = false)
    private String account;
    
    @Column(columnDefinition = "Decimal(20,2) default 0", name = "balance", nullable = false)
    private Double balance;
    
    @Column(columnDefinition = "Decimal(20,2) default 0", name = "last_balance", nullable = false)
    private Double last_balance;
	
    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP", name = "created_at")
    @CreationTimestamp
    private Timestamp created_at;
    
	@Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP", name = "updated_at")
    @CreationTimestamp
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

}

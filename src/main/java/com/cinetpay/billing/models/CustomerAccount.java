/**
 * 
 */
package com.cinetpay.billing.models;

import java.sql.Timestamp;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author Germinator
 *
 */
@Entity
@Table(name = "customers_accounts")
@EntityListeners(AuditingEntityListener.class)
public class CustomerAccount {
	
	@Id
	@Column(columnDefinition = "varchar(255)", nullable = false, unique = true)
	protected String id;
	
    @Column(columnDefinition = "varchar(255)", name = "vendor", nullable = false)
    private String vendor;

    @Column(columnDefinition = "varchar(255)", name = "currency", nullable = false)
    private String currency;
	
    @Column(columnDefinition = "varchar(255)", name = "owner", nullable = false)
    private String owner;
	
    @Column(columnDefinition = "varchar(255)", name = "account", nullable = false, unique = true)
    private String account;
    
    @Column(columnDefinition = "decimal(20,2) default 0", name = "balance", nullable = false)
    private Double balance;

    @Column(name = "is_blocked", columnDefinition = "boolean default false")
    private Boolean isBlocked = false;

    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP", name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;
    
	@Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP", name = "updated_at")
    @CreationTimestamp
    @LastModifiedDate
    private Timestamp updatedAt;

}

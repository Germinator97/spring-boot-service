/**
 * 
 */
package com.cinetpay.billing.models;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.*;

import com.cinetpay.billing.infrastructure.enumerations.*;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author Germinator
 *
 */
@Entity
@Table(name = "billings_services")
@EntityListeners(AuditingEntityListener.class)
public class BillingService {
	
	@Id
	@Column(columnDefinition = "varchar(255)", nullable = false, unique = true)
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

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive = true;

    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP", name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;
    
	@Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP", name = "updated_at")
    @CreationTimestamp
    @LastModifiedDate
    private Timestamp updatedAt;
	
	@OneToMany(mappedBy = "billingService")
    private Set<CommissionService> commissionsServices;

}

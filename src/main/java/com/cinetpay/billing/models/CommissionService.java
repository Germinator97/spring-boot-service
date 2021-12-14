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
@Table(name = "commissions_services")
@EntityListeners(AuditingEntityListener.class)
public class CommissionService {
	
	@Id
	@Column(columnDefinition = "varchar(255)", nullable = false, unique = true)
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
    
    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP", name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;
    
	@Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP", name = "updated_at")
    @CreationTimestamp
    @LastModifiedDate
    private Timestamp updatedAt;
    
	@ManyToOne
    @JoinColumn(name = "billingService")
    private BillingService billingService;

}

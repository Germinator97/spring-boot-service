/**
 * 
 */
package com.cinetpay.billing.models;

import java.sql.Timestamp;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author Germinator
 *
 */
@Entity
@Table(name = "commissions_services")
@EntityListeners(AuditingEntityListener.class)
public class Commission_Service {
	
	@Id
	@Column(columnDefinition = "varchar(255)", nullable = false, unique = true)
	protected String id;
	
    @Column(columnDefinition = "Decimal(20,2) default 0", name = "min", nullable = false)
    private Double min;
    
    @Column(columnDefinition = "Decimal(20,2) default 0", name = "max", nullable = false)
    private Double max;
	
    @Column(columnDefinition = "Decimal(10,2) default 0", name = "commission_fixe", nullable = false)
    private Double commission_fixe;
    
    @Column(columnDefinition = "Decimal(2,2) default 0", name = "commission_variable", nullable = false)
    private Double commission_variable;
    
    @Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP", name = "created_at", nullable = false)
    @CreatedDate
    private Timestamp created_at;
    
	@Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP", name = "updated_at", nullable = false)
    @LastModifiedDate
    private Timestamp updated_at;
    
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "commissions_services")
    private Billing_Service billing_service;

	/**
	 * 
	 */
	public Commission_Service() {
		// TODO Auto-generated constructor stub
	}

}

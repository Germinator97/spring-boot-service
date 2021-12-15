/**
 * 
 */
package com.cinetpay.billing.infrastructure.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Germinator
 *
 */
@Entity
@Table(name = "partners_accounts")
@EntityListeners(AuditingEntityListener.class)
@Data
public class PartnerAccountModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
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
	
    @Column(columnDefinition = "varchar(255)", name = "account", nullable = false, unique = true)
    private String account;
    
    @Column(name = "balance", nullable = false)
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

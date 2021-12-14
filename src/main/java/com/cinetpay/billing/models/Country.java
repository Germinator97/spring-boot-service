/**
 * 
 */
package com.cinetpay.billing.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author Germinator
 *
 */
@Entity
@Table(name = "countries")
@EntityListeners(AuditingEntityListener.class)
public class Country {
	
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

}

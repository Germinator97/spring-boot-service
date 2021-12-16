package com.cinetpay.billing.infrastructure.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author mac
 *
 */
@Entity
@Table(name = "currencies")
@EntityListeners(AuditingEntityListener.class)
@Data
public class CurrencyModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(columnDefinition = "varchar(255)", updatable = false, nullable = false, unique = true)
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

    public Boolean getActive() {
        return isActive;
    }


    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

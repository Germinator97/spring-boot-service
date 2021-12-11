/**
 * 
 */
package com.cinetpay.billing.domain.country.entities;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author mac
 *
 */
public class Country {

	protected String id;
	
	private String code;
	
    private String name;

    private Boolean isActive;

    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;

	public void generateId() {
        this.id = UUID.randomUUID().toString();
        System.out.println(this.id);
    }

    public void passCode(String sequence) {
        this.code = sequence;
    }

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

    public Boolean getIsActive() {
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

package com.cinetpay.billing.application.dtos.partner;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DeletePartnerDto {

    @Schema(hidden = true)
    protected String id;
	
    @Schema(hidden = true)
    private String name;

    @NotNull(message = "The partner is_active must not be null.")
    @Pattern(regexp = "^true$|^false$", message = "The partner is_active must be : true or false")
    @Schema(description = "The partner status active",  type = "boolean", required = true, allowableValues = {"true", "false"})
    private String isActive;

    @Schema(hidden = true)
    private LocalDateTime createdAt;

    @Schema(hidden = true)
    private LocalDateTime updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
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

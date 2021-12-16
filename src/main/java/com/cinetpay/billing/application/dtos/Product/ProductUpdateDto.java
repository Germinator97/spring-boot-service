package com.cinetpay.billing.application.dtos.product;

import java.time.LocalDateTime;

import javax.validation.constraints.Pattern;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProductUpdateDto {
    
    @Schema(hidden = true)
    protected String id;

    @Schema(hidden = true)
    private String code;
	
    @Schema(description = "The product name",  type = "string", example ="PAYIN")
    private String name;

    @Pattern(regexp = "^true$|^false$", message = "The product is_active must be : true or false")
    @Schema(description = "The product status active",  type = "boolean", allowableValues = {"true", "false"})
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

    public boolean check() {
        if (this.isActive == null) {
            return false;
        }
        return true;
    }
    
}

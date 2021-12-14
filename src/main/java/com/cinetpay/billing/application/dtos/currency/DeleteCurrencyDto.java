package com.cinetpay.billing.application.dtos.currency;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
public class DeleteCurrencyDto {

    @Schema(hidden = true)
    protected String id;

    @Schema(hidden = true)
    private String code;
	
    @Schema(hidden = true)
    private String name;

    @NotNull(message = "The currency is_active must not be null.")
    @Pattern(regexp = "^true$|^false$", message = "The currency is_active must be : true or false")
    @Schema(description = "The currency status active",  type = "boolean", required = true, allowableValues = {"true", "false"})
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
}
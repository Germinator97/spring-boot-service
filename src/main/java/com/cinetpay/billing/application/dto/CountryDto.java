/**
 * 
 */
package com.cinetpay.billing.application.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author mac
 *
 */
@Data
public class CountryDto {

	@Schema(hidden = true)
	protected String id;
	
	@Schema(hidden = true)
	private String code;
	
	@NotNull(message = "The country name must not be null.")
	@NotEmpty(message = "The country name must not be empty.")
    @Schema(description = "The country name ISO2",  type = "string", required = true, example ="CI")
    private String name;

	@Schema(hidden = true)
    private Boolean isActive;

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

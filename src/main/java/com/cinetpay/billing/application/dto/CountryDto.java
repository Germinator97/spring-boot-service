/**
 * 
 */
package com.cinetpay.billing.application.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.*;

import lombok.Data;

/**
 * @author mac
 *
 */
@Data
public class CountryDto {

	protected String id;
	
	private String code;
	
	@NotNull(message = "The country name must not be null.")
	@NotEmpty(message = "The country name must not be empty.")
    private String name;

    private Boolean is_active;

	private LocalDateTime created_at;

    private LocalDateTime updated_at;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the is_active
	 */
	public Boolean getIs_active() {
		return is_active;
	}

	/**
	 * @param is_active the is_active to set
	 */
	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

	

}

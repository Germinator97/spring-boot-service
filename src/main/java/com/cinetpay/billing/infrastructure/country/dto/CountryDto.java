/**
 * 
 */
package com.cinetpay.billing.infrastructure.country.dto;

import javax.validation.constraints.*;

import lombok.Data;

/**
 * @author mac
 *
 */
@Data
public class CountryDto {
	
	@NotNull(message = "The number of bags is required.")
	@Min(value = 1, message = "The number of bags must be greater than 0")
	@Max(value = 3, message = "The number of bags must be greater than 3")
	@NotEmpty(message = "The extras must have at least one item.")
	private String code;
	
    private String name;

    private Boolean is_active;

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

}

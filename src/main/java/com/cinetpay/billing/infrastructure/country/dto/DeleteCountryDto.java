package com.cinetpay.billing.infrastructure.country.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class DeleteCountryDto {
    private String code;
	
    private String name;

    @NotNull(message = "The country is_active must not be null.")
    @Pattern(regexp = "^true$|^false$", message = "The country is_active must be : true or false")
    private String is_active;

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

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

}

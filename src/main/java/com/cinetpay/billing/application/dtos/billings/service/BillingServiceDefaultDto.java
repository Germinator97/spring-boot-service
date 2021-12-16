package com.cinetpay.billing.application.dtos.billings.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.*;

import com.cinetpay.billing.application.dtos.commissions.service.CommissionServiceDefaultDto;
import com.cinetpay.billing.infrastructure.enums.Mode;
import com.cinetpay.billing.infrastructure.enums.Option;
import com.cinetpay.billing.infrastructure.enums.Period;
import com.cinetpay.billing.infrastructure.enums.Type;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author mac
 *
 */
@Data
public class BillingServiceDefaultDto {

    @Schema(hidden = true)
	protected String id;
	
    @Schema(hidden = true)
    private String vendor;

    @NotNull(message = "The product name must not be null.")
	@NotEmpty(message = "The product name must not be empty.")
    @Schema(description = "The product name",  type = "string", required = true, example ="PAYIN")
    private String product;

    @NotNull(message = "The country name must not be null.")
	@NotEmpty(message = "The country name must not be empty.")
    @Schema(description = "The country name ISO2",  type = "string", required = true, example ="CI")
    private String country;

    @NotNull(message = "The partner name must not be null.")
	@NotEmpty(message = "The partner name must not be empty.")
    @Schema(description = "The partner name",  type = "string", required = true, example ="OM")
    private String partner;

    @NotNull(message = "The currency name must not be null.")
	@NotEmpty(message = "The currency name must not be empty.")
    @Schema(description = "The currency name",  type = "string", required = true, example ="XOF")
    private String currency;
	
    @Schema(hidden = true)
    private String owner;
    
    @Schema(hidden = true)
    private Option option = Option.DEFAULT;
    
    @Schema(hidden = true)
    private Mode mode = Mode.ONCE;
    
    @Schema(hidden = true)
    private Type type = Type.TRANSACTION;
    
    @Schema(hidden = true)
    private Period period = Period.DAY;
    
    @Schema(hidden = true)
    private Integer frequency = 1;

	@Schema(hidden = true)
    private Boolean isActive;

	@Schema(hidden = true)
	private LocalDateTime createdAt;

	@Schema(hidden = true)
    private LocalDateTime updatedAt;

    @NotNull(message = "The commission for the service must not be null.")
	@NotEmpty(message = "The commission for the service must not be empty.")
    @Schema(description = "The list of billing",  type = "array", required = true)
	private List<CommissionServiceDefaultDto> commissionsServices;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
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

    public List<CommissionServiceDefaultDto> getCommissionsServices() {
        return commissionsServices;
    }

    public void setCommissionsServices(List<CommissionServiceDefaultDto> commissionsServices) {
        this.commissionsServices = commissionsServices;
    }
    
}

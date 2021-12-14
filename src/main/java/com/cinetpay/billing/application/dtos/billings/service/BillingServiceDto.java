package com.cinetpay.billing.application.dtos.billings.service;

import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.constraints.*;

import com.cinetpay.billing.application.dtos.commissions.service.CommissionServiceDto;
import com.cinetpay.billing.infrastructure.enumerations.Mode;
import com.cinetpay.billing.infrastructure.enumerations.Option;
import com.cinetpay.billing.infrastructure.enumerations.Period;
import com.cinetpay.billing.infrastructure.enumerations.Type;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author mac
 *
 */
@Data
public class BillingServiceDto {

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
	
    @NotNull(message = "The owner service must not be null.")
	@NotEmpty(message = "The owner service must not be empty.")
    @Schema(description = "The owner service",  type = "string", required = true, example ="1xBet")
    private String owner;
    
    @Schema(description = "The billing option",  type = "string", required = false, example ="PREPAID", allowableValues = {"PREPAID", "POSTPAID"})
    private Option option = Option.PREPAID;
    
    @Schema(description = "The billing mode",  type = "string", required = false, example ="ONCE", allowableValues = {"ONCE", "INTERVAL"})
    private Mode mode = Mode.ONCE;
    
    @Schema(description = "The billing type",  type = "string", required = false, example ="TRANSACTION", allowableValues = {"TRANSACTION", "VOLUME", "BALANCE"})
    private Type type = Type.TRANSACTION;
    
    @Schema(description = "The billing period",  type = "string", required = false, example ="DAY", allowableValues = {"DAY", "WEEK", "MONTH"})
    private Period period = Period.DAY;
    
    @Schema(description = "The billing frequency use for POSTPAID option",  type = "integer", required = false,  example = "1", allowableValues = {"DAY", "WEEK", "MONTH"})
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
	private Set<CommissionServiceDto> commissionsServices;

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

    public Set<CommissionServiceDto> getCommissionsServices() {
        return commissionsServices;
    }

    public void setCommissionsServices(Set<CommissionServiceDto> commissionsServices) {
        this.commissionsServices = commissionsServices;
    }

    
}

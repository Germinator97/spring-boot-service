package com.cinetpay.billing.application.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.cinetpay.billing.application.dtos.billings.service.BillingServiceDto;
import com.cinetpay.billing.application.dtos.commissions.service.CommissionServiceDto;
import com.cinetpay.billing.application.mapper.Mapper;
import com.cinetpay.billing.application.response.ResponseHandler;
import com.cinetpay.billing.domain.billings.service.entity.BillingService;
import com.cinetpay.billing.domain.billings.service.repository.BillingServiceRepository;
import com.cinetpay.billing.domain.commissions.service.entity.CommissionService;
import com.cinetpay.billing.domain.commissions.service.repository.CommissionServiceRepository;
import com.cinetpay.billing.infrastructure.entities.CommissionServiceModel;
import com.cinetpay.billing.infrastructure.enumerations.Mode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping(value = "/commission/service")
@Api( tags = "Commissions Services")
public class BillingServiceController {

    @Autowired
	private Mapper mapper;

	@Autowired
	private BillingServiceRepository billingServiceRepository;

	@Autowired
	private CommissionServiceRepository commissionServiceRepository;

	@ApiOperation(value = "This method is used to get the clients.")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestHeader(name = "vendor", required = true) String vendor, @Valid @RequestBody BillingServiceDto billingServiceDto) {
		try {
			BillingService optionalBillingService = billingServiceRepository.findWithService(vendor, billingServiceDto.getProduct(), billingServiceDto.getCountry(), billingServiceDto.getPartner(), billingServiceDto.getCurrency(), billingServiceDto.getOwner());

			if (optionalBillingService != null) {
				return ResponseHandler.generateResponse(400, false, HttpStatus.FOUND.name(), null, HttpStatus.FOUND);
			}

			BillingService billingService = mapper.mapper(billingServiceDto, BillingService.class);

			billingService.setVendor(vendor);
			billingService.setIsActive(true);

			BillingService billingServiceCreated = billingServiceRepository.create(billingService);

			Set<CommissionServiceDto> commissions = billingServiceDto.getCommissionsServices();

			List<CommissionServiceDto> commissionList = new ArrayList<CommissionServiceDto>(commissions);

			Set<CommissionService> commissionsAdd = new HashSet<CommissionService>();

			System.out.println(commissionList.size());

			for (int i = 0; i < commissionList.size(); i++) {
				CommissionServiceDto commission = commissionList.get(i);
	
				CommissionService commissionServiceExist = commissionServiceRepository.findByInterval(billingServiceCreated, commission.getMin(), commission.getMax());

				if (commissionServiceExist == null) {
					CommissionService commissionService = mapper.mapper(commission, CommissionService.class);
	
					commissionService.setBillingService(billingServiceCreated);
					commissionService.setIsActive(true);
	
					CommissionService commissionServiceCreated =commissionServiceRepository.create(commissionService);

					commissionsAdd.add(commissionService);
	
					if (billingServiceCreated.getMode() == Mode.ONCE) {
						break;
					}
				}
			}

			billingServiceCreated.setCommissionsServices(commissionsAdd);

			return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.CREATED.name(), billingServiceCreated, HttpStatus.OK);
			
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.toString(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
}

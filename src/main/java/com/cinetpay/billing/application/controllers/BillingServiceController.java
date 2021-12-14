package com.cinetpay.billing.application.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import com.cinetpay.billing.application.dtos.billings.service.BillingServiceDto;
import com.cinetpay.billing.application.dtos.billings.service.BillingServiceSearchDto;
import com.cinetpay.billing.application.dtos.commissions.service.CommissionServiceDto;
import com.cinetpay.billing.application.mapper.Mapper;
import com.cinetpay.billing.application.response.ResponseHandler;
import com.cinetpay.billing.domain.billings.service.entity.BillingService;
import com.cinetpay.billing.domain.billings.service.repository.BillingServiceRepository;
import com.cinetpay.billing.domain.commissions.service.entity.CommissionService;
import com.cinetpay.billing.domain.commissions.service.repository.CommissionServiceRepository;
import com.cinetpay.billing.infrastructure.enums.Mode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
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
    @RequestMapping(value = {"/search", "/search/{amount}"}, method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestHeader(name = "vendor", required = true) String vendor, @PathVariable(name = "amount") double amount,  @Valid @RequestBody BillingServiceSearchDto billingServiceSearchDto) {
		try {
			BillingService optionalBillingService = billingServiceRepository.findWithService(vendor, billingServiceSearchDto.getProduct(), billingServiceSearchDto.getCountry(), billingServiceSearchDto.getPartner(), billingServiceSearchDto.getCurrency(), billingServiceSearchDto.getOwner());

			if (optionalBillingService == null) {
				return ResponseHandler.generateResponse(400, false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
			}

			else {
				double commissionFixe = 0;
				double commissionVariable = 0;
				String option = null;

				optionalBillingService.getCommissionsServices().clear();

				CommissionService commissionServiceMerchantExist = null;
	
				if (optionalBillingService.getMode() == Mode.ONCE) {
					commissionServiceMerchantExist = commissionServiceRepository.findForOne(optionalBillingService);
				}
	
				else {
					commissionServiceMerchantExist = commissionServiceRepository.findInInterval(optionalBillingService, amount);
					System.out.println(commissionServiceMerchantExist);
				}
	
				if (commissionServiceMerchantExist == null) {
					BillingService optionalBillingServiceDefault = billingServiceRepository.findWithService(vendor, billingServiceSearchDto.getProduct(), billingServiceSearchDto.getCountry(), billingServiceSearchDto.getPartner(), billingServiceSearchDto.getCurrency(), null);
	
					if (optionalBillingServiceDefault == null) {
						return ResponseHandler.generateResponse(400, false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
					}
	
					else {
						optionalBillingServiceDefault.getCommissionsServices().clear();
	
						CommissionService commissionServiceDefaultExist = commissionServiceRepository.findForOne(optionalBillingServiceDefault);

						if (commissionServiceDefaultExist == null) {
							return ResponseHandler.generateResponse(400, false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
						}

						else {
							option = optionalBillingServiceDefault.getOption().toString();
							commissionFixe = commissionServiceDefaultExist.getCommissionFixe();
							commissionVariable = commissionServiceDefaultExist.getCommissionVariable();
						}
					}
				}

				else {
					option = optionalBillingService.getOption().toString();
					commissionFixe = commissionServiceMerchantExist.getCommissionFixe();
					commissionVariable = commissionServiceMerchantExist.getCommissionVariable();
				}

				Map<String, Object> result  = new HashMap<String, Object>();
				result.put("option", option);
				result.put("commission_fixe", commissionFixe);
				result.put("commission_variable", commissionVariable);

				return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.FOUND.name(), result, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.toString(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    

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
			billingService.getCommissionsServices().clear();

			BillingService billingServiceCreated = billingServiceRepository.create(billingService);

			Set<CommissionServiceDto> commissions = billingServiceDto.getCommissionsServices();

			CommissionServiceDto[] commissionList = commissions.toArray(new CommissionServiceDto[commissions.size()]);

			List<CommissionService> commissionsAdd = new ArrayList<CommissionService>();

			for (int i = 0; i < commissionList.length; i++) {
				if (((billingServiceCreated.getMode() == Mode.ONCE) && (i == 0)) || (billingServiceCreated.getMode() == Mode.INTERVAL)) {
					CommissionServiceDto commission = commissionList[i];
	
					CommissionService commissionServiceExist = commissionServiceRepository.findByInterval(billingServiceCreated, commission.getMin(), commission.getMax());
	
					if (commissionServiceExist == null) {
						CommissionService commissionService = mapper.mapper(commission, CommissionService.class);
	
						if (commissionService.getCommissionVariable() <= 1) {
							commissionService.setBillingService(billingServiceCreated);
							commissionService.setIsActive(true);
			
							CommissionService commissionServiceCreated = commissionServiceRepository.create(commissionService);
	
							commissionServiceCreated.setBillingService(null);
		
							commissionsAdd.add(commissionServiceCreated);
						}
					}
				}
			}

			Set<CommissionService> result = new HashSet<CommissionService>(commissionsAdd);

			billingServiceCreated.setCommissionsServices(result);

			return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.CREATED.name(), billingServiceCreated, HttpStatus.OK);
			
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.toString(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
}

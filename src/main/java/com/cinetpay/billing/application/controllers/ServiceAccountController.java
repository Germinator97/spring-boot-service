package com.cinetpay.billing.application.controllers;

import javax.validation.Valid;

import com.cinetpay.billing.application.dtos.accounts.service.ServiceAccountDto;
import com.cinetpay.billing.application.mapper.Mapper;
import com.cinetpay.billing.application.response.ResponseHandler;
import com.cinetpay.billing.application.utils.NextSequence;
import com.cinetpay.billing.domain.accounts.service.entity.ServiceAccount;
import com.cinetpay.billing.domain.accounts.service.repository.ServiceAccountRepository;
import com.cinetpay.billing.domain.sequence.entity.Sequence;
import com.cinetpay.billing.domain.sequence.repository.SequenceRepository;

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
@RequestMapping(value = "/account/service")
@Api( tags = "Accounts Services")
public class ServiceAccountController {

    @Autowired
	private Mapper mapper;

	@Autowired
	private ServiceAccountRepository serviceAccountRepository;

    @Autowired
	private SequenceRepository sequenceRepository;

    @Autowired
    private NextSequence nextSequence;

    @ApiOperation(value = "This method is used to get the clients.")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestHeader(name = "vendor", required = true) String vendor, @Valid @RequestBody ServiceAccountDto serviceAccountDto) {
		try {
			ServiceAccount optionalServiceAccount = serviceAccountRepository.findWithService(vendor, serviceAccountDto.getProduct(), serviceAccountDto.getCountry(), serviceAccountDto.getCurrency(), serviceAccountDto.getOwner());

			if (optionalServiceAccount != null) {
				return ResponseHandler.generateResponse(HttpStatus.FOUND.value(), false, HttpStatus.FOUND.name(), null, HttpStatus.FOUND);
			}

			ServiceAccount serviceAccount = mapper.mapper(serviceAccountDto, ServiceAccount.class);

            Sequence sequence = sequenceRepository.find();

			String code = sequence.getServiceAccount();

			String account = nextSequence.account(code, "07", serviceAccountDto.getCountry(), serviceAccountDto.getCurrency(), serviceAccountDto.getProduct());

            if (account == null) {
                return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND.value(), false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
            }

            serviceAccount.setVendor(vendor);
            serviceAccount.setAccount(account);
            serviceAccount.setBalance(0.0);
			serviceAccount.setIsBlocked(false);

			ServiceAccount serviceAccountCreated = serviceAccountRepository.create(serviceAccount);

            String nextAccount = nextSequence.nexAccount(sequence, code);

			sequence.setServiceAccount(nextAccount);
			sequenceRepository.create(sequence);


			return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.CREATED.name(), serviceAccountCreated, HttpStatus.OK);
			
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.toString(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
    
}

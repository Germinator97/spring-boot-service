package com.cinetpay.billing.application.controllers;

import javax.validation.Valid;

import com.cinetpay.billing.application.dtos.partner.DeletePartnerDto;
import com.cinetpay.billing.application.dtos.partner.PartnerDto;
import com.cinetpay.billing.application.mapper.Mapper;
import com.cinetpay.billing.application.response.ResponseHandler;
import com.cinetpay.billing.domain.partner.entity.Partner;
import com.cinetpay.billing.domain.partner.repository.PartnerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

@Validated
@RestController
@RequestMapping(value = "/partner")
public class PartnerController {
    
    @Autowired
	private Mapper mapper;

	@Autowired
	private PartnerRepository partnerRepository;

	@RequestMapping(value = {"/find/name", "/find/name/{name}"}, method = RequestMethod.GET)
	public ResponseEntity<Object> findByName(@PathVariable(name = "name") @Parameter(name ="name", schema = @Schema(description = "The partner name",  type = "string", required = true, example ="OM")) String name) {
		try {
			Partner partner = partnerRepository.findByName(name);

			if (partner == null) {
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND.value(), false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
			}

			return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.FOUND.name(), partner, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Object> create(@Valid @RequestBody PartnerDto partnerDto) {
		try {
			Partner optionalPartner = partnerRepository.findByName(partnerDto.getName());

			if (optionalPartner != null) {

				if (!optionalPartner.getIsActive()) {
					optionalPartner.setIsActive(true);

					Partner partner = partnerRepository.update(optionalPartner);
		
					return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.OK.name(), partner, HttpStatus.OK);
				}

				return ResponseHandler.generateResponse(400, false, HttpStatus.FOUND.name(), null, HttpStatus.FOUND);
			}

			Partner data = mapper.mapper(partnerDto, Partner.class);
			data.setIsActive(true);
			Partner country = partnerRepository.create(data);

			return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.CREATED.name(), country, HttpStatus.OK);
			
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.toString(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = {"/update", "/update/{name}"}, method = RequestMethod.POST)
	public ResponseEntity<Object> update(@PathVariable(name = "name") String name, @Valid @RequestBody PartnerDto partnerDto) {
		try {
			Partner exist = partnerRepository.findByName(name);

			if (exist == null) {
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND.value(), false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
			}

			exist.setName(partnerDto.getName());

			Partner partner = partnerRepository.update(exist);

			return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.OK.name(), partner, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = {"/delete", "/delete/{name}"}, method = RequestMethod.POST)
	public ResponseEntity<Object> delete(@PathVariable(name = "name") String name, @Valid @RequestBody DeletePartnerDto partnerDto) {
		try {
			Partner exist = partnerRepository.findByName(name);

			if (exist == null) {
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND.value(), false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
			}

			exist.setIsActive(Boolean.valueOf(partnerDto.getIsActive()));

			Partner partner = partnerRepository.update(exist);

			return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.OK.name(), partner, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

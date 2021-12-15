package com.cinetpay.billing.application.controllers;

import javax.validation.Valid;

import com.cinetpay.billing.application.dtos.currency.CurrencyDto;
import com.cinetpay.billing.application.dtos.currency.CurrencyUpdateDto;
import com.cinetpay.billing.application.mapper.Mapper;
import com.cinetpay.billing.application.response.ResponseHandler;
import com.cinetpay.billing.application.utils.NextSequence;
import com.cinetpay.billing.application.utils.Properties;
import com.cinetpay.billing.domain.currency.entity.Currency;
import com.cinetpay.billing.domain.currency.repository.CurrencyRepository;
import com.cinetpay.billing.domain.sequence.entity.Sequence;
import com.cinetpay.billing.domain.sequence.repository.SequenceRepository;

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

/**
 * @author mac
 *
 */
@Validated
@RestController
@RequestMapping(value = "/currency")
public class CurrencyController {

    @Autowired
	private Mapper mapper;

	@Autowired
	private SequenceRepository sequenceRepository;

	@Autowired
	private CurrencyRepository currencyRepository;

	@Autowired
	private Properties properties;

	@Autowired
	private NextSequence nextSequence;

	@RequestMapping(value = {"/find/name", "/find/name/{name}"}, method = RequestMethod.GET)
	public ResponseEntity<Object> findByName(@PathVariable(name = "name") @Parameter(name ="name", schema = @Schema(description = "The currency name", type = "string", required = true, example ="XOF")) String name) {
		try {
			Currency currency = currencyRepository.findByName(name);

			if (currency == null) {
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND.value(), false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
			}

			return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.FOUND.name(), currency, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Object> create(@Valid @RequestBody CurrencyDto currencyDto) {
		try {
			Currency optionalCurrency = currencyRepository.findByName(currencyDto.getName());

			if (optionalCurrency != null) {

				if (!optionalCurrency.getIsActive()) {
					optionalCurrency.setIsActive(true);

					Currency currency = currencyRepository.create(optionalCurrency);
		
					return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.OK.name(), currency, HttpStatus.OK);
				}

				return ResponseHandler.generateResponse(400, false, HttpStatus.FOUND.name(), null, HttpStatus.FOUND);
			}

			Sequence sequence = sequenceRepository.find();

			String code = sequence.getCurrency();

			Currency data = mapper.mapper(currencyDto, Currency.class);
			data.setCode(code);
			data.setIsActive(true);
			Currency currency = currencyRepository.create(data);

			String nextCode = nextSequence.nexCode(sequence, code);

			sequence.setCurrency(nextCode);
			sequenceRepository.create(sequence);

			return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.CREATED.name(), currency, HttpStatus.OK);
			
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.toString(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = {"/update", "/update/{name}"}, method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@PathVariable(name = "name") String name, @Valid @RequestBody CurrencyUpdateDto currencyUpdateDto) {
		try {
			Currency exist = currencyRepository.findByName(name);

			if (exist == null) {
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND.value(), false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
			}

			properties.copyNonNullProperties(currencyUpdateDto, exist);

			if (currencyUpdateDto.check()) {
				exist.setIsActive(Boolean.valueOf(currencyUpdateDto.getIsActive()));
			}

			Currency currency = currencyRepository.create(exist);

			return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.OK.name(), currency, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
}

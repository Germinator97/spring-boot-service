/**
 * 
 */
package com.cinetpay.billing.application.controllers;

import javax.validation.Valid;

import com.cinetpay.billing.application.dtos.country.CountryDto;
import com.cinetpay.billing.application.dtos.country.CountryUpdateDto;
import com.cinetpay.billing.application.mapper.Mapper;
import com.cinetpay.billing.application.response.ResponseHandler;
import com.cinetpay.billing.application.utils.KafkaProducer;
import com.cinetpay.billing.application.utils.NextSequence;
import com.cinetpay.billing.application.utils.Properties;
import com.cinetpay.billing.domain.country.entity.Country;
import com.cinetpay.billing.domain.country.repository.CountryRepository;
import com.cinetpay.billing.domain.sequence.entity.Sequence;
import com.cinetpay.billing.domain.sequence.repository.SequenceRepository;

import org.json.JSONObject;
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

import java.util.Map;

/**
 * @author mac
 *
 */
@Validated
@RestController
@RequestMapping(value = "/country")
public class CountryController {

	private final KafkaProducer kafkaProducer;

	public CountryController(KafkaProducer kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}

    @Autowired
	private Mapper mapper;

	@Autowired
	private SequenceRepository sequenceRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private Properties properties;

	@Autowired
	private NextSequence nextSequence;

	@RequestMapping(value = {"/find/name", "/find/name/{name}"}, method = RequestMethod.GET)
	public ResponseEntity<Object> findByName(@PathVariable(name = "name") @Parameter(name ="name", schema = @Schema(description = "The country name ISO2", type = "string", required = true, example ="CI")) String name) {
		try {
			Country country = countryRepository.findByName(name);

			if (country == null) {
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND.value(), false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
			}

			return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.FOUND.name(), country, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Object> create(@Valid @RequestBody CountryDto countryDto) {
		try {
			Country optionalCountry = countryRepository.findByName(countryDto.getName());

			if (optionalCountry != null) {

				if (!optionalCountry.getIsActive()) {
					optionalCountry.setIsActive(true);

					Country country = countryRepository.create(optionalCountry);
		
					return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.OK.name(), country, HttpStatus.OK);
				}

				return ResponseHandler.generateResponse(400, false, HttpStatus.FOUND.name(), null, HttpStatus.FOUND);
			}

			Sequence sequence = sequenceRepository.find();

			String code = sequence.getCountry();

			Country data = mapper.mapper(countryDto, Country.class);
			data.setCode(code);
			data.setIsActive(true);
			Country country = countryRepository.create(data);

			String nextCode = nextSequence.nexCode(sequence, code);

			sequence.setCountry(nextCode);
			sequenceRepository.create(sequence);

			this.kafkaProducer.sendMessage(country.getCode()+"+"+country.getName());

			return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.CREATED.name(), country, HttpStatus.OK);
			
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.toString(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = {"/update", "/update/{name}"}, method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@PathVariable(name = "name") String name, @Valid @RequestBody CountryUpdateDto countryUpdateDto) {
		try {
			Country exist = countryRepository.findByName(name);

			if (exist == null) {
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND.value(), false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
			}

			properties.copyNonNullProperties(countryUpdateDto, exist);

			if (countryUpdateDto.check()) {
				exist.setIsActive(Boolean.valueOf(countryUpdateDto.getIsActive()));
			}

			Country country = countryRepository.create(exist);

			return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.OK.name(), country, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

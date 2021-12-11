/**
 * 
 */
package com.cinetpay.billing.application.controllers;

import java.util.Optional;

import javax.validation.Valid;

import com.cinetpay.billing.application.SequenceRepository;
import com.cinetpay.billing.application.dtos.country.CountryDto;
import com.cinetpay.billing.application.dtos.country.DeleteCountryDto;
import com.cinetpay.billing.application.mapper.Mapper;
import com.cinetpay.billing.application.response.ResponseHandler;
import com.cinetpay.billing.domain.country.entities.Country;
import com.cinetpay.billing.domain.country.repositories.CountryRepository;
import com.cinetpay.billing.models.Sequence;

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
@RequestMapping(value = "/country")
public class CountryController {

    @Autowired
	private Mapper mapper;

	@Autowired
	private SequenceRepository sequenceRepository;

	@Autowired
	private CountryRepository countryRepository;
 
	@RequestMapping(value = {"/find/code", "/find/code/{code}"}, method = RequestMethod.GET)
	public ResponseEntity<Object> findByCode(@PathVariable(name = "code") String code) {
		try {
			Country country = countryRepository.findByCode(code);

			if (country == null) {
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND.value(), false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
			}
			return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.FOUND.name(), country, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = {"/find/name", "/find/name/{name}"}, method = RequestMethod.GET)
	public ResponseEntity<Object> findByName(@PathVariable(name = "name") @Parameter(name ="name", schema = @Schema(description = "The country name ISO2",  type = "string", required = true, example ="CI")) String name) {
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

					Country country = countryRepository.update(optionalCountry);
		
					return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.OK.name(), country, HttpStatus.OK);
				}

				return ResponseHandler.generateResponse(400, false, HttpStatus.FOUND.name(), null, HttpStatus.FOUND);
			}

			Optional<Sequence> sequence = sequenceRepository.findById(1);

			String code = sequence.get().getCountry();

			if (sequence.isPresent()) {
				Country data = mapper.mapper(countryDto, Country.class);
				data.generateId();
				data.passCode(code);
				data.setIsActive(true);
				Country country = countryRepository.create(data);

				String[] array = code.split("\\.");
				String prefix = array[0];
				String suffix = array[1];
				Integer newSuffix = Integer.parseInt(suffix) + 1;
				String nextCoce = prefix + "." + newSuffix.toString();

				sequence.get().setCountry(nextCoce);
				sequenceRepository.save(sequence.get());

				return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.CREATED.name(), country, HttpStatus.OK);
			}

			else {
				return ResponseHandler.generateResponse(400, false, HttpStatus.FOUND.name(), null, HttpStatus.FOUND);
			}

			
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.toString(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = {"/update", "/update/{name}"}, method = RequestMethod.POST)
	public ResponseEntity<Object> update(@PathVariable(name = "name") String name, @Valid @RequestBody CountryDto countryDto) {
		try {
			Country exist = countryRepository.findByName(name);

			if (exist == null) {
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND.value(), false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
			}

			exist.setName(countryDto.getName());

			Country country = countryRepository.update(exist);

			return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.OK.name(), country, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = {"/delete", "/delete/{name}"}, method = RequestMethod.POST)
	public ResponseEntity<Object> delete(@PathVariable(name = "name") String name, @Valid @RequestBody DeleteCountryDto countryDto) {
		try {
			Country exist = countryRepository.findByName(name);

			if (exist == null) {
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND.value(), false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
			}

			exist.setIsActive(Boolean.valueOf(countryDto.getIsActive()));

			Country country = countryRepository.update(exist);

			return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.OK.name(), country, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

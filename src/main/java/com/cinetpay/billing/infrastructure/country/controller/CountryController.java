/**
 * 
 */
package com.cinetpay.billing.infrastructure.country.controller;

import java.util.Optional;

import javax.validation.Valid;

import com.cinetpay.billing.configurations.ResponseHandler;
import com.cinetpay.billing.configurations.SequenceRepository;
import com.cinetpay.billing.database.country.mapper.CountryOutMapper;
import com.cinetpay.billing.entities.Country;
import com.cinetpay.billing.infrastructure.country.dto.CountryDto;
import com.cinetpay.billing.models.Sequence;
import com.cinetpay.billing.use_cases.country.CreateCountry;
import com.cinetpay.billing.use_cases.country.FindCountryByCode;
import com.cinetpay.billing.use_cases.country.FindCountryByName;
import com.cinetpay.billing.use_cases.country.UpdateCountry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mac
 *
 */
@Validated
@RestController
@RequestMapping(value = "/country")
public class CountryController {

	@Autowired
	private FindCountryByCode findCountryByCode;

	@Autowired
	private FindCountryByName findCountryByName;

	@Autowired
	private CreateCountry createCountry;

	@Autowired
	private SequenceRepository sequenceRepository;

	@Autowired
	private UpdateCountry updateCountry;
 
	@RequestMapping(value = {"/find/code", "/find/code/{code}"}, method = RequestMethod.GET)
	public ResponseEntity<Object> findByCode(@PathVariable(name = "code") String code) {
		try {
			Country country = findCountryByCode.find(code);

				if (country == null) {
					return ResponseHandler.generateResponse(404, false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
				}
				return ResponseHandler.generateResponse(200, true,  HttpStatus.FOUND.name(), CountryOutMapper.toDto(country), HttpStatus.OK);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(500, false,  e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = {"/find/name", "/find/name/{name}"}, method = RequestMethod.GET)
	public ResponseEntity<Object> findByName(@PathVariable(name = "name") String name) {
		try {
			Country country = findCountryByName.find(name);

				if (country == null) {
					return ResponseHandler.generateResponse(404, false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
				}
				return ResponseHandler.generateResponse(200, true,  HttpStatus.FOUND.name(), CountryOutMapper.toDto(country), HttpStatus.OK);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(500, false,  e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Object> create(@Valid @RequestBody CountryDto countryDto) {
		try {
			Country optionalCountry = findCountryByName.find(countryDto.getName());

			if (optionalCountry != null) {

				if (!optionalCountry.getIs_active()) {
					optionalCountry.setIs_active(true);

					Country country = updateCountry.update(optionalCountry);
		
					return ResponseHandler.generateResponse(200, true,  HttpStatus.OK.name(), CountryOutMapper.toDto(country), HttpStatus.OK);
				}

				return ResponseHandler.generateResponse(400, false, HttpStatus.FOUND.name(), null, HttpStatus.FOUND);
			}

			Optional<Sequence> sequence = sequenceRepository.findById(1);

			String code = sequence.get().getCountry();

			if (sequence.isPresent()) {
				Country country = createCountry.create(countryDto, code);


				String[] array = code.split("\\.");
				String prefix = array[0];
				String suffix = array[1];
				Integer newSuffix = Integer.parseInt(suffix) + 1;
				String nextCoce = prefix + "." + newSuffix.toString();

				sequence.get().setCountry(nextCoce);
				sequenceRepository.save(sequence.get());

				return ResponseHandler.generateResponse(200, true,  HttpStatus.CREATED.name(), CountryOutMapper.toDto(country), HttpStatus.OK);
			}

			else {
				return ResponseHandler.generateResponse(400, false, HttpStatus.FOUND.name(), null, HttpStatus.FOUND);
			}

			
		} catch (Exception e) {
			return ResponseHandler.generateResponse(500, false,  e.toString(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = {"/update", "/update/{name}"}, method = RequestMethod.POST)
	public ResponseEntity<Object> update(@PathVariable(name = "name") String name, @Valid @RequestBody CountryDto countryDto) {
		try {
			Country exist = findCountryByName.find(name);

			if (exist == null) {
				return ResponseHandler.generateResponse(404, false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
			}

			exist.setName(countryDto.getName());

			Country country = updateCountry.update(exist);

			return ResponseHandler.generateResponse(200, true,  HttpStatus.OK.name(), CountryOutMapper.toDto(country), HttpStatus.OK);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(500, false,  e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = {"/delete", "/delete/{name}"}, method = RequestMethod.POST)
	public ResponseEntity<Object> delete(@PathVariable(name = "name") String name, @Valid @RequestBody CountryDto countryDto) {
		try {
			Country exist = findCountryByName.find(name);

			if (exist == null) {
				return ResponseHandler.generateResponse(404, false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
			}

			exist.setIs_active(countryDto.getIs_active());

			Country country = updateCountry.update(exist);

			return ResponseHandler.generateResponse(200, true,  HttpStatus.OK.name(), CountryOutMapper.toDto(country), HttpStatus.OK);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(500, false,  e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

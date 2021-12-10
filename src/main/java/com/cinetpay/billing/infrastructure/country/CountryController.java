/**
 * 
 */
package com.cinetpay.billing.infrastructure.country;

import java.util.Optional;

import javax.validation.Valid;

import com.cinetpay.billing.configuration.ResponseHandler;
import com.cinetpay.billing.database.country.mapper.CountryOutMapper;
import com.cinetpay.billing.entities.Country;
import com.cinetpay.billing.infrastructure.country.dto.CountryDto;
import com.cinetpay.billing.use_cases.country.CreateCountry;
import com.cinetpay.billing.use_cases.country.FindCountryByCode;
import com.cinetpay.billing.use_cases.country.FindCountryByName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	// @Autowired
	// private CreateCountry createCountry;
 
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

	@PostMapping("/create")
	public ResponseEntity<Object> create(@Valid @RequestBody CountryDto countryDto) {
		try {
			return ResponseHandler.generateResponse(400, false, HttpStatus.FOUND.name(), null, HttpStatus.FOUND);

			// Country optionalCountry = findCountryByName.find(countryDto.getName());

			// if (optionalCountry != null) {
			// 	return ResponseHandler.generateResponse(400, false, HttpStatus.FOUND.name(), null, HttpStatus.FOUND);
			// }

			// Country country = createCountry.create(countryDto);

			// return ResponseHandler.generateResponse(200, true,  HttpStatus.FOUND.name(), CountryOutMapper.toDto(country), HttpStatus.OK);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(500, false,  e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

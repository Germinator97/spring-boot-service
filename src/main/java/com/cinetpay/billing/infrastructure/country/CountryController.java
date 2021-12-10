/**
 * 
 */
package com.cinetpay.billing.infrastructure.country;

import java.util.Optional;

import com.cinetpay.billing.configuration.ResponseHandler;
import com.cinetpay.billing.database.country.mapper.CountryOutMapper;
import com.cinetpay.billing.entities.Country;
import com.cinetpay.billing.use_cases.country.FindCountryByCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
 
	@GetMapping("/find/{code}")
	public ResponseEntity<Object> findByCode(@PathVariable("code") Optional<String> code) {
		if (!code.isPresent()) {
			return ResponseHandler.generateResponse(400, false, HttpStatus.BAD_REQUEST.getReasonPhrase(), null, HttpStatus.BAD_REQUEST);
		}

		try {
			Country country = findCountryByCode.find(code.get());

			if (country == null) {
				return ResponseHandler.generateResponse(404, false, HttpStatus.NOT_FOUND.getReasonPhrase(), null, HttpStatus.NOT_FOUND);
			}
			return ResponseHandler.generateResponse(200, true,  HttpStatus.FOUND.getReasonPhrase(), CountryOutMapper.toDto(country), HttpStatus.OK);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(500, false,  e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/find")
	public ResponseEntity<Object> findByCodeNull() {
		return ResponseHandler.generateResponse(400, false, HttpStatus.BAD_REQUEST.getReasonPhrase(), null, HttpStatus.BAD_REQUEST);
	}

}

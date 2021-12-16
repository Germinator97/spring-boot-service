package com.cinetpay.billing.application.controllers;

import javax.validation.Valid;

import com.cinetpay.billing.application.dtos.product.ProductUpdateDto;
import com.cinetpay.billing.application.dtos.product.ProductDto;
import com.cinetpay.billing.application.mapper.Mapper;
import com.cinetpay.billing.application.response.ResponseHandler;
import com.cinetpay.billing.application.utils.NextSequence;
import com.cinetpay.billing.application.utils.Properties;
import com.cinetpay.billing.domain.product.entity.Product;
import com.cinetpay.billing.domain.product.repository.ProductRepository;
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

@Validated
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
	private Mapper mapper;

	@Autowired
	private SequenceRepository sequenceRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private Properties properties;

	@Autowired
	private NextSequence nextSequence;

	@RequestMapping(value = {"/find/name", "/find/name/{name}"}, method = RequestMethod.GET)
	public ResponseEntity<Object> findByName(@PathVariable(name = "name") @Parameter(name ="name", schema = @Schema(description = "The product name",  type = "string", required = true, example ="PAYIN")) String name) {
		try {
			Product product = productRepository.findByName(name);

			if (product == null) {
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND.value(), false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
			}

			return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.FOUND.name(), product, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Object> create(@Valid @RequestBody ProductDto productDto) {
		try {
			Product optionalProduct = productRepository.findByName(productDto.getName());

			if (optionalProduct != null) {

				if (!optionalProduct.getIsActive()) {
					optionalProduct.setIsActive(true);

					Product product = productRepository.create(optionalProduct);
		
					return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.OK.name(), product, HttpStatus.OK);
				}

				return ResponseHandler.generateResponse(400, false, HttpStatus.FOUND.name(), null, HttpStatus.FOUND);
			}

			Sequence sequence = sequenceRepository.find();

			String code = sequence.getProduct();

			Product data = mapper.mapper(productDto, Product.class);
			data.setCode(code);
			data.setIsActive(true);
			Product product = productRepository.create(data);

			String nextCode = nextSequence.nexCode(sequence, code);

			sequence.setProduct(nextCode);
			sequenceRepository.create(sequence);

			return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.CREATED.name(), product, HttpStatus.OK);
			
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.toString(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = {"/update", "/update/{name}"}, method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@PathVariable(name = "name") String name, @Valid @RequestBody ProductUpdateDto productUpdateDto) {
		try {
			Product exist = productRepository.findByName(name);

			if (exist == null) {
				return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND.value(), false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
			}

			properties.copyNonNullProperties(productUpdateDto, exist);

			if (productUpdateDto.check()) {
				exist.setIsActive(Boolean.valueOf(productUpdateDto.getIsActive()));
			}

			Product product = productRepository.create(exist);

			return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.OK.name(), product, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
 
}

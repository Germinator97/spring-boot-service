package com.cinetpay.billing.application.controllers;



import com.cinetpay.billing.application.dtos.currency.CurrencyDto;
import com.cinetpay.billing.application.dtos.currency.DeleteCurrencyDto;
import com.cinetpay.billing.application.mapper.Mapper;
import com.cinetpay.billing.application.response.ResponseHandler;
import com.cinetpay.billing.domain.currency.entity.Currency;
import com.cinetpay.billing.domain.currency.repository.CurrencyRepository;
import com.cinetpay.billing.domain.sequence.entity.Sequence;
import com.cinetpay.billing.domain.sequence.repository.SequenceRepository;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(value = "/currency")
public class CurrencyController {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private SequenceRepository sequenceRepository;

    @Autowired
    private Mapper mapper;

    @RequestMapping(value = {"/find/code", "/find/code/{code}"}, method = RequestMethod.GET)
    public ResponseEntity<Object> findByCode(@PathVariable(name = "code") String code)
    {
        try {
            Currency currency = currencyRepository.findByCode(code);
            if (currency == null){
                return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND.value(), false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
            }else {
                return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.FOUND.name(), currency, HttpStatus.OK);
            }
        }catch (Exception e){
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = {"/find/name", "/find/name/{name}"}, method = RequestMethod.GET)
    public ResponseEntity<Object> findByName(@PathVariable(name = "name") @Parameter(name ="name", schema = @Schema(description = "The currency name",  type = "string", required = true, example ="XOF")) String name) {
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
            //get currency by name
            Currency optionalCurrency = currencyRepository.findByName(currencyDto.getName());

            if (optionalCurrency != null) { //if currency exist
                if (!optionalCurrency.getIsActive()) { //if currency is disabled
                    System.out.println(optionalCurrency);
                    optionalCurrency.setIsActive(true); //enable currency

                    Currency currency = currencyRepository.update(optionalCurrency); //update new currency status

                    //return endpoint response
                    return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.OK.name(), currency, HttpStatus.OK);
                }

                //currency already exists
                return ResponseHandler.generateResponse(400, false, HttpStatus.FOUND.name(), null, HttpStatus.FOUND);
            }

            Sequence sequence = sequenceRepository.find();

            String code = sequence.getCurrency(); //get last currency code

            Currency data = mapper.mapper(currencyDto, Currency.class);
            data.generateId();
            data.passCode(code);
            data.setIsActive(true);
            Currency currency = currencyRepository.create(data); //save new currency

            String[] array = code.split("\\.");
            String prefix = array[0];
            String suffix = array[1];
            int newSuffix = Integer.parseInt(suffix) + 1;
            String nextCode = prefix + "." + newSuffix;

            sequence.setCurrency(nextCode);
            sequenceRepository.update(sequence);

            return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.CREATED.name(), currency, HttpStatus.OK);

        } catch (Exception e) {
            //return response with exception message
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.toString(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = {"/update", "/update/{name}"}, method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@PathVariable(name = "name") String name, @Valid @RequestBody CurrencyDto currencyDto) {
        try {
            Currency exist = currencyRepository.findByName(name);

            if (exist == null) {
                return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND.value(), false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
            }

            exist.setName(currencyDto.getName());

            Currency currency = currencyRepository.update(exist);

            return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.OK.name(), currency, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = {"/delete", "/delete/{name}"}, method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable(name = "name") String name, @Valid @RequestBody DeleteCurrencyDto deleteCurrencyDto) {
        try {
            Currency exist = currencyRepository.findByName(name);

            System.out.println(exist);
            if (exist == null) {
                return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND.value(), false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
            }

            exist.setIsActive(Boolean.valueOf(deleteCurrencyDto.getIsActive()));

            Currency currency = currencyRepository.update(exist);

            return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.OK.name(), currency, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
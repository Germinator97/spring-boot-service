package com.cinetpay.billing.application.controllers;




import com.cinetpay.billing.application.dtos.accounts.vendor.VendorAccountDto;
import com.cinetpay.billing.application.dtos.accounts.vendor.UpdateVendorAccountDto;
import com.cinetpay.billing.application.mapper.Mapper;
import com.cinetpay.billing.application.response.ResponseHandler;
import com.cinetpay.billing.application.utils.Properties;
import com.cinetpay.billing.domain.accounts.vendor.entity.VendorAccount;
import com.cinetpay.billing.domain.accounts.vendor.repository.VendorAccountRepository;
import com.cinetpay.billing.domain.sequence.entity.Sequence;
import com.cinetpay.billing.domain.sequence.repository.SequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(value = "/vendor/account")
public class VendorAccountController {

    @Autowired
    private VendorAccountRepository vendorAccountRepository;

    @Autowired
    private SequenceRepository sequenceRepository;

    @Autowired
    private Mapper mapper;

    private final Properties properties = new Properties();

    @RequestMapping(value = {"/{account}"}, method = RequestMethod.GET)
    public ResponseEntity<Object> findByCode(@PathVariable(name = "account") String account)
    {
        try {
            System.out.println(account);
            VendorAccount vendorAccount = vendorAccountRepository.findByAccount(account);
            if (vendorAccount == null){
                return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND.value(), false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
            }else {
                return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.FOUND.name(), vendorAccount, HttpStatus.OK);
            }
        }catch (Exception e){
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestHeader(name = "vendor", required = true) String vendor, @Valid @RequestBody VendorAccountDto vendorAccountDto) {
        try {
            VendorAccount optionalVendorAccount = vendorAccountRepository.findByAccount(vendorAccountDto.getAccount());
            if (optionalVendorAccount != null) {
                if (optionalVendorAccount.getBlocked()) {
                    optionalVendorAccount.setBlocked(false);

                    VendorAccount vendorAccount = vendorAccountRepository.update(optionalVendorAccount);

                    return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.OK.name(), vendorAccount, HttpStatus.OK);
                }

                return ResponseHandler.generateResponse(400, false, HttpStatus.FOUND.name(), null, HttpStatus.FOUND);
            }

            Sequence sequence = sequenceRepository.find();

            String code = sequence.getVendorAccount();

            VendorAccount data = mapper.mapper(vendorAccountDto, VendorAccount.class);
            data.setAccount(code);
            data.setVendor(vendor);
            data.setBlocked(false);
            data.setBalance(0.0);
            VendorAccount vendorAccount = vendorAccountRepository.create(data);

            String[] array = code.split("\\.");
            String prefix = array[0];
            String suffix = array[1];
            int newSuffix = Integer.parseInt(suffix) + 1;
            String nextCode = prefix + "." + newSuffix;

            sequence.setVendorAccount(nextCode);
            sequenceRepository.create(sequence);
            return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.CREATED.name(), vendorAccount, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.toString(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = {"/update", "/update/{account}"}, method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@PathVariable(name = "account") String account, @Valid @RequestBody UpdateVendorAccountDto vendorAccountDto) {
        try {
            VendorAccount exist = vendorAccountRepository.findByAccount(account);

            if (exist == null){
                return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND.value(), false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
            }
            properties.copyNonNullProperties(vendorAccountDto, exist);
            VendorAccount vendorAccount = vendorAccountRepository.create(exist);
            return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.OK.name(), vendorAccount, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
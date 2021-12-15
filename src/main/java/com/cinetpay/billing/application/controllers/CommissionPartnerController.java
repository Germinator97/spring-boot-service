package com.cinetpay.billing.application.controllers;




import com.cinetpay.billing.application.dtos.commissions.partner.CommissionPartnerDto;
import com.cinetpay.billing.application.dtos.commissions.partner.UpdateCommissionPartnerDto;
import com.cinetpay.billing.application.mapper.Mapper;
import com.cinetpay.billing.application.response.ResponseHandler;
import com.cinetpay.billing.application.utils.Properties;
import com.cinetpay.billing.domain.commissions.partner.entity.CommissionPartner;
import com.cinetpay.billing.domain.commissions.partner.repository.CommissionPartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(value = "/commission/partner")
public class CommissionPartnerController {

    @Autowired
    private CommissionPartnerRepository commissionPartnerRepository;

    @Autowired
    private Mapper mapper;

    private final Properties properties = new Properties();

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ResponseEntity<Object> findByCode(
            @RequestParam(name = "vendor") String vendor,
            @RequestParam(name = "product") String product,
            @RequestParam(name = "currency") String currency,
            @RequestParam(name = "partner") String partner,
            @RequestParam(name = "country") String country)
    {
        try {
            CommissionPartner commissionPartner = commissionPartnerRepository.findByVendorAndProductAndCurrencyAndPartnerAndCountry(vendor, product, currency,partner, country);
            if (commissionPartner == null){
                return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND.value(), false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
            }else {
                return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.FOUND.name(), commissionPartner, HttpStatus.OK);
            }
        }catch (Exception e){
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestHeader(name = "vendor", required = true) String vendor, @Valid @RequestBody CommissionPartnerDto commissionPartnerDto) {
        try {
            CommissionPartner OptionalCommissionPartner = commissionPartnerRepository.findByVendorAndProductAndCurrencyAndPartnerAndCountry(commissionPartnerDto.getVendor(),commissionPartnerDto.getProduct(),commissionPartnerDto.getCurrency(),commissionPartnerDto.getPartner(),commissionPartnerDto.getCountry());

            if (OptionalCommissionPartner != null) {

                if (!OptionalCommissionPartner.getActive()) {
                    OptionalCommissionPartner.setActive(true);

                    CommissionPartner commissionPartner = commissionPartnerRepository.update(OptionalCommissionPartner);

                    return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.OK.name(), commissionPartner, HttpStatus.OK);
                }
                return ResponseHandler.generateResponse(400, false, HttpStatus.FOUND.name(), null, HttpStatus.FOUND);
            }

            CommissionPartner data = mapper.mapper(commissionPartnerDto, CommissionPartner.class);
            data.setVendor(vendor);
            CommissionPartner commissionPartner = commissionPartnerRepository.create(data);
            return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.CREATED.name(), commissionPartner, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.toString(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = {"/update", "/update/{id}"}, method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@RequestHeader(name = "vendor", required = true) String vendor, @PathVariable(name = "id") String id, @Valid @RequestBody UpdateCommissionPartnerDto commissionPartnerDto) {
        try {
            CommissionPartner exist = commissionPartnerRepository.findById(id);
            if (exist == null) {
                return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND.value(), false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
            }
            commissionPartnerDto.setVendor(vendor);
            System.out.println(exist.getCommissionFixe());
            System.out.println(exist.getCountry());
            System.out.println(exist.getCurrency());
            properties.copyNonNullProperties(commissionPartnerDto, exist);
            CommissionPartner commissionPartner = commissionPartnerRepository.create(exist);
            return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.OK.name(), commissionPartner, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
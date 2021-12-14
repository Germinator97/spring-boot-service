package com.cinetpay.billing.application.controllers;




import com.cinetpay.billing.application.dtos.commissions.partner.CommissionPartnerDto;
import com.cinetpay.billing.application.mapper.Mapper;
import com.cinetpay.billing.application.response.ResponseHandler;
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
    public ResponseEntity<Object> create(@Valid @RequestBody CommissionPartnerDto commissionPartnerDto) {
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
            data.generateId();
            data.setActive(commissionPartnerDto.getActive());
            data.setCommissionFixe(commissionPartnerDto.getCommissionFixe());
            data.setCommissionVariable(commissionPartnerDto.getCommissionVariable());
            data.setPartner(commissionPartnerDto.getPartner());
            data.setCountry(commissionPartnerDto.getCountry());
            data.setCurrency(commissionPartnerDto.getCurrency());
            data.setProduct(commissionPartnerDto.getProduct());
            CommissionPartner commissionPartner = commissionPartnerRepository.create(data);
            return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.CREATED.name(), commissionPartner, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.toString(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*@RequestMapping(value = {"/update/{id}"}, method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@PathVariable(name = "id") String id, @Valid @RequestBody CommissionPartnerDto commissionPartnerDto) {
        try {
            CommissionPartner exist = commissionPartnerRepository.findById(id);
            if (exist == null) {
                return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND.value(), false, HttpStatus.NOT_FOUND.name(), null, HttpStatus.NOT_FOUND);
            }

            exist.setId(id);
            exist.setActive(commissionPartnerDto.getActive());
            exist.setCommissionFixe(commissionPartnerDto.getCommissionFixe());
            exist.setCommissionVariable(commissionPartnerDto.getCommissionVariable());
            exist.setPartner(commissionPartnerDto.getPartner());
            exist.setCountry(commissionPartnerDto.getCountry());
            exist.setCurrency(commissionPartnerDto.getCurrency());
            exist.setProduct(commissionPartnerDto.getProduct());
            exist.setVendor(commissionPartnerDto.getVendor());
            CommissionPartner commissionPartner = commissionPartnerRepository.update(exist);

            return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.OK.name(), commissionPartner, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
}
package com.cinetpay.billing.application.controllers;




import com.cinetpay.billing.application.dtos.accounts.vendor.VendorAccountDto;
import com.cinetpay.billing.application.dtos.commissions.partner.CommissionPartnerDto;
import com.cinetpay.billing.application.mapper.Mapper;
import com.cinetpay.billing.application.response.ResponseHandler;
import com.cinetpay.billing.domain.accounts.vendor.entity.VendorAccount;
import com.cinetpay.billing.domain.accounts.vendor.repository.VendorAccountRepository;
import com.cinetpay.billing.domain.commissions.partner.entity.CommissionPartner;
import com.cinetpay.billing.domain.commissions.partner.repository.CommissionPartnerRepository;
import com.cinetpay.billing.domain.country.entity.Country;
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
    public ResponseEntity<Object> create(@Valid @RequestBody VendorAccountDto vendorAccountDto) {
        try {
            VendorAccount optionalVendorAccount = vendorAccountRepository.findByAccount(vendorAccountDto.getAccount());

            if (optionalVendorAccount != null) {
                if (!optionalVendorAccount.getBlocked()) {
                    optionalVendorAccount.setBlocked(true);

                    VendorAccount vendorAccount = vendorAccountRepository.update(optionalVendorAccount);

                    return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.OK.name(), vendorAccount, HttpStatus.OK);
                }

                return ResponseHandler.generateResponse(400, false, HttpStatus.FOUND.name(), null, HttpStatus.FOUND);
            }

            Sequence sequence = sequenceRepository.find();

            String code = sequence.getVendorAccount();

            VendorAccount data = mapper.mapper(vendorAccountDto, VendorAccount.class);
           /* data.setAccount(code);
            data.setBlocked(true);
            Country country = countryRepository.create(data);

            String[] array = code.split("\\.");
            String prefix = array[0];
            String suffix = array[1];
            Integer newSuffix = Integer.parseInt(suffix) + 1;
            String nextCoce = prefix + "." + newSuffix.toString();

            sequence.setCountry(nextCoce);
            sequenceRepository.update(sequence);*/


            /*CommissionPartner data = mapper.mapper(commissionPartnerDto, CommissionPartner.class);
            data.generateId();
            data.setActive(commissionPartnerDto.getActive());
            data.setCommissionFixe(commissionPartnerDto.getCommissionFixe());
            data.setCommissionVariable(commissionPartnerDto.getCommissionVariable());
            data.setPartner(commissionPartnerDto.getPartner());
            data.setCountry(commissionPartnerDto.getCountry());
            data.setCurrency(commissionPartnerDto.getCurrency());
            data.setProduct(commissionPartnerDto.getProduct());
            CommissionPartner commissionPartner = commissionPartnerRepository.create(data);*/
            //return ResponseHandler.generateResponse(HttpStatus.OK.value(), true,  HttpStatus.CREATED.name(), commissionPartner, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,  e.toString(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }
}
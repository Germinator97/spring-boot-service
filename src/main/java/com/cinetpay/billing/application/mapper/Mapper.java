package com.cinetpay.billing.application.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class Mapper {

    @Autowired
    private ModelMapper modelMapper;

    /**
   * @param <D> destination type
   * @param source object to map from
   * @param destinationType type to map to
   */
    public <D> D mapper(Object source,  Class<D> destinationType) {
        if (source == null) {
            return null;
        }
        return modelMapper.map(source, destinationType);
    }

}

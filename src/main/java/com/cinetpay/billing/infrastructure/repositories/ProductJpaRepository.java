package com.cinetpay.billing.infrastructure.repositories;

import com.cinetpay.billing.infrastructure.entities.ProductModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductModel, String> {

    public ProductModel findByName(String name);
    
}

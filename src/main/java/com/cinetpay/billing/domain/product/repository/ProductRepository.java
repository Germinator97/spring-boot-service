package com.cinetpay.billing.domain.product.repository;

import com.cinetpay.billing.domain.product.entity.Product;

public interface ProductRepository {

    public Product findByName(String name);

	public Product create(Product product);
    
}

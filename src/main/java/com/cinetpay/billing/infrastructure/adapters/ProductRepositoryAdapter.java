package com.cinetpay.billing.infrastructure.adapters;

import com.cinetpay.billing.application.mapper.Mapper;
import com.cinetpay.billing.domain.product.entity.Product;
import com.cinetpay.billing.domain.product.repository.ProductRepository;
import com.cinetpay.billing.infrastructure.entities.ProductModel;
import com.cinetpay.billing.infrastructure.repositories.ProductJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductRepositoryAdapter implements ProductRepository {

    @Autowired
    private ProductJpaRepository productJpaRepository;

    @Autowired
	private Mapper mapper;

    @Override
    public Product findByName(String name) {
        ProductModel country = productJpaRepository.findByName(name);
        return mapper.mapper(country, Product.class);
    }

    @Override
    public Product create(Product data) {
        ProductModel model = mapper.mapper(data, ProductModel.class);
        ProductModel product = productJpaRepository.save(model);
        return mapper.mapper(product, Product.class);
    }

    @Override
    public Product update(Product data) {
        ProductModel model = mapper.mapper(data, ProductModel.class);
        ProductModel product = productJpaRepository.save(model);
        return mapper.mapper(product, Product.class);
    }
    
}

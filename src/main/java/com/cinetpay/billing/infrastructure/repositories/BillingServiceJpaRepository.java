package com.cinetpay.billing.infrastructure.repositories;

import com.cinetpay.billing.infrastructure.entities.BillingServiceModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BillingServiceJpaRepository extends JpaRepository<BillingServiceModel, String> {

    public BillingServiceModel findWithService(String vendor, String product, String country, String partner, String currency, String owner);

    @Query(value = "SELECT * FROM billings_services WHERE vendor = :vendor AND product = :product AND country = :country AND partner = :partner AND currency = :currency", nativeQuery = true)
    public BillingServiceModel findWithoutService(@Param("vendor") String vendor, @Param("product") String product, @Param("country") String country, @Param("partner") String partner, @Param("currency") String currency);
    
}

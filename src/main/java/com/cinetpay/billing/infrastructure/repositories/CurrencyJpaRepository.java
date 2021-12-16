/**
 * 
 */
package com.cinetpay.billing.infrastructure.repositories;

import com.cinetpay.billing.infrastructure.entities.CurrencyModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CurrencyJpaRepository extends JpaRepository<CurrencyModel, String> {

    public CurrencyModel findByName(String name);
    
}

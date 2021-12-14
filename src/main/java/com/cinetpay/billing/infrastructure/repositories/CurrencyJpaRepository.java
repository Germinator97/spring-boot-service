/**
 * 
 */
package com.cinetpay.billing.infrastructure.repositories;

import com.cinetpay.billing.infrastructure.entities.CurrencyModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mac
 *
 */
public interface CurrencyJpaRepository extends JpaRepository<CurrencyModel, String> {

	public CurrencyModel findByCode(String code);

	public CurrencyModel findByName(String name);
}

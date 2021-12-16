/**
 * 
 */
package com.cinetpay.billing.infrastructure.repositories;

import com.cinetpay.billing.infrastructure.entities.CommissionPartnerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author mac
 *
 */
public interface CommissionPartnerJpaRepository extends JpaRepository<CommissionPartnerModel, String> {
	public CommissionPartnerModel findByVendorAndProductAndCurrencyAndPartnerAndCountry
			(String vendor, String product, String currency, String partner, String country);

	public Optional<CommissionPartnerModel> findById(String id);
}
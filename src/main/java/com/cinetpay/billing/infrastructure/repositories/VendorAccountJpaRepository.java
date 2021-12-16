/**
 * 
 */
package com.cinetpay.billing.infrastructure.repositories;

import com.cinetpay.billing.infrastructure.entities.CommissionPartnerModel;
import com.cinetpay.billing.infrastructure.entities.VendorAccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author mac
 *
 */
public interface VendorAccountJpaRepository extends JpaRepository<VendorAccountModel, String> {
	public VendorAccountModel findByAccount(String account);
}
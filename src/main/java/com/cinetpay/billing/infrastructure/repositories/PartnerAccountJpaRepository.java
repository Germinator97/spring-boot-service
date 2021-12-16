/**
 * 
 */
package com.cinetpay.billing.infrastructure.repositories;

import com.cinetpay.billing.infrastructure.entities.PartnerAccountModel;
import com.cinetpay.billing.infrastructure.entities.VendorAccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mac
 *
 */
public interface PartnerAccountJpaRepository extends JpaRepository<PartnerAccountModel, String> {
	public PartnerAccountModel findByAccount(String account);
}
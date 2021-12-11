/**
 * 
 */
package com.cinetpay.billing.infrastructure.repository;

import com.cinetpay.billing.infrastructure.entities.CountryModel;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mac
 *
 */
public interface CountryJpaRepository extends JpaRepository<CountryModel, String> {

	public CountryModel findByCode(String code);

	public CountryModel findByName(String name);

}

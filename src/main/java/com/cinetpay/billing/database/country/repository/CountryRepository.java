/**
 * 
 */
package com.cinetpay.billing.database.country.repository;

import java.io.Serializable;

import com.cinetpay.billing.database.country.model.CountryModel;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mac
 *
 */
public interface CountryRepository extends JpaRepository<CountryModel, Serializable> {

	public CountryModel findByCode(String code);

	public CountryModel findByName(String name);

}

/**
 * 
 */
package com.cinetpay.billing.database.country.repository;

import java.io.Serializable;

import com.cinetpay.billing.database.country.model.CountryModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author mac
 *
 */
public interface CountryRepository extends JpaRepository<CountryModel, Long> {

	public CountryModel findByCode(String code);

	public CountryModel findByName(String name);

	// @Query("select c.code from countries c orderdy c.created_at limit 1")
	// public CountryModel findLast();

}

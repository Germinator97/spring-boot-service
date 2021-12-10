/**
 * 
 */
package com.cinetpay.billing.database.country.repository;

import java.io.Serializable;
import java.util.List;

import com.cinetpay.billing.database.country.model.CountryModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author mac
 *
 */
public interface CountryRepository extends JpaRepository<CountryModel, String> {

	public CountryModel findByCode(String code);

	public CountryModel findByName(String name);

}

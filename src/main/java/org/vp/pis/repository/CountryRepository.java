package org.vp.pis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.vp.pis.model.Country;
/**
 * Class used to retrieve values from Country Table
 *
 */
public interface CountryRepository extends Repository<Country, Long> {
	
	/**
	 * Get Country Detail
	 * @param Country code 
	 * @return country detail
	 */
	@Query("select c from Country c where c.country_code_2a = ?1")
	List<Country> getDetailsByCountryCode(String country_code);
	
}

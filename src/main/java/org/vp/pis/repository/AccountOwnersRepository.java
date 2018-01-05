package org.vp.pis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.vp.pis.model.AccountOwners;

/**
 * Class used to retrieve values from AccountOwners Table
 *
 */
public interface AccountOwnersRepository extends Repository<AccountOwners, Long> {

	/**
	 * Get from Account owners Detail
	 * 
	 * @param scheme_name
	 * @param account_identification
	 * @return
	 */
	@Query("select c from AccountOwners c where c.scheme_name = ?1 and c.account_identification = ?2")
	List<AccountOwners> getPartyID(String scheme_name, String account_identification);

}

package org.vp.pis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.vp.pis.model.PaymentLimit;

/**
 * Class used to retrieve values from PaymentLimits Table
 *
 */
public interface PaymentLimitsRepository extends Repository<PaymentLimit, Integer> {

	/**
	 * Get Details of Payment Limits
	 * 
	 * @return List Payment Limits
	 */
	@Query("select b from PaymentLimit b")
	List<PaymentLimit> getDetailsOfPaymentLimits();

}

package org.vp.pis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.vp.pis.model.Payment;

/**
 * Class used to retrieve values from Payment Table
 *
 */
public interface PaymentRepository extends Repository<Payment, Integer> {

	/**
	 * Get Details by Payment id
	 * 
	 * @param paymentId
	 * @return List Payment
	 */
	@Query("select b from Payment b where b.payment_ref_id = ?1")
	List<Payment> getDetailsByPaymentRefId(String payment_ref_id);

	/**
	 * Payment
	 * 
	 * @param payment
	 *            payment details
	 * @return payment details
	 */
	Payment save(Payment payment);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Payment p set p.payment_submission_status=?2,p.payment_submission_id=?3,p.scheme_payment_id=?4,internal_status=?5 ,internal_status_error_code=?6 where p.payment_ref_id=?1")
	void updatePaymentValuesByPaymentRefId(String payment_ref_id, String payment_submission_status,
			String payment_submission_id, String scheme_payment_id, String internal_status, String internal_status_error_code);

	/**
	 * Get Details by Payment Submission id
	 * 
	 * @param payment_submission_Id
	 * @return List Payment
	 */
	@Query("select b from Payment b where b.payment_submission_id = ?1")
	List<Payment> getDetailsByPaymentSubmissionId(String payment_submission_id);

}

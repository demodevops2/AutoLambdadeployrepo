package org.vp.pis.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class Data holds the information like payment id , payment submission id and
 * its status, creation date and Initiation details
 *
 */
public class Data {

	/**
	 * Holds the PaymentSubmissionId of Data.
	 */
	private String PaymentSubmissionId;
	/**
	 * Holds the PaymentId of Data.
	 */
	private String PaymentId;
	/**
	 * Holds the Status of Data.
	 */
	private String Status;
	/**
	 * Holds the CreationDateTime of Data.
	 */
	private String CreationDateTime;
	/**
	 * Holds the Initiation of Data.
	 */
	private Initiation Initiation;
	/**
	 * Holds the PaymentSubmissionStatus of Data.
	 */
	private String PaymentSubmissionStatus;

	/**
	 * @return PaymentId
	 */
	@JsonProperty("PaymentId")
	public String getPaymentId() {
		return PaymentId;
	}

	/**
	 * @param paymentId
	 */
	public void setPaymentId(String paymentId) {
		PaymentId = paymentId;
	}

	/**
	 * @return Status
	 */
	@JsonProperty("Status")
	public String getStatus() {
		return Status;
	}

	/**
	 * @param status
	 */
	public void setStatus(String status) {
		Status = status;
	}

	/**
	 * @return CreationDateTime
	 */
	@JsonProperty("CreationDateTime")
	public String getCreationDateTime() {
		return CreationDateTime;
	}

	/**
	 * @param creationDateTime
	 */
	public void setCreationDateTime(String creationDateTime) {
		CreationDateTime = creationDateTime;
	}

	/**
	 * @return Initiation
	 */
	@JsonProperty("Initiation")
	public Initiation getInitiation() {
		return Initiation;
	}

	/**
	 * @param initiation
	 */
	public void setInitiation(Initiation initiation) {
		Initiation = initiation;
	}

	/**
	 * @return PaymentSubmissionId
	 */
	@JsonProperty("PaymentSubmissionId")
	public String getPaymentSubmissionId() {
		return PaymentSubmissionId;
	}

	/**
	 * @param paymentSubmissionId
	 */
	public void setPaymentSubmissionId(String paymentSubmissionId) {
		PaymentSubmissionId = paymentSubmissionId;
	}

	/**
	 * @return PaymentSubmissionStatus
	 */
	@JsonProperty("PaymentSubmissionStatus")
	public String getPaymentSubmissionStatus() {
		return PaymentSubmissionStatus;
	}

	/**
	 * @param paymentSubmissionStatus
	 */
	public void setPaymentSubmissionStatus(String paymentSubmissionStatus) {
		PaymentSubmissionStatus = paymentSubmissionStatus;
	}

}

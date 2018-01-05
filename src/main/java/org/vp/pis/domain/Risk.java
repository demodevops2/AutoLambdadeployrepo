package org.vp.pis.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that holds the Risk values of payment request
 *
 */
public class Risk {
	/**
	 * Holds the PaymentContextCode values of payment request
	 *
	 */
	private String PaymentContextCode;
	/**
	 * Holds the MerchantCategoryCode values of payment request
	 *
	 */
	private String MerchantCategoryCode;
	/**
	 * Holds the MerchantCustomerIdentification values of payment request
	 *
	 */
	private String MerchantCustomerIdentification;
	/**
	 * Holds the DeliveryAddress values of payment request
	 *
	 */
	private DeliveryAddress DeliveryAddress;

	/**
	 * @return the paymentContextCode
	 */
	@JsonProperty("PaymentContextCode")
	public String getPaymentContextCode() {
		return PaymentContextCode;
	}

	/**
	 * @param paymentContextCode
	 *            the paymentContextCode to set
	 */
	public void setPaymentContextCode(String paymentContextCode) {
		PaymentContextCode = paymentContextCode;
	}

	/**
	 * @return the merchantCategoryCode
	 */
	@JsonProperty("MerchantCategoryCode")
	public String getMerchantCategoryCode() {
		return MerchantCategoryCode;
	}

	/**
	 * @param merchantCategoryCode
	 *            the merchantCategoryCode to set
	 */
	public void setMerchantCategoryCode(String merchantCategoryCode) {
		MerchantCategoryCode = merchantCategoryCode;
	}

	/**
	 * @return the merchantCustomerIdentification
	 */
	@JsonProperty("MerchantCustomerIdentification")
	public String getMerchantCustomerIdentification() {
		return MerchantCustomerIdentification;
	}

	/**
	 * @param merchantCustomerIdentification
	 *            the merchantCustomerIdentification to set
	 */
	public void setMerchantCustomerIdentification(String merchantCustomerIdentification) {
		MerchantCustomerIdentification = merchantCustomerIdentification;
	}

	/**
	 * @return the deliveryAddress
	 */
	@JsonProperty("DeliveryAddress")
	public DeliveryAddress getDeliveryAddress() {
		return DeliveryAddress;
	}

	/**
	 * @param deliveryAddress
	 *            the deliveryAddress to set
	 */
	public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
		DeliveryAddress = deliveryAddress;
	}

}

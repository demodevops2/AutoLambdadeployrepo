package org.vp.pis.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that holds the Remittance information values of payment request
 *
 */
public class RemittanceInformation {

	/**
	 * Holds the Unstructured values of payment request
	 *
	 */
	private String Unstructured;
	/**
	 * Holds the Reference values of payment request
	 *
	 */
	private String Reference;

	/**
	 * @return the unstructured
	 */
	@JsonProperty("Unstructured")
	public String getUnstructured() {
		return Unstructured;
	}

	/**
	 * @param unstructured
	 *            the unstructured to set
	 */
	public void setUnstructured(String unstructured) {
		Unstructured = unstructured;
	}

	/**
	 * @return the reference
	 */
	@JsonProperty("Reference")
	public String getReference() {
		return Reference;
	}

	/**
	 * @param reference
	 *            the reference to set
	 */
	public void setReference(String reference) {
		Reference = reference;
	}

}

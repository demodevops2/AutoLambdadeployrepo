package org.vp.pis.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * This Class represents the Creditor Agent details
 *
 */
public class CreditorAgent {

	/**
	 * Holds the SchemeName of Creditor Agent.
	 */
	private String SchemeName;

	/**
	 * Holds the Identification of Creditor Agent.
	 */
	private String Identification;

	/**
	 * @return the schemeName
	 */
	@JsonProperty("SchemeName")
	public String getSchemeName() {
		return SchemeName;
	}

	/**
	 * @param schemeName
	 *            the schemeName to set
	 */
	public void setSchemeName(String schemeName) {
		SchemeName = schemeName;
	}

	/**
	 * @return the identification
	 */
	@JsonProperty("Identification")
	public String getIdentification() {
		return Identification;
	}

	/**
	 * @param identification
	 *            the identification to set
	 */
	public void setIdentification(String identification) {
		Identification = identification;
	}

}

package org.vp.pis.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * This Class represents the Creditor Account details
 *
 */
public class CreditorAccount {

	/**
	 * Holds the SchemeName of Creditor Account.
	 */
	private String SchemeName;

	/**
	 * Holds the Identification of Creditor Account.
	 */
	private String Identification;

	/**
	 * Holds the Name of Creditor Account.
	 */
	private String Name;

	/**
	 * Holds the SecondaryIdentification of Creditor Account.
	 */
	private String SecondaryIdentification;
	

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

	/**
	 * @return the name
	 */
	@JsonProperty("Name")
	public String getName() {
		return Name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * @return the secondaryIdentification
	 */
	@JsonProperty("SecondaryIdentification")
	public String getSecondaryIdentification() {
		return SecondaryIdentification;
	}

	/**
	 * @param secondaryIdentification
	 *            the secondaryIdentification to set
	 */
	public void setSecondaryIdentification(String secondaryIdentification) {
		SecondaryIdentification = secondaryIdentification;
	}

}

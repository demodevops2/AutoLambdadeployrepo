package org.vp.pis.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * This Class represents the Debtor Account details
 *
 */
public class DebtorAccount {
	/**
	 * Holds the SchemeName of Debtor Account.
	 */
	private String SchemeName;
	/**
	 * Holds the Identification of Debtor Account.
	 */
	private String Identification;
	/**
	 * Holds the Name of Debtor Account.
	 */
	private String Name;
	/**
	 * Holds the Secondary Identification of Debtor Account.
	 */
	private String SecondaryIdentification;

	/**
	 * @return SchemeName
	 */
	@JsonProperty("SchemeName")
	public String getSchemeName() {
		return SchemeName;
	}

	/**
	 * @param schemeName
	 */
	public void setSchemeName(String schemeName) {
		SchemeName = schemeName;
	}

	/**
	 * @return Identification
	 */
	@JsonProperty("Identification")
	public String getIdentification() {
		return Identification;
	}

	/**
	 * @param identification
	 */
	public void setIdentification(String identification) {
		Identification = identification;
	}

	/**
	 * @return Name
	 */
	@JsonProperty("Name")
	public String getName() {
		return Name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * @return SecondaryIdentification
	 */
	@JsonProperty("SecondaryIdentification")
	public String getSecondaryIdentification() {
		return SecondaryIdentification;
	}

	/**
	 * @param secondaryIdentification
	 */
	public void setSecondaryIdentification(String secondaryIdentification) {
		SecondaryIdentification = secondaryIdentification;
	}

}

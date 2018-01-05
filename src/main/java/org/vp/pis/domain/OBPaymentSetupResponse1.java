package org.vp.pis.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that holds the Data, Risk , Link and Meta of payment request
 *
 */
public class OBPaymentSetupResponse1 {

	/**
	 * Holds the Data of payment request
	 *
	 */
	private Data Data;
	/**
	 * Holds the Risk of payment request
	 *
	 */
	private Risk Risk;
	/**
	 * Holds the Links of payment request
	 *
	 */
	private Links Links;
	/**
	 * Holds the Meta of payment request
	 *
	 */
	private Meta Meta;

	/**
	 * @return the data
	 */
	@JsonProperty("Data")
	public Data getData() {
		return Data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Data data) {
		Data = data;
	}

	/**
	 * @return the risk
	 */
	@JsonProperty("Risk")
	public Risk getRisk() {
		return Risk;
	}

	/**
	 * @param risk
	 *            the risk to set
	 */
	public void setRisk(Risk risk) {
		Risk = risk;
	}

	/**
	 * @return the links
	 */
	public Links getLinks() {
		return Links;
	}

	/**
	 * @param links
	 *            the links to set
	 */
	public void setLinks(Links links) {
		Links = links;
	}

	/**
	 * @return the meta
	 */
	public Meta getMeta() {
		return Meta;
	}

	/**
	 * @param meta
	 *            the meta to set
	 */
	public void setMeta(Meta meta) {
		Meta = meta;
	}

}
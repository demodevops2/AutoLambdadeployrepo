package org.vp.pis.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that holds the Instructed Amount values of payment request
 *
 */
public class InstructedAmount {

	/**
	 * Holds the Amount of Instructed Amount
	 */
	private String Amount;
	/**
	 * Holds the Currency of Instructed Amount
	 */
	private String Currency;

	/**
	 * @return the amount
	 */
	@JsonProperty("Amount")
	public String getAmount() {
		return Amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(String amount) {
		Amount = amount;
	}

	/**
	 * @return the currency
	 */
	@JsonProperty("Currency")
	public String getCurrency() {
		return Currency;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency) {
		Currency = currency;
	}

}

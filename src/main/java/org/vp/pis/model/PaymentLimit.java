package org.vp.pis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class Consists of Column of payment limits
 *
 */

@Entity
@Table(name = "PaymentLimit")
public class PaymentLimit implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Holds the payment_limit_id of PaymentLimit
	 */
	@Id
	@Column(name = "payment_limit_id")
	private int payment_limit_id;

	/**
	 * Holds the client_id of PaymentLimit
	 */
	/*@Column(name = "client_id")
	private int client_id;*/

	/**
	 * Holds the no_of_transaction_per_day of PaymentLimit
	 */
	@Column(name = "no_of_transaction_per_day")
	private int no_of_transaction_per_day;

	/**
	 * Holds the maximum_transaction_amount of PaymentLimit
	 */
	@Column(name = "maximum_transaction_amount")
	private float maximum_transaction_amount;

	/**
	 * Holds the minimum_transaction_amount of PaymentLimit
	 */
	@Column(name = "minimum_transaction_amount")
	private int minimum_transaction_amount;

	/**
	 * Holds the currency_code of PaymentLimit
	 */
	@Column(name = "currency_code")
	private String currency_code;

	/**
	 * @return the payment_limit_id
	 */
	public int getPayment_limit_id() {
		return payment_limit_id;
	}

	/**
	 * @param payment_limit_id
	 *            the payment_limit_id to set
	 */
	public void setPayment_limit_id(int payment_limit_id) {
		this.payment_limit_id = payment_limit_id;
	}

	/**
	 * @return the client_id
	 */
	/*public int getClient_id() {
		return client_id;
	}*/
	
	/**
	 * @param client_id
	 *            the client_id to set
	 */
	/*public void setClient_id(int client_id) {
		this.client_id = client_id;
	}*/

	/**
	 * @return the no_of_transaction_per_day
	 */
	public int getNo_of_transaction_per_day() {
		return no_of_transaction_per_day;
	}

	/**
	 * @param no_of_transaction_per_day
	 *            the no_of_transaction_per_day to set
	 */
	public void setNo_of_transaction_per_day(int no_of_transaction_per_day) {
		this.no_of_transaction_per_day = no_of_transaction_per_day;
	}

	/**
	 * @return the maximum_transaction_amount
	 */
	public float getMaximum_transaction_amount() {
		return maximum_transaction_amount;
	}

	/**
	 * @param maximum_transaction_amount
	 *            the maximum_transaction_amount to set
	 */
	public void setMaximum_transaction_amount(float maximum_transaction_amount) {
		this.maximum_transaction_amount = maximum_transaction_amount;
	}

	/**
	 * @return the minimum_transaction_amount
	 */
	public int getMinimum_transaction_amount() {
		return minimum_transaction_amount;
	}

	/**
	 * @param minimum_transaction_amount
	 *            the minimum_transaction_amount to set
	 */
	public void setMinimum_transaction_amount(int minimum_transaction_amount) {
		this.minimum_transaction_amount = minimum_transaction_amount;
	}

	/**
	 * @return the currency_code
	 */
	public String getCurrency_code() {
		return currency_code;
	}

	/**
	 * @param currency_code
	 *            the currency_code to set
	 */
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

}

package org.vp.pis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Account table mapped with this model Class
 *
 */
@Entity
@Table( name = "Account")

public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Account id of Account Table
	 */
	@Id
	@Column (name = "account_id" )
	private int account_id;

	/**
	 * Scheme Name of Account Table
	 */
	@Column (name = "scheme_name" )
	private String scheme_name;
	
	/**
	 * Account identification of Account Table
	 */
	@Column (name = "account_identification" )
	private String account_identification;
	
	/**
	 * Secondary identification of Account Table
	 */
	@Column (name = "secondary_identification" )
	private String secondary_identification;
	
	/**
	 * Bank id of Account Table
	 */
	@Column (name = "bank_id" )
	private int bank_id;
	
	/**
	 * Account Name of Account Table
	 */
	@Column (name = "account_name" )
	private String account_name;
	
	/**
	 * Nick Name of Account Table
	 */
	@Column (name = "nickname" )
	private String nickname;
	
	/**
	 * Product id of Account Table
	 */
	@Column (name = "product_id" )
	private int product_id;
	
	/**
	 * Account Currency of Account Table
	 */
	@Column (name = "account_currency" )
	private String account_currency;
	
	/**
	 * Balance of Account Table
	 */
	@Column (name = "balance" )
	private float balance;
	
	/**
	 * Status of Account Table
	 */
	@Column (name = "status" )
	private String status;

	/**
	 * @return the account_id
	 */
	public int getAccount_id() {
		return account_id;
	}

	/**
	 * @param account_id the account_id to set
	 */
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	/**
	 * @return the scheme_name
	 */
	public String getScheme_name() {
		return scheme_name;
	}

	/**
	 * @param scheme_name the scheme_name to set
	 */
	public void setScheme_name(String scheme_name) {
		this.scheme_name = scheme_name;
	}

	/**
	 * @return the account_identification
	 */
	public String getAccount_identification() {
		return account_identification;
	}

	/**
	 * @param account_identification the account_identification to set
	 */
	public void setAccount_identification(String account_identification) {
		this.account_identification = account_identification;
	}

	/**
	 * @return the secondary_identification
	 */
	public String getSecondary_identification() {
		return secondary_identification;
	}

	/**
	 * @param secondary_identification the secondary_identification to set
	 */
	public void setSecondary_identification(String secondary_identification) {
		this.secondary_identification = secondary_identification;
	}

	/**
	 * @return the bank_id
	 */
	public int getBank_id() {
		return bank_id;
	}

	/**
	 * @param bank_id the bank_id to set
	 */
	public void setBank_id(int bank_id) {
		this.bank_id = bank_id;
	}

	/**
	 * @return the account_name
	 */
	public String getAccount_name() {
		return account_name;
	}

	/**
	 * @param account_name the account_name to set
	 */
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the product_id
	 */
	public int getProduct_id() {
		return product_id;
	}

	/**
	 * @param product_id the product_id to set
	 */
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	/**
	 * @return the account_currency
	 */
	public String getAccount_currency() {
		return account_currency;
	}

	/**
	 * @param account_currency the account_currency to set
	 */
	public void setAccount_currency(String account_currency) {
		this.account_currency = account_currency;
	}

	/**
	 * @return the balance
	 */
	public float getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(float balance) {
		this.balance = balance;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	
}

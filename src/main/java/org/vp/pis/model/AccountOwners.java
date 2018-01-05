package org.vp.pis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AccountOwners")
public class AccountOwners implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Holds the Id of AccountOwners
	 */
	@Id
	@Column(name = "account_owner_id")
	private int account_owner_id;
	/**
	 * Holds the Account id of AccountOwners
	 */
	@Column(name = "account_id")
	private int account_id;
	/**
	 * Holds the party id of AccountOwners
	 */
	@Column(name = "party_id")
	private int party_id;

	/**
	 * Holds the Scheme Name of AccountOwners
	 */
	@Column(name = "scheme_name")
	private String scheme_name;

	/**
	 * Holds the Account Identification of Account Owners
	 */
	@Column(name = "account_identification")
	private String account_identification;

	/**
	 * @return the account_id
	 */
	public int getAccount_id() {
		return account_id;
	}

	/**
	 * @param account_id
	 *            the account_id to set
	 */
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	/**
	 * @return the party_id
	 */
	public int getParty_id() {
		return party_id;
	}

	/**
	 * @param party_id
	 *            the party_id to set
	 */
	public void setParty_id(int party_id) {
		this.party_id = party_id;
	}

	/**
	 * @return the scheme_name
	 */
	public String getScheme_name() {
		return scheme_name;
	}

	/**
	 * @param scheme_name
	 *            the scheme_name to set
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
	 * @param account_identification
	 *            the account_identification to set
	 */
	public void setAccount_identification(String account_identification) {
		this.account_identification = account_identification;
	}

	/**
	 * @return the account_owner_id
	 */
	public int getAccount_owner_id() {
		return account_owner_id;
	}

	/**
	 * @param account_owner_id the account_owner_id to set
	 */
	public void setAccount_owner_id(int account_owner_id) {
		this.account_owner_id = account_owner_id;
	}

}

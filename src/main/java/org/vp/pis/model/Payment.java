package org.vp.pis.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class holds the Payment details
 *
 */
@Entity
@Table(name = "Payment")
public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Holds the Payment ID of Payment
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "payment_id")
	private int paymentID;
	/**
	 * Holds the Payment Ref ID of Payment
	 */
	@Column(name = "payment_ref_id")
	private String payment_ref_id;
	/**
	 * Holds the Payment Submission ID of Payment
	 */
	@Column(name = "payment_submission_id")
	private String payment_submission_id;
	/**
	 * Holds the Scheme Payment ID of Payment
	 */
	@Column(name = "scheme_payment_id")
	private String scheme_payment_id;
	/**
	 * Holds the From party ID of Payment
	 */
	@Column(name = "from_party_id")
	private int from_party_id;
	/**
	 * Holds the to party ID of Payment
	 */
	@Column(name = "to_party_id")
	private int to_party_id;
	/**
	 * Holds the value date of Payment
	 */
	@Column(name = "value_date")
	private Date value_date;
	/**
	 * Holds the Transaction Amount of Payment
	 */
	@Column(name = "transaction_amount")
	private float transaction_amount;
	/**
	 * Holds the Currency of Payment
	 */
	@Column(name = "transaction_currency")
	private String transaction_currency;
	/**
	 * Holds the payment status of Payment
	 */
	@Column(name = "payment_status")
	@Enumerated(EnumType.STRING)
	private PaymentStatus payment_status;
	/**
	 * Holds the payment setup status of Payment
	 */
	@Column(name = "payment_setup_status")
	private String payment_setup_status;
	/**
	 * Holds the internal status of Payment
	 */
	@Column(name = "internal_status")
	private String internal_status;
	/**
	 * Holds the internal status error code of Payment
	 */
	@Column(name = "internal_status_error_code")
	private String internal_status_error_code;
	/**
	 * Holds the payment submission status of Payment
	 */
	@Column(name = "payment_submission_status")
	private String payment_submission_status;
	/**
	 * Holds the creation date and time of Payment
	 */
	@Column(name = "creation_date_time")
	private Date creation_date_time;
	/**
	 * Holds the payment period of Payment
	 */
	@Column(name = "payment_period")
	private int payment_period;
	/**
	 * Holds the payment notes of Payment
	 */
	@Column(name = "payment_notes")
	private String payment_notes;
	/**
	 * Holds the payment mode of Payment
	 */
	@Column(name = "payment_mode")
	private String payment_mode;
	/**
	 * Holds the gateway id of Payment
	 */
	@Column(name = "gateway_id")
	private int gateway_id;

	/**
	 * Holds the transfer type of Payment
	 */
	@Column(name = "transfer_type")
	private String transfer_type;
	/**
	 * Holds the transfer mode of Payment
	 */
	@Column(name = "transfer_mode")
	private String transfer_mode;
	/**
	 * Holds the authorization status of Payment
	 */
	@Column(name = "authorization_status")
	private String authorization_status;
	/**
	 * Holds the authorization decision of Payment
	 */
	@Column(name = "authorization_decision")
	private String authorization_decision;
	/**
	 * Holds the authorization number of Payment
	 */
	@Column(name = "authorization_number")
	private String authorization_number;
	/**
	 * Holds the authorization response of Payment
	 */
	@Column(name = "authorization_response")
	private String authorization_response;
	/**
	 * Holds the payment method of Payment
	 */
	@Column(name = "payment_method")
	private String payment_method;
	/**
	 * Holds the payment type of Payment
	 */
	@Column(name = "payment_type")
	private String payment_type;

	/**
	 * Holds the debtor bank of Payment
	 */
	@Column(name = "debtor_bank")
	private String debtor_bank;
	/**
	 * Holds the creditor bank of Payment
	 */
	@Column(name = "creditor_bank")
	private String creditor_bank;
	/**
	 * Holds the debtor agent scheme name of Payment
	 */
	@Column(name = "debtor_agent_scheme_name")
	private String debtor_agent_scheme_name;
	/**
	 * Holds the debtor agent identification of Payment
	 */
	@Column(name = "debtor_agent_identification")
	private String debtor_agent_identification;
	/**
	 * Holds the debtor account scheme name of Payment
	 */
	@Column(name = "debtor_account_schemename")
	private String debtor_account_schemename;

	/**
	 * Holds the debtor_account_identification of Payment
	 */
	@Column(name = "debtor_account_identification")
	private String debtor_account_identification;
	/**
	 * Holds the debtor_account secondary identification of Payment
	 */
	@Column(name = "debtor_account_secondary_identification")
	private String debtor_account_secondary_identification;
	/**
	 * Holds the debtor account name of Payment
	 */
	@Column(name = "debtor_account_name")
	private String debtor_account_name;
	/**
	 * Holds the creditor agent scheme name of Payment
	 */
	@Column(name = "creditor_agent_schemename")
	private String creditor_agent_schemename;
	/**
	 * Holds the creditor agent identification of Payment
	 */
	@Column(name = "creditor_agent_identification")
	private String creditor_agent_identification;
	/**
	 * Holds the creditor account scheme of Payment
	 */
	@Column(name = "creditor_account_schemename")
	private String creditor_account_schemename;

	/**
	 * Holds the creditor_account_identification of Payment
	 */
	@Column(name = "creditor_account_identification")
	private String creditor_account_identification;
	/**
	 * Holds the creditor account name of Payment
	 */
	@Column(name = "creditor_account_name")
	private String creditor_account_name;
	/**
	 * Holds the instruction identification of Payment
	 */
	@Column(name = "instruction_identification")
	private String instruction_identification;
	/**
	 * Holds the end to end identification of Payment
	 */
	@Column(name = "end_to_end_identification")
	private String end_to_end_identification;
	/**
	 * Holds the remittance reference of Payment
	 */
	@Column(name = "remittance_reference")
	private String remittance_reference;

	/**
	 * Holds the crisk unstructured of Payment
	 */
	@Column(name = "remittance_unstructured_reference")
	private String remittance_unstructured_reference;

	/**
	 * Holds the creditor secondary identification of Payment
	 */
	@Column(name = "creditor_secondary_identification")
	private String creditor_secondary_identification;
	/**
	 * Holds the payment context of Payment
	 */
	@Column(name = "risk_payment_context_code")
	private String risk_payment_context_code;
	/**
	 * Holds the category code of Payment
	 */
	@Column(name = "risk_merchant_category_code")
	private String risk_merchant_category_code;
	/**
	 * Holds the customer identification of Payment
	 */
	@Column(name = "risk_merchant_customer_identification")
	private String risk_merchant_customer_identification;
	/**
	 * Holds the address line of Payment
	 */
	@Column(name = "risk_delivery_address_line1")
	private String risk_delivery_address_line1;
	/**
	 * Holds the address line of Payment
	 */
	@Column(name = "risk_delivery_address_line2")
	private String risk_delivery_address_line2;
	/**
	 * Holds the street name of Payment
	 */
	@Column(name = "risk_delivery_address_streetName")
	private String risk_delivery_address_streetName;
	/**
	 * Holds the building number of Payment
	 */
	@Column(name = "risk_delivery_address_building_number")
	private String risk_delivery_address_building_number;
	/**
	 * Holds the post code of Payment
	 */
	@Column(name = "risk_delivery_address_postcode")
	private String risk_delivery_address_postcode;
	/**
	 * Holds the address townname of Payment
	 */
	@Column(name = "risk_delivery_address_townname")
	private String risk_delivery_address_townname;
	/**
	 * Holds the country sub division of Payment
	 */
	@Column(name = "risk_delivery_address_county_subdivision")
	private String risk_delivery_address_county_subdivision;
	/**
	 * Holds the country of Payment
	 */
	@Column(name = "risk_delivery_address_country")
	private String risk_delivery_address_country;
	/**
	 * Holds the order number of Payment
	 */
	@Column(name = "order_number")
	private String order_number;
	/**
	 * Holds the order date time of Payment
	 */
	@Column(name = "order_date_time")
	private Date order_date_time;
	/**
	 * Holds the order description of Payment
	 */
	@Column(name = "order_description")
	private String order_description;
	/**
	 * Holds the order unit count of Payment
	 */
	@Column(name = "order_unit_count")
	private int order_unit_count;
	/**
	 * Holds the order currency of Payment
	 */
	@Column(name = "order_currency")
	private String order_currency;
	/**
	 * Holds the order currency exponent of Payment
	 */
	@Column(name = "order_currency_exponent")
	private float order_currency_exponent;
	/**
	 * Holds the order commission percentage of Payment
	 */
	@Column(name = "order_commission_percentage")
	private float order_commission_percentage;
	/**
	 * Holds the order discount percentage of Payment
	 */
	@Column(name = "order_discount_percentage")
	private float order_discount_percentage;
	/**
	 * Holds the oder vat of Payment
	 */
	@Column(name = "order_vat_percentage")
	private float order_vat_percentage;
	/**
	 * Holds the order gross amount of Payment
	 */
	@Column(name = "order_gross_amount")
	private float order_gross_amount;
	/**
	 * Holds the order net amount of Payment
	 */
	@Column(name = "order_net_amount")
	private float order_net_amount;
	/**
	 * Holds the order vat amount of Payment
	 */
	@Column(name = "order_vat_amount")
	private float order_vat_amount;
	/**
	 * Holds the order home currency of Payment
	 */
	@Column(name = "order_home_currency")
	private String order_home_currency;
	/**
	 * Holds the order home currency exponent of Payment
	 */
	@Column(name = "order_home_currency_exponent")
	private float order_home_currency_exponent;
	/**
	 * Holds the order home currency order amount of Payment
	 */
	@Column(name = "order_home_currency_order_amount")
	private float order_home_currency_order_amount;
	/**
	 * Holds the order exchange rate of Payment
	 */
	@Column(name = "order_exchange_rate")
	private float order_exchange_rate;
	/**
	 * Holds the order recurring of Payment
	 */
	@Column(name = "order_recurring_payment")
	private float order_recurring_payment;
	/**
	 * Holds the order recurring frequency of Payment
	 */
	@Column(name = "order_recurring_frequency")
	private int order_recurring_frequency;
	/**
	 * Holds the order installmentss of Payment
	 */
	@Column(name = "order_installments")
	private int order_installments;
	/**
	 * Holds the order recurring expiry of Payment
	 */
	@Column(name = "order_recurring_expiry")
	private Date order_recurring_expiry;
	/**
	 * Holds the order recurring subscription nameof Payment
	 */
	@Column(name = "order_recurring_payment_subscription_name")
	private String order_recurring_payment_subscription_name;
	/**
	 * Holds the order recurring subscription code of Payment
	 */
	@Column(name = "order_recurring_payment_subscription_code")
	private String order_recurring_payment_subscription_code;
	/**
	 * Holds the maker date of Payment
	 */
	@Column(name = "maker_date")
	private Date maker_date;
	/**
	 * Holds the checker date of Payment
	 */
	@Column(name = "checker_date")
	private Date checker_date;
	/**
	 * Holds the maker id of Payment
	 */
	@Column(name = "maker_id")
	private String maker_id;
	/**
	 * Holds the checker id of Payment
	 */
	@Column(name = "checker_id")
	private String checker_id;
	/**
	 * Holds the modified by of Payment
	 */
	@Column(name = "modified_by")
	private String modified_by;
	/**
	 * Holds the order recurring frequency of Payment
	 */
	@Column(name = "to_bank_id")
	private int to_bank_id;
	/**
	 * Holds the modified date of Payment
	 */
	@Column(name = "modified_date")
	private Date modified_date;

	/**
	 * Holds the Payment Status
	 */
	public enum PaymentStatus {
		wip, pendingwithpartner, completed, failed, rejected
	}

	/**
	 * @return the paymentID
	 */
	public int getPaymentID() {
		return paymentID;
	}

	/**
	 * @param paymentID
	 *            the paymentID to set
	 */
	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	/**
	 * @return the payment_ref_id
	 */
	public String getPayment_ref_id() {
		return payment_ref_id;
	}

	/**
	 * @param payment_ref_id
	 *            the payment_ref_id to set
	 */
	public void setPayment_ref_id(String payment_ref_id) {
		this.payment_ref_id = payment_ref_id;
	}

	/**
	 * @return the payment_submission_id
	 */
	public String getPayment_submission_id() {
		return payment_submission_id;
	}

	/**
	 * @param payment_submission_id
	 *            the payment_submission_id to set
	 */
	public void setPayment_submission_id(String payment_submission_id) {
		this.payment_submission_id = payment_submission_id;
	}

	/**
	 * @return the scheme_payment_id
	 */
	public String getScheme_payment_id() {
		return scheme_payment_id;
	}

	/**
	 * @param scheme_payment_id
	 *            the scheme_payment_id to set
	 */
	public void setScheme_payment_id(String scheme_payment_id) {
		this.scheme_payment_id = scheme_payment_id;
	}

	/**
	 * @return the from_party_id
	 */
	public int getFrom_party_id() {
		return from_party_id;
	}

	/**
	 * @param from_party_id
	 *            the from_party_id to set
	 */
	public void setFrom_party_id(int from_party_id) {
		this.from_party_id = from_party_id;
	}

	/**
	 * @return the to_party_id
	 */
	public int getTo_party_id() {
		return to_party_id;
	}

	/**
	 * @param to_party_id
	 *            the to_party_id to set
	 */
	public void setTo_party_id(int to_party_id) {
		this.to_party_id = to_party_id;
	}

	/**
	 * @return the value_date
	 */
	public Date getValue_date() {
		return value_date;
	}

	/**
	 * @param value_date
	 *            the value_date to set
	 */
	public void setValue_date(Date value_date) {
		this.value_date = value_date;
	}

	/**
	 * @return the transaction_amount
	 */
	public float getTransaction_amount() {
		return transaction_amount;
	}

	/**
	 * @param transaction_amount
	 *            the transaction_amount to set
	 */
	public void setTransaction_amount(float transaction_amount) {
		this.transaction_amount = transaction_amount;
	}

	/**
	 * @return the transaction_currency
	 */
	public String getTransaction_currency() {
		return transaction_currency;
	}

	/**
	 * @param transaction_currency
	 *            the transaction_currency to set
	 */
	public void setTransaction_currency(String transaction_currency) {
		this.transaction_currency = transaction_currency;
	}

	/**
	 * @return the payment_status
	 */
	public PaymentStatus getPayment_status() {
		return payment_status;
	}

	/**
	 * @param payment_status
	 *            the payment_status to set
	 */
	public void setPayment_status(PaymentStatus payment_status) {
		this.payment_status = payment_status;
	}

	/**
	 * @return the payment_setup_status
	 */
	public String getPayment_setup_status() {
		return payment_setup_status;
	}

	/**
	 * @param payment_setup_status
	 *            the payment_setup_status to set
	 */
	public void setPayment_setup_status(String payment_setup_status) {
		this.payment_setup_status = payment_setup_status;
	}

	/**
	 * @return the internal_status
	 */
	public String getInternal_status() {
		return internal_status;
	}

	/**
	 * @param internal_status
	 *            the internal_status to set
	 */
	public void setInternal_status(String internal_status) {
		this.internal_status = internal_status;
	}

	/**
	 * @return the internal_status_error_code
	 */
	public String getInternal_status_error_code() {
		return internal_status_error_code;
	}

	/**
	 * @param internal_status_error_code
	 *            the internal_status_error_code to set
	 */
	public void setInternal_status_error_code(String internal_status_error_code) {
		this.internal_status_error_code = internal_status_error_code;
	}

	/**
	 * @return the payment_submission_status
	 */
	public String getPayment_submission_status() {
		return payment_submission_status;
	}

	/**
	 * @param payment_submission_status
	 *            the payment_submission_status to set
	 */
	public void setPayment_submission_status(String payment_submission_status) {
		this.payment_submission_status = payment_submission_status;
	}

	/**
	 * @return the creation_date_time
	 */
	public Date getCreation_date_time() {
		return creation_date_time;
	}

	/**
	 * @param creation_date_time
	 *            the creation_date_time to set
	 */
	public void setCreation_date_time(Date creation_date_time) {
		this.creation_date_time = creation_date_time;
	}

	/**
	 * @return the payment_period
	 */
	public int getPayment_period() {
		return payment_period;
	}

	/**
	 * @param payment_period
	 *            the payment_period to set
	 */
	public void setPayment_period(int payment_period) {
		this.payment_period = payment_period;
	}

	/**
	 * @return the payment_notes
	 */
	public String getPayment_notes() {
		return payment_notes;
	}

	/**
	 * @param payment_notes
	 *            the payment_notes to set
	 */
	public void setPayment_notes(String payment_notes) {
		this.payment_notes = payment_notes;
	}

	/**
	 * @return the payment_mode
	 */
	public String getPayment_mode() {
		return payment_mode;
	}

	/**
	 * @param payment_mode
	 *            the payment_mode to set
	 */
	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}

	/**
	 * @return the gateway_id
	 */
	public int getGateway_id() {
		return gateway_id;
	}

	/**
	 * @param gateway_id
	 *            the gateway_id to set
	 */
	public void setGateway_id(int gateway_id) {
		this.gateway_id = gateway_id;
	}

	/**
	 * @return the transfer_type
	 */
	public String getTransfer_type() {
		return transfer_type;
	}

	/**
	 * @param transfer_type
	 *            the transfer_type to set
	 */
	public void setTransfer_type(String transfer_type) {
		this.transfer_type = transfer_type;
	}

	/**
	 * @return the transfer_mode
	 */
	public String getTransfer_mode() {
		return transfer_mode;
	}

	/**
	 * @param transfer_mode
	 *            the transfer_mode to set
	 */
	public void setTransfer_mode(String transfer_mode) {
		this.transfer_mode = transfer_mode;
	}

	/**
	 * @return the authorization_status
	 */
	public String getAuthorization_status() {
		return authorization_status;
	}

	/**
	 * @param authorization_status
	 *            the authorization_status to set
	 */
	public void setAuthorization_status(String authorization_status) {
		this.authorization_status = authorization_status;
	}

	/**
	 * @return the authorization_decision
	 */
	public String getAuthorization_decision() {
		return authorization_decision;
	}

	/**
	 * @param authorization_decision
	 *            the authorization_decision to set
	 */
	public void setAuthorization_decision(String authorization_decision) {
		this.authorization_decision = authorization_decision;
	}

	/**
	 * @return the authorization_number
	 */
	public String getAuthorization_number() {
		return authorization_number;
	}

	/**
	 * @param authorization_number
	 *            the authorization_number to set
	 */
	public void setAuthorization_number(String authorization_number) {
		this.authorization_number = authorization_number;
	}

	/**
	 * @return the authorization_response
	 */
	public String getAuthorization_response() {
		return authorization_response;
	}

	/**
	 * @param authorization_response
	 *            the authorization_response to set
	 */
	public void setAuthorization_response(String authorization_response) {
		this.authorization_response = authorization_response;
	}

	/**
	 * @return the payment_method
	 */
	public String getPayment_method() {
		return payment_method;
	}

	/**
	 * @param payment_method
	 *            the payment_method to set
	 */
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	/**
	 * @return the payment_type
	 */
	public String getPayment_type() {
		return payment_type;
	}

	/**
	 * @param payment_type
	 *            the payment_type to set
	 */
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	/**
	 * @return the debtor_bank
	 */
	public String getDebtor_bank() {
		return debtor_bank;
	}

	/**
	 * @param debtor_bank
	 *            the debtor_bank to set
	 */
	public void setDebtor_bank(String debtor_bank) {
		this.debtor_bank = debtor_bank;
	}

	/**
	 * @return the creditor_bank
	 */
	public String getCreditor_bank() {
		return creditor_bank;
	}

	/**
	 * @param creditor_bank
	 *            the creditor_bank to set
	 */
	public void setCreditor_bank(String creditor_bank) {
		this.creditor_bank = creditor_bank;
	}

	/**
	 * @return the debtor_agent_scheme_name
	 */
	public String getDebtor_agent_scheme_name() {
		return debtor_agent_scheme_name;
	}

	/**
	 * @param debtor_agent_scheme_name
	 *            the debtor_agent_scheme_name to set
	 */
	public void setDebtor_agent_scheme_name(String debtor_agent_scheme_name) {
		this.debtor_agent_scheme_name = debtor_agent_scheme_name;
	}

	/**
	 * @return the debtor_agent_identification
	 */
	public String getDebtor_agent_identification() {
		return debtor_agent_identification;
	}

	/**
	 * @param debtor_agent_identification
	 *            the debtor_agent_identification to set
	 */
	public void setDebtor_agent_identification(String debtor_agent_identification) {
		this.debtor_agent_identification = debtor_agent_identification;
	}

	/**
	 * @return the debtor_account_schemename
	 */
	public String getDebtor_account_schemename() {
		return debtor_account_schemename;
	}

	/**
	 * @param debtor_account_schemename
	 *            the debtor_account_schemename to set
	 */
	public void setDebtor_account_schemename(String debtor_account_schemename) {
		this.debtor_account_schemename = debtor_account_schemename;
	}

	/**
	 * @return the debtor_account_identification
	 */
	public String getDebtor_account_identification() {
		return debtor_account_identification;
	}

	/**
	 * @param debtor_account_identification
	 *            the debtor_account_identification to set
	 */
	public void setDebtor_account_identification(String debtor_account_identification) {
		this.debtor_account_identification = debtor_account_identification;
	}

	/**
	 * @return the debtor_account_secondary_identification
	 */
	public String getDebtor_account_secondary_identification() {
		return debtor_account_secondary_identification;
	}

	/**
	 * @param debtor_account_secondary_identification
	 *            the debtor_account_secondary_identification to set
	 */
	public void setDebtor_account_secondary_identification(String debtor_account_secondary_identification) {
		this.debtor_account_secondary_identification = debtor_account_secondary_identification;
	}

	/**
	 * @return the debtor_account_name
	 */
	public String getDebtor_account_name() {
		return debtor_account_name;
	}

	/**
	 * @param debtor_account_name
	 *            the debtor_account_name to set
	 */
	public void setDebtor_account_name(String debtor_account_name) {
		this.debtor_account_name = debtor_account_name;
	}

	/**
	 * @return the creditor_agent_schemename
	 */
	public String getCreditor_agent_schemename() {
		return creditor_agent_schemename;
	}

	/**
	 * @param creditor_agent_schemename
	 *            the creditor_agent_schemename to set
	 */
	public void setCreditor_agent_schemename(String creditor_agent_schemename) {
		this.creditor_agent_schemename = creditor_agent_schemename;
	}

	/**
	 * @return the creditor_agent_identification
	 */
	public String getCreditor_agent_identification() {
		return creditor_agent_identification;
	}

	/**
	 * @param creditor_agent_identification
	 *            the creditor_agent_identification to set
	 */
	public void setCreditor_agent_identification(String creditor_agent_identification) {
		this.creditor_agent_identification = creditor_agent_identification;
	}

	/**
	 * @return the creditor_account_schemename
	 */
	public String getCreditor_account_schemename() {
		return creditor_account_schemename;
	}

	/**
	 * @param creditor_account_schemename
	 *            the creditor_account_schemename to set
	 */
	public void setCreditor_account_schemename(String creditor_account_schemename) {
		this.creditor_account_schemename = creditor_account_schemename;
	}

	/**
	 * @return the creditor_account_identification
	 */
	public String getCreditor_account_identification() {
		return creditor_account_identification;
	}

	/**
	 * @param creditor_account_identification
	 *            the creditor_account_identification to set
	 */
	public void setCreditor_account_identification(String creditor_account_identification) {
		this.creditor_account_identification = creditor_account_identification;
	}

	/**
	 * @return the creditor_account_name
	 */
	public String getCreditor_account_name() {
		return creditor_account_name;
	}

	/**
	 * @param creditor_account_name
	 *            the creditor_account_name to set
	 */
	public void setCreditor_account_name(String creditor_account_name) {
		this.creditor_account_name = creditor_account_name;
	}

	/**
	 * @return the instruction_identification
	 */
	public String getInstruction_identification() {
		return instruction_identification;
	}

	/**
	 * @param instruction_identification
	 *            the instruction_identification to set
	 */
	public void setInstruction_identification(String instruction_identification) {
		this.instruction_identification = instruction_identification;
	}

	/**
	 * @return the end_to_end_identification
	 */
	public String getEnd_to_end_identification() {
		return end_to_end_identification;
	}

	/**
	 * @param end_to_end_identification
	 *            the end_to_end_identification to set
	 */
	public void setEnd_to_end_identification(String end_to_end_identification) {
		this.end_to_end_identification = end_to_end_identification;
	}

	/**
	 * @return the remittance_reference
	 */
	public String getRemittance_reference() {
		return remittance_reference;
	}

	/**
	 * @param remittance_reference
	 *            the remittance_reference to set
	 */
	public void setRemittance_reference(String remittance_reference) {
		this.remittance_reference = remittance_reference;
	}

	/**
	 * @return the remittance_unstructured_reference
	 */
	public String getRemittance_unstructured_reference() {
		return remittance_unstructured_reference;
	}

	/**
	 * @param remittance_unstructured_reference
	 *            the remittance_unstructured_reference to set
	 */
	public void setRemittance_unstructured_reference(String remittance_unstructured_reference) {
		this.remittance_unstructured_reference = remittance_unstructured_reference;
	}

	/**
	 * @return the creditor_secondary_identification
	 */
	public String getCreditor_secondary_identification() {
		return creditor_secondary_identification;
	}

	/**
	 * @param creditor_secondary_identification
	 *            the creditor_secondary_identification to set
	 */
	public void setCreditor_secondary_identification(String creditor_secondary_identification) {
		this.creditor_secondary_identification = creditor_secondary_identification;
	}

	/**
	 * @return the risk_payment_context_code
	 */
	public String getRisk_payment_context_code() {
		return risk_payment_context_code;
	}

	/**
	 * @param risk_payment_context_code
	 *            the risk_payment_context_code to set
	 */
	public void setRisk_payment_context_code(String risk_payment_context_code) {
		this.risk_payment_context_code = risk_payment_context_code;
	}

	/**
	 * @return the risk_merchant_category_code
	 */
	public String getRisk_merchant_category_code() {
		return risk_merchant_category_code;
	}

	/**
	 * @param risk_merchant_category_code
	 *            the risk_merchant_category_code to set
	 */
	public void setRisk_merchant_category_code(String risk_merchant_category_code) {
		this.risk_merchant_category_code = risk_merchant_category_code;
	}

	/**
	 * @return the risk_merchant_customer_identification
	 */
	public String getRisk_merchant_customer_identification() {
		return risk_merchant_customer_identification;
	}

	/**
	 * @param risk_merchant_customer_identification
	 *            the risk_merchant_customer_identification to set
	 */
	public void setRisk_merchant_customer_identification(String risk_merchant_customer_identification) {
		this.risk_merchant_customer_identification = risk_merchant_customer_identification;
	}

	/**
	 * @return the risk_delivery_address_line1
	 */
	public String getRisk_delivery_address_line1() {
		return risk_delivery_address_line1;
	}

	/**
	 * @param risk_delivery_address_line1
	 *            the risk_delivery_address_line1 to set
	 */
	public void setRisk_delivery_address_line1(String risk_delivery_address_line1) {
		this.risk_delivery_address_line1 = risk_delivery_address_line1;
	}

	/**
	 * @return the risk_delivery_address_line2
	 */
	public String getRisk_delivery_address_line2() {
		return risk_delivery_address_line2;
	}

	/**
	 * @param risk_delivery_address_line2
	 *            the risk_delivery_address_line2 to set
	 */
	public void setRisk_delivery_address_line2(String risk_delivery_address_line2) {
		this.risk_delivery_address_line2 = risk_delivery_address_line2;
	}

	/**
	 * @return the risk_delivery_address_streetName
	 */
	public String getRisk_delivery_address_streetName() {
		return risk_delivery_address_streetName;
	}

	/**
	 * @param risk_delivery_address_streetName
	 *            the risk_delivery_address_streetName to set
	 */
	public void setRisk_delivery_address_streetName(String risk_delivery_address_streetName) {
		this.risk_delivery_address_streetName = risk_delivery_address_streetName;
	}

	/**
	 * @return the risk_delivery_address_building_number
	 */
	public String getRisk_delivery_address_building_number() {
		return risk_delivery_address_building_number;
	}

	/**
	 * @param risk_delivery_address_building_number
	 *            the risk_delivery_address_building_number to set
	 */
	public void setRisk_delivery_address_building_number(String risk_delivery_address_building_number) {
		this.risk_delivery_address_building_number = risk_delivery_address_building_number;
	}

	/**
	 * @return the risk_delivery_address_postcode
	 */
	public String getRisk_delivery_address_postcode() {
		return risk_delivery_address_postcode;
	}

	/**
	 * @param risk_delivery_address_postcode
	 *            the risk_delivery_address_postcode to set
	 */
	public void setRisk_delivery_address_postcode(String risk_delivery_address_postcode) {
		this.risk_delivery_address_postcode = risk_delivery_address_postcode;
	}

	/**
	 * @return the risk_delivery_address_townname
	 */
	public String getRisk_delivery_address_townname() {
		return risk_delivery_address_townname;
	}

	/**
	 * @param risk_delivery_address_townname
	 *            the risk_delivery_address_townname to set
	 */
	public void setRisk_delivery_address_townname(String risk_delivery_address_townname) {
		this.risk_delivery_address_townname = risk_delivery_address_townname;
	}

	/**
	 * @return the risk_delivery_address_county_subdivision
	 */
	public String getRisk_delivery_address_county_subdivision() {
		return risk_delivery_address_county_subdivision;
	}

	/**
	 * @param risk_delivery_address_county_subdivision
	 *            the risk_delivery_address_county_subdivision to set
	 */
	public void setRisk_delivery_address_county_subdivision(String risk_delivery_address_county_subdivision) {
		this.risk_delivery_address_county_subdivision = risk_delivery_address_county_subdivision;
	}

	/**
	 * @return the risk_delivery_address_country
	 */
	public String getRisk_delivery_address_country() {
		return risk_delivery_address_country;
	}

	/**
	 * @param risk_delivery_address_country
	 *            the risk_delivery_address_country to set
	 */
	public void setRisk_delivery_address_country(String risk_delivery_address_country) {
		this.risk_delivery_address_country = risk_delivery_address_country;
	}

	/**
	 * @return the order_number
	 */
	public String getOrder_number() {
		return order_number;
	}

	/**
	 * @param order_number
	 *            the order_number to set
	 */
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}

	/**
	 * @return the order_date_time
	 */
	public Date getOrder_date_time() {
		return order_date_time;
	}

	/**
	 * @param order_date_time
	 *            the order_date_time to set
	 */
	public void setOrder_date_time(Date order_date_time) {
		this.order_date_time = order_date_time;
	}

	/**
	 * @return the order_description
	 */
	public String getOrder_description() {
		return order_description;
	}

	/**
	 * @param order_description
	 *            the order_description to set
	 */
	public void setOrder_description(String order_description) {
		this.order_description = order_description;
	}

	/**
	 * @return the order_unit_count
	 */
	public int getOrder_unit_count() {
		return order_unit_count;
	}

	/**
	 * @param order_unit_count
	 *            the order_unit_count to set
	 */
	public void setOrder_unit_count(int order_unit_count) {
		this.order_unit_count = order_unit_count;
	}

	/**
	 * @return the order_currency
	 */
	public String getOrder_currency() {
		return order_currency;
	}

	/**
	 * @param order_currency
	 *            the order_currency to set
	 */
	public void setOrder_currency(String order_currency) {
		this.order_currency = order_currency;
	}

	/**
	 * @return the order_currency_exponent
	 */
	public float getOrder_currency_exponent() {
		return order_currency_exponent;
	}

	/**
	 * @param order_currency_exponent
	 *            the order_currency_exponent to set
	 */
	public void setOrder_currency_exponent(float order_currency_exponent) {
		this.order_currency_exponent = order_currency_exponent;
	}

	/**
	 * @return the order_commission_percentage
	 */
	public float getOrder_commission_percentage() {
		return order_commission_percentage;
	}

	/**
	 * @param order_commission_percentage
	 *            the order_commission_percentage to set
	 */
	public void setOrder_commission_percentage(float order_commission_percentage) {
		this.order_commission_percentage = order_commission_percentage;
	}

	/**
	 * @return the order_discount_percentage
	 */
	public float getOrder_discount_percentage() {
		return order_discount_percentage;
	}

	/**
	 * @param order_discount_percentage
	 *            the order_discount_percentage to set
	 */
	public void setOrder_discount_percentage(float order_discount_percentage) {
		this.order_discount_percentage = order_discount_percentage;
	}

	/**
	 * @return the order_vat_percentage
	 */
	public float getOrder_vat_percentage() {
		return order_vat_percentage;
	}

	/**
	 * @param order_vat_percentage
	 *            the order_vat_percentage to set
	 */
	public void setOrder_vat_percentage(float order_vat_percentage) {
		this.order_vat_percentage = order_vat_percentage;
	}

	/**
	 * @return the order_gross_amount
	 */
	public float getOrder_gross_amount() {
		return order_gross_amount;
	}

	/**
	 * @param order_gross_amount
	 *            the order_gross_amount to set
	 */
	public void setOrder_gross_amount(float order_gross_amount) {
		this.order_gross_amount = order_gross_amount;
	}

	/**
	 * @return the order_net_amount
	 */
	public float getOrder_net_amount() {
		return order_net_amount;
	}

	/**
	 * @param order_net_amount
	 *            the order_net_amount to set
	 */
	public void setOrder_net_amount(float order_net_amount) {
		this.order_net_amount = order_net_amount;
	}

	/**
	 * @return the order_vat_amount
	 */
	public float getOrder_vat_amount() {
		return order_vat_amount;
	}

	/**
	 * @param order_vat_amount
	 *            the order_vat_amount to set
	 */
	public void setOrder_vat_amount(float order_vat_amount) {
		this.order_vat_amount = order_vat_amount;
	}

	/**
	 * @return the order_home_currency
	 */
	public String getOrder_home_currency() {
		return order_home_currency;
	}

	/**
	 * @param order_home_currency
	 *            the order_home_currency to set
	 */
	public void setOrder_home_currency(String order_home_currency) {
		this.order_home_currency = order_home_currency;
	}

	/**
	 * @return the order_home_currency_exponent
	 */
	public float getOrder_home_currency_exponent() {
		return order_home_currency_exponent;
	}

	/**
	 * @param order_home_currency_exponent
	 *            the order_home_currency_exponent to set
	 */
	public void setOrder_home_currency_exponent(float order_home_currency_exponent) {
		this.order_home_currency_exponent = order_home_currency_exponent;
	}

	/**
	 * @return the order_home_currency_order_amount
	 */
	public float getOrder_home_currency_order_amount() {
		return order_home_currency_order_amount;
	}

	/**
	 * @param order_home_currency_order_amount
	 *            the order_home_currency_order_amount to set
	 */
	public void setOrder_home_currency_order_amount(float order_home_currency_order_amount) {
		this.order_home_currency_order_amount = order_home_currency_order_amount;
	}

	/**
	 * @return the order_exchange_rate
	 */
	public float getOrder_exchange_rate() {
		return order_exchange_rate;
	}

	/**
	 * @param order_exchange_rate
	 *            the order_exchange_rate to set
	 */
	public void setOrder_exchange_rate(float order_exchange_rate) {
		this.order_exchange_rate = order_exchange_rate;
	}

	/**
	 * @return the order_recurring_payment
	 */
	public float getOrder_recurring_payment() {
		return order_recurring_payment;
	}

	/**
	 * @param order_recurring_payment
	 *            the order_recurring_payment to set
	 */
	public void setOrder_recurring_payment(float order_recurring_payment) {
		this.order_recurring_payment = order_recurring_payment;
	}

	/**
	 * @return the order_recurring_frequency
	 */
	public int getOrder_recurring_frequency() {
		return order_recurring_frequency;
	}

	/**
	 * @param order_recurring_frequency
	 *            the order_recurring_frequency to set
	 */
	public void setOrder_recurring_frequency(int order_recurring_frequency) {
		this.order_recurring_frequency = order_recurring_frequency;
	}

	/**
	 * @return the order_installments
	 */
	public int getOrder_installments() {
		return order_installments;
	}

	/**
	 * @param order_installments
	 *            the order_installments to set
	 */
	public void setOrder_installments(int order_installments) {
		this.order_installments = order_installments;
	}

	/**
	 * @return the order_recurring_expiry
	 */
	public Date getOrder_recurring_expiry() {
		return order_recurring_expiry;
	}

	/**
	 * @param order_recurring_expiry
	 *            the order_recurring_expiry to set
	 */
	public void setOrder_recurring_expiry(Date order_recurring_expiry) {
		this.order_recurring_expiry = order_recurring_expiry;
	}

	/**
	 * @return the order_recurring_payment_subscription_name
	 */
	public String getOrder_recurring_payment_subscription_name() {
		return order_recurring_payment_subscription_name;
	}

	/**
	 * @param order_recurring_payment_subscription_name
	 *            the order_recurring_payment_subscription_name to set
	 */
	public void setOrder_recurring_payment_subscription_name(String order_recurring_payment_subscription_name) {
		this.order_recurring_payment_subscription_name = order_recurring_payment_subscription_name;
	}

	/**
	 * @return the order_recurring_payment_subscription_code
	 */
	public String getOrder_recurring_payment_subscription_code() {
		return order_recurring_payment_subscription_code;
	}

	/**
	 * @param order_recurring_payment_subscription_code
	 *            the order_recurring_payment_subscription_code to set
	 */
	public void setOrder_recurring_payment_subscription_code(String order_recurring_payment_subscription_code) {
		this.order_recurring_payment_subscription_code = order_recurring_payment_subscription_code;
	}

	/**
	 * @return the maker_date
	 */
	public Date getMaker_date() {
		return maker_date;
	}

	/**
	 * @param maker_date
	 *            the maker_date to set
	 */
	public void setMaker_date(Date maker_date) {
		this.maker_date = maker_date;
	}

	/**
	 * @return the checker_date
	 */
	public Date getChecker_date() {
		return checker_date;
	}

	/**
	 * @param checker_date
	 *            the checker_date to set
	 */
	public void setChecker_date(Date checker_date) {
		this.checker_date = checker_date;
	}

	/**
	 * @return the maker_id
	 */
	public String getMaker_id() {
		return maker_id;
	}

	/**
	 * @param maker_id
	 *            the maker_id to set
	 */
	public void setMaker_id(String maker_id) {
		this.maker_id = maker_id;
	}

	/**
	 * @return the checker_id
	 */
	public String getChecker_id() {
		return checker_id;
	}

	/**
	 * @param checker_id
	 *            the checker_id to set
	 */
	public void setChecker_id(String checker_id) {
		this.checker_id = checker_id;
	}

	/**
	 * @return the modified_by
	 */
	public String getModified_by() {
		return modified_by;
	}

	/**
	 * @param modified_by
	 *            the modified_by to set
	 */
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	/**
	 * @return the modified_date
	 */
	public Date getModified_date() {
		return modified_date;
	}

	/**
	 * @param modified_date
	 *            the modified_date to set
	 */
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}

}

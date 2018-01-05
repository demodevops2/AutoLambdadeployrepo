package org.vp.pis.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.vp.pis.domain.CreditorAccount;
import org.vp.pis.domain.CreditorAgent;
import org.vp.pis.domain.Data;
import org.vp.pis.domain.DebtorAccount;
import org.vp.pis.domain.DebtorAgent;
import org.vp.pis.domain.DeliveryAddress;
import org.vp.pis.domain.Initiation;
import org.vp.pis.domain.InstructedAmount;
import org.vp.pis.domain.OBPaymentSetupResponse1;
import org.vp.pis.domain.RemittanceInformation;
import org.vp.pis.domain.Risk;
import org.vp.pis.model.Payment;
import org.vp.pis.repository.PaymentRepository;

/**
 * This class consists of generating payment id and payment submission id
 * Validate the request JSON of both payment setup and submission and validate
 * the initiation with submission values
 *
 */
public class PaymentsUtil {

	@Autowired
	PaymentRepository paymentRepository;

	public static final String STATUS_REJECTED = "Rejected";

	final static Logger LOG = LoggerFactory.getLogger(PaymentsUtil.class);

	/**
	 * This method validates the request JSON
	 * 
	 * @param paymentRequestBody
	 * @param mapForPost
	 * @return map with status of payment and error
	 */

	public Map<String, Object> validatePaymentRequest(OBPaymentSetupResponse1 paymentRequestBody,
			Map<String, Object> mapForPost) {
		LOG.info("Method validatePaymentRequest Starts");
		String status = "";
		List<String> paymentContextCodevalidation = Arrays.asList("BillPayment", "EcommerceGoods", "EcommerceServices",
				"Other", "PersonToPerson");
		List<String> agentType = Arrays.asList("BICFI", "UKSortCode");
		List<String> accountType = Arrays.asList("IBAN", "BBAN");

		try {

			Data data = new Data();
			Risk risk = new Risk();
			InstructedAmount instructedAmount = new InstructedAmount();
			Initiation initiation = new Initiation();
			DebtorAgent debtorAgent = new DebtorAgent();
			DebtorAccount debtorAccount = new DebtorAccount();
			CreditorAgent creditorAgent = new CreditorAgent();
			CreditorAccount creditorAccount = new CreditorAccount();
			RemittanceInformation remittanceInformation = new RemittanceInformation();
			DeliveryAddress deliveryAddress = new DeliveryAddress();
			data = paymentRequestBody.getData();
			risk = paymentRequestBody.getRisk();
			initiation = data.getInitiation();

			String endToEndIdentification = initiation.getEndToEndIdentification();
			String instructionIdentification = initiation.getInstructionIdentification();
			instructedAmount = initiation.getInstructedAmount();
			String amount = instructedAmount.getAmount();
			String currency = instructedAmount.getCurrency();
			String merchantCategoryCode = risk.getMerchantCategoryCode();
			debtorAgent = initiation.getDebtorAgent();
			String debtorAgentSchema = "";
			String debtorAgentIdentification = "";
			if (debtorAgent != null) {
				debtorAgentSchema = debtorAgent.getSchemeName();
				debtorAgentIdentification = debtorAgent.getIdentification();
			}
			debtorAccount = initiation.getDebtorAccount();
			String debtorAccountSchema = "";
			String debtorAccountIdentification = "";
			String debtorAccount2ryIdentification = "";
			String debtorAccountName = "";
			if (debtorAccount != null) {
				LOG.info("Inside Debtor Account");
				debtorAccountSchema = debtorAccount.getSchemeName();
				debtorAccountIdentification = debtorAccount.getIdentification();
				debtorAccount2ryIdentification = debtorAccount.getSecondaryIdentification();
				debtorAccountName = debtorAccount.getName();
			}
			creditorAgent = initiation.getCreditorAgent();
			String creditorAgentSchemaName = "";
			String creditorAgentIdentification = "";
			if (creditorAgent != null) {
				creditorAgentSchemaName = creditorAgent.getSchemeName();
				creditorAgentIdentification = creditorAgent.getIdentification();
			}
			creditorAccount = initiation.getCreditorAccount();
			String creditorAccountSchema = "";
			String creditorAccountIdentification = "";
			String creditorAccount2ryIdentification = "";
			String creditorAccountName = "";
			if (creditorAccount != null) {
				creditorAccountSchema = creditorAccount.getSchemeName();
				creditorAccountIdentification = creditorAccount.getIdentification();
				creditorAccount2ryIdentification = creditorAccount.getSecondaryIdentification();
				creditorAccountName = creditorAccount.getName();
			}
			remittanceInformation = initiation.getRemittanceInformation();
			String reference = "";
			String unstructured = "";
			String merchantCustomerIdentification = "";
			String paymentContextCode = "";
			if (remittanceInformation != null) {
				reference = remittanceInformation.getReference();
				unstructured = remittanceInformation.getUnstructured();
				merchantCustomerIdentification = risk.getMerchantCustomerIdentification();
				paymentContextCode = risk.getPaymentContextCode();
			}
			deliveryAddress = risk.getDeliveryAddress();
			String[] address = null;
			String buildingNumber = "";
			String townName = "";
			String country = "";
			String postCode = "";
			String streetName = "";
			String[] countrysub = null;

			if (deliveryAddress != null) {
				address = deliveryAddress.getAddressLine();
				buildingNumber = deliveryAddress.getBuildingNumber();
				townName = deliveryAddress.getTownName();
				country = deliveryAddress.getCountry();
				postCode = deliveryAddress.getPostCode();
				streetName = deliveryAddress.getStreetName();
				countrysub = deliveryAddress.getCountrySubDivision();
			}

			Float amountdec = Float.valueOf(amount.replace(",", ""));
			boolean executeDA = false;
			LOG.info(" Delivery address" + deliveryAddress);
			if ((address != null && buildingNumber != null && townName != null && country != null && postCode != null
					&& streetName != null && countrysub != null && deliveryAddress != null)
					|| "EcommerceGoods".equalsIgnoreCase(paymentContextCode)) {
				executeDA = true;
			}
			LOG.info("countrysub" + countrysub);
			if (instructionIdentification == null || instructionIdentification == ""
					|| ((instructionIdentification.length()) < 1) || ((instructionIdentification.length()) > 35)) {
				status = STATUS_REJECTED;
				LOG.info("Instruction Identification doesn't match Validation on Payment Setup");
				mapForPost.put("internalStatusCode",
						"Instruction Identification doesn't match Validation on Payment Setup");
			} else if (endToEndIdentification == null || ((endToEndIdentification.length()) < 1)
					|| ((endToEndIdentification.length()) > 35)) {
				status = STATUS_REJECTED;
				LOG.info("End to End Identification doesn't match Validation on Payment Setup");
				mapForPost.put("internalStatusCode",
						"End to End Identification  doesn't match Validation on Payment Setup");
			} else if (amountdec <= 0 || amountdec == null) {
				status = STATUS_REJECTED;
				LOG.info("Amount doesn't match Validation on Payment Setup");
				mapForPost.put("internalStatusCode", "Amount doesn't match Validation on Payment Setup");
			} else if (currency == null || currency == "" || !(currency).matches("[A-Z]{3,3}")) {
				status = STATUS_REJECTED;
				LOG.info("Currency doesn't match Validation on Payment Setup");
				mapForPost.put("internalStatusCode", "Currency doesn't match Validation on Payment Setup");
			} else if (unstructured == "" || unstructured == null || unstructured.length() < 1
					|| unstructured.length() > 140) {
				status = STATUS_REJECTED;
				LOG.info("Unstructured doesn't match Validation on Payment Setup");
				mapForPost.put("internalStatusCode", "Unstructured doesn't match Validation on Payment Setup");
			} else if (reference == "" || reference == null || reference.length() < 1 || reference.length() > 35) {
				status = STATUS_REJECTED;
				LOG.info("Reference doesn't match Validation on Payment Setup");
				mapForPost.put("internalStatusCode", "Reference doesn't match Validation on Payment Setup");
			} else if (paymentContextCode == null || !paymentContextCodevalidation.contains(paymentContextCode)) {
				status = STATUS_REJECTED;
				LOG.info("Payment Context Code doesn't match Validation on Payment Setup");
				mapForPost.put("internalStatusCode", "Payment Context Code doesn't match Validation on Payment Setup");
			} else if (!agentType.contains(creditorAgentSchemaName) || creditorAgentSchemaName == ""
					|| creditorAgentSchemaName == null) {
				status = STATUS_REJECTED;
				mapForPost.put("internalStatusCode", "Creditor Agent Schema doesn't match Validation on Payment Setup");
				LOG.info("Creditor Agent Schema doesn't match Validation on Payment Setup");
			} else if (creditorAgentIdentification == null || creditorAgentIdentification == ""
					|| String.valueOf(creditorAgentIdentification).length() < 1
					|| String.valueOf(creditorAgentIdentification).length() > 35) {
				status = STATUS_REJECTED;
				LOG.info("Creditor Agent Identification doesn't match Validation on Payment Setup");
				mapForPost.put("internalStatusCode",
						"Creditor Agent Identification doesn't match Validation on Payment Setup");
			} else if (!accountType.contains(creditorAccountSchema) || creditorAccountSchema == ""
					|| creditorAccountSchema == null) {
				mapForPost.put("internalStatusCode",
						"Creditor Account Schema doesn't match Validation on Payment Setup");
				status = STATUS_REJECTED;
				LOG.info("Creditor Account Schema doesn't match Validation on Payment Setup");
			} else if (String.valueOf(creditorAccountIdentification).length() < 1
					|| String.valueOf(creditorAccountIdentification).length() > 34
					|| creditorAccountIdentification == null || String.valueOf(creditorAccountIdentification) == "") {
				status = STATUS_REJECTED;
				LOG.info("Creditor Account Identification doesn't match Validation on Payment Setup");
				mapForPost.put("internalStatusCode",
						"Creditor Account Identification doesn't match Validation on Payment Setup");
			} else if (creditorAccountName == "" || creditorAccountName == null || creditorAccountName.length() < 1
					|| creditorAccountName.length() > 70) {
				status = STATUS_REJECTED;
				LOG.info("Creditor Account Name doesn't match Validation on Payment Setup");
				mapForPost.put("internalStatusCode", "Creditor Account Name doesn't match Validation on Payment Setup");
			} else if (!("PersonToPerson".equalsIgnoreCase(paymentContextCode))) {
				if (creditorAccount2ryIdentification == "" || creditorAccount2ryIdentification == null
						|| creditorAccount2ryIdentification.length() < 1
						|| creditorAccount2ryIdentification.length() > 34) {
					status = STATUS_REJECTED;
					LOG.info("Creditor Account Secondary Identification doesn't match Validation on Payment Setup");
					mapForPost.put("internalStatusCode",
							"Creditor Account Secondary Identification doesn't match Validation on Payment Setup");
				}
			}
			boolean execute = false;
			if (debtorAgent != null) {
				if (debtorAgentIdentification == null || debtorAgentIdentification == ""
						|| debtorAgentIdentification.length() < 1 || debtorAgentIdentification.length() > 35) {
					execute = true;
					status = STATUS_REJECTED;
					mapForPost.put("internalStatusCode",
							"Debtor Agent Identification Length doesn't match Validation on Payment Setup");
					LOG.info("Debtor Agent Identification Length doesn't match Validation on Payment Setup");
				}
				if (!agentType.contains(debtorAgentSchema) || debtorAgentSchema == null || debtorAgentSchema == "") {
					execute = true;
					status = STATUS_REJECTED;
					LOG.info("Debtor Agent Schema doesn't match Validation on Payment Setup" + debtorAgentSchema);
					mapForPost.put("internalStatusCode",
							"Debtor Agent Schema doesn't match Validation on Payment Setup");
				}
			}
			if (!execute && debtorAccount != null) {
				LOG.info("Debtor Account Check");
				if (!accountType.contains(debtorAccountSchema) || debtorAccountSchema == null
						|| debtorAccountSchema == "") {
					status = STATUS_REJECTED;
					mapForPost.put("internalStatusCode",
							"Debtor Account Schema doesn't match Validation on Payment Setup");
					LOG.info("Debtor Account Schema doesn't match Validation on Payment Setup");
					execute = true;
				} else if (String.valueOf(debtorAccountIdentification).length() < 1
						|| String.valueOf(debtorAccountIdentification).length() > 34
						|| String.valueOf(debtorAccountIdentification) == "" || debtorAccountIdentification == null) {
					execute = true;
					status = STATUS_REJECTED;
					LOG.info("Debtor Account Identification doesn't match Validation on Payment Setup");
					mapForPost.put("internalStatusCode",
							"Debtor Account Identification doesn't match Validation on Payment Setup");
				} else if (debtorAccountName == "" || debtorAccountName == null || debtorAccountName.length() < 1
						|| debtorAccountName.length() > 70) {
					execute = true;
					status = STATUS_REJECTED;
					LOG.info("Debtor Account Name doesn't match Validation on Payment Setup");
					mapForPost.put("internalStatusCode",
							"Debtor Account Name doesn't match Validation on Payment Setup");
				} else if (!("PersonToPerson".equalsIgnoreCase(paymentContextCode))) {
					if (debtorAccount2ryIdentification == "" || debtorAccount2ryIdentification == null
							|| debtorAccount2ryIdentification.length() < 1
							|| debtorAccount2ryIdentification.length() > 34) {
						execute = true;
						status = STATUS_REJECTED;
						LOG.info("Debtor Account Secondary Identification doesn't match Validation on Payment Setup");
						mapForPost.put("internalStatusCode",
								"Debtor Account Secondary Identification doesn't match Validation on Payment Setup");
					}
				}
			}

			if (!execute && (merchantCategoryCode != null || "EcommerceGoods".equalsIgnoreCase(paymentContextCode)
					|| "EcommerceServices".equalsIgnoreCase(paymentContextCode))) {
				if (merchantCategoryCode == null || merchantCategoryCode == "" || merchantCategoryCode.length() < 3
						|| merchantCategoryCode.length() > 4) {
					execute = true;
					status = STATUS_REJECTED;
					LOG.info("Merchant Category Code doesn't match Validation on Payment Setup");
					mapForPost.put("internalStatusCode",
							"Merchant Category Code doesn't match Validation on Payment Setup");
				}
			}

			if (!execute
					&& (merchantCustomerIdentification != null || "EcommerceGoods".equalsIgnoreCase(paymentContextCode)
							|| "EcommerceServices".equalsIgnoreCase(paymentContextCode))) {
				if (merchantCustomerIdentification == "" || merchantCustomerIdentification == null
						|| merchantCustomerIdentification.length() < 1
						|| merchantCustomerIdentification.length() > 70) {
					status = STATUS_REJECTED;
					LOG.info("Merchant Customer Identification doesn't match Validation on Payment Setup");
					mapForPost.put("internalStatusCode",
							"Merchant Customer Identification doesn't match Validation on Payment Setup");
				}
			}
			if (executeDA) {
				if (buildingNumber == "" || String.valueOf(buildingNumber).length() < 1
						|| String.valueOf(buildingNumber).length() > 16) {
					status = STATUS_REJECTED;
					LOG.info("Building Number doesn't match Validation on Payment Setup");
					mapForPost.put("internalStatusCode", "Building Number doesn't match Validation on Payment Setup");
				} else if ((address.equals("") || address == null || Arrays.asList(address).isEmpty()
						|| address[0].length() < 1 || address[1].length() < 1 || address[0].length() > 70
						|| address[1].length() > 70)) {
					status = STATUS_REJECTED;
					LOG.info("Address doesn't match Validation on Payment Setup");
					mapForPost.put("internalStatusCode", "Address doesn't match Validation on Payment Setup");
				} else if (townName == "" || townName == null || townName.length() < 1 || townName.length() > 35) {
					status = STATUS_REJECTED;
					LOG.info("Town Name doesn't match Validation on Payment Setup");
					mapForPost.put("internalStatusCode", "Town Name doesn't match Validation on Payment Setup");
				} else if (postCode == "" || postCode == null || postCode.length() < 1 || postCode.length() > 16) {
					status = STATUS_REJECTED;
					LOG.info("Post Code doesn't match Validation on Payment Setup");
					mapForPost.put("internalStatusCode", "Post Code doesn't match Validation on Payment Setup");
				} else if (streetName == "" || streetName == null || streetName.length() < 1
						|| streetName.length() > 70) {
					status = STATUS_REJECTED;
					LOG.info("Street Name doesn't match Validation on Payment Setup");
					mapForPost.put("internalStatusCode", "Street Name doesn't match Validation on Payment Setup");
				} else if (countrysub.equals("") || countrysub == null || Arrays.asList(countrysub).isEmpty()
						|| (countrysub[0].length()) < 1 || (countrysub[0].length()) > 35) {
					status = STATUS_REJECTED;
					LOG.info("Country Sub Division doesn't match Validation on Payment Setup");
					mapForPost.put("internalStatusCode",
							"Country Sub Division doesn't match Validation on Payment Setup");
				} else if (country == null || !(country).matches("[A-Z]{2,2}")) {
					status = STATUS_REJECTED;
					LOG.info("Country doesn't match Validation on Payment Setup");
					mapForPost.put("internalStatusCode", "Country doesn't match Validation on Payment Setup");
				}
			}
		} catch (Exception exception) {
			LOG.info(getStackTrace(exception));
		}
		mapForPost.put("status", status);
		LOG.info("Method validatePaymentRequest ends");
		return mapForPost;
	}

	/**
	 * This method validates the request JSON
	 * 
	 * @param paymentRequestBody
	 * @param mapForPost
	 * @return map with status of payment and error
	 */

	public Map<String, Object> validatePaymentRequestTableData(OBPaymentSetupResponse1 paymentRequestBody,
			Map<String, Object> mapForPost, List<Payment> paymentValue) {
		LOG.info("Method validatePaymentRequestTableData Starts");
		String status = "";
		try {

			List<Payment> paymentValues = paymentValue;

			// JSON values
			Data data = new Data();
			Risk risk = new Risk();
			InstructedAmount instructedAmount = new InstructedAmount();
			Initiation initiation = new Initiation();
			DebtorAgent debtorAgent = new DebtorAgent();
			DebtorAccount debtorAccount = new DebtorAccount();
			CreditorAgent creditorAgent = new CreditorAgent();
			CreditorAccount creditorAccount = new CreditorAccount();
			RemittanceInformation remittanceInformation = new RemittanceInformation();
			DeliveryAddress deliveryAddress = new DeliveryAddress();
			data = paymentRequestBody.getData();
			risk = paymentRequestBody.getRisk();
			initiation = data.getInitiation();

			String endToEndIdentification = initiation.getEndToEndIdentification();
			String instructionIdentification = initiation.getInstructionIdentification();
			instructedAmount = initiation.getInstructedAmount();
			String amount = instructedAmount.getAmount();
			String currency = instructedAmount.getCurrency();
			String merchantCategoryCode = risk.getMerchantCategoryCode();
			debtorAgent = initiation.getDebtorAgent();
			String debtorAgentSchema = "";
			String debtorAgentIdentification = "";
			if (debtorAgent != null) {
				debtorAgentSchema = debtorAgent.getSchemeName();
				debtorAgentIdentification = debtorAgent.getIdentification();
			}
			debtorAccount = initiation.getDebtorAccount();
			String debtorAccountSchema = "";
			String debtorAccountIdentification = "";
			String debtorAccount2ryIdentification = "";
			String debtorAccountName = "";
			if (debtorAccount != null) {
				debtorAccountSchema = debtorAccount.getSchemeName();
				debtorAccountIdentification = debtorAccount.getIdentification();
				debtorAccount2ryIdentification = debtorAccount.getSecondaryIdentification();
				debtorAccountName = debtorAccount.getName();
			}
			creditorAgent = initiation.getCreditorAgent();
			String creditorAgentSchemaName = "";
			String creditorAgentIdentification = "";
			if (creditorAgent != null) {
				creditorAgentSchemaName = creditorAgent.getSchemeName();
				creditorAgentIdentification = creditorAgent.getIdentification();
			}
			creditorAccount = initiation.getCreditorAccount();
			String creditorAccountSchema = "";
			String creditorAccountIdentification = "";
			String creditorAccount2ryIdentification = "";
			String creditorAccountName = "";
			if (creditorAccount != null) {
				creditorAccountSchema = creditorAccount.getSchemeName();
				creditorAccountIdentification = creditorAccount.getIdentification();
				creditorAccount2ryIdentification = creditorAccount.getSecondaryIdentification();
				creditorAccountName = creditorAccount.getName();
			}
			remittanceInformation = initiation.getRemittanceInformation();
			String reference = "";
			String unstructured = "";
			String merchantCustomerIdentification = "";
			String paymentContextCode = "";
			if (remittanceInformation != null) {
				reference = remittanceInformation.getReference();
				unstructured = remittanceInformation.getUnstructured();
				merchantCustomerIdentification = risk.getMerchantCustomerIdentification();
				paymentContextCode = risk.getPaymentContextCode();
			}
			deliveryAddress = risk.getDeliveryAddress();
			String[] address = null;
			String buildingNumber = "";
			String townName = "";
			String country = "";
			String postCode = "";
			String streetName = "";
			String[] countrysub = null;

			if (deliveryAddress != null) {
				address = deliveryAddress.getAddressLine();
				buildingNumber = deliveryAddress.getBuildingNumber();
				townName = deliveryAddress.getTownName();
				country = deliveryAddress.getCountry();
				postCode = deliveryAddress.getPostCode();
				streetName = deliveryAddress.getStreetName();
				countrysub = deliveryAddress.getCountrySubDivision();
			}

			// Compare table values and JSON values
			if (!instructionIdentification.equalsIgnoreCase(paymentValues.get(0).getInstruction_identification())) {
				status = STATUS_REJECTED;
				LOG.info(
						"Instruction Indentification of Payment Submisssion doesn't match with corresponding Instruction Indentification of Payment Resource");
				mapForPost.put("internalStatusCode",
						"Instruction Indentification of Payment Submisssion doesn't match with corresponding Instruction Indentification of Payment Resource");
			} else if (!endToEndIdentification.equalsIgnoreCase(paymentValues.get(0).getEnd_to_end_identification())) {
				status = STATUS_REJECTED;
				LOG.info(
						"End to End Indentification of Payment Submisssion doesn't match with corresponding End to End Indentification of Payment Resource");
				mapForPost.put("internalStatusCode",
						"End to End Indentification of Payment Submisssion doesn't match with corresponding End to End Indentification of Payment Resource");
			} else if (Float.parseFloat(amount) != (paymentValues.get(0).getTransaction_amount())) {
				status = STATUS_REJECTED;
				LOG.info("Amount of Payment Submisssion doesn't match with corresponding Amount of Payment Resource");
				mapForPost.put("internalStatusCode",
						"Amount of Payment Submisssion doesn't match with corresponding Amount of Payment Resource");
			} else if (!currency.equalsIgnoreCase(paymentValues.get(0).getTransaction_currency())) {
				status = STATUS_REJECTED;
				LOG.info(
						"Currency of Payment Submisssion doesn't match with corresponding Currency of Payment Resource");
				mapForPost.put("internalStatusCode",
						"Currency of Payment Submisssion doesn't match with corresponding Currency of Payment Resource");

			} else if (!creditorAgentSchemaName.equalsIgnoreCase(paymentValues.get(0).getCreditor_agent_schemename())) {
				status = STATUS_REJECTED;
				mapForPost.put("internalStatusCode",
						"Creditor Agent Schema of Payment Submisssion doesn't match with corresponding Creditor Agent Schema of Payment Resource");
				LOG.info(
						"Creditor Agent Schema of Payment Submisssion doesn't match with corresponding Creditor Agent Schema of Payment Resource");
			} else if (!creditorAgentIdentification
					.equalsIgnoreCase(paymentValues.get(0).getCreditor_agent_identification())) {
				status = STATUS_REJECTED;
				LOG.info(
						"Creditor Agent Identification of Payment Submisssion doesn't match with corresponding Creditor Agent Identification of Payment Resource");
				mapForPost.put("internalStatusCode",
						"Creditor Agent Identification of Payment Submisssion doesn't match with corresponding Creditor Agent Identification of Payment Resource");
			} else if (!creditorAccountSchema.equalsIgnoreCase(paymentValues.get(0).getCreditor_account_schemename())) {
				mapForPost.put("internalStatusCode",
						"Creditor Account Schema of Payment Submisssion doesn't match with corresponding Creditor Account Schema of Payment Resource");
				status = STATUS_REJECTED;
				LOG.info(
						"Creditor Account Schema of Payment Submisssion doesn't match with corresponding Creditor Account Schema of Payment Resource");
			} else if (!creditorAccountIdentification
					.equalsIgnoreCase(paymentValues.get(0).getCreditor_account_identification())) {
				status = STATUS_REJECTED;
				LOG.info(
						"Creditor Account Identification of Payment Submisssion doesn't match with corresponding Creditor Account Identification of Payment Resource");
				mapForPost.put("internalStatusCode",
						"Creditor Account Identification of Payment Submisssion doesn't match with corresponding Creditor Account Identification of Payment Resource");
			} else if (!creditorAccountName.equalsIgnoreCase(paymentValues.get(0).getCreditor_account_name())) {
				status = STATUS_REJECTED;
				LOG.info(
						"Creditor Account Name of Payment Submisssion doesn't match with corresponding Creditor Account Name of Payment Resource");
				mapForPost.put("internalStatusCode",
						"Creditor Account Name of Payment Submisssion doesn't match with corresponding Creditor Account Name of Payment Resource");
			} else if (!creditorAccount2ryIdentification
					.equalsIgnoreCase(paymentValues.get(0).getCreditor_secondary_identification())) {
				status = STATUS_REJECTED;
				LOG.info(
						"Creditor Account Secondary Identification of Payment Submisssion doesn't match with corresponding Creditor Account Secondary Identification of Payment Resource");
				mapForPost.put("internalStatusCode",
						"Creditor Account Secondary Identification of Payment Submisssion doesn't match with corresponding Creditor Account Secondary Identification of Payment Resource");
			} else if (!unstructured.equalsIgnoreCase(paymentValues.get(0).getRemittance_unstructured_reference())) {
				status = STATUS_REJECTED;
				LOG.info(
						"Unstructured of Payment Submisssion doesn't match with corresponding Unstructured of Payment Resource");
				mapForPost.put("internalStatusCode",
						"Unstructured of Payment Submisssion doesn't match with corresponding Unstructured of Payment Resource");
			} else if (!reference.equalsIgnoreCase(paymentValues.get(0).getRemittance_reference())) {
				status = STATUS_REJECTED;
				LOG.info(
						"Reference of Payment Submisssion doesn't match with corresponding Reference of Payment Resource");
				mapForPost.put("internalStatusCode",
						"Reference of Payment Submisssion doesn't match with corresponding Reference of Payment Resource");
			} else if (!paymentContextCode.equalsIgnoreCase(paymentValues.get(0).getRisk_payment_context_code())) {
				status = STATUS_REJECTED;
				LOG.info(
						"Payment Context Code of Payment Submisssion doesn't match with corresponding Payment Context Code of Payment Resource");
				mapForPost.put("internalStatusCode",
						"Payment Context Code of Payment Submisssion doesn't match with corresponding Payment Context Code of Payment Resource");
			} else if (!merchantCategoryCode.equalsIgnoreCase(paymentValues.get(0).getRisk_merchant_category_code())) {
				status = STATUS_REJECTED;
				LOG.info(
						"Merchant Category Code of Payment Submisssion doesn't match with corresponding Merchant Category Code of Payment Resource");
				mapForPost.put("internalStatusCode",
						"Merchant Category Code of Payment Submisssion doesn't match with corresponding Merchant Category Code of Payment Resource");
			} else if (!merchantCustomerIdentification
					.equalsIgnoreCase(paymentValues.get(0).getRisk_merchant_customer_identification())) {
				status = STATUS_REJECTED;
				LOG.info(
						"Merchant Customer Identification of Payment Submisssion doesn't match with corresponding Merchant Customer Identification of Payment Resource");
				mapForPost.put("internalStatusCode",
						"Merchant Customer Identification of Payment Submisssion doesn't match with corresponding Merchant Customer Identification of Payment Resource");
			} else if (!buildingNumber.equals(paymentValues.get(0).getRisk_delivery_address_building_number())) {
				status = STATUS_REJECTED;
				LOG.info(
						"Building Number of Payment Submisssion doesn't match with corresponding Building Number of Payment Resource");
				mapForPost.put("internalStatusCode",
						"Building Number of Payment Submisssion doesn't match with corresponding Building Number of Payment Resource");
			} else if (!Arrays.asList(address).isEmpty()
					&& (!address[1].equalsIgnoreCase(paymentValues.get(0).getRisk_delivery_address_line2())
							|| !address[0].equalsIgnoreCase(paymentValues.get(0).getRisk_delivery_address_line1()))) {
				status = STATUS_REJECTED;
				LOG.info("Address of Payment Submisssion doesn't match with corresponding Address of Payment Resource");
				mapForPost.put("internalStatusCode",
						"Address of Payment Submisssion doesn't match with corresponding Address of Payment Resource");
			} else if (!townName.equalsIgnoreCase(paymentValues.get(0).getRisk_delivery_address_townname())) {
				status = STATUS_REJECTED;
				LOG.info(
						"Town Name of Payment Submisssion doesn't match with corresponding Town Name of Payment Resource");
				mapForPost.put("internalStatusCode",
						"Town Name of Payment Submisssion doesn't match with corresponding Town Name of Payment Resource");
			} else if (!postCode.equalsIgnoreCase(paymentValues.get(0).getRisk_delivery_address_postcode())) {
				status = STATUS_REJECTED;
				LOG.info(
						"Post Code of Payment Submisssion doesn't match with corresponding Post Code of Payment Resource");
				mapForPost.put("internalStatusCode",
						"Post Code of Payment Submisssion doesn't match with corresponding Post Code of Payment Resource");
			} else if (!streetName.equalsIgnoreCase(paymentValues.get(0).getRisk_delivery_address_streetName())) {
				status = STATUS_REJECTED;
				LOG.info(
						"Street Name of Payment Submisssion doesn't match with corresponding Street Name of Payment Resource");
				mapForPost.put("internalStatusCode",
						"Street Name of Payment Submisssion doesn't match with corresponding Street Name of Payment Resource");
			} else if (!Arrays.asList(countrysub).isEmpty() && !countrysub[0]
					.equalsIgnoreCase(paymentValues.get(0).getRisk_delivery_address_county_subdivision())) {
				status = STATUS_REJECTED;
				LOG.info(
						"Country Sub Division of Payment Submisssion doesn't match with corresponding Country Sub Division of Payment Resource");
				mapForPost.put("internalStatusCode",
						"Country Sub Division of Payment Submisssion doesn't match with corresponding Country Sub Division of Payment Resource");
			} else if (!country.equalsIgnoreCase(paymentValues.get(0).getRisk_delivery_address_country())) {
				status = STATUS_REJECTED;
				LOG.info("Country of Payment Submisssion doesn't match with corresponding Country of Payment Resource");
				mapForPost.put("internalStatusCode",
						"Country of Payment Submisssion doesn't match with corresponding Country of Payment Resource");
			} else if (!debtorAgentIdentification
					.equalsIgnoreCase(paymentValues.get(0).getDebtor_agent_identification())) {
				status = STATUS_REJECTED;
				mapForPost.put("internalStatusCode",
						"Debtor Agent Identification of Payment Submisssion doesn't match with corresponding Debtor Agent Identification of Payment Resource");
				LOG.info("Debtor Agent Identification Length doesn't match Validation ");
			} else if (!debtorAgentSchema.equalsIgnoreCase(paymentValues.get(0).getDebtor_agent_scheme_name())) {
				status = STATUS_REJECTED;
				LOG.info(
						"Debtor Agent Schema of Payment Submisssion doesn't match with corresponding Debtor Agent Schema of Payment Resource");
				mapForPost.put("internalStatusCode",
						"Debtor Agent Schema of Payment Submisssion doesn't match with corresponding Debtor Agent Schema of Payment Resource");
			} else if (!debtorAccountSchema.equalsIgnoreCase(paymentValues.get(0).getDebtor_account_schemename())) {
				status = STATUS_REJECTED;
				mapForPost.put("internalStatusCode",
						"Debtor Account Schema of Payment Submisssion doesn't match with corresponding Debtor Account Schema of Payment Resource");
				LOG.info(
						"Debtor Account Schema of Payment Submisssion doesn't match with corresponding Debtor Account Schema of Payment Resource");
			} else if (!debtorAccountIdentification
					.equalsIgnoreCase(paymentValues.get(0).getDebtor_account_identification())) {
				status = STATUS_REJECTED;
				LOG.info(
						"Debtor Account Identification of Payment Submisssion doesn't match with corresponding Debtor Account Identification of Payment Resource");
				mapForPost.put("internalStatusCode",
						"Debtor Account Identification of Payment Submisssion doesn't match with corresponding Debtor Account Identification of Payment Resource");
			} else if (!debtorAccountName.equalsIgnoreCase(paymentValues.get(0).getDebtor_account_name())) {
				status = STATUS_REJECTED;
				LOG.info(
						"Debtor Account Name of Payment Submisssion doesn't match with corresponding Debtor Account Name of Payment Resource");
				mapForPost.put("internalStatusCode",
						"Debtor Account Name of Payment Submisssion doesn't match with corresponding Debtor Account Name of Payment Resource");
			} else if (!debtorAccount2ryIdentification
					.equalsIgnoreCase(paymentValues.get(0).getDebtor_account_secondary_identification())) {
				status = STATUS_REJECTED;
				LOG.info(
						"Debtor Account Secondary Identification of Payment Submisssion doesn't match with corresponding Debtor Account Secondary Identification of Payment Resource");
				mapForPost.put("internalStatusCode",
						"Debtor Account Secondary Identification of Payment Submisssion doesn't match with corresponding Debtor Account Secondary Identification of Payment Resource");
			}

		} catch (Exception exception) {
			LOG.info(getStackTrace(exception));
		}
		mapForPost.put("status", status);
		LOG.info("Method validatePaymentRequestTableData ends");
		return mapForPost;
	}

	/**
	 * Generate ID of payment , payment submission and Scheme
	 * 
	 * @param type
	 * @return
	 */
	public String generateID(String type) {
		LOG.info("Method generateID Starts" + type);
		SecureRandom random = new SecureRandom();
		int num = random.nextInt(1000000);
		String formatted = String.format("%06d", num);
		return type + formatted;
	}

	/**
	 * Convert the StackTrace of Exception into String
	 * 
	 * @param throwable
	 * @return
	 */
	public String getStackTrace(final Throwable throwable) {
		final StringWriter sw = new StringWriter();
		final PrintWriter pw = new PrintWriter(sw, true);
		throwable.printStackTrace(pw);
		return sw.getBuffer().toString();
	}

	/**
	 * POST method Validate Request Header
	 * 
	 * @param request
	 * @param response
	 * @param responseResultPS
	 * @return
	 */
	public String postRequestHeaderValidation(HttpServletRequest request, HttpServletResponse response,
			String responseResultPS) {

		LOG.info("postRequestHeaderValidation Starts");
		String idempotencyKey = request.getHeader("x-idempotency-key");
		LOG.info(idempotencyKey);
		String logInDate = request.getHeader("x-fapi-customer-last-logged-time");
		LOG.info(logInDate);
		String financialID = request.getHeader("x-fapi-financial-id");
		LOG.info(financialID);
		/*
		 * String authorization = request.getHeader("Authorization");
		 * LOG.info(authorization);
		 */
		if (idempotencyKey == null || idempotencyKey == "") {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			responseResultPS = "{\"Status\" : \" The Idempotency key is Mandatory \"}";
		} else if (idempotencyKey.length() > 40) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			responseResultPS = "{\"Status\" : \" The Idempotency key provided in the header must be at most 40 characters in size \"}";
		} else if (financialID == null || financialID == "") {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			responseResultPS = "{\"Status\" : \" FAPI Financial ID is Mandatory \"}";
		} /*
			 * else if (authorization == null ||
			 * "".equalsIgnoreCase(authorization)) {
			 * response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			 * responseResultPS =
			 * "{\"Status\" : \" Authorization Token is Mandatory \"}"; }
			 */ else if (logInDate != null && logInDate != "") {
			if (!(logInDate.matches(
					"[A-Z]{1}[a-z]{2},\\s[0-9]{2}\\s[A-Z]{1}[a-z]{2}\\s[0-9]{4}\\s[0-9]{2}:[0-9]{2}:[0-9]{2}\\s[A-Z]{3}"))) {

				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				responseResultPS = "{\"Status\" : \" HTTP headers should be represented as RFC 7231 Full Dates \"}";
			}
		}
		LOG.info("postRequestHeaderValidation Ends");
		return responseResultPS;
	}

	/**
	 * 
	 * GET method Request Header Validation
	 * 
	 * @param request
	 * @param response
	 * @param responseResult
	 * @return
	 */
	public String getRequestHeaderValidation(HttpServletRequest request, HttpServletResponse response,
			String responseResult) {
		LOG.info("getRequestHeaderValidation Starts");
		String logInDate = request.getHeader("x-fapi-customer-last-logged-time");
		LOG.info(logInDate);
		/*
		 * String authorization = request.getHeader("Authorization");
		 * LOG.info(authorization);
		 */
		String financialID = request.getHeader("x-fapi-financial-id");
		LOG.info(financialID);
		/*
		 * if (authorization == null || authorization == "") {
		 * response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		 * responseResult =
		 * "{\"Status\" : \" Authorization Token is Mandatory \"}"; } else
		 */ if (financialID == null || financialID == "") {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			responseResult = "{\"Status\" : \" FAPI Financial ID is Mandatory \"}";
		} else if (logInDate != null && logInDate != "") {
			if (!(logInDate.matches(
					"[A-Z]{1}[a-z]{2},\\s[0-9]{2}\\s[A-Z]{1}[a-z]{2}\\s[0-9]{4}\\s[0-9]{2}:[0-9]{2}:[0-9]{2}\\s[A-Z]{3}"))) {

				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				responseResult = "{\"Status\" : \" HTTP headers should be represented as RFC 7231 Full Dates \"}";
			}
		}
		LOG.info("getRequestHeaderValidation Ends");
		return responseResult;
	}
}

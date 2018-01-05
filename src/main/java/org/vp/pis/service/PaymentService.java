package org.vp.pis.service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vp.pis.domain.CreditorAccount;
import org.vp.pis.domain.CreditorAgent;
import org.vp.pis.domain.Data;
import org.vp.pis.domain.DebtorAccount;
import org.vp.pis.domain.DebtorAgent;
import org.vp.pis.domain.DeliveryAddress;
import org.vp.pis.domain.Initiation;
import org.vp.pis.domain.InstructedAmount;
import org.vp.pis.domain.Links;
import org.vp.pis.domain.Meta;
import org.vp.pis.domain.OBPaymentSetupResponse1;
import org.vp.pis.domain.RemittanceInformation;
import org.vp.pis.domain.Risk;
import org.vp.pis.model.Payment;
import org.vp.pis.model.Payment.PaymentStatus;
import org.vp.pis.repository.PaymentRepository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Class Payment Service save the Payment setup Retrieve the payment setup
 * Retrieve payment submission
 *
 */
@Component
public class PaymentService {

	/**
	 * To fetch payment details from database
	 */
	@Autowired
	private PaymentRepository paymentRepository;

	Locale indiaLocale = new Locale("en", "IN");
	NumberFormat newFormat = NumberFormat.getCurrencyInstance();
	String pattern = ((DecimalFormat) newFormat).toPattern();
	String newPattern = pattern.replace("\u00A4", "").trim();
	NumberFormat defaultformat = new DecimalFormat(newPattern);

	final static Logger LOG = LoggerFactory.getLogger(PaymentService.class);

	/**
	 * Save Payment Setup Request in payment table
	 * 
	 * @param paymentSetupResponse1
	 * @param postMap
	 * @throws Exception
	 */
	@SuppressWarnings("null")
	public void savePayment(OBPaymentSetupResponse1 paymentSetupResponse1, Map<String, Object> postMap)
			throws Exception {// TODO

		LOG.info("Method savePayment Starts");

		List<String> paymentContextCodevalidation = Arrays.asList("BillPayment", "EcommerceGoods", "EcommerceServices",
				"Other", "PersonToPerson");

		Payment payment = new Payment();
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
		data = paymentSetupResponse1.getData();
		risk = paymentSetupResponse1.getRisk();
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
		// int debtorAccountIdentification = 0;
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
		// long creditorAccountIdentification = 0;
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
		payment.setCreation_date_time((Date) postMap.get("creationDateTime"));
		payment.setCreditor_account_identification(trimString(creditorAccountIdentification, 34));
		payment.setCreditor_account_name(trimString(creditorAccountName, 70));
		payment.setCreditor_account_schemename(
				!("IBAN".equalsIgnoreCase(creditorAccountSchema)) && !("BBAN".equalsIgnoreCase(creditorAccountSchema))
						? "IBAN" : creditorAccountSchema);
		payment.setCreditor_secondary_identification(
				(creditorAccount2ryIdentification == null || creditorAccount2ryIdentification == "") ? ""
						: trimString(creditorAccount2ryIdentification, 34));
		payment.setCreditor_agent_identification(trimString(creditorAgentIdentification, 35));
		payment.setCreditor_agent_schemename(!("UKSortCode".equalsIgnoreCase(creditorAgentSchemaName))
				&& !("BICFI".equalsIgnoreCase(creditorAgentSchemaName)) ? "BICFI" : creditorAgentSchemaName);
		payment.setCreditor_bank(((postMap.get("creditorBank") == "" || postMap.get("creditorBank") == null) ? "0"
				: String.valueOf(postMap.get("creditorBank"))));
		payment.setDebtor_account_identification(trimString(debtorAccountIdentification, 34));
		payment.setDebtor_account_name(trimString(debtorAccountName, 70));
		payment.setDebtor_account_schemename(
				!("IBAN".equalsIgnoreCase(debtorAccountSchema)) && !("BBAN".equalsIgnoreCase(debtorAccountSchema))
						? "IBAN" : debtorAccountSchema);
		payment.setDebtor_account_secondary_identification(
				(debtorAccount2ryIdentification == null || debtorAccount2ryIdentification == "") ? ""
						: trimString(debtorAccount2ryIdentification, 34));
		payment.setDebtor_agent_identification(trimString(debtorAgentIdentification, 35));
		payment.setDebtor_agent_scheme_name(
				!("UKSortCode".equalsIgnoreCase(debtorAgentSchema)) && !("BICFI".equalsIgnoreCase(debtorAgentSchema))
						? "BICFI" : debtorAgentSchema);
		payment.setDebtor_bank(((postMap.get("debtrBank") == "" || postMap.get("debtrBank") == null) ? "0"
				: String.valueOf(postMap.get("debtrBank"))));
		payment.setEnd_to_end_identification(trimString(endToEndIdentification, 35));
		payment.setInstruction_identification(trimString(instructionIdentification, 35));

		payment.setInternal_status((String) postMap.get("internalStatusCode"));
		payment.setInternal_status_error_code((String.valueOf(postMap.get("Status_Code")) == "202") ? "0"
				: String.valueOf(postMap.get("Status_Code")));
		payment.setPayment_mode("Online");
		payment.setPayment_setup_status((String) postMap.get("Status"));
		payment.setPayment_ref_id((String) postMap.get("paymentID"));
		payment.setPayment_type("Online Banking");
		payment.setRemittance_reference(trimString(reference, 35));
		payment.setRemittance_unstructured_reference(trimString(unstructured, 140));
		payment.setTransfer_type("interbank");
		payment.setTransfer_mode("imps");
		payment.setTransaction_currency(trimString(currency, 3));
		payment.setTransaction_amount(
				paymentSetupResponse1.getData().getInitiation().getInstructedAmount().getAmount() == "" ? 0
						: Float.parseFloat(paymentSetupResponse1.getData().getInitiation().getInstructedAmount()
								.getAmount().replace(",", "")));
		payment.setRisk_payment_context_code(
				!(paymentContextCodevalidation.contains(paymentContextCode)) ? "Other" : paymentContextCode);
		payment.setRisk_merchant_customer_identification(
				trimString(((merchantCustomerIdentification == "" || merchantCustomerIdentification == null) ? "0"
						: merchantCustomerIdentification), 70));
		payment.setRisk_merchant_category_code(trimString(
				((merchantCategoryCode == "" || merchantCategoryCode == null) ? "0" : merchantCategoryCode), 4));
		payment.setRisk_delivery_address_townname(townName == null ? "" : trimString(townName, 35));
		payment.setRisk_delivery_address_streetName(streetName == null ? "" : trimString(streetName, 70));
		payment.setRisk_delivery_address_postcode(postCode == null ? "" : trimString(postCode, 16));
		String[] addressLine = address;
		if (addressLine != null) {
			List<String> address1 = Arrays.asList(addressLine);
			if (address1.isEmpty()) {
				payment.setRisk_delivery_address_line1("");
				payment.setRisk_delivery_address_line2("");
			} else {
				payment.setRisk_delivery_address_line1(trimString(address1.get(0), 70));
				payment.setRisk_delivery_address_line2(trimString(address1.get(1), 70));
			}
		}
		String[] cuntrySub = countrysub;
		if (cuntrySub != null) {
			List<String> subDiv = Arrays.asList(cuntrySub);
			if (subDiv.isEmpty()) {
				payment.setRisk_delivery_address_county_subdivision("");
			} else {
				payment.setRisk_delivery_address_county_subdivision(trimString(subDiv.get(0),
						35)/* +" "+subDiv.get(1)== null?"":subDiv.get(1) */);
			}
		}
		payment.setRisk_delivery_address_country(country == null ? "" : trimString(country, 2));
		payment.setRisk_delivery_address_building_number(buildingNumber == null ? "" : trimString(buildingNumber, 16));
		payment.setPayment_mode("Online");
		payment.setMaker_date((Date) postMap.get("creationDateTime"));
		payment.setMaker_id("maker_id");
		payment.setPayment_method("Online Banking");
		payment.setPayment_notes("");
		payment.setTo_party_id((int) ((postMap.get("CreditorPartyID") == "" || postMap.get("CreditorPartyID") == null)
				? 0 : postMap.get("CreditorPartyID")));//TODO
		payment.setFrom_party_id((int) ((postMap.get("debitorPartyID") == "" || postMap.get("debitorPartyID") == null)
				? 0 : postMap.get("debitorPartyID")));
		payment.setGateway_id(1);
		payment.setValue_date((Date) postMap.get("creationDateTime"));
		payment.setPayment_status(PaymentStatus.wip);
		payment.setPayment_submission_status("Pending");
		payment.setAuthorization_status("");
		payment.setAuthorization_decision("");
		payment.setAuthorization_number("");
		payment.setAuthorization_response("");
		payment.setOrder_number("");
		payment.setOrder_date_time((Date) postMap.get("creationDateTime"));
		payment.setOrder_description("");
		payment.setOrder_currency("");
		payment.setOrder_home_currency("");
		payment.setOrder_recurring_expiry((Date) postMap.get("creationDateTime"));
		payment.setOrder_recurring_payment_subscription_name("");
		payment.setOrder_recurring_payment_subscription_code("");
		payment.setPayment_submission_id("");
		payment.setScheme_payment_id("");
		payment.setMaker_date(new Date());
		payment.setChecker_date(new Date());
		payment.setMaker_id("1111");
		payment.setChecker_id("2222");
		payment.setModified_by("3333");
		payment.setModified_date(new Date());
		LOG.debug("savePayment method Ends");
		paymentRepository.save(payment);

	}

	/**
	 * Get Payment Request response by ID
	 * 
	 * @param PaymentId
	 * @param request
	 * @param response
	 * @return
	 */
	public String getPayment(String PaymentId, HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("getPayment method Starts");
		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
		OBPaymentSetupResponse1 oBPaymentSetupResponse1 = new OBPaymentSetupResponse1();
		Data data = new Data();
		List<org.vp.pis.model.Payment> list;
		list = paymentRepository.getDetailsByPaymentRefId(PaymentId);
		if (list.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			data.setStatus("Requested payment Id is not available");
			oBPaymentSetupResponse1.setData(data);
		} else {
			LOG.info("size of Payment Table " + list.size());
			SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss'Z'");
			formatter.setTimeZone(TimeZone.getTimeZone("Europe/London"));
			String creationDate = formatter.format(list.get(0).getCreation_date_time());
			data.setPaymentId(list.get(0).getPayment_ref_id());
			data.setCreationDateTime(creationDate.replace("Z", "+00:00"));
			data.setStatus(list.get(0).getPayment_setup_status());

			Initiation initiation = new Initiation();
			initiation.setInstructionIdentification(list.get(0).getInstruction_identification());
			initiation.setEndToEndIdentification(list.get(0).getEnd_to_end_identification());

			InstructedAmount instructedAmount = new InstructedAmount();
			instructedAmount.setAmount(String.format("%.2f", list.get(0).getTransaction_amount()));
			instructedAmount.setCurrency(list.get(0).getTransaction_currency());
			initiation.setInstructedAmount(instructedAmount);
			LOG.info("boolean " + ("".equalsIgnoreCase(list.get(0).getDebtor_agent_identification())));
			if (!("".equalsIgnoreCase(list.get(0).getDebtor_agent_identification()))) {
				DebtorAgent debtorAgent = new DebtorAgent();
				debtorAgent.setSchemeName(list.get(0).getDebtor_agent_scheme_name());
				debtorAgent.setIdentification(list.get(0).getDebtor_agent_identification());
				initiation.setDebtorAgent(debtorAgent);
			}
			if (!("".equalsIgnoreCase(list.get(0).getDebtor_account_identification()))
					&& !("".equalsIgnoreCase(list.get(0).getDebtor_account_name()))) {
				DebtorAccount debtorAccount = new DebtorAccount();
				debtorAccount.setSchemeName(list.get(0).getDebtor_account_schemename());
				debtorAccount.setIdentification(String.valueOf(list.get(0).getDebtor_account_identification()));
				debtorAccount.setName(list.get(0).getDebtor_account_name());
				debtorAccount.setSecondaryIdentification(list.get(0).getDebtor_account_secondary_identification());
				initiation.setDebtorAccount(debtorAccount);
			}
			if (!("".equalsIgnoreCase(list.get(0).getCreditor_agent_identification()))) {
				CreditorAgent creditorAgent = new CreditorAgent();
				creditorAgent.setSchemeName(list.get(0).getCreditor_agent_schemename());
				creditorAgent.setIdentification(String.valueOf(list.get(0).getCreditor_agent_identification()));
				initiation.setCreditorAgent(creditorAgent);
			}
			if (!("".equalsIgnoreCase(list.get(0).getCreditor_account_identification()))
					&& !("".equalsIgnoreCase(list.get(0).getCreditor_account_name()))) {
				CreditorAccount creditorAccount = new CreditorAccount();
				creditorAccount.setSchemeName(list.get(0).getCreditor_account_schemename());
				creditorAccount.setIdentification(String.valueOf(list.get(0).getCreditor_account_identification()));
				creditorAccount.setName(list.get(0).getCreditor_account_name());
				creditorAccount.setSecondaryIdentification(list.get(0).getCreditor_secondary_identification());
				initiation.setCreditorAccount(creditorAccount);
			}
			RemittanceInformation remittanceInformation = new RemittanceInformation();
			remittanceInformation.setUnstructured(list.get(0).getRemittance_unstructured_reference());
			remittanceInformation.setReference(list.get(0).getRemittance_reference());
			initiation.setRemittanceInformation(remittanceInformation);
			data.setInitiation(initiation);

			Risk risk = new Risk();
			risk.setPaymentContextCode(list.get(0).getRisk_payment_context_code());
			risk.setMerchantCategoryCode(list.get(0).getRisk_merchant_category_code());
			risk.setMerchantCustomerIdentification(list.get(0).getRisk_merchant_customer_identification());
			DeliveryAddress deliveryAddress = new DeliveryAddress();
			deliveryAddress.setAddressLine((list.get(0).getRisk_delivery_address_line1() + "!@#"
					+ list.get(0).getRisk_delivery_address_line2()).split("!@#"));
			deliveryAddress.setStreetName(list.get(0).getRisk_delivery_address_streetName());
			deliveryAddress.setBuildingNumber(list.get(0).getRisk_delivery_address_building_number());
			deliveryAddress.setPostCode(list.get(0).getRisk_delivery_address_postcode());
			deliveryAddress.setTownName(list.get(0).getRisk_delivery_address_townname());
			deliveryAddress.setCountrySubDivision((list.get(0).getRisk_delivery_address_county_subdivision() == null
					|| list.get(0).getRisk_delivery_address_county_subdivision() == "") ? new String[0]
							: list.get(0).getRisk_delivery_address_county_subdivision().split(","));
			deliveryAddress.setCountry(list.get(0).getRisk_delivery_address_country());
			risk.setDeliveryAddress(deliveryAddress);
			oBPaymentSetupResponse1.setRisk(risk);
			oBPaymentSetupResponse1.setData(data);

			Links links = new Links();
			links.setSelf(request.getRequestURL().toString());
			oBPaymentSetupResponse1.setLinks(links);

			Meta meta = new Meta();
			meta.setTotalPages(null);
			oBPaymentSetupResponse1.setMeta(meta);
			LOG.debug("getPayment method Ends");
		}
		return gson.toJson(oBPaymentSetupResponse1);
	}

	/**
	 * GET payment submission values by ID
	 * 
	 * @param PaymentSubmissionId
	 * @param request
	 * @param response
	 * @return
	 */
	public String getPaymentSubmission(String PaymentSubmissionId, HttpServletRequest request,
			HttpServletResponse response) {
		LOG.info("Method getPaymentSubmission Starts");
		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
		OBPaymentSetupResponse1 oBPaymentSetupResponse1 = new OBPaymentSetupResponse1();
		Data data = new Data();
		List<org.vp.pis.model.Payment> dataList = paymentRepository
				.getDetailsByPaymentSubmissionId(PaymentSubmissionId);
		if (dataList.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			data.setStatus("Requested payment submission id is not available");
			LOG.info("PSID not available");
		} else {
			SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss.SSSXXX");
			formatter.setTimeZone(TimeZone.getTimeZone("Europe/London"));
			String creationDate = formatter.format(dataList.get(0).getCreation_date_time());
			data.setCreationDateTime(creationDate);
			data.setPaymentId(String.valueOf(dataList.get(0).getPayment_ref_id()));
			data.setPaymentSubmissionId(String.valueOf(dataList.get(0).getPayment_submission_id()));
			data.setStatus(dataList.get(0).getPayment_submission_status());
			Links links = new Links();
			links.setSelf(request.getRequestURL().toString()+"/"+PaymentSubmissionId);
			oBPaymentSetupResponse1.setLinks(links);

			Meta meta = new Meta();
			meta.setTotalPages(null);
			oBPaymentSetupResponse1.setMeta(meta);
		}
		LOG.info("Method getPaymentSubmission Ends");
		oBPaymentSetupResponse1.setData(data);

		return gson.toJson(oBPaymentSetupResponse1);
	}

	/**
	 * Trim the String to particular size
	 * 
	 * @param Value
	 * @param size
	 * @return
	 */
	public String trimString(String Value, int size) {
		LOG.debug("trimString : " + Value + " size : " + size);
		String returnString = "";
		if (Value == "") {
			returnString = "";
		} else if ((Value).length() >= size) {
			returnString = (Value).substring(0, size);
		} else {
			returnString = Value;
		}
		return returnString;
	}
}

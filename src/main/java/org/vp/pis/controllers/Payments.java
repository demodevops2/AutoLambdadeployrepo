package org.vp.pis.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.vp.pis.domain.DeliveryAddress;
import org.vp.pis.domain.Links;
import org.vp.pis.domain.Meta;
import org.vp.pis.domain.OBPaymentSetupResponse1;
import org.vp.pis.model.Account;
import org.vp.pis.model.AccountOwners;
import org.vp.pis.model.Country;
import org.vp.pis.model.PaymentLimit;
import org.vp.pis.repository.AccountOwnersRepository;
import org.vp.pis.repository.AccountRepository;
import org.vp.pis.repository.CountryRepository;
import org.vp.pis.repository.PaymentLimitsRepository;
import org.vp.pis.repository.PaymentRepository;
import org.vp.pis.service.PaymentService;
import org.vp.pis.util.PaymentsUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This class consists of 4 end points initiating the single domestic immediate
 * payment initiated as Person2Person and from Merchant payment setup post
 * request payment setup get request payment submission post request payment
 * submission get request
 *
 */

@RestController
public class Payments {

	/**
	 * List of Status in Payment initiation Initial as Pending, API call is
	 * valid - AcceptedTechnicalValidation, Authorization failed or Payment
	 * rejected - Rejected, Payment submitted to ASPSP -
	 * AcceptedSettlementInProcess
	 */

	public static final String STATUS_REJECTED = "Rejected";
	public static final String STATUS_PENDING = "Pending";
	public static final String STATUS_ACCEPTED = "AcceptedTechnicalValidation";
	public static final String STATUS_ACCEPTED_CP = "AcceptedCustomerProfile";
	public static final String STATUS_ACCEPTED_SP = "AcceptedSettlementInProcess";

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	PaymentService paymentService;

	@Autowired
	PaymentLimitsRepository paymentLimitsRepository;

	@Autowired
	AccountOwnersRepository accountOwnersRepository;

	final static Logger LOG = LoggerFactory.getLogger(Payments.class);

	/**
	 * Method Creates the Payment setup generates payment id
	 * 
	 * @param Request
	 *            body
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return payment setup response
	 */
	@RequestMapping(value = "/payments", method = RequestMethod.POST)
	public String createSinglePayment(@RequestBody OBPaymentSetupResponse1 body, HttpServletRequest request,
			HttpServletResponse response) {

		LOG.info("createSinglePayment Starts");
		String responseResult = "";
		String internalStatusCode = "";
		String status = "";
		Map<String, Object> mapForPostPayment = new HashMap<String, Object>();
		List<Account> debtorAccount = new ArrayList<Account>();
		List<Account> creditorAccount = new ArrayList<Account>();
		List<Country> country = new ArrayList<Country>();
		PaymentsUtil paymentsUtil = new PaymentsUtil();
		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
		try {
			mapForPostPayment.put("internalStatusCode", "");
			//String contentType = request.getContentType();
			String accountIdentification = "";
			String schemeName = "";
			String countryDB = "";
			String amount = "";
			String currency = "";

			/*
			 * Validate Request body is in JSON format and Character encoding as
			 * utf-8
			 */
			/*
			 * LOG.info("Json and utf-8" + contentType); if
			 * (!("application/json").equalsIgnoreCase(contentType) ||
			 * !("charset=utf-8").contains(contentType)) {
			 * response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			 * responseResult =
			 * "{\"Status\" : \" The request contained an accept header that requested a content-type other than application/json and a character set other than UTF-8 \"}"
			 * ; } else
			 */

			responseResult = paymentsUtil.postRequestHeaderValidation(request, response, responseResult);

			if (response.getStatus() != HttpServletResponse.SC_BAD_REQUEST
					&& response.getStatus() != HttpServletResponse.SC_NOT_ACCEPTABLE) {
				/*
				 * Validate the Request JSON - PaymentsUtil -
				 * validatePaymentRequest()
				 */
				mapForPostPayment = paymentsUtil.validatePaymentRequest(body, mapForPostPayment);
				status = (String) mapForPostPayment.get("status");
				internalStatusCode = (String) mapForPostPayment.get("internalStatusCode");
				amount = body.getData().getInitiation().getInstructedAmount().getAmount()==""?"0":body.getData().getInitiation().getInstructedAmount().getAmount();
				currency = body.getData().getInitiation().getInstructedAmount().getCurrency();
				if (body.getData().getInitiation().getDebtorAccount() != null) {
					accountIdentification = body.getData().getInitiation().getDebtorAccount().getIdentification();
					schemeName = body.getData().getInitiation().getDebtorAccount().getSchemeName();
				}

				if(body.getRisk().getDeliveryAddress() != null){
					countryDB = body.getRisk().getDeliveryAddress().getCountry();
				}
				List<AccountOwners> accountOwnersDeb = new ArrayList<AccountOwners>();
				List<AccountOwners> accountOwnersCreditor = new ArrayList<AccountOwners>();
				int CreditorPartyID = 0;
				int debitorPartyID = 0;
				int debtrBank = 0;
				String creditorBank = "";
				mapForPostPayment.put("CreditorPartyID", CreditorPartyID);
				mapForPostPayment.put("creditorBank", creditorBank);
				mapForPostPayment.put("debitorPartyID", debitorPartyID);
				mapForPostPayment.put("debtrBank", debtrBank);
				debtorAccount = accountRepository.getDetailsBySchemeNameAndAccountIdentification(schemeName,
						accountIdentification);
				DeliveryAddress deliveryAddress = new DeliveryAddress();
				deliveryAddress = body.getRisk().getDeliveryAddress();
				String[] address = null;
				String buildingNumber = "";
				String townName = "";
				String countryrisk = "";
				String postCode = "";
				String streetName = "";
				String[] countrysub = null;

				if (deliveryAddress != null) {
					address = deliveryAddress.getAddressLine();
					buildingNumber = deliveryAddress.getBuildingNumber();
					townName = deliveryAddress.getTownName();
					countryrisk = deliveryAddress.getCountry();
					postCode = deliveryAddress.getPostCode();
					streetName = deliveryAddress.getStreetName();
					countrysub = deliveryAddress.getCountrySubDivision();
				}
				
				String paymentContextCode = "";
					paymentContextCode = body.getRisk().getPaymentContextCode();
					boolean executeDA = false;
				if ((address != null && buildingNumber != null && townName != null && countryrisk != null && postCode != null
						&& streetName != null && countrysub != null && deliveryAddress != null)
						|| "EcommerceGoods".equalsIgnoreCase(paymentContextCode)) {
					executeDA = true;
				}
				country = countryRepository.getDetailsByCountryCode(countryDB);
				/* Set Bank id and Party id of Debtor Account */
				if (!debtorAccount.isEmpty()) {
					debtrBank = debtorAccount.get(0).getBank_id();
					mapForPostPayment.put("debtrBank", debtrBank);
					accountOwnersDeb = accountOwnersRepository.getPartyID(schemeName, accountIdentification);
					if (!accountOwnersDeb.isEmpty()) {
						debitorPartyID = accountOwnersDeb.get(0).getParty_id();
						mapForPostPayment.put("debitorPartyID", debitorPartyID);
					}
				}

				String creditorAccountIdentification = body.getData().getInitiation().getCreditorAccount()
						.getIdentification();
				String creditorSchemeName = body.getData().getInitiation().getCreditorAccount().getSchemeName();
				creditorAccount = accountRepository.getDetailsBySchemeNameAndAccountIdentification(creditorSchemeName,
						creditorAccountIdentification);

				/* Set Bank id and Party id of Creditor Account */
				if (!creditorAccount.isEmpty()) {
					creditorBank = String.valueOf(creditorAccount.get(0).getBank_id());
					mapForPostPayment.put("creditorBank", creditorBank);
					accountOwnersCreditor = accountOwnersRepository.getPartyID(creditorSchemeName,
							creditorAccountIdentification);
					if (!accountOwnersCreditor.isEmpty()) {
						CreditorPartyID = accountOwnersCreditor.get(0).getParty_id();
						mapForPostPayment.put("CreditorPartyID", CreditorPartyID);
					}
				}

				if (!STATUS_REJECTED.equalsIgnoreCase(status)) {
					/*
					 * Validate the debtor account if it was present in JSON
					 * request
					 */
					LOG.info("Validate Debtor Account" + body.getData().getInitiation().getDebtorAccount());

					if (accountIdentification != null && accountIdentification != "") {

						if (debtorAccount.isEmpty() || debtorAccount.size() == 0) {
							internalStatusCode = "Debtor Account is not Valid ";
							status = STATUS_REJECTED;
						} else {
							float amountDbtr = debtorAccount.get(0).getBalance();
							String currencyDbtr = debtorAccount.get(0).getAccount_currency();
							LOG.info("Amount --> " + amountDbtr + " " + currencyDbtr);
							if (!currency.equalsIgnoreCase(currencyDbtr)) {
								internalStatusCode = "Currency format not Matched";
								status = STATUS_REJECTED;
							} else if (amountDbtr <= Float.parseFloat(amount.replace(",", ""))) {
								internalStatusCode = "Transaction Amount is Greater than the Balance Amount";
								status = STATUS_REJECTED;
							} else if (executeDA &&(country.isEmpty() || country.size() == 0)) {
								internalStatusCode = "Country is not valid";
								status = STATUS_REJECTED;
							}
						}

					}
				}
				/* Payment Limit Validation */
				List<PaymentLimits> limits = new ArrayList<PaymentLimits>();
				limits = paymentLimitsRepository.getDetailsOfPaymentLimits();
				if (!STATUS_REJECTED.equalsIgnoreCase(status)) {
					if (!limits.isEmpty()) {
						LOG.info("Max --> " + limits.get(0).getMaximum_transaction_amount() + " Min --> "
								+ limits.get(0).getMinimum_transaction_amount());
						LOG.info("AMount " + Float.parseFloat(amount.replace(",", "")));
						LOG.info("Return" + (Float.parseFloat(amount.replace(",", "")) < limits.get(0)
								.getMinimum_transaction_amount()));
						if (Float.parseFloat(amount.replace(",", "")) > limits.get(0).getMaximum_transaction_amount()) {
							response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
							status = STATUS_REJECTED;
							internalStatusCode = "Maximum transaction amount for the transaction : "
									+ limits.get(0).getMaximum_transaction_amount();
						} else if (Float.parseFloat(amount.replace(",", "")) < limits.get(0)
								.getMinimum_transaction_amount()) {
							response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
							status = STATUS_REJECTED;
							internalStatusCode = "Minimum transaction amount for the transaction : "
									+ limits.get(0).getMinimum_transaction_amount();
						}
					} else {
						response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
						responseResult = "{\"Status\" : \" Not able to check daily Limit of Transaction \"}";
					}
				}
				mapForPostPayment.put("internalStatusCode", internalStatusCode);
				if (STATUS_REJECTED.equalsIgnoreCase(status)) {
					LOG.info("Response Code - 400");
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				} else {
					status = STATUS_ACCEPTED;
					LOG.info("Response Code - 201");
					response.setStatus(HttpServletResponse.SC_CREATED);
				}

				String paymentID = "";
				/* Generate payment ID */
				paymentID = paymentsUtil.generateID("PID");
				List<org.vp.pis.model.Payment> totCount = paymentRepository.getDetailsByPaymentRefId(paymentID);
				if (totCount.size() > 1) {
					paymentID = paymentsUtil.generateID("PID");
				}

				Date dateStr = new Date();
				mapForPostPayment.put("paymentID", paymentID);
				mapForPostPayment.put("creationDateTime", dateStr);
				mapForPostPayment.put("Status_Code", response.getStatus());
				mapForPostPayment.put("Status", status);
				/* Save the Payment setup in Payment Table */
				LOG.info("Save Payment");
				paymentService.savePayment(body, mapForPostPayment);
				//responseResult = paymentService.getPayment(paymentID, request, response);
				List<org.vp.pis.model.Payment> list;
				list = paymentRepository.getDetailsByPaymentRefId(paymentID);
				body.getData().setPaymentId(paymentID);
				if(!list.isEmpty()){
					SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss'Z'");
					String creationDate = formatter.format(list.get(0).getCreation_date_time());
					body.getData().setCreationDateTime(creationDate.replace("Z", "+00:00"));
					body.getData().setStatus(list.get(0).getPayment_setup_status());
					LOG.info("Status " +list.get(0).getInternal_status());
				}
				
				Links links = new Links();
				links.setSelf(request.getRequestURL().toString()+"/"+paymentID);
				body.setLinks(links);

				Meta meta = new Meta();
				meta.setTotalPages(null);
				body.setMeta(meta);
				
				responseResult = gson.toJson(body);
			}
		} catch (Exception exception) {
			LOG.info("Exception --> " + paymentsUtil.getStackTrace(exception));
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			responseResult = "{\"Status\" : \"" + exception.getCause() + "\" - \"" + paymentsUtil.getStackTrace(exception)
					+ "\"}";
		}
		response.addHeader("x-jws-signature", request.getHeader("x-jws-signature"));
		response.addHeader("x-fapi-interaction-id", request.getHeader("x-fapi-interaction-id"));
		LOG.info("createSinglePayment Ends");

		return responseResult;

	}

	/**
	 * Get the Payment setup response based on Payment Id
	 * 
	 * @param paymentID
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/payments/{PaymentId}", method = RequestMethod.GET)
	public String getSinglePayment(@PathVariable(value = "PaymentId") String PaymentId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LOG.info("getPaymentSetup Starts");
		PaymentsUtil paymentsUtil = new PaymentsUtil();
		String responseResult = "";
		try {

			/*
			 * Retrieve from Payment Table based on Payment id from the header
			 * and check Request Header
			 */
			responseResult = paymentsUtil.getRequestHeaderValidation(request, response, responseResult);

			if (response.getStatus() != HttpServletResponse.SC_BAD_REQUEST
					&& response.getStatus() != HttpServletResponse.SC_NOT_ACCEPTABLE) {
				responseResult = paymentService.getPayment(PaymentId, request, response);
			}
			LOG.info("GET request" + responseResult);
		} catch (Exception e) {
			LOG.info("Exception --> " + paymentsUtil.getStackTrace(e));
			responseResult = "{\"Status\" : \"" + e.getCause() + "\" - \"" + paymentsUtil.getStackTrace(e) + "\"}";
		}
		response.addHeader("x-jws-signature", request.getHeader("x-jws-signature"));
		response.addHeader("x-fapi-interaction-id", request.getHeader("x-fapi-interaction-id"));
		LOG.info("getPaymentSetup Ends");
		return responseResult;
	}

	/**
	 * Create Payment Submission for the payemnt request
	 * 
	 * @param body
	 * @param request
	 * @param response
	 * @return response of payment submission
	 * @throws Exception
	 */
	@RequestMapping(value = "/payment-submissions", method = RequestMethod.POST)
	public String createPaymentSubmission(@RequestBody OBPaymentSetupResponse1 body, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LOG.info("createPaymentSubmission Starts");
		String responseResultPS = "";
		String status = "";
		String internalStatusCode = "";
		String paymentStatus = "";
		Map<String, Object> mapForPostPayment = new HashMap<String, Object>();
		PaymentsUtil paymentsUtil = new PaymentsUtil();
		List<Account> debtorAccount = new ArrayList<Account>();
		List<Country> country = new ArrayList<Country>();

		try {
			List<org.vp.pis.model.Payment> paymentValues = paymentRepository
					.getDetailsByPaymentRefId(body.getData().getPaymentId());
			mapForPostPayment.put("internalStatusCode", "");
			responseResultPS = paymentsUtil.postRequestHeaderValidation(request, response, responseResultPS);

			if (response.getStatus() != HttpServletResponse.SC_BAD_REQUEST
					&& response.getStatus() != HttpServletResponse.SC_NOT_ACCEPTABLE) {
				if (paymentValues.size() == 1) {
					paymentStatus = paymentValues.get(0).getPayment_setup_status();
					String paymentSubStatus = paymentValues.get(0).getPayment_submission_status();
					if (STATUS_ACCEPTED_CP.equalsIgnoreCase(paymentStatus)) {
						if (("AcceptedSettlementInProcess").equalsIgnoreCase(paymentSubStatus)
								|| ("AcceptedSettlementCompleted").equalsIgnoreCase(paymentSubStatus)) {

							responseResultPS = "{ \"Status\" : \"Payment Submission was already processed\" }";
							response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
						} else {
							/*
							 * Validate the Request JSON - PaymentsUtil -
							 * validatePaymentRequest()
							 */
							mapForPostPayment = paymentsUtil.validatePaymentRequest(body, mapForPostPayment);
							status = (String) mapForPostPayment.get("status");
							internalStatusCode = (String) mapForPostPayment.get("internalStatusCode");
							if (!STATUS_REJECTED.equalsIgnoreCase(status)) {
								mapForPostPayment = paymentsUtil.validatePaymentRequestTableData(body,
										mapForPostPayment, paymentValues);
								status = (String) mapForPostPayment.get("status");
								internalStatusCode = (String) mapForPostPayment.get("internalStatusCode");
								if (!STATUS_REJECTED.equalsIgnoreCase(status)) {
									/*
									 * Validate the debtor account if it was
									 * present in JSON request
									 */

									String accountIdentification = body.getData().getInitiation().getDebtorAccount()
											.getIdentification();
									String schemeName = body.getData().getInitiation().getDebtorAccount()
											.getSchemeName();
									String amount = body.getData().getInitiation().getInstructedAmount().getAmount();
									String currency = body.getData().getInitiation().getInstructedAmount()
											.getCurrency();

									if (accountIdentification != null && accountIdentification != "") {
										debtorAccount = accountRepository
												.getDetailsBySchemeNameAndAccountIdentification(schemeName,
														accountIdentification);
										country = countryRepository.getDetailsByCountryCode(
												body.getRisk().getDeliveryAddress().getCountry());
										if (debtorAccount.isEmpty() || debtorAccount.size() == 0) {
											internalStatusCode = "Debitor Account is not Valid ";
											status = STATUS_REJECTED;
										} else {
											float amountDbtr = debtorAccount.get(0).getBalance();
											String currencyDbtr = debtorAccount.get(0).getAccount_currency();
											LOG.info("Amount --> " + amountDbtr + " " + currencyDbtr);
											if (!currency.equalsIgnoreCase(currencyDbtr)) {
												internalStatusCode = "Currency format not Matched";
												status = STATUS_REJECTED;
											} else if (amountDbtr <= Float.parseFloat(amount.replace(",", ""))) {
												internalStatusCode = "Transaction Amount is Greater than the Balance Amount";
												status = STATUS_REJECTED;
											} else if (country.isEmpty() || country.size() == 0) {
												internalStatusCode = "Country is not valid";
												status = STATUS_REJECTED;
											}
										}
										mapForPostPayment.put("internalStatusCode", internalStatusCode);
									}

								}
							}

							LOG.info("Status " + status);
							status = (String) mapForPostPayment.get("status");
							internalStatusCode = (String) mapForPostPayment.get("internalStatusCode");
							LOG.info("Status " + status);
							if (STATUS_REJECTED.equalsIgnoreCase(status)) {
								status = STATUS_REJECTED;
								LOG.info("Response Code - 400");
								response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
							} else {
								status = STATUS_ACCEPTED_SP;
								LOG.info("Response Code - 201");
								response.setStatus(HttpServletResponse.SC_CREATED);
							}
							String paymentSubmissionId = paymentsUtil.generateID("PSID");
							String schemePaymentId = paymentsUtil.generateID("SPID");
							paymentRepository.updatePaymentValuesByPaymentRefId(body.getData().getPaymentId(), status,
									paymentSubmissionId, schemePaymentId,internalStatusCode,String.valueOf(response.getStatus()));

							responseResultPS = paymentService.getPaymentSubmission(paymentSubmissionId, request,
									response);

						}
					} else {
						responseResultPS = "{ \"Status\" : \" Requested Payment Submission is Pending from Customer Authentication or Rejected at Payment Setup Request\" }";
						response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					}
				} else {
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					responseResultPS = "{\"Status\" : \"Payment Reference ID is not available\"}";
				}
			}
		} catch (Exception e) {
			LOG.info("Exception --> " + paymentsUtil.getStackTrace(e));
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			responseResultPS = "{\"Status\" : \"" + e.getCause() + "\" - \"" + paymentsUtil.getStackTrace(e) + "\"}";
		}

		response.addHeader("x-jws-signature", request.getHeader("x-jws-signature"));
		response.addHeader("x-fapi-interaction-id", request.getHeader("x-fapi-interaction-id"));
		LOG.info("Method createpaymentSubmission Ends");
		return responseResultPS;
	}

	/**
	 * Get Payment submission status based on ID
	 * 
	 * @param PaymentSubmissionId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/payment-submissions/{PaymentSubmissionId}", method = RequestMethod.GET)
	public String getPaymentSubmission(@PathVariable(value = "PaymentSubmissionId") String PaymentSubmissionId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		LOG.info("getPaymentSubmission starts");
		String responseResult = "";
		PaymentsUtil paymentsUtil = new PaymentsUtil();
		try {
			responseResult = paymentsUtil.getRequestHeaderValidation(request, response, responseResult);
			if (response.getStatus() != HttpServletResponse.SC_BAD_REQUEST
					&& response.getStatus() != HttpServletResponse.SC_NOT_ACCEPTABLE) {
				responseResult = paymentService.getPaymentSubmission(PaymentSubmissionId, request, response);
			}
			LOG.info("getPaymentSubmission " + responseResult);

		} catch (Exception e) {
			LOG.info("Exception --> " + paymentsUtil.getStackTrace(e));
			responseResult = "{\"Status\" : \"" + e.getCause() + "\" - \"" + paymentsUtil.getStackTrace(e) + "\"}";
		}
		response.addHeader("x-jws-signature", request.getHeader("x-jws-signature"));
		response.addHeader("x-fapi-interaction-id", request.getHeader("x-fapi-interaction-id"));
		LOG.info("getPaymentSubmission Ends");
		return responseResult;

	}

}

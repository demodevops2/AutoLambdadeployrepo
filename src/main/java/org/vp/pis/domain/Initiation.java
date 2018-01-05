package org.vp.pis.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that holds the Initiation values of payment request
 *
 */
public class Initiation {

	/*
	 * Holds the Instruction Identification of Initiation
	 */
	private String InstructionIdentification;
	/*
	 * Holds the End to End Identification of Initiation
	 */
	private String EndToEndIdentification;
	/*
	 * Holds the Instructed Amount of Initiation
	 */
	private InstructedAmount InstructedAmount;
	/*
	 * Holds the Debtor Agent of Initiation
	 */
	private DebtorAgent DebtorAgent;
	/*
	 * Holds the Debtor Account of Initiation
	 */
	private DebtorAccount DebtorAccount;
	/*
	 * Holds the Creditor Agent of Initiation
	 */
	private CreditorAgent CreditorAgent;
	/*
	 * Holds the Creditor Account of Initiation
	 */
	private CreditorAccount CreditorAccount;
	/*
	 * Holds the Remittance Information of Initiation
	 */
	private RemittanceInformation RemittanceInformation;

	/**
	 * @return the instructionIdentification
	 */
	@JsonProperty("InstructionIdentification")
	public String getInstructionIdentification() {
		return InstructionIdentification;
	}

	/**
	 * @param instructionIdentification
	 *            the instructionIdentification to set
	 */
	public void setInstructionIdentification(String instructionIdentification) {
		InstructionIdentification = instructionIdentification;
	}

	/**
	 * @return the endToEndIdentification
	 */
	@JsonProperty("EndToEndIdentification")
	public String getEndToEndIdentification() {
		return EndToEndIdentification;
	}

	/**
	 * @param endToEndIdentification
	 *            the endToEndIdentification to set
	 */
	public void setEndToEndIdentification(String endToEndIdentification) {
		EndToEndIdentification = endToEndIdentification;
	}

	/**
	 * @return the instructedAmount
	 */

	@JsonProperty("InstructedAmount")
	public InstructedAmount getInstructedAmount() {
		return InstructedAmount;
	}

	/**
	 * @param instructedAmount
	 *            the instructedAmount to set
	 */
	public void setInstructedAmount(InstructedAmount instructedAmount) {
		InstructedAmount = instructedAmount;
	}

	/**
	 * @return the debtorAgent
	 */
	@JsonProperty("DebtorAgent")
	public DebtorAgent getDebtorAgent() {
		return DebtorAgent;
	}

	/**
	 * @param debtorAgent
	 *            the debtorAgent to set
	 */
	public void setDebtorAgent(DebtorAgent debtorAgent) {
		DebtorAgent = debtorAgent;
	}

	/**
	 * @return the debtorAccount
	 */
	@JsonProperty("DebtorAccount")
	public DebtorAccount getDebtorAccount() {
		return DebtorAccount;
	}

	/**
	 * @param debtorAccount
	 *            the debtorAccount to set
	 */
	public void setDebtorAccount(DebtorAccount debtorAccount) {
		DebtorAccount = debtorAccount;
	}

	/**
	 * @return the creditorAgent
	 */
	@JsonProperty("CreditorAgent")
	public CreditorAgent getCreditorAgent() {
		return CreditorAgent;
	}

	/**
	 * @param creditorAgent
	 *            the creditorAgent to set
	 */
	public void setCreditorAgent(CreditorAgent creditorAgent) {
		CreditorAgent = creditorAgent;
	}

	/**
	 * @return the creditorAccount
	 */
	@JsonProperty("CreditorAccount")
	public CreditorAccount getCreditorAccount() {
		return CreditorAccount;
	}

	/**
	 * @param creditorAccount
	 *            the creditorAccount to set
	 */
	public void setCreditorAccount(CreditorAccount creditorAccount) {
		CreditorAccount = creditorAccount;
	}

	/**
	 * @return the remittanceInformation
	 */
	@JsonProperty("RemittanceInformation")
	public RemittanceInformation getRemittanceInformation() {
		return RemittanceInformation;
	}

	/**
	 * @param remittanceInformation
	 *            the remittanceInformation to set
	 */
	public void setRemittanceInformation(RemittanceInformation remittanceInformation) {
		RemittanceInformation = remittanceInformation;
	}

}

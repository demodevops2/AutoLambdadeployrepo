package org.vp.pis.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeliveryAddress {

	/*
	 * Holds the AddressLine of Delivery Address
	 */
	private String[] AddressLine;
	/*
	 * Holds the Street Name of Delivery Address
	 */
	private String StreetName;
	/*
	 * Holds the Building Number of Delivery Address
	 */
	private String BuildingNumber;
	/*
	 * Holds the Post Code of Delivery Address
	 */
	private String PostCode;
	/*
	 * Holds the Town Name of Delivery Address
	 */
	private String TownName;
	/*
	 * Holds the CountrySub Division of Delivery Address
	 */
	private String[] CountrySubDivision;
	/*
	 * Holds the Country of Delivery Address
	 */
	private String Country;

	/**
	 * @return the addressLine
	 */
	@JsonProperty("AddressLine")
	public String[] getAddressLine() {
		return AddressLine;
	}

	/**
	 * @param addressLine
	 *            the addressLine to set
	 */
	public void setAddressLine(String[] addressLine) {
		AddressLine = addressLine;
	}

	/**
	 * @return the streetName
	 */
	@JsonProperty("StreetName")
	public String getStreetName() {
		return StreetName;
	}

	/**
	 * @param streetName
	 *            the streetName to set
	 */
	public void setStreetName(String streetName) {
		StreetName = streetName;
	}

	/**
	 * @return the buildingNumber
	 */
	@JsonProperty("BuildingNumber")
	public String getBuildingNumber() {
		return BuildingNumber;
	}

	/**
	 * @param buildingNumber
	 *            the buildingNumber to set
	 */
	public void setBuildingNumber(String buildingNumber) {
		BuildingNumber = buildingNumber;
	}

	/**
	 * @return the postCode
	 */
	@JsonProperty("PostCode")
	public String getPostCode() {
		return PostCode;
	}

	/**
	 * @param postCode
	 *            the postCode to set
	 */
	public void setPostCode(String postCode) {
		PostCode = postCode;
	}

	/**
	 * @return the townName
	 */
	@JsonProperty("TownName")
	public String getTownName() {
		return TownName;
	}

	/**
	 * @param townName
	 *            the townName to set
	 */
	public void setTownName(String townName) {
		TownName = townName;
	}

	/**
	 * @return the countrySubDivision
	 */
	@JsonProperty("CountrySubDivision")
	public String[] getCountrySubDivision() {
		return CountrySubDivision;
	}

	/**
	 * @param countrySubDivision
	 *            the countrySubDivision to set
	 */
	public void setCountrySubDivision(String[] countrySubDivision) {
		CountrySubDivision = countrySubDivision;
	}

	/**
	 * @return the country
	 */
	@JsonProperty("Country")
	public String getCountry() {
		return Country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		Country = country;
	}

}

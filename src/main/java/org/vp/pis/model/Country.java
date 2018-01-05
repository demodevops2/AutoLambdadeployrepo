package org.vp.pis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class holds the value of country table
 *
 */
@Entity
@Table( name = "Country")
public class Country implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Holds the country id
	 */
	@Id
	@Column (name = "country_id" )
	private int country_id;
	
	/**
	 * Holds the Country name
	 */
	@Column (name = "country" ) 
	private String country;
	
	/**
	 * Holds the Country code in 2 letter
	 */
	@Column (name = "country_code_2a" )
	private String country_code_2a;


}

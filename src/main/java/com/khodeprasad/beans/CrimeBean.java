/**
 * 
 */
package com.khodeprasad.beans;

import java.io.Serializable;

/**
 * @author Prasad Khode
 *
 */
public class CrimeBean implements Serializable {

	private static final long serialVersionUID = -5597527358868857011L;

	private int year;

	private String id;
	private String primaryType;

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the primaryType
	 */
	public String getPrimaryType() {
		return primaryType;
	}

	/**
	 * @param primaryType the primaryType to set
	 */
	public void setPrimaryType(String primaryType) {
		this.primaryType = primaryType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CrimeBean [year=");
		builder.append(year);
		builder.append(", id=");
		builder.append(id);
		builder.append(", primaryType=");
		builder.append(primaryType);
		builder.append("]");
		return builder.toString();
	}
}
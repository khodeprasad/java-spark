/**
 * 
 */
package com.khodeprasad.beans;

import java.io.Serializable;

/**
 * @author Prasad Khode
 *
 */
public class ResultBean implements Serializable {

	private static final long serialVersionUID = -2874239800330141693L;

	private int year;
	private long crimeCount;
	
	private String crimeCategory;

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
	 * @return the crimeCount
	 */
	public long getCrimeCount() {
		return crimeCount;
	}

	/**
	 * @param crimeCount the crimeCount to set
	 */
	public void setCrimeCount(long crimeCount) {
		this.crimeCount = crimeCount;
	}

	/**
	 * @return the crimeCategory
	 */
	public String getCrimeCategory() {
		return crimeCategory;
	}

	/**
	 * @param crimeCategory the crimeCategory to set
	 */
	public void setCrimeCategory(String crimeCategory) {
		this.crimeCategory = crimeCategory;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResultBean [year=");
		builder.append(year);
		builder.append(", crimeCount=");
		builder.append(crimeCount);
		builder.append(", crimeCategory=");
		builder.append(crimeCategory);
		builder.append("]");
		return builder.toString();
	}
}

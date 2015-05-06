/**
 * 
 */
package com.seshenghuo.ui.bean;

import com.google.gson.Gson;

/**
 * @author carlli
 * 
 */
public class UserEducationBean {
	private int eduId = 0;
	private int userId = 0;
	private String school = "";
	private String majorStudy = "";
	private String entryDate = "";
	private String endDate = "";
	private String desc = "";
	private boolean isCurrent = false;

	/**
	 * 
	 */
	public UserEducationBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the eduId
	 */
	public int getEduId() {
		return eduId;
	}

	/**
	 * @param eduId
	 *            the eduId to set
	 */
	public void setEduId(int eduId) {
		this.eduId = eduId;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the school
	 */
	public String getSchool() {
		return school;
	}

	/**
	 * @param school
	 *            the school to set
	 */
	public void setSchool(String school) {
		this.school = school;
	}

	/**
	 * @return the majorStudy
	 */
	public String getMajorStudy() {
		return majorStudy;
	}

	/**
	 * @param majorStudy
	 *            the majorStudy to set
	 */
	public void setMajorStudy(String majorStudy) {
		this.majorStudy = majorStudy;
	}

	/**
	 * @return the entryDate
	 */
	public String getEntryDate() {
		return entryDate;
	}

	/**
	 * @param entryDate
	 *            the entryDate to set
	 */
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the isCurrent
	 */
	public boolean isCurrent() {
		return isCurrent;
	}

	/**
	 * @param isCurrent
	 *            the isCurrent to set
	 */
	public void setCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		Gson g = new Gson();
		String json = g.toJson(this);

		g = null;

		return json;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + eduId;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result
				+ ((entryDate == null) ? 0 : entryDate.hashCode());
		result = prime * result + (isCurrent ? 1231 : 1237);
		result = prime * result
				+ ((majorStudy == null) ? 0 : majorStudy.hashCode());
		result = prime * result + ((school == null) ? 0 : school.hashCode());
		result = prime * result + userId;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEducationBean other = (UserEducationBean) obj;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (eduId != other.eduId)
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (entryDate == null) {
			if (other.entryDate != null)
				return false;
		} else if (!entryDate.equals(other.entryDate))
			return false;
		if (isCurrent != other.isCurrent)
			return false;
		if (majorStudy == null) {
			if (other.majorStudy != null)
				return false;
		} else if (!majorStudy.equals(other.majorStudy))
			return false;
		if (school == null) {
			if (other.school != null)
				return false;
		} else if (!school.equals(other.school))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

}

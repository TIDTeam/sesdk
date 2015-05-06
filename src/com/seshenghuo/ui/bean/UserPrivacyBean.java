/**
 * 
 */
package com.seshenghuo.ui.bean;

import com.google.gson.Gson;

/**
 * @author carlli
 * 
 */
public class UserPrivacyBean {
	private int userId = 0;
	private int qqPrivacyState = 1;
	private int gmailPrivacyState = 0;
	private int msnPrivacyState = 0;
	private int weiboPrivacyState = 0;
	private int tqqPrivacyState = 0;
	private int taobaoPrivacyState = 1;
	private int birthdayPrivacyState = 1;
	private int genderPrivacyState = 1;
	private int eduPrivacyState = 0;
	private int occupationPrivacyState = 0;
	private int skillsPrivacyState = 0;
	private int employmentPrivacyState = 0;

	/**
	 * 
	 */
	public UserPrivacyBean() {
		// TODO Auto-generated constructor stub
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
	 * @return the qqPrivacyState
	 */
	public int getQqPrivacyState() {
		return qqPrivacyState;
	}

	/**
	 * @param qqPrivacyState
	 *            the qqPrivacyState to set
	 */
	public void setQqPrivacyState(int qqPrivacyState) {
		this.qqPrivacyState = qqPrivacyState;
	}

	/**
	 * @return the gmailPrivacyState
	 */
	public int getGmailPrivacyState() {
		return gmailPrivacyState;
	}

	/**
	 * @param gmailPrivacyState
	 *            the gmailPrivacyState to set
	 */
	public void setGmailPrivacyState(int gmailPrivacyState) {
		this.gmailPrivacyState = gmailPrivacyState;
	}

	/**
	 * @return the msnPrivacyState
	 */
	public int getMsnPrivacyState() {
		return msnPrivacyState;
	}

	/**
	 * @param msnPrivacyState
	 *            the msnPrivacyState to set
	 */
	public void setMsnPrivacyState(int msnPrivacyState) {
		this.msnPrivacyState = msnPrivacyState;
	}

	/**
	 * @return the weiboPrivacyState
	 */
	public int getWeiboPrivacyState() {
		return weiboPrivacyState;
	}

	/**
	 * @param weiboPrivacyState
	 *            the weiboPrivacyState to set
	 */
	public void setWeiboPrivacyState(int weiboPrivacyState) {
		this.weiboPrivacyState = weiboPrivacyState;
	}

	/**
	 * @return the tqqPrivacyState
	 */
	public int getTqqPrivacyState() {
		return tqqPrivacyState;
	}

	/**
	 * @param tqqPrivacyState
	 *            the tqqPrivacyState to set
	 */
	public void setTqqPrivacyState(int tqqPrivacyState) {
		this.tqqPrivacyState = tqqPrivacyState;
	}

	/**
	 * @return the taobaoPrivacyState
	 */
	public int getTaobaoPrivacyState() {
		return taobaoPrivacyState;
	}

	/**
	 * @param taobaoPrivacyState
	 *            the taobaoPrivacyState to set
	 */
	public void setTaobaoPrivacyState(int taobaoPrivacyState) {
		this.taobaoPrivacyState = taobaoPrivacyState;
	}

	/**
	 * @return the birthdayPrivacyState
	 */
	public int getBirthdayPrivacyState() {
		return birthdayPrivacyState;
	}

	/**
	 * @param birthdayPrivacyState
	 *            the birthdayPrivacyState to set
	 */
	public void setBirthdayPrivacyState(int birthdayPrivacyState) {
		this.birthdayPrivacyState = birthdayPrivacyState;
	}

	/**
	 * @return the genderPrivacyState
	 */
	public int getGenderPrivacyState() {
		return genderPrivacyState;
	}

	/**
	 * @param genderPrivacyState
	 *            the genderPrivacyState to set
	 */
	public void setGenderPrivacyState(int genderPrivacyState) {
		this.genderPrivacyState = genderPrivacyState;
	}

	/**
	 * @return the eduPrivacyState
	 */
	public int getEduPrivacyState() {
		return eduPrivacyState;
	}

	/**
	 * @param eduPrivacyState
	 *            the eduPrivacyState to set
	 */
	public void setEduPrivacyState(int eduPrivacyState) {
		this.eduPrivacyState = eduPrivacyState;
	}

	/**
	 * @return the occupationPrivacyState
	 */
	public int getOccupationPrivacyState() {
		return occupationPrivacyState;
	}

	/**
	 * @param occupationPrivacyState
	 *            the occupationPrivacyState to set
	 */
	public void setOccupationPrivacyState(int occupationPrivacyState) {
		this.occupationPrivacyState = occupationPrivacyState;
	}

	/**
	 * @return the skillsPrivacyState
	 */
	public int getSkillsPrivacyState() {
		return skillsPrivacyState;
	}

	/**
	 * @param skillsPrivacyState
	 *            the skillsPrivacyState to set
	 */
	public void setSkillsPrivacyState(int skillsPrivacyState) {
		this.skillsPrivacyState = skillsPrivacyState;
	}

	/**
	 * @return the employmentPrivacyState
	 */
	public int getEmploymentPrivacyState() {
		return employmentPrivacyState;
	}

	/**
	 * @param employmentPrivacyState
	 *            the employmentPrivacyState to set
	 */
	public void setEmploymentPrivacyState(int employmentPrivacyState) {
		this.employmentPrivacyState = employmentPrivacyState;
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
		result = prime * result + birthdayPrivacyState;
		result = prime * result + eduPrivacyState;
		result = prime * result + employmentPrivacyState;
		result = prime * result + genderPrivacyState;
		result = prime * result + gmailPrivacyState;
		result = prime * result + msnPrivacyState;
		result = prime * result + occupationPrivacyState;
		result = prime * result + qqPrivacyState;
		result = prime * result + skillsPrivacyState;
		result = prime * result + taobaoPrivacyState;
		result = prime * result + tqqPrivacyState;
		result = prime * result + userId;
		result = prime * result + weiboPrivacyState;
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
		UserPrivacyBean other = (UserPrivacyBean) obj;
		if (birthdayPrivacyState != other.birthdayPrivacyState)
			return false;
		if (eduPrivacyState != other.eduPrivacyState)
			return false;
		if (employmentPrivacyState != other.employmentPrivacyState)
			return false;
		if (genderPrivacyState != other.genderPrivacyState)
			return false;
		if (gmailPrivacyState != other.gmailPrivacyState)
			return false;
		if (msnPrivacyState != other.msnPrivacyState)
			return false;
		if (occupationPrivacyState != other.occupationPrivacyState)
			return false;
		if (qqPrivacyState != other.qqPrivacyState)
			return false;
		if (skillsPrivacyState != other.skillsPrivacyState)
			return false;
		if (taobaoPrivacyState != other.taobaoPrivacyState)
			return false;
		if (tqqPrivacyState != other.tqqPrivacyState)
			return false;
		if (userId != other.userId)
			return false;
		if (weiboPrivacyState != other.weiboPrivacyState)
			return false;
		return true;
	}

}

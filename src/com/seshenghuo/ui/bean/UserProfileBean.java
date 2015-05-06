package com.seshenghuo.ui.bean;

import java.util.ArrayList;

import com.google.gson.Gson;

public class UserProfileBean {
	private UserBasicBean basic = new UserBasicBean();
	private UserExtraBean extra = new UserExtraBean();
	private UserWorkBean work = new UserWorkBean();
	private UserPrivacyBean privacy = new UserPrivacyBean();
	private ArrayList<UserEducationBean> educationList = new ArrayList<UserEducationBean>();
	private ArrayList<UserEmploymentBean> employmentList = new ArrayList<UserEmploymentBean>();

	public UserProfileBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the basic
	 */
	public UserBasicBean getBasic() {
		return basic;
	}

	/**
	 * @param basic
	 *            the basic to set
	 */
	public void setBasic(UserBasicBean basic) {
		this.basic = basic;
	}

	/**
	 * @return the educationList
	 */
	public ArrayList<UserEducationBean> getEducationList() {
		return educationList;
	}

	/**
	 * @param educationList
	 *            the educationList to set
	 */
	public void setEducationList(ArrayList<UserEducationBean> educationList) {
		this.educationList = educationList;
	}

	/**
	 * @return the employmentList
	 */
	public ArrayList<UserEmploymentBean> getEmploymentList() {
		return employmentList;
	}

	/**
	 * @param employmentList
	 *            the employmentList to set
	 */
	public void setEmploymentList(ArrayList<UserEmploymentBean> employmentList) {
		this.employmentList = employmentList;
	}

	/**
	 * @return the extra
	 */
	public UserExtraBean getExtra() {
		return extra;
	}

	/**
	 * @param extra
	 *            the extra to set
	 */
	public void setExtra(UserExtraBean extra) {
		this.extra = extra;
	}

	/**
	 * @return the privacy
	 */
	public UserPrivacyBean getPrivacy() {
		return privacy;
	}

	/**
	 * @param privacy
	 *            the privacy to set
	 */
	public void setPrivacy(UserPrivacyBean privacy) {
		this.privacy = privacy;
	}

	/**
	 * @return the work
	 */
	public UserWorkBean getWork() {
		return work;
	}

	/**
	 * @param work
	 *            the work to set
	 */
	public void setWork(UserWorkBean work) {
		this.work = work;
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
		result = prime * result + ((basic == null) ? 0 : basic.hashCode());
		result = prime * result
				+ ((educationList == null) ? 0 : educationList.hashCode());
		result = prime * result
				+ ((employmentList == null) ? 0 : employmentList.hashCode());
		result = prime * result + ((extra == null) ? 0 : extra.hashCode());
		result = prime * result + ((privacy == null) ? 0 : privacy.hashCode());
		result = prime * result + ((work == null) ? 0 : work.hashCode());
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
		UserProfileBean other = (UserProfileBean) obj;
		if (basic == null) {
			if (other.basic != null)
				return false;
		} else if (!basic.equals(other.basic))
			return false;
		if (educationList == null) {
			if (other.educationList != null)
				return false;
		} else if (!educationList.equals(other.educationList))
			return false;
		if (employmentList == null) {
			if (other.employmentList != null)
				return false;
		} else if (!employmentList.equals(other.employmentList))
			return false;
		if (extra == null) {
			if (other.extra != null)
				return false;
		} else if (!extra.equals(other.extra))
			return false;
		if (privacy == null) {
			if (other.privacy != null)
				return false;
		} else if (!privacy.equals(other.privacy))
			return false;
		if (work == null) {
			if (other.work != null)
				return false;
		} else if (!work.equals(other.work))
			return false;
		return true;
	}

}

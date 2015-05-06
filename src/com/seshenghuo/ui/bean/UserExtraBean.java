/**
 * 
 */
package com.seshenghuo.ui.bean;

import com.google.gson.Gson;

/**
 * @author carlli
 * 
 */
public class UserExtraBean {
	private int userId = 0;
	private int userQQ = 0;
	private String userGmail = "";
	private String userMSN = "";
	private String userWeibo = "";
	private String userTQQ = "";
	private String userWeixin = "";
	private String userTaobao = "";
	private String birthday = "";
	private int gender = 0;
	private String cover = "";

	/**
	 * 
	 */
	public UserExtraBean() {
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
	 * @return the userQQ
	 */
	public int getUserQQ() {
		return userQQ;
	}

	/**
	 * @param userQQ
	 *            the userQQ to set
	 */
	public void setUserQQ(int userQQ) {
		this.userQQ = userQQ;
	}

	/**
	 * @return the userGmail
	 */
	public String getUserGmail() {
		return userGmail;
	}

	/**
	 * @param userGmail
	 *            the userGmail to set
	 */
	public void setUserGmail(String userGmail) {
		this.userGmail = userGmail;
	}

	/**
	 * @return the userMSN
	 */
	public String getUserMSN() {
		return userMSN;
	}

	/**
	 * @param userMSN
	 *            the userMSN to set
	 */
	public void setUserMSN(String userMSN) {
		this.userMSN = userMSN;
	}

	/**
	 * @return the userWeibo
	 */
	public String getUserWeibo() {
		return userWeibo;
	}

	/**
	 * @param userWeibo
	 *            the userWeibo to set
	 */
	public void setUserWeibo(String userWeibo) {
		this.userWeibo = userWeibo;
	}

	/**
	 * @return the userTQQ
	 */
	public String getUserTQQ() {
		return userTQQ;
	}

	/**
	 * @param userTQQ
	 *            the userTQQ to set
	 */
	public void setUserTQQ(String userTQQ) {
		this.userTQQ = userTQQ;
	}

	/**
	 * @return the userWeixin
	 */
	public String getUserWeixin() {
		return userWeixin;
	}

	/**
	 * @param userWeixin
	 *            the userWeixin to set
	 */
	public void setUserWeixin(String userWeixin) {
		this.userWeixin = userWeixin;
	}

	/**
	 * @return the userTaobao
	 */
	public String getUserTaobao() {
		return userTaobao;
	}

	/**
	 * @param userTaobao
	 *            the userTaobao to set
	 */
	public void setUserTaobao(String userTaobao) {
		this.userTaobao = userTaobao;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the gender
	 */
	public int getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}

	/**
	 * @return the cover
	 */
	public String getCover() {
		return cover;
	}

	/**
	 * @param cover
	 *            the cover to set
	 */
	public void setCover(String cover) {
		this.cover = cover;
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
		result = prime * result
				+ ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((cover == null) ? 0 : cover.hashCode());
		result = prime * result + gender;
		result = prime * result
				+ ((userGmail == null) ? 0 : userGmail.hashCode());
		result = prime * result + userId;
		result = prime * result + ((userMSN == null) ? 0 : userMSN.hashCode());
		result = prime * result + userQQ;
		result = prime * result + ((userTQQ == null) ? 0 : userTQQ.hashCode());
		result = prime * result
				+ ((userTaobao == null) ? 0 : userTaobao.hashCode());
		result = prime * result
				+ ((userWeibo == null) ? 0 : userWeibo.hashCode());
		result = prime * result
				+ ((userWeixin == null) ? 0 : userWeixin.hashCode());
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
		UserExtraBean other = (UserExtraBean) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (cover == null) {
			if (other.cover != null)
				return false;
		} else if (!cover.equals(other.cover))
			return false;
		if (gender != other.gender)
			return false;
		if (userGmail == null) {
			if (other.userGmail != null)
				return false;
		} else if (!userGmail.equals(other.userGmail))
			return false;
		if (userId != other.userId)
			return false;
		if (userMSN == null) {
			if (other.userMSN != null)
				return false;
		} else if (!userMSN.equals(other.userMSN))
			return false;
		if (userQQ != other.userQQ)
			return false;
		if (userTQQ == null) {
			if (other.userTQQ != null)
				return false;
		} else if (!userTQQ.equals(other.userTQQ))
			return false;
		if (userTaobao == null) {
			if (other.userTaobao != null)
				return false;
		} else if (!userTaobao.equals(other.userTaobao))
			return false;
		if (userWeibo == null) {
			if (other.userWeibo != null)
				return false;
		} else if (!userWeibo.equals(other.userWeibo))
			return false;
		if (userWeixin == null) {
			if (other.userWeixin != null)
				return false;
		} else if (!userWeixin.equals(other.userWeixin))
			return false;
		return true;
	}

}

package com.seshenghuo.ui.bean;

import com.google.gson.Gson;

/**
 * 
 */

/**
 * @author carlli
 * 
 */
public class UserBasicBean {
	private int userId = 0;
	private String userName = "";
	private String userPasswd = "";
	private String nickName = "";
	private String userEmail = "";
	private String regDate = "";
	private String regIp = "";
	private String loginDate = "";
	private String loginIp = "";
	private int userStatus = 0;
	private String userAvator = "";

	/**
	 * 
	 */
	public UserBasicBean() {
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userPasswd
	 */
	public String getUserPasswd() {
		return userPasswd;
	}

	/**
	 * @param userPasswd
	 *            the userPasswd to set
	 */
	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName
	 *            the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * @param userEmail
	 *            the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * @return the regDate
	 */
	public String getRegDate() {
		return regDate;
	}

	/**
	 * @param regDate
	 *            the regDate to set
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	/**
	 * @return the regIp
	 */
	public String getRegIp() {
		return regIp;
	}

	/**
	 * @param regIp
	 *            the regIp to set
	 */
	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}

	/**
	 * @return the loginDate
	 */
	public String getLoginDate() {
		return loginDate;
	}

	/**
	 * @param loginDate
	 *            the loginDate to set
	 */
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	/**
	 * @return the loginIp
	 */
	public String getLoginIp() {
		return loginIp;
	}

	/**
	 * @param loginIp
	 *            the loginIp to set
	 */
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	/**
	 * @return the userStatus
	 */
	public int getUserStatus() {
		return userStatus;
	}

	/**
	 * @param userStatus
	 *            the userStatus to set
	 */
	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	/**
	 * @return the userAvator
	 */
	public String getUserAvator() {
		return userAvator;
	}

	/**
	 * @param userAvator
	 *            the userAvator to set
	 */
	public void setUserAvator(String userAvator) {
		this.userAvator = userAvator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		Gson g = new Gson();
		UserBasicBean ubb = new UserBasicBean();

		ubb.setUserId(userId);
		ubb.setUserName(userName);
		ubb.setNickName(nickName);
		ubb.setUserPasswd(null);
		ubb.setUserEmail(userEmail);
		ubb.setRegDate(regDate);
		ubb.setRegIp(regIp);
		ubb.setLoginDate(loginDate);
		ubb.setLoginIp(loginIp);
		ubb.setUserStatus(userStatus);
		ubb.setUserAvator(userAvator);

		String json = g.toJson(ubb);

		g = null;
		ubb = null;

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
				+ ((loginDate == null) ? 0 : loginDate.hashCode());
		result = prime * result + ((loginIp == null) ? 0 : loginIp.hashCode());
		result = prime * result
				+ ((nickName == null) ? 0 : nickName.hashCode());
		result = prime * result + ((regDate == null) ? 0 : regDate.hashCode());
		result = prime * result + ((regIp == null) ? 0 : regIp.hashCode());
		result = prime * result
				+ ((userAvator == null) ? 0 : userAvator.hashCode());
		result = prime * result
				+ ((userEmail == null) ? 0 : userEmail.hashCode());
		result = prime * result + userId;
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		result = prime * result
				+ ((userPasswd == null) ? 0 : userPasswd.hashCode());
		result = prime * result + userStatus;
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
		UserBasicBean other = (UserBasicBean) obj;
		if (loginDate == null) {
			if (other.loginDate != null)
				return false;
		} else if (!loginDate.equals(other.loginDate))
			return false;
		if (loginIp == null) {
			if (other.loginIp != null)
				return false;
		} else if (!loginIp.equals(other.loginIp))
			return false;
		if (nickName == null) {
			if (other.nickName != null)
				return false;
		} else if (!nickName.equals(other.nickName))
			return false;
		if (regDate == null) {
			if (other.regDate != null)
				return false;
		} else if (!regDate.equals(other.regDate))
			return false;
		if (regIp == null) {
			if (other.regIp != null)
				return false;
		} else if (!regIp.equals(other.regIp))
			return false;
		if (userAvator == null) {
			if (other.userAvator != null)
				return false;
		} else if (!userAvator.equals(other.userAvator))
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		if (userId != other.userId)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userPasswd == null) {
			if (other.userPasswd != null)
				return false;
		} else if (!userPasswd.equals(other.userPasswd))
			return false;
		if (userStatus != other.userStatus)
			return false;
		return true;
	}

}

/**
 * 
 */
package com.seshenghuo.ui.bean;

import com.google.gson.Gson;

/**
 * @author carlli
 * 
 */
public class PostsBean {
	private int postId = 0;
	private int postUserId = 0;
	private String postUserName = "";
	private String postUserNickName = "";
	private String postUserAvator = "";
	private String postTitle = "";
	private String postSummary = "";
	private int postLoveHit = 0;
	private int postPrivacyState = 0;
	private String postDate = "";
	private String postType = "text";

	/**
	 * 
	 */
	public PostsBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the postId
	 */
	public int getPostId() {
		return postId;
	}

	/**
	 * @param postId
	 *            the postId to set
	 */
	public void setPostId(int postId) {
		this.postId = postId;
	}

	/**
	 * @return the postUserId
	 */
	public int getPostUserId() {
		return postUserId;
	}

	/**
	 * @param postUserId
	 *            the postUserId to set
	 */
	public void setPostUserId(int postUserId) {
		this.postUserId = postUserId;
	}

	/**
	 * @return the postUserName
	 */
	public String getPostUserName() {
		return postUserName;
	}

	/**
	 * @param postUserName
	 *            the postUserName to set
	 */
	public void setPostUserName(String postUserName) {
		this.postUserName = postUserName;
	}

	/**
	 * @return the postUserNickName
	 */
	public String getPostUserNickName() {
		return postUserNickName;
	}

	/**
	 * @param postUserNickName
	 *            the postUserNickName to set
	 */
	public void setPostUserNickName(String postUserNickName) {
		this.postUserNickName = postUserNickName;
	}

	/**
	 * @return the postUserAvator
	 */
	public String getPostUserAvator() {
		return postUserAvator;
	}

	/**
	 * @param postUserAvator
	 *            the postUserAvator to set
	 */
	public void setPostUserAvator(String postUserAvator) {
		this.postUserAvator = postUserAvator;
	}

	/**
	 * @return the postTitle
	 */
	public String getPostTitle() {
		return postTitle;
	}

	/**
	 * @param postTitle
	 *            the postTitle to set
	 */
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	/**
	 * @return the postSummary
	 */
	public String getPostSummary() {
		return postSummary;
	}

	/**
	 * @param postSummary
	 *            the postSummary to set
	 */
	public void setPostSummary(String postSummary) {
		this.postSummary = postSummary;
	}

	/**
	 * @return the postLoveHit
	 */
	public int getPostLoveHit() {
		return postLoveHit;
	}

	/**
	 * @param postLoveHit
	 *            the postLoveHit to set
	 */
	public void setPostLoveHit(int postLoveHit) {
		this.postLoveHit = postLoveHit;
	}

	/**
	 * @return the postPrivacyState
	 */
	public int getPostPrivacyState() {
		return postPrivacyState;
	}

	/**
	 * @param postPrivacyState
	 *            the postPrivacyState to set
	 */
	public void setPostPrivacyState(int postPrivacyState) {
		this.postPrivacyState = postPrivacyState;
	}

	/**
	 * @return the postDate
	 */
	public String getPostDate() {
		return postDate;
	}

	/**
	 * @param postDate
	 *            the postDate to set
	 */
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	/**
	 * @return the postType
	 */
	public String getPostType() {
		return postType;
	}

	/**
	 * @param postType
	 *            the postType to set
	 */
	public void setPostType(String postType) {
		this.postType = postType;
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
				+ ((postDate == null) ? 0 : postDate.hashCode());
		result = prime * result + postId;
		result = prime * result + postLoveHit;
		result = prime * result + postPrivacyState;
		result = prime * result
				+ ((postSummary == null) ? 0 : postSummary.hashCode());
		result = prime * result
				+ ((postTitle == null) ? 0 : postTitle.hashCode());
		result = prime * result
				+ ((postType == null) ? 0 : postType.hashCode());
		result = prime * result
				+ ((postUserAvator == null) ? 0 : postUserAvator.hashCode());
		result = prime * result + postUserId;
		result = prime * result
				+ ((postUserName == null) ? 0 : postUserName.hashCode());
		result = prime
				* result
				+ ((postUserNickName == null) ? 0 : postUserNickName.hashCode());
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
		PostsBean other = (PostsBean) obj;
		if (postDate == null) {
			if (other.postDate != null)
				return false;
		} else if (!postDate.equals(other.postDate))
			return false;
		if (postId != other.postId)
			return false;
		if (postLoveHit != other.postLoveHit)
			return false;
		if (postPrivacyState != other.postPrivacyState)
			return false;
		if (postSummary == null) {
			if (other.postSummary != null)
				return false;
		} else if (!postSummary.equals(other.postSummary))
			return false;
		if (postTitle == null) {
			if (other.postTitle != null)
				return false;
		} else if (!postTitle.equals(other.postTitle))
			return false;
		if (postType == null) {
			if (other.postType != null)
				return false;
		} else if (!postType.equals(other.postType))
			return false;
		if (postUserAvator == null) {
			if (other.postUserAvator != null)
				return false;
		} else if (!postUserAvator.equals(other.postUserAvator))
			return false;
		if (postUserId != other.postUserId)
			return false;
		if (postUserName == null) {
			if (other.postUserName != null)
				return false;
		} else if (!postUserName.equals(other.postUserName))
			return false;
		if (postUserNickName == null) {
			if (other.postUserNickName != null)
				return false;
		} else if (!postUserNickName.equals(other.postUserNickName))
			return false;
		return true;
	}

}

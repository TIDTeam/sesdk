package com.seshenghuo.ui.bean;

import com.google.gson.Gson;

/**
 * 
 */

/**
 * @author carlli
 * 
 */
public class TextsPostsBean extends PostsBean {
	private int textId = 0;
	private String textBody = "";
	private String textPostDate = "";

	/**
	 * 
	 */
	public TextsPostsBean() {
		// TODO Auto-generated constructor stub
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.bean.PostsBean#getPostId()
	 */
	@Override
	public int getPostId() {
		// TODO Auto-generated method stub
		return super.getPostId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.bean.PostsBean#setPostId(int)
	 */
	@Override
	public void setPostId(int postId) {
		// TODO Auto-generated method stub
		super.setPostId(postId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.bean.PostsBean#getPostTitle()
	 */
	@Override
	public String getPostTitle() {
		// TODO Auto-generated method stub
		return super.getPostTitle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.bean.PostsBean#setPostTitle(java.lang.String)
	 */
	@Override
	public void setPostTitle(String postTitle) {
		// TODO Auto-generated method stub
		super.setPostTitle(postTitle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.bean.PostsBean#getPostSummary()
	 */
	@Override
	public String getPostSummary() {
		// TODO Auto-generated method stub
		return super.getPostSummary();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.bean.PostsBean#setPostSummary(java.lang.String)
	 */
	@Override
	public void setPostSummary(String postSummary) {
		// TODO Auto-generated method stub
		super.setPostSummary(postSummary);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.bean.PostsBean#getPostLoveHit()
	 */
	@Override
	public int getPostLoveHit() {
		// TODO Auto-generated method stub
		return super.getPostLoveHit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.bean.PostsBean#setPostLoveHit(int)
	 */
	@Override
	public void setPostLoveHit(int postLoveHit) {
		// TODO Auto-generated method stub
		super.setPostLoveHit(postLoveHit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.bean.PostsBean#getPostPrivacyState()
	 */
	@Override
	public int getPostPrivacyState() {
		// TODO Auto-generated method stub
		return super.getPostPrivacyState();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.bean.PostsBean#setPostPrivacyState(int)
	 */
	@Override
	public void setPostPrivacyState(int postPrivacyState) {
		// TODO Auto-generated method stub
		super.setPostPrivacyState(postPrivacyState);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.bean.PostsBean#getPostDate()
	 */
	@Override
	public String getPostDate() {
		// TODO Auto-generated method stub
		return super.getPostDate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.bean.PostsBean#setPostDate(java.lang.String)
	 */
	@Override
	public void setPostDate(String postDate) {
		// TODO Auto-generated method stub
		super.setPostDate(postDate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.bean.PostsBean#getPostType()
	 */
	@Override
	public String getPostType() {
		// TODO Auto-generated method stub
		return super.getPostType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.bean.PostsBean#setPostType(java.lang.String)
	 */
	@Override
	public void setPostType(String postType) {
		// TODO Auto-generated method stub
		super.setPostType(postType);
	}

	/**
	 * @return the textId
	 */
	public int getTextId() {
		return textId;
	}

	/**
	 * @param textId
	 *            the textId to set
	 */
	public void setTextId(int textId) {
		this.textId = textId;
	}

	/**
	 * @return the textBody
	 */
	public String getTextBody() {
		return textBody;
	}

	/**
	 * @param textBody
	 *            the textBody to set
	 */
	public void setTextBody(String textBody) {
		this.textBody = textBody;
	}

	/**
	 * @return the textPostDate
	 */
	public String getTextPostDate() {
		return textPostDate;
	}

	/**
	 * @param textPostDate
	 *            the textPostDate to set
	 */
	public void setTextPostDate(String textPostDate) {
		this.textPostDate = textPostDate;
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
		int result = super.hashCode();
		result = prime * result
				+ ((textBody == null) ? 0 : textBody.hashCode());
		result = prime * result + textId;
		result = prime * result
				+ ((textPostDate == null) ? 0 : textPostDate.hashCode());
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TextsPostsBean other = (TextsPostsBean) obj;
		if (textBody == null) {
			if (other.textBody != null)
				return false;
		} else if (!textBody.equals(other.textBody))
			return false;
		if (textId != other.textId)
			return false;
		if (textPostDate == null) {
			if (other.textPostDate != null)
				return false;
		} else if (!textPostDate.equals(other.textPostDate))
			return false;
		return true;
	}

}

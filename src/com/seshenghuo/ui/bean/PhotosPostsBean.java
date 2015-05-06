/**
 * 
 */
package com.seshenghuo.ui.bean;

import com.google.gson.Gson;

/**
 * @author carlli
 * 
 */
public class PhotosPostsBean extends PostsBean {
	private int photoId = 0;
	private String photoPath = "";
	private String photoAddDate = "";

	/**
	 * 
	 */
	public PhotosPostsBean() {
		// TODO Auto-generated constructor stub
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.bean.TextPostsBean#getPostId()
	 */
	@Override
	public int getPostId() {
		// TODO Auto-generated method stub
		return super.getPostId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.bean.TextPostsBean#setPostId(int)
	 */
	@Override
	public void setPostId(int postId) {
		// TODO Auto-generated method stub
		super.setPostId(postId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.bean.TextPostsBean#getPostTitle()
	 */
	@Override
	public String getPostTitle() {
		// TODO Auto-generated method stub
		return super.getPostTitle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.bean.TextPostsBean#setPostTitle(java.lang.String)
	 */
	@Override
	public void setPostTitle(String postTitle) {
		// TODO Auto-generated method stub
		super.setPostTitle(postTitle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.bean.TextPostsBean#getPostSummary()
	 */
	@Override
	public String getPostSummary() {
		// TODO Auto-generated method stub
		return super.getPostSummary();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.seshenghuo.ui.bean.TextPostsBean#setPostSummary(java.lang.String)
	 */
	@Override
	public void setPostSummary(String postSummary) {
		// TODO Auto-generated method stub
		super.setPostSummary(postSummary);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.bean.TextPostsBean#getPostLoveHit()
	 */
	@Override
	public int getPostLoveHit() {
		// TODO Auto-generated method stub
		return super.getPostLoveHit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.bean.TextPostsBean#setPostLoveHit(int)
	 */
	@Override
	public void setPostLoveHit(int postLoveHit) {
		// TODO Auto-generated method stub
		super.setPostLoveHit(postLoveHit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.bean.TextPostsBean#getPostPrivacyState()
	 */
	@Override
	public int getPostPrivacyState() {
		// TODO Auto-generated method stub
		return super.getPostPrivacyState();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.bean.TextPostsBean#setPostPrivacyState(int)
	 */
	@Override
	public void setPostPrivacyState(int postPrivacyState) {
		// TODO Auto-generated method stub
		super.setPostPrivacyState(postPrivacyState);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.bean.TextPostsBean#getPostDate()
	 */
	@Override
	public String getPostDate() {
		// TODO Auto-generated method stub
		return super.getPostDate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.bean.TextPostsBean#setPostDate(java.lang.String)
	 */
	@Override
	public void setPostDate(String postDate) {
		// TODO Auto-generated method stub
		super.setPostDate(postDate);
	}

	/**
	 * @return the photoId
	 */
	public int getPhotoId() {
		return photoId;
	}

	/**
	 * @param photoId
	 *            the photoId to set
	 */
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	/**
	 * @return the photoPath
	 */
	public String getPhotoPath() {
		return photoPath;
	}

	/**
	 * @param photoPath
	 *            the photoPath to set
	 */
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	/**
	 * @return the photoAddDate
	 */
	public String getPhotoAddDate() {
		return photoAddDate;
	}

	/**
	 * @param photoAddDate
	 *            the photoAddDate to set
	 */
	public void setPhotoAddDate(String photoAddDate) {
		this.photoAddDate = photoAddDate;
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
				+ ((photoAddDate == null) ? 0 : photoAddDate.hashCode());
		result = prime * result + photoId;
		result = prime * result
				+ ((photoPath == null) ? 0 : photoPath.hashCode());
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
		PhotosPostsBean other = (PhotosPostsBean) obj;
		if (photoAddDate == null) {
			if (other.photoAddDate != null)
				return false;
		} else if (!photoAddDate.equals(other.photoAddDate))
			return false;
		if (photoId != other.photoId)
			return false;
		if (photoPath == null) {
			if (other.photoPath != null)
				return false;
		} else if (!photoPath.equals(other.photoPath))
			return false;
		return true;
	}

}

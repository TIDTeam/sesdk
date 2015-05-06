/**
 * 
 */
package com.seshenghuo.ui.bean;

import com.google.gson.Gson;

/**
 * @author carlli
 * 
 */
public class LifePostsBean extends PostsBean {
	private int lifeId = 0;
	private String lifeCommodityImage = "";
	private float lifeCommodityPrice = 0.00F;
	private String lifeBusinessOffline = "";
	private String lifeBusinessOnline = "";
	private String lifeMatrixCode = "";
	private String lifeDetail = "";
	private String lifePostDate = "";

	/**
	 * 
	 */
	public LifePostsBean() {
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
	 * @return the lifeId
	 */
	public int getLifeId() {
		return lifeId;
	}

	/**
	 * @param lifeId
	 *            the lifeId to set
	 */
	public void setLifeId(int lifeId) {
		this.lifeId = lifeId;
	}

	/**
	 * @return the lifeCommodityImage
	 */
	public String getLifeCommodityImage() {
		return lifeCommodityImage;
	}

	/**
	 * @param lifeCommodityImage
	 *            the lifeCommodityImage to set
	 */
	public void setLifeCommodityImage(String lifeCommodityImage) {
		this.lifeCommodityImage = lifeCommodityImage;
	}

	/**
	 * @return the lifeCommodityPrice
	 */
	public float getLifeCommodityPrice() {
		return lifeCommodityPrice;
	}

	/**
	 * @param lifeCommodityPrice
	 *            the lifeCommodityPrice to set
	 */
	public void setLifeCommodityPrice(float lifeCommodityPrice) {
		this.lifeCommodityPrice = lifeCommodityPrice;
	}

	/**
	 * @return the lifeBusinessOffline
	 */
	public String getLifeBusinessOffline() {
		return lifeBusinessOffline;
	}

	/**
	 * @param lifeBusinessOffline
	 *            the lifeBusinessOffline to set
	 */
	public void setLifeBusinessOffline(String lifeBusinessOffline) {
		this.lifeBusinessOffline = lifeBusinessOffline;
	}

	/**
	 * @return the lifeBusinessOnline
	 */
	public String getLifeBusinessOnline() {
		return lifeBusinessOnline;
	}

	/**
	 * @param lifeBusinessOnline
	 *            the lifeBusinessOnline to set
	 */
	public void setLifeBusinessOnline(String lifeBusinessOnline) {
		this.lifeBusinessOnline = lifeBusinessOnline;
	}

	/**
	 * @return the lifeMatrixCode
	 */
	public String getLifeMatrixCode() {
		return lifeMatrixCode;
	}

	/**
	 * @param lifeMatrixCode
	 *            the lifeMatrixCode to set
	 */
	public void setLifeMatrixCode(String lifeMatrixCode) {
		this.lifeMatrixCode = lifeMatrixCode;
	}

	/**
	 * @return the lifeDetail
	 */
	public String getLifeDetail() {
		return lifeDetail;
	}

	/**
	 * @param lifeDetail
	 *            the lifeDetail to set
	 */
	public void setLifeDetail(String lifeDetail) {
		this.lifeDetail = lifeDetail;
	}

	/**
	 * @return the lifePostDate
	 */
	public String getLifePostDate() {
		return lifePostDate;
	}

	/**
	 * @param lifePostDate
	 *            the lifePostDate to set
	 */
	public void setLifePostDate(String lifePostDate) {
		this.lifePostDate = lifePostDate;
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
		result = prime
				* result
				+ ((lifeBusinessOffline == null) ? 0 : lifeBusinessOffline
						.hashCode());
		result = prime
				* result
				+ ((lifeBusinessOnline == null) ? 0 : lifeBusinessOnline
						.hashCode());
		result = prime
				* result
				+ ((lifeCommodityImage == null) ? 0 : lifeCommodityImage
						.hashCode());
		result = prime * result + Float.floatToIntBits(lifeCommodityPrice);
		result = prime * result
				+ ((lifeDetail == null) ? 0 : lifeDetail.hashCode());
		result = prime * result + lifeId;
		result = prime * result
				+ ((lifeMatrixCode == null) ? 0 : lifeMatrixCode.hashCode());
		result = prime * result
				+ ((lifePostDate == null) ? 0 : lifePostDate.hashCode());
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
		LifePostsBean other = (LifePostsBean) obj;
		if (lifeBusinessOffline == null) {
			if (other.lifeBusinessOffline != null)
				return false;
		} else if (!lifeBusinessOffline.equals(other.lifeBusinessOffline))
			return false;
		if (lifeBusinessOnline == null) {
			if (other.lifeBusinessOnline != null)
				return false;
		} else if (!lifeBusinessOnline.equals(other.lifeBusinessOnline))
			return false;
		if (lifeCommodityImage == null) {
			if (other.lifeCommodityImage != null)
				return false;
		} else if (!lifeCommodityImage.equals(other.lifeCommodityImage))
			return false;
		if (Float.floatToIntBits(lifeCommodityPrice) != Float
				.floatToIntBits(other.lifeCommodityPrice))
			return false;
		if (lifeDetail == null) {
			if (other.lifeDetail != null)
				return false;
		} else if (!lifeDetail.equals(other.lifeDetail))
			return false;
		if (lifeId != other.lifeId)
			return false;
		if (lifeMatrixCode == null) {
			if (other.lifeMatrixCode != null)
				return false;
		} else if (!lifeMatrixCode.equals(other.lifeMatrixCode))
			return false;
		if (lifePostDate == null) {
			if (other.lifePostDate != null)
				return false;
		} else if (!lifePostDate.equals(other.lifePostDate))
			return false;
		return true;
	}

}

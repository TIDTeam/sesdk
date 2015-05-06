/**
 * 
 */
package com.seshenghuo.ui.bean;

import com.google.gson.Gson;

/**
 * @author carlli
 * 
 */
public class EventsPostsBean extends PostsBean {
	private int eventId = 0;
	private String eventStartDate = "";
	private String eventEndDate = "";
	private String eventLocation = "";
	private String eventDetail = "";
	private String eventWebsite = "";
	private String eventCreateDate = "";

	/**
	 * 
	 */
	public EventsPostsBean() {
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
	 * @return the eventId
	 */
	public int getEventId() {
		return eventId;
	}

	/**
	 * @param eventId
	 *            the eventId to set
	 */
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the eventStartDate
	 */
	public String getEventStartDate() {
		return eventStartDate;
	}

	/**
	 * @param eventStartDate
	 *            the eventStartDate to set
	 */
	public void setEventStartDate(String eventStartDate) {
		this.eventStartDate = eventStartDate;
	}

	/**
	 * @return the eventEndDate
	 */
	public String getEventEndDate() {
		return eventEndDate;
	}

	/**
	 * @param eventEndDate
	 *            the eventEndDate to set
	 */
	public void setEventEndDate(String eventEndDate) {
		this.eventEndDate = eventEndDate;
	}

	/**
	 * @return the eventLocation
	 */
	public String getEventLocation() {
		return eventLocation;
	}

	/**
	 * @param eventLocation
	 *            the eventLocation to set
	 */
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	/**
	 * @return the eventDetail
	 */
	public String getEventDetail() {
		return eventDetail;
	}

	/**
	 * @param eventDetail
	 *            the eventDetail to set
	 */
	public void setEventDetail(String eventDetail) {
		this.eventDetail = eventDetail;
	}

	/**
	 * @return the eventWebsite
	 */
	public String getEventWebsite() {
		return eventWebsite;
	}

	/**
	 * @param eventWebsite
	 *            the eventWebsite to set
	 */
	public void setEventWebsite(String eventWebsite) {
		this.eventWebsite = eventWebsite;
	}

	/**
	 * @return the eventCreateDate
	 */
	public String getEventCreateDate() {
		return eventCreateDate;
	}

	/**
	 * @param eventCreateDate
	 *            the eventCreateDate to set
	 */
	public void setEventCreateDate(String eventCreateDate) {
		this.eventCreateDate = eventCreateDate;
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
				+ ((eventCreateDate == null) ? 0 : eventCreateDate.hashCode());
		result = prime * result
				+ ((eventDetail == null) ? 0 : eventDetail.hashCode());
		result = prime * result
				+ ((eventEndDate == null) ? 0 : eventEndDate.hashCode());
		result = prime * result + eventId;
		result = prime * result
				+ ((eventLocation == null) ? 0 : eventLocation.hashCode());
		result = prime * result
				+ ((eventStartDate == null) ? 0 : eventStartDate.hashCode());
		result = prime * result
				+ ((eventWebsite == null) ? 0 : eventWebsite.hashCode());
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
		EventsPostsBean other = (EventsPostsBean) obj;
		if (eventCreateDate == null) {
			if (other.eventCreateDate != null)
				return false;
		} else if (!eventCreateDate.equals(other.eventCreateDate))
			return false;
		if (eventDetail == null) {
			if (other.eventDetail != null)
				return false;
		} else if (!eventDetail.equals(other.eventDetail))
			return false;
		if (eventEndDate == null) {
			if (other.eventEndDate != null)
				return false;
		} else if (!eventEndDate.equals(other.eventEndDate))
			return false;
		if (eventId != other.eventId)
			return false;
		if (eventLocation == null) {
			if (other.eventLocation != null)
				return false;
		} else if (!eventLocation.equals(other.eventLocation))
			return false;
		if (eventStartDate == null) {
			if (other.eventStartDate != null)
				return false;
		} else if (!eventStartDate.equals(other.eventStartDate))
			return false;
		if (eventWebsite == null) {
			if (other.eventWebsite != null)
				return false;
		} else if (!eventWebsite.equals(other.eventWebsite))
			return false;
		return true;
	}

}

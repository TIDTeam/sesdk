/**
 * 
 */
package com.seshenghuo.ui.logic;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seshenghuo.base.Response;
import com.seshenghuo.logger.L;
import com.seshenghuo.ui.base.BasicPosts;
import com.seshenghuo.ui.base.EventsPosts;
import com.seshenghuo.ui.base.LifePosts;
import com.seshenghuo.ui.base.PhotosPosts;
import com.seshenghuo.ui.base.TextsPosts;
import com.seshenghuo.ui.bean.EventsPostsBean;
import com.seshenghuo.ui.bean.LifePostsBean;
import com.seshenghuo.ui.bean.PhotosPostsBean;
import com.seshenghuo.ui.bean.PostsBean;
import com.seshenghuo.ui.bean.TextsPostsBean;
import com.seshenghuo.util.Util;

/**
 * @author carlli
 * 
 */
public class Posts extends AbstractLogic {	
	public static final int STATE_PUBLIC = 0;
	public static final int STATE_PRIVATE = 1;
	
	/**
	 * @param req
	 * @param resp
	 */
	public Posts(HttpServletRequest req, HttpServletResponse resp) {		
		super(req, resp);
		// TODO Auto-generated constructor stub
	}

	public Response<Object> queryAllPosts(PostsBean bean, int pageIndex,
			int pageSize) {
		long s = System.currentTimeMillis();
		Response<Object> resp = null;
		BasicPosts basic = new BasicPosts();
		resp = basic.query(bean, pageIndex, pageSize);
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", Posts.class, "queryAllPosts()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp,
				"UID: " + bean.getPostUserId(), source, cost);

		return resp;
	}

	public Response<Object> queryAllPosts() {
		PostsBean bean = new PostsBean();

		int pageIndex = this.getIntParameter("page_index", 0);
		int pageSize = this.getIntParameter("page_size", 0);

		bean.setPostUserId(this.getIntParameter("uid", 0));
		bean.setPostId(this.getIntParameter("post_id", 0));
		bean.setPostPrivacyState(this.getIntParameter("state", STATE_PUBLIC));
		bean.setPostDate(this.getStringParameter("date", ""));

		return queryAllPosts(bean, pageIndex, pageSize);
	}

	public Response<ArrayList<TextsPostsBean>> queryTextPosts(TextsPostsBean bean, int pageIndex,
			int pageSize) {
		long s = System.currentTimeMillis();
		Response<ArrayList<TextsPostsBean>> resp = null;
		TextsPosts texts = new TextsPosts();
		resp = texts.query(bean, pageIndex, pageSize);
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", Posts.class, "queryTextPosts()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp,
				"UID: " + bean.getPostUserId(), source, cost);

		return resp;
	}

	public Response<ArrayList<TextsPostsBean>> queryTextPosts() {
		TextsPostsBean bean = new TextsPostsBean();

		int pageIndex = this.getIntParameter("page_index", 0);
		int pageSize = this.getIntParameter("page_size", 0);

		bean.setPostUserId(this.getIntParameter("uid", 0));
		bean.setPostId(this.getIntParameter("post_id", 0));
		bean.setPostPrivacyState(this.getIntParameter("state", STATE_PUBLIC));
		bean.setPostDate(this.getStringParameter("date", ""));

		return queryTextPosts(bean, pageIndex, pageSize);
	}

	public Response<ArrayList<EventsPostsBean>> queryEventsPosts(EventsPostsBean bean,
			int pageIndex, int pageSize) {
		long s = System.currentTimeMillis();
		Response<ArrayList<EventsPostsBean>> resp = null;
		EventsPosts events = new EventsPosts();
		resp = events.query(bean, pageIndex, pageSize);
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", Posts.class, "queryEventsPosts()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp,
				"UID: " + bean.getPostUserId(), source, cost);

		return resp;
	}

	public Response<ArrayList<EventsPostsBean>> queryEventsPosts() {
		EventsPostsBean bean = new EventsPostsBean();

		int pageIndex = this.getIntParameter("page_index", 0);
		int pageSize = this.getIntParameter("page_size", 0);

		bean.setPostUserId(this.getIntParameter("uid", 0));
		bean.setPostId(this.getIntParameter("post_id", 0));
		bean.setPostPrivacyState(this.getIntParameter("state", STATE_PUBLIC));
		bean.setPostDate(this.getStringParameter("date", ""));

		return queryEventsPosts(bean, pageIndex, pageSize);
	}

	public Response<ArrayList<LifePostsBean>> queryLifePosts(LifePostsBean bean, int pageIndex,
			int pageSize) {
		long s = System.currentTimeMillis();
		Response<ArrayList<LifePostsBean>> resp = null;
		LifePosts life = new LifePosts();
		resp = life.query(bean, pageIndex, pageSize);
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", Posts.class, "queryLifePosts()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp,
				"UID: " + bean.getPostUserId(), source, cost);

		return resp;
	}

	public Response<ArrayList<LifePostsBean>> queryLifePosts() {
		LifePostsBean bean = new LifePostsBean();

		int pageIndex = this.getIntParameter("page_index", 0);
		int pageSize = this.getIntParameter("page_size", 0);

		bean.setPostUserId(this.getIntParameter("uid", 0));
		bean.setPostId(this.getIntParameter("post_id", 0));
		bean.setPostPrivacyState(this.getIntParameter("state", STATE_PUBLIC));
		bean.setPostDate(this.getStringParameter("date", ""));

		return queryLifePosts(bean, pageIndex, pageSize);
	}

	public Response<ArrayList<PhotosPostsBean>> queryPhotosPosts(PhotosPostsBean bean,
			int pageIndex, int pageSize) {
		long s = System.currentTimeMillis();
		Response<ArrayList<PhotosPostsBean>> resp = null;
		PhotosPosts photo = new PhotosPosts();
		resp = photo.query(bean, pageIndex, pageSize);
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", Posts.class, "queryPhotosPosts()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp,
				"UID: " + bean.getPostUserId(), source, cost);

		return resp;
	}

	public Response<ArrayList<PhotosPostsBean>> queryPhotosPosts() {
		PhotosPostsBean bean = new PhotosPostsBean();

		int pageIndex = this.getIntParameter("page_index", 0);
		int pageSize = this.getIntParameter("page_size", 0);

		bean.setPostUserId(this.getIntParameter("uid", 0));
		bean.setPostId(this.getIntParameter("post_id", 0));
		bean.setPostPrivacyState(this.getIntParameter("state", STATE_PUBLIC));
		bean.setPostDate(this.getStringParameter("date", ""));

		return queryPhotosPosts(bean, pageIndex, pageSize);
	}

	public Response<Object> updateLoveHit(int postId) {
		long s = System.currentTimeMillis();
		Response<Object> resp = null;
		BasicPosts basic = new BasicPosts();
		resp = basic.updateLoveHit(postId);
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", Posts.class, "updateLoveHit()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp, "Post ID: " + postId,
				source, cost);

		return resp;
	}

	public Response<Object> updateLoveHit() {
		int postId = this.getIntParameter("post_id");

		return updateLoveHit(postId);
	}

	public Response<Object> updateTextsPosts(TextsPostsBean bean) {
		long s = System.currentTimeMillis();
		Response<Object> resp = null;
		
		if(checkToken()){
			TextsPosts texts = new TextsPosts();
			resp = texts.update(bean);
		}else{
			resp = timeout();
		}
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", Posts.class, "updateTextPosts()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp,
				"Post ID: " + bean.getPostId(), source, cost);

		return resp;
	}

	public Response<Object> updateTextsPosts() {
		TextsPostsBean bean = new TextsPostsBean();		
		
		bean.setPostId(this.getIntParameter("post_id"));
		bean.setPostTitle(this.getStringParameter("post_title"));
		bean.setPostSummary(this.getStringParameter("post_summary"));
		bean.setPostPrivacyState(this.getIntParameter("state", STATE_PUBLIC));
		bean.setTextBody(this.getStringParameter("text_body"));
		
		String seed = this.getStringParameter("seed");
		setSeed(seed);
		
		return updateTextsPosts(bean);
	}

	public Response<Object> updateEventsPosts(EventsPostsBean bean) {
		long s = System.currentTimeMillis();
		Response<Object> resp = null;
		
		if(checkToken()){
			EventsPosts events = new EventsPosts();
			resp = events.update(bean);
		}else{
			resp = timeout();
		}
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", Posts.class, "updateEventsPosts()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp,
				"Post ID: " + bean.getPostId(), source, cost);

		return resp;
	}

	public Response<Object> updateEventsPosts() {
		EventsPostsBean bean = new EventsPostsBean();

		bean.setPostId(this.getIntParameter("post_id"));
		bean.setPostTitle(this.getStringParameter("post_title"));
		bean.setPostSummary(this.getStringParameter("post_summary"));
		bean.setPostPrivacyState(this.getIntParameter("state", STATE_PUBLIC));
		bean.setEventStartDate(this.getStringParameter("event_start"));
		bean.setEventEndDate(this.getStringParameter("event_end"));
		bean.setEventLocation(this.getStringParameter("event_location"));
		bean.setEventDetail(this.getStringParameter("event_detail"));
		bean.setEventWebsite(this.getStringParameter("event_website"));
		
		String seed = this.getStringParameter("seed");
		setSeed(seed);

		return updateEventsPosts(bean);
	}

	public Response<Object> updatePhotosPosts(PhotosPostsBean bean) {
		long s = System.currentTimeMillis();
		Response<Object> resp = null;
		
		if(checkToken()){
			PhotosPosts photos = new PhotosPosts();
			resp = photos.update(bean);
		}else{
			resp = timeout();
		}
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", Posts.class, "updatePhotosPosts()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp,
				"Post ID: " + bean.getPostId(), source, cost);

		return resp;
	}

	public Response<Object> updatePhotosPosts() {
		PhotosPostsBean bean = new PhotosPostsBean();

		bean.setPostId(this.getIntParameter("post_id"));
		bean.setPostTitle(this.getStringParameter("post_title"));
		bean.setPostSummary(this.getStringParameter("post_summary"));
		bean.setPostPrivacyState(this.getIntParameter("state", STATE_PUBLIC));
		bean.setPhotoPath(this.getStringParameter("photo_path"));
		
		String seed = this.getStringParameter("seed");
		setSeed(seed);

		return updatePhotosPosts(bean);
	}

	public Response<Object> updateLifePosts(LifePostsBean bean) {
		long s = System.currentTimeMillis();
		Response<Object> resp = null;
		
		if(checkToken()){
			LifePosts life = new LifePosts();
			resp = life.update(bean);
		}else{
			resp = timeout();
		}
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", Posts.class, "updateLifePosts()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp,
				"Post ID: " + bean.getPostId(), source, cost);

		return resp;
	}

	public Response<Object> updateLifePosts() {
		LifePostsBean bean = new LifePostsBean();

		bean.setPostId(this.getIntParameter("post_id"));
		bean.setPostTitle(this.getStringParameter("post_title"));
		bean.setPostSummary(this.getStringParameter("post_summary"));
		bean.setPostPrivacyState(this.getIntParameter("state", STATE_PUBLIC));
		bean.setLifeCommodityImage(this.getStringParameter("commodity_image"));
		bean.setLifeCommodityPrice(this.getFloatParameter("commodity_price"));
		bean.setLifeBusinessOffline(this.getStringParameter("business_offline"));
		bean.setLifeBusinessOnline(this.getStringParameter("business_online"));
		bean.setLifeMatrixCode(this.getStringParameter("matrix_code"));
		bean.setLifeDetail(this.getStringParameter("life_detail"));
		
		String seed = this.getStringParameter("seed");
		setSeed(seed);

		return updateLifePosts(bean);
	}

	public Response<Object> addTextsPosts(TextsPostsBean bean) {
		long s = System.currentTimeMillis();
		Response<Object> resp = null;
		
		if(checkToken() && bean.getPostUserId() > 0){
			TextsPosts texts = new TextsPosts();
			resp = texts.add(bean);
		}else{
			resp = timeout();
		}
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", Posts.class, "addTextPosts()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp,
				"UID: " + bean.getPostUserId(), source, cost);

		return resp;
	}

	public Response<Object> addTextsPosts() {
		TextsPostsBean bean = new TextsPostsBean();

		bean.setPostUserId(this.getIntCookie("uid", 0));
		bean.setPostTitle(this.getStringParameter("post_title"));
		bean.setPostSummary(this.getStringParameter("post_summary"));
		bean.setPostLoveHit(0);
		bean.setPostPrivacyState(this.getIntParameter("state", STATE_PUBLIC));
		bean.setPostDate(Util.getDateString());
		bean.setPostType(this.getStringParameter("post_type"));
		bean.setTextBody(this.getStringParameter("text_body"));
		bean.setTextPostDate(Util.getDateString());
		
		String seed = this.getStringParameter("seed");
		setSeed(seed);

		return addTextsPosts(bean);
	}

	public Response<Object> addEventsPosts(EventsPostsBean bean) {
		long s = System.currentTimeMillis();
		Response<Object> resp = null;
		
		if(checkToken() && bean.getPostUserId() > 0){
			EventsPosts events = new EventsPosts();
			resp = events.add(bean);
		}else{
			resp = timeout();
		}
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", Posts.class, "addEventsPosts()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp,
				"UID: " + bean.getPostUserId(), source, cost);

		return resp;
	}

	public Response<Object> addEventsPosts() {
		EventsPostsBean bean = new EventsPostsBean();

		bean.setPostUserId(this.getIntCookie("uid", 0));
		bean.setPostTitle(this.getStringParameter("post_title"));
		bean.setPostSummary(this.getStringParameter("post_summary"));
		bean.setPostLoveHit(0);
		bean.setPostPrivacyState(this.getIntParameter("state", STATE_PUBLIC));
		bean.setPostDate(Util.getDateString());
		bean.setPostType(this.getStringParameter("post_type"));
		bean.setEventStartDate(this.getStringParameter("event_start"));
		bean.setEventEndDate(this.getStringParameter("event_end"));
		bean.setEventLocation(this.getStringParameter("event_location"));
		bean.setEventDetail(this.getStringParameter("event_detail"));
		bean.setEventWebsite(this.getStringParameter("event_website"));
		bean.setEventCreateDate(Util.getDateString());
		
		String seed = this.getStringParameter("seed");
		setSeed(seed);

		return addEventsPosts(bean);
	}

	public Response<Object> addPhotosPosts(PhotosPostsBean bean) {
		long s = System.currentTimeMillis();
		Response<Object> resp = null;
		
		if(checkToken() && bean.getPostUserId() > 0){
			PhotosPosts photos = new PhotosPosts();
			resp = photos.add(bean);
		}else{
			resp = timeout();
		}
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", Posts.class, "addPhotosPosts()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp,
				"UID: " + bean.getPostUserId(), source, cost);

		return resp;
	}

	public Response<Object> addPhotosPosts() {
		PhotosPostsBean bean = new PhotosPostsBean();

		bean.setPostUserId(this.getIntCookie("uid", 0));
		bean.setPostTitle(this.getStringParameter("post_title"));
		bean.setPostSummary(this.getStringParameter("post_summary"));
		bean.setPostLoveHit(0);
		bean.setPostPrivacyState(this.getIntParameter("state", STATE_PUBLIC));
		bean.setPostDate(Util.getDateString());
		bean.setPostType(this.getStringParameter("post_type"));
		bean.setPhotoPath(this.getStringParameter("photo_path"));
		bean.setPhotoAddDate(Util.getDateString());
		
		String seed = this.getStringParameter("seed");
		setSeed(seed);

		return addPhotosPosts(bean);
	}

	public Response<Object> addLifePosts(LifePostsBean bean) {
		long s = System.currentTimeMillis();
		Response<Object> resp = null;
		
		if(checkToken() && bean.getPostUserId() > 0){
			LifePosts life = new LifePosts();
			resp = life.add(bean);
		}else{
			resp = timeout();
		}
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", Posts.class, "addLifePosts()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp,
				"UID: " + bean.getPostUserId(), source, cost);

		return resp;
	}

	public Response<Object> addLifePosts() {
		LifePostsBean bean = new LifePostsBean();

		bean.setPostUserId(this.getIntCookie("uid", 0));
		bean.setPostTitle(this.getStringParameter("post_title"));
		bean.setPostSummary(this.getStringParameter("post_summary"));
		bean.setPostLoveHit(0);
		bean.setPostPrivacyState(this.getIntParameter("state", STATE_PUBLIC));
		bean.setPostDate(Util.getDateString());
		bean.setPostType(this.getStringParameter("post_type"));
		bean.setLifeCommodityImage(this.getStringParameter("commodity_image"));
		bean.setLifeCommodityPrice(this.getFloatParameter("commodity_price"));
		bean.setLifeBusinessOffline(this.getStringParameter("business_offline"));
		bean.setLifeBusinessOnline(this.getStringParameter("business_online"));
		bean.setLifeMatrixCode(this.getStringParameter("matrix_code"));
		bean.setLifeDetail(this.getStringParameter("life_detail"));
		bean.setLifePostDate(Util.getDateString());
		
		String seed = this.getStringParameter("seed");
		setSeed(seed);

		return addLifePosts(bean);
	}
}

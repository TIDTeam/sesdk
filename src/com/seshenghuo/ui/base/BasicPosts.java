/**
 * 
 */
package com.seshenghuo.ui.base;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.seshenghuo.base.Response;
import com.seshenghuo.database.DBUtil;
import com.seshenghuo.database.SQL;
import com.seshenghuo.logger.L;
import com.seshenghuo.messagecode.Alarm;
import com.seshenghuo.ui.bean.EventsPostsBean;
import com.seshenghuo.ui.bean.LifePostsBean;
import com.seshenghuo.ui.bean.PhotosPostsBean;
import com.seshenghuo.ui.bean.PostsBean;
import com.seshenghuo.ui.bean.TextsPostsBean;
import com.seshenghuo.ui.constant.Constant;
import com.seshenghuo.util.Message;

/**
 * @author carlli
 * 
 */
public class BasicPosts extends AbstractBase<PostsBean> {

	/**
	 * 
	 */
	public BasicPosts() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.base.AbstractBase#update(java.lang.Object)
	 */
	@Override
	public Response<Object> update(PostsBean bean) {
		// TODO Auto-generated method stub
		String sql = SQL.getSQL(Constant.SQL.SP_UPT_POSTS);
		Connection conn = db.getConnection();

		int code = DBUtil.UNKNOWN_ERROR;
		int result = 0;
		String rcode = "";
		String rmessage = "";
		Response<Object> resp = new Response<Object>();
		CallableStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareCall(sql);

			stmt.setInt(1, bean.getPostId());
			stmt.setString(2, bean.getPostTitle());
			stmt.setString(3, bean.getPostSummary());
			stmt.setInt(4, bean.getPostPrivacyState());

			stmt.executeUpdate();

			rs = stmt.getResultSet();

			if (rs.next()) {
				result = rs.getInt(1);
			}

			L.info(BasicPosts.class, "update()", "INFO", "Result is " + result);

			if (result >= 1) {
				code = DBUtil.SUCCESS;
				rcode = "" + code;
			} else {
				code = DBUtil.NOT_MATCHED;
				rcode = Alarm.Level.SYSTEM + Alarm.Type.SQL + Alarm.Module.SQL
						+ code;
			}

			rmessage = Message.getMessage(rcode);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			code = DBUtil.SQL_EXCEPTION;
			rcode = Alarm.Level.SYSTEM + Alarm.Type.SQL + Alarm.Module.SQL
					+ code;
			rmessage = Message.getMessage(rcode);
			rmessage = Message.formatMessage(rmessage, e.getMessage(),
					e.getSQLState(), e.getErrorCode());

			L.error(BasicPosts.class, "update()", rcode, rmessage);
		} finally {
			db.close(stmt, rs);
			resp.setCode(rcode);
			resp.setMessage(rmessage);
		}

		return resp;
	}

	public Response<Object> updateLoveHit(int postId) {
		// TODO Auto-generated method stub
		String sql = SQL.getSQL(Constant.SQL.SP_UPT_LOVEHIT);
		Connection conn = db.getConnection();

		int code = DBUtil.UNKNOWN_ERROR;
		int result = 0;
		String rcode = "";
		String rmessage = "";
		Response<Object> resp = new Response<Object>();
		CallableStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareCall(sql);

			stmt.setInt(1, postId);

			stmt.executeUpdate();

			rs = stmt.getResultSet();

			if (rs.next()) {
				result = rs.getInt(1);
			}

			L.info(BasicPosts.class, "updateLoveHit()", "INFO", "Result is "
					+ result);

			if (result >= 1) {
				code = DBUtil.SUCCESS;
				rcode = "" + code;
			} else {
				code = DBUtil.NOT_MATCHED;
				rcode = Alarm.Level.SYSTEM + Alarm.Type.SQL + Alarm.Module.SQL
						+ code;
			}

			rmessage = Message.getMessage(rcode);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			code = DBUtil.SQL_EXCEPTION;
			rcode = Alarm.Level.SYSTEM + Alarm.Type.SQL + Alarm.Module.SQL
					+ code;
			rmessage = Message.getMessage(rcode);
			rmessage = Message.formatMessage(rmessage, e.getMessage(),
					e.getSQLState(), e.getErrorCode());

			L.error(BasicPosts.class, "updateLoveHit()", rcode, rmessage);
		} finally {
			db.close(stmt, rs);
			resp.setCode(rcode);
			resp.setMessage(rmessage);
		}

		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.base.AbstractBase#add(java.lang.Object)
	 */
	@Override
	public Response<Object> add(PostsBean bean) {
		// TODO Auto-generated method stub
		String sql = SQL.getSQL(Constant.SQL.SP_INS_POSTS);
		Connection conn = db.getConnection();

		int code = DBUtil.UNKNOWN_ERROR;
		int result = 0;
		String rcode = "";
		String rmessage = "";
		Response<Object> resp = new Response<Object>();
		CallableStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareCall(sql);

			stmt.setInt(1, bean.getPostUserId());
			stmt.setString(2, bean.getPostTitle());
			stmt.setString(3, bean.getPostSummary());
			stmt.setInt(4, bean.getPostLoveHit());
			stmt.setInt(5, bean.getPostPrivacyState());
			stmt.setString(6, bean.getPostDate());
			stmt.setString(7, bean.getPostType());

			stmt.executeUpdate();

			rs = stmt.getResultSet();

			if (rs.next()) {
				result = rs.getInt(1);
			}

			L.info(BasicPosts.class, "add()", "INFO", "Result is " + result);

			if (result >= 1) {
				code = DBUtil.SUCCESS;
				rcode = "" + code;
			} else {
				code = DBUtil.NOT_MATCHED;
				rcode = Alarm.Level.SYSTEM + Alarm.Type.SQL + Alarm.Module.SQL
						+ code;
			}

			rmessage = Message.getMessage(rcode);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			code = DBUtil.SQL_EXCEPTION;
			rcode = Alarm.Level.SYSTEM + Alarm.Type.SQL + Alarm.Module.SQL
					+ code;
			rmessage = Message.getMessage(rcode);
			rmessage = Message.formatMessage(rmessage, e.getMessage(),
					e.getSQLState(), e.getErrorCode());

			L.error(BasicPosts.class, "add()", rcode, rmessage);
		} finally {
			db.close(stmt, rs);
			resp.setCode(rcode);
			resp.setMessage(rmessage);
		}

		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.base.AbstractBase#query(java.lang.Object, int,
	 * int)
	 */
	@Override
	public Response<Object> query(PostsBean bean, int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		String sql = SQL.getSQL(Constant.SQL.SP_QRY_POSTS);
		Connection conn = db.getConnection();

		int code = DBUtil.UNKNOWN_ERROR;
		String rcode = "";
		String rmessage = "";
		Response<Object> resp = new Response<Object>();
		ArrayList<Object> list = new ArrayList<Object>();
		
		TextsPosts texts = new TextsPosts();
		Response<ArrayList<TextsPostsBean>> tpresp = null;
		ArrayList<TextsPostsBean> textList = null;
		EventsPosts events = new EventsPosts();
		Response<ArrayList<EventsPostsBean>>epresp = null;
		ArrayList<EventsPostsBean> eventList = null;
		PhotosPosts photos = new PhotosPosts();
		Response<ArrayList<PhotosPostsBean>> ppresp = null;
		ArrayList<PhotosPostsBean> photoList = null;
		LifePosts life = new LifePosts();
		Response<ArrayList<LifePostsBean>> lpresp = null;
		ArrayList<LifePostsBean> lifeList = null;
		
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		// PostsBean b = null;
		int recordsize = 0;
		int postId = 0;

		try {
			stmt = conn.prepareCall(sql);

			stmt.setInt(1, bean.getPostId());
			stmt.setInt(2, bean.getPostUserId());
			stmt.setInt(3, bean.getPostPrivacyState());
			stmt.setString(4, bean.getPostDate());
			stmt.setInt(5, pageIndex);
			stmt.setInt(6, pageSize);

			rs = stmt.executeQuery();

			while (rs.next()) {
				// b = new PostsBean();
				//
				// b.setPostId(rs.getInt(1));
				// b.setPostUserId(rs.getInt(2));
				// b.setPostTitle(rs.getString(3));
				// b.setPostSummary(rs.getString(4));
				// b.setPostLoveHit(rs.getInt(5));
				// b.setPostPrivacyState(rs.getInt(6));
				// b.setPostDate(rs.getString(7));
				// b.setPostType(rs.getString(8));
				// b.setPostUserName(rs.getString(9));
				// b.setPostUserNickName(rs.getString(8));
				// b.setPostUserAvator(rs.getString(9));

				postId = rs.getInt(1);

				switch (rs.getString(8)) {
				case "text":
					tpresp = texts.query(postId);
					textList = tpresp.getResponse();
					
					if(tpresp.getCode().equals(Alarm.SUCCESS) && textList.size() > 0){
						list.add(textList.get(0));
					}
					break;
				case "event":
					epresp = events.query(postId);
					eventList = epresp.getResponse();
					
					if(epresp.getCode().equals(Alarm.SUCCESS) && eventList.size() > 0){
						list.add(eventList.get(0));
					}
					break;
				case "photo":
					ppresp = photos.query(postId);
					photoList = ppresp.getResponse();
					
					if(ppresp.getCode().equals(Alarm.SUCCESS) && photoList.size() > 0){
						list.add(photoList.get(0));
					}
					break;
				case "life":
					lpresp = life.query(postId);
					lifeList = lpresp.getResponse();
					
					if(lpresp.getCode().equals(Alarm.SUCCESS) && lifeList.size() > 0){
						list.add(lifeList.get(0));
					}
					break;
				}
			}

			db.close(null, rs);

			if (stmt.getMoreResults()) {
				rs = stmt.getResultSet();

				if (rs.next()) {
					recordsize = rs.getInt(1);
				}

			}

			code = DBUtil.SUCCESS;
			rcode = "" + code;
			rmessage = Message.getMessage(rcode);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			code = DBUtil.SQL_EXCEPTION;
			rcode = Alarm.Level.SYSTEM + Alarm.Type.SQL + Alarm.Module.SQL
					+ code;
			rmessage = Message.getMessage(rcode);
			rmessage = Message.formatMessage(rmessage, e.getMessage(),
					e.getSQLState(), e.getErrorCode());

			L.error(BasicPosts.class, "query()", rcode, rmessage);
		} finally {
			db.close(stmt, rs);

			resp.setPage(pageIndex);
			resp.setPageSize(pageSize);
			resp.setRecordSize(recordsize);
			resp.setCode(rcode);
			resp.setMessage(rmessage);
			resp.setResponse(list);
		}

		return resp;
	}

}

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
import com.seshenghuo.ui.constant.Constant;
import com.seshenghuo.util.Message;

/**
 * @author carlli
 * 
 */
public class EventsPosts extends AbstractBase<EventsPostsBean> {

	/**
	 * 
	 */
	public EventsPosts() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.base.AbstractBase#update(java.lang.Object)
	 */
	@Override
	public Response<Object> update(EventsPostsBean bean) {
		// TODO Auto-generated method stub
		String sql = SQL.getSQL(Constant.SQL.SP_UPT_EVENTS);
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
			stmt.setString(5, bean.getEventStartDate());
			stmt.setString(6, bean.getEventEndDate());
			stmt.setString(7, bean.getEventLocation());
			stmt.setString(8, bean.getEventDetail());
			stmt.setString(9, bean.getEventWebsite());

			stmt.executeUpdate();

			rs = stmt.getResultSet();

			if (rs.next()) {
				result = rs.getInt(1);
			}
			
			if (rs.next()){			
				result = result & rs.getInt(1);
			}

			L.info(EventsPosts.class, "update()", "INFO", "Result is " + result);

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

			L.error(EventsPosts.class, "update()", rcode, rmessage);
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
	public Response<Object> add(EventsPostsBean bean) {
		// TODO Auto-generated method stub
		String sql = SQL.getSQL(Constant.SQL.SP_INS_EVENTS);
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
			stmt.setString(8, bean.getEventStartDate());
			stmt.setString(9, bean.getEventEndDate());
			stmt.setString(10, bean.getEventLocation());
			stmt.setString(11, bean.getEventDetail());
			stmt.setString(12, bean.getEventWebsite());
			stmt.setString(13, bean.getEventCreateDate());

			stmt.executeUpdate();

			rs = stmt.getResultSet();

			if (rs.next()) {
				result = rs.getInt(1);
			}
			
			if (rs.next()){			
				result = result & rs.getInt(1);
			}

			L.info(EventsPosts.class, "add()", "INFO", "Result is " + result);

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

			L.error(EventsPosts.class, "add()", rcode, rmessage);
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
	public Response<ArrayList<EventsPostsBean>> query(EventsPostsBean bean, int pageIndex,
			int pageSize) {
		// TODO Auto-generated method stub
		String sql = SQL.getSQL(Constant.SQL.SP_QRY_EVENTS);
		Connection conn = db.getConnection();

		int code = DBUtil.UNKNOWN_ERROR;
		String rcode = "";
		String rmessage = "";
		Response<ArrayList<EventsPostsBean>> resp = new Response<ArrayList<EventsPostsBean>>();
		CallableStatement stmt = null;
		ResultSet rs = null;
		ArrayList<EventsPostsBean> list = new ArrayList<EventsPostsBean>();
		;
		EventsPostsBean b = null;
		int recordsize = 0;

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
				b = new EventsPostsBean();

				b.setPostId(rs.getInt(1));
				b.setPostUserId(rs.getInt(2));
				b.setPostTitle(rs.getString(3));
				b.setPostSummary(rs.getString(4));
				b.setPostLoveHit(rs.getInt(5));
				b.setPostPrivacyState(rs.getInt(6));
				b.setPostDate(rs.getString(7));
				b.setPostType(rs.getString(8));
				b.setEventId(rs.getInt(9));
				b.setEventStartDate(rs.getString(10));
				b.setEventEndDate(rs.getString(11));
				b.setEventLocation(rs.getString(12));
				b.setEventDetail(rs.getString(13));
				b.setEventWebsite(rs.getString(14));
				b.setEventCreateDate(rs.getString(15));
				b.setPostUserName(rs.getString(16));
				b.setPostUserNickName(rs.getString(17));
				b.setPostUserAvator(rs.getString(18));

				list.add(b);
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

			L.error(EventsPosts.class, "query()", rcode, rmessage);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.base.AbstractBase#query(int)
	 */
	@Override
	public Response<ArrayList<EventsPostsBean>> query(int uniqueId) {
		// TODO Auto-generated method stub
		EventsPostsBean bean = new EventsPostsBean();
		bean.setPostId(uniqueId);
		Response<ArrayList<EventsPostsBean>> resp = query(bean, 0, 0);

		return resp;
	}
}

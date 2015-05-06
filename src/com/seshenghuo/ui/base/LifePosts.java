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
import com.seshenghuo.ui.bean.LifePostsBean;
import com.seshenghuo.ui.constant.Constant;
import com.seshenghuo.util.Message;

/**
 * @author carlli
 * 
 */
public class LifePosts extends AbstractBase<LifePostsBean> {

	/**
	 * 
	 */
	public LifePosts() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.base.AbstractBase#update(java.lang.Object)
	 */
	@Override
	public Response<Object> update(LifePostsBean bean) {
		// TODO Auto-generated method stub
		String sql = SQL.getSQL(Constant.SQL.SP_UPT_LIFE);
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
			stmt.setString(5, bean.getLifeCommodityImage());
			stmt.setFloat(6, bean.getLifeCommodityPrice());
			stmt.setString(7, bean.getLifeBusinessOffline());
			stmt.setString(8, bean.getLifeBusinessOnline());
			stmt.setString(9, bean.getLifeMatrixCode());
			stmt.setString(10, bean.getLifeDetail());

			stmt.executeUpdate();

			rs = stmt.getResultSet();

			if (rs.next()) {
				result = rs.getInt(1);
			}
			
			if (rs.next()){			
				result = result & rs.getInt(1);
			}

			L.info(LifePosts.class, "update()", "INFO", "Result is " + result);

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

			L.error(LifePosts.class, "update()", rcode, rmessage);
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
	public Response<Object> add(LifePostsBean bean) {
		// TODO Auto-generated method stub
		String sql = SQL.getSQL(Constant.SQL.SP_INS_LIFE);
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
			stmt.setString(8, bean.getLifeCommodityImage());
			stmt.setFloat(9, bean.getLifeCommodityPrice());
			stmt.setString(10, bean.getLifeBusinessOffline());
			stmt.setString(11, bean.getLifeBusinessOnline());
			stmt.setString(12, bean.getLifeMatrixCode());
			stmt.setString(13, bean.getLifeDetail());
			stmt.setString(14, bean.getLifePostDate());

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

			L.error(LifePosts.class, "add()", rcode, rmessage);
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
	public Response<ArrayList<LifePostsBean>> query(LifePostsBean bean, int pageIndex,
			int pageSize) {
		// TODO Auto-generated method stub
		String sql = SQL.getSQL(Constant.SQL.SP_QRY_LIFE);
		Connection conn = db.getConnection();

		int code = DBUtil.UNKNOWN_ERROR;
		String rcode = "";
		String rmessage = "";
		Response<ArrayList<LifePostsBean>> resp = new Response<ArrayList<LifePostsBean>>();
		CallableStatement stmt = null;
		ResultSet rs = null;
		ArrayList<LifePostsBean> list = new ArrayList<LifePostsBean>();
		;
		LifePostsBean b = null;
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
				b = new LifePostsBean();

				b.setPostId(rs.getInt(1));
				b.setPostUserId(rs.getInt(2));
				b.setPostTitle(rs.getString(3));
				b.setPostSummary(rs.getString(4));
				b.setPostLoveHit(rs.getInt(5));
				b.setPostPrivacyState(rs.getInt(6));
				b.setPostDate(rs.getString(7));
				b.setPostType(rs.getString(8));
				b.setLifeId(rs.getInt(9));
				b.setLifeCommodityImage(rs.getString(10));
				b.setLifeCommodityPrice(rs.getFloat(11));
				b.setLifeBusinessOffline(rs.getString(12));
				b.setLifeBusinessOnline(rs.getString(13));
				b.setLifeMatrixCode(rs.getString(14));
				b.setLifeDetail(rs.getString(15));
				b.setLifePostDate(rs.getString(16));
				b.setPostUserName(rs.getString(17));
				b.setPostUserNickName(rs.getString(18));
				b.setPostUserAvator(rs.getString(19));

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

			L.error(LifePosts.class, "query()", rcode, rmessage);
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
	public Response<ArrayList<LifePostsBean>> query(int uniqueId) {
		// TODO Auto-generated method stub
		LifePostsBean bean = new LifePostsBean();
		bean.setPostId(uniqueId);
		Response<ArrayList<LifePostsBean>> resp = query(bean, 0, 0);

		return resp;
	}
}

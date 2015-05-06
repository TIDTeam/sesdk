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
import com.seshenghuo.ui.bean.PhotosPostsBean;
import com.seshenghuo.ui.constant.Constant;
import com.seshenghuo.util.Message;

/**
 * @author carlli
 * 
 */
public class PhotosPosts extends AbstractBase<PhotosPostsBean> {

	/**
	 * 
	 */
	public PhotosPosts() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.base.AbstractBase#update(java.lang.Object)
	 */
	@Override
	public Response<Object> update(PhotosPostsBean bean) {
		// TODO Auto-generated method stub
		String sql = SQL.getSQL(Constant.SQL.SP_UPT_PHOTOS);
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
			stmt.setString(5, bean.getPhotoPath());

			stmt.executeUpdate();

			rs = stmt.getResultSet();

			if (rs.next()) {
				result = rs.getInt(1);
			}
			
			if (rs.next()){			
				result = result & rs.getInt(1);
			}

			L.info(PhotosPosts.class, "update()", "INFO", "Result is " + result);

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

			L.error(PhotosPosts.class, "update()", rcode, rmessage);
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
	public Response<Object> add(PhotosPostsBean bean) {
		// TODO Auto-generated method stub
		String sql = SQL.getSQL(Constant.SQL.SP_INS_PHOTOS);
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
			stmt.setString(8, bean.getPhotoPath());
			stmt.setString(9, bean.getPhotoAddDate());

			stmt.executeUpdate();

			rs = stmt.getResultSet();

			if (rs.next()) {
				result = rs.getInt(1);
			}
			
			if (rs.next()){			
				result = result & rs.getInt(1);
			}

			L.info(PhotosPosts.class, "add()", "INFO", "Result is " + result);

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

			L.error(PhotosPosts.class, "add()", rcode, rmessage);
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
	public Response<ArrayList<PhotosPostsBean>> query(PhotosPostsBean bean, int pageIndex,
			int pageSize) {
		// TODO Auto-generated method stub
		String sql = SQL.getSQL(Constant.SQL.SP_QRY_PHOTOS);
		Connection conn = db.getConnection();

		int code = DBUtil.UNKNOWN_ERROR;
		String rcode = "";
		String rmessage = "";
		Response<ArrayList<PhotosPostsBean>> resp = new Response<ArrayList<PhotosPostsBean>>();
		CallableStatement stmt = null;
		ResultSet rs = null;
		ArrayList<PhotosPostsBean> list = new ArrayList<PhotosPostsBean>();
		;
		PhotosPostsBean b = null;
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
				b = new PhotosPostsBean();

				b.setPostId(rs.getInt(1));
				b.setPostUserId(rs.getInt(2));
				b.setPostTitle(rs.getString(3));
				b.setPostSummary(rs.getString(4));
				b.setPostLoveHit(rs.getInt(5));
				b.setPostPrivacyState(rs.getInt(6));
				b.setPostDate(rs.getString(7));
				b.setPostType(rs.getString(8));
				b.setPhotoId(rs.getInt(9));
				b.setPhotoPath(rs.getString(10));
				b.setPhotoAddDate(rs.getString(11));
				b.setPostUserName(rs.getString(12));
				b.setPostUserNickName(rs.getString(13));
				b.setPostUserAvator(rs.getString(14));

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

			L.error(PhotosPosts.class, "query()", rcode, rmessage);
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
	public Response<ArrayList<PhotosPostsBean>> query(int uniqueId) {
		// TODO Auto-generated method stub
		PhotosPostsBean bean = new PhotosPostsBean();
		bean.setPostId(uniqueId);
		Response<ArrayList<PhotosPostsBean>> resp = query(bean, 0, 0);

		return resp;
	}
}

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
import com.seshenghuo.ui.bean.UserBasicBean;
import com.seshenghuo.ui.constant.Constant;
import com.seshenghuo.util.Message;

/**
 * @author carlli
 * 
 */
public class UserBasic extends AbstractBase<UserBasicBean> {

	/**
	 * 
	 */
	public UserBasic() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.base.AbstractBase#update(java.lang.Object)
	 */
	@Override
	public Response<Object> update(final UserBasicBean bean) {
		// TODO Auto-generated method stub
		String sql = SQL.getSQL(Constant.SQL.SP_UPT_USER_BASIC);
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

			stmt.setInt(1, bean.getUserId());
			stmt.setInt(2, bean.getUserStatus());
			stmt.setString(3, bean.getUserAvator());

			stmt.executeUpdate();

			rs = stmt.getResultSet();

			if (rs.next()) {
				result = rs.getInt(1);
			}

			L.info(UserBasic.class, "update()", "INFO", "Result is " + result);

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

			L.error(UserBasic.class, "update()", rcode, rmessage);
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
	public Response<Object> add(final UserBasicBean bean) {
		// TODO Auto-generated method stub
		String sql = SQL.getSQL(Constant.SQL.SP_INS_USER_BASIC);
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

			stmt.setString(1, bean.getUserName());
			stmt.setString(2, bean.getNickName());
			stmt.setString(3, bean.getUserPasswd());
			stmt.setString(4, bean.getUserEmail());
			stmt.setString(5, bean.getRegDate());
			stmt.setString(6, bean.getRegIp());
			stmt.setString(7, bean.getLoginDate());
			stmt.setString(8, bean.getLoginIp());
			stmt.setInt(9, bean.getUserStatus());
			stmt.setString(10, bean.getUserAvator());

			stmt.executeUpdate();

			rs = stmt.getResultSet();

			if (rs.next()) {
				result = rs.getInt(1);
			}

			L.info(UserBasic.class, "add()", "INFO", "Result is " + result);

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

			L.error(UserBasic.class, "add()", rcode, rmessage);
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
	public Response<ArrayList<UserBasicBean>> query(final UserBasicBean bean,
			final int pageIndex, final int pageSize) {
		// TODO Auto-generated method stub
		String sql = SQL.getSQL(Constant.SQL.SP_QRY_USER_BASIC);
		Connection conn = db.getConnection();

		int code = DBUtil.UNKNOWN_ERROR;
		String rcode = "";
		String rmessage = "";
		Response<ArrayList<UserBasicBean>> resp = new Response<ArrayList<UserBasicBean>>();
		CallableStatement stmt = null;
		ResultSet rs = null;
		ArrayList<UserBasicBean> list = new ArrayList<UserBasicBean>();
		;
		UserBasicBean b = null;
		int recordsize = 0;

		try {
			stmt = conn.prepareCall(sql);

			stmt.setInt(1, bean.getUserId());
			stmt.setString(2, bean.getUserName());
			stmt.setString(3, bean.getUserEmail());
			stmt.setInt(4, bean.getUserStatus());
			stmt.setInt(5, pageIndex);
			stmt.setInt(6, pageSize);

			rs = stmt.executeQuery();

			while (rs.next()) {
				b = new UserBasicBean();

				b.setUserId(rs.getInt(1));
				b.setUserName(rs.getString(2));
				b.setNickName(rs.getString(3));
				b.setUserEmail(rs.getString(4));
				b.setRegDate(rs.getString(5));
				b.setRegIp(rs.getString(6));
				b.setLoginDate(rs.getString(7));
				b.setLoginIp(rs.getString(8));
				b.setUserStatus(rs.getInt(9));
				b.setUserAvator(rs.getString(10));

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

			L.error(UserBasic.class, "query()", rcode, rmessage);
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

	public Response<String> getUserPasswd(String userName, int status) {
		String sql = SQL.getSQL(Constant.SQL.SP_QRY_USER_PASSWD);
		Connection conn = db.getConnection();

		int code = DBUtil.UNKNOWN_ERROR;
		String rcode = "";
		String rmessage = "";
		Response<String> resp = new Response<String>();
		CallableStatement stmt = null;
		ResultSet rs = null;
		String result = null;

		try {
			stmt = conn.prepareCall(sql);

			stmt.setInt(1, 0);
			stmt.setString(2, userName);
			stmt.setInt(3, status);

			rs = stmt.executeQuery();

			if (rs.next()) {
				result = rs.getString(1);
			}

			if (null != result && !"".equals(result)) {
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

			L.error(UserBasic.class, "getUserPasswd()", rcode, rmessage);
		} finally {
			db.close(stmt, rs);

			resp.setCode(rcode);
			resp.setMessage(rmessage);
			resp.setResponse(result);
		}

		return resp;
	}

	public Response<String> getUserPasswd(int userId, int status) {
		String sql = SQL.getSQL(Constant.SQL.SP_QRY_USER_PASSWD);
		Connection conn = db.getConnection();

		int code = DBUtil.UNKNOWN_ERROR;
		String rcode = "";
		String rmessage = "";
		Response<String> resp = new Response<String>();
		CallableStatement stmt = null;
		ResultSet rs = null;
		String result = null;

		try {
			stmt = conn.prepareCall(sql);

			stmt.setInt(1, userId);
			stmt.setString(2, "");
			stmt.setInt(3, status);

			rs = stmt.executeQuery();

			if (rs.next()) {
				result = rs.getString(1);
			}

			if (null != result && !"".equals(result)) {
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

			L.error(UserBasic.class, "getUserPasswd()", rcode, rmessage);
		} finally {
			db.close(stmt, rs);

			resp.setCode(rcode);
			resp.setMessage(rmessage);
			resp.setResponse(result);
		}

		return resp;
	}

	public Response<Object> login(final UserBasicBean bean) {
		String sql = SQL.getSQL(Constant.SQL.SP_QRY_USER_LOGIN);
		Connection conn = db.getConnection();

		int code = DBUtil.UNKNOWN_ERROR;
		String rcode = "";
		String rmessage = "";
		Response<Object> resp = new Response<Object>();
		CallableStatement stmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			stmt = conn.prepareCall(sql);

			stmt.setString(1, bean.getUserName());
			stmt.setString(2, bean.getUserPasswd());
			stmt.setString(3, bean.getLoginDate());
			stmt.setString(4, bean.getLoginIp());
			stmt.setInt(5, bean.getUserStatus());

			rs = stmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

			if (1 == result) {
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

			L.error(UserBasic.class, "login()", rcode, rmessage);
		} finally {
			db.close(stmt, rs);

			resp.setCode(rcode);
			resp.setMessage(rmessage);
		}

		return resp;
	}

	public Response<Object> passwd(final int userId, final String passwd,
			final String newPasswd) {
		String sql = SQL.getSQL(Constant.SQL.SP_QRY_USER_MODIFY_PASSWD);
		Connection conn = db.getConnection();

		int code = DBUtil.UNKNOWN_ERROR;
		String rcode = "";
		String rmessage = "";
		Response<Object> resp = new Response<Object>();
		CallableStatement stmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			stmt = conn.prepareCall(sql);

			stmt.setInt(1, userId);
			stmt.setString(2, passwd);
			stmt.setString(3, newPasswd);

			rs = stmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

			if (1 == result) {
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

			L.error(UserBasic.class, "passwd()", rcode, rmessage);
		} finally {
			db.close(stmt, rs);

			resp.setCode(rcode);
			resp.setMessage(rmessage);
		}

		return resp;
	}

	public Response<Object> checkUserName(final String userName) {
		String sql = SQL.getSQL(Constant.SQL.SP_QRY_USERNAME_EXIST);
		Connection conn = db.getConnection();

		int code = DBUtil.UNKNOWN_ERROR;
		String rcode = "";
		String rmessage = "";
		Response<Object> resp = new Response<Object>();
		CallableStatement stmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			stmt = conn.prepareCall(sql);

			stmt.setString(1, userName);

			rs = stmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

			if (1 == result) {
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

			L.error(UserBasic.class, "checkUserName()", rcode, rmessage);
		} finally {
			db.close(stmt, rs);

			resp.setCode(rcode);
			resp.setMessage(rmessage);
		}

		return resp;
	}

	public Response<Object> checkNickName(final String nickName) {
		String sql = SQL.getSQL(Constant.SQL.SP_QRY_NICKNAME_EXIST);
		Connection conn = db.getConnection();

		int code = DBUtil.UNKNOWN_ERROR;
		String rcode = "";
		String rmessage = "";
		Response<Object> resp = new Response<Object>();
		CallableStatement stmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			stmt = conn.prepareCall(sql);

			stmt.setString(1, nickName);

			rs = stmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

			if (1 == result) {
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

			L.error(UserBasic.class, "checkNickName()", rcode, rmessage);
		} finally {
			db.close(stmt, rs);

			resp.setCode(rcode);
			resp.setMessage(rmessage);
		}

		return resp;
	}

	public Response<Object> checkUserEmail(final String email) {
		String sql = SQL.getSQL(Constant.SQL.SP_QRY_USEREMAIL_EXIST);
		Connection conn = db.getConnection();

		int code = DBUtil.UNKNOWN_ERROR;
		String rcode = "";
		String rmessage = "";
		Response<Object> resp = new Response<Object>();
		CallableStatement stmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			stmt = conn.prepareCall(sql);

			stmt.setString(1, email);

			rs = stmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

			if (1 == result) {
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

			L.error(UserBasic.class, "checkUserEmail()", rcode, rmessage);
		} finally {
			db.close(stmt, rs);

			resp.setCode(rcode);
			resp.setMessage(rmessage);
		}

		return resp;
	}
}

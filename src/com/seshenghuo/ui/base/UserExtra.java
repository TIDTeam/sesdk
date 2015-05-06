/**
 * 
 */
package com.seshenghuo.ui.base;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seshenghuo.base.Response;
import com.seshenghuo.database.DBUtil;
import com.seshenghuo.database.SQL;
import com.seshenghuo.logger.L;
import com.seshenghuo.messagecode.Alarm;
import com.seshenghuo.ui.bean.UserExtraBean;
import com.seshenghuo.ui.constant.Constant;
import com.seshenghuo.util.Message;

/**
 * @author carlli
 * 
 */
public class UserExtra extends AbstractBase<UserExtraBean> {

	/**
	 * 
	 */
	public UserExtra() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.base.AbstractBase#update(java.lang.Object)
	 */
	@Override
	public Response<Object> update(UserExtraBean bean) {
		// TODO Auto-generated method stub
		String sql = SQL.getSQL(Constant.SQL.SP_UPT_USER_EDU);
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
			stmt.setInt(2, bean.getUserQQ());
			stmt.setString(3, bean.getUserGmail());
			stmt.setString(4, bean.getUserMSN());
			stmt.setString(5, bean.getUserWeibo());
			stmt.setString(6, bean.getUserTQQ());
			stmt.setString(7, bean.getUserWeixin());
			stmt.setString(8, bean.getUserTaobao());
			stmt.setString(9, bean.getBirthday());
			stmt.setInt(10, bean.getGender());
			stmt.setString(11, bean.getCover());

			stmt.executeUpdate();

			rs = stmt.getResultSet();

			if (rs.next()) {
				result = rs.getInt(1);
			}

			L.info(UserExtra.class, "update()", "INFO", "Result is " + result);

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

			L.error(UserExtra.class, "update()", rcode, rmessage);
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
	public Response<Object> add(UserExtraBean bean) {
		// TODO Auto-generated method stub
		String sql = SQL.getSQL(Constant.SQL.SP_INS_USER_EDU);
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
			stmt.setInt(2, bean.getUserQQ());
			stmt.setString(3, bean.getUserGmail());
			stmt.setString(4, bean.getUserMSN());
			stmt.setString(5, bean.getUserWeibo());
			stmt.setString(6, bean.getUserTQQ());
			stmt.setString(7, bean.getUserWeixin());
			stmt.setString(8, bean.getUserTaobao());
			stmt.setString(9, bean.getBirthday());
			stmt.setInt(10, bean.getGender());
			stmt.setString(11, bean.getCover());

			stmt.executeUpdate();

			rs = stmt.getResultSet();

			if (rs.next()) {
				result = rs.getInt(1);
			}

			L.info(UserExtra.class, "add()", "INFO", "Result is " + result);

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

			L.error(UserExtra.class, "add()", rcode, rmessage);
		} finally {
			db.close(stmt, rs);
			resp.setCode(rcode);
			resp.setMessage(rmessage);
		}

		return resp;
	}
}

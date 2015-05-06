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
import com.seshenghuo.ui.bean.UserWorkBean;
import com.seshenghuo.ui.constant.Constant;
import com.seshenghuo.util.Message;

/**
 * @author carlli
 * 
 */
public class UserWork extends AbstractBase<UserWorkBean> {

	/**
	 * 
	 */
	public UserWork() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.base.AbstractBase#update(java.lang.Object)
	 */
	@Override
	public Response<Object> update(UserWorkBean bean) {
		// TODO Auto-generated method stub
		String sql = SQL.getSQL(Constant.SQL.SP_UPT_USER_WORK);
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
			stmt.setString(2, bean.getOccupation());
			stmt.setString(3, bean.getSkills());
			stmt.setString(4, bean.getSummary());

			stmt.executeUpdate();

			rs = stmt.getResultSet();

			if (rs.next()) {
				result = rs.getInt(1);
			}

			L.info(UserWork.class, "update()", "INFO", "Result is " + result);

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

			L.error(UserWork.class, "update()", rcode, rmessage);
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
	public Response<Object> add(UserWorkBean bean) {
		// TODO Auto-generated method stub
		String sql = SQL.getSQL(Constant.SQL.SP_INS_USER_WORK);
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
			stmt.setString(2, bean.getOccupation());
			stmt.setString(3, bean.getSkills());
			stmt.setString(4, bean.getSummary());

			stmt.executeUpdate();

			rs = stmt.getResultSet();

			if (rs.next()) {
				result = rs.getInt(1);
			}

			L.info(UserWork.class, "add()", "INFO", "Result is " + result);

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

			L.error(UserWork.class, "add()", rcode, rmessage);
		} finally {
			db.close(stmt, rs);
			resp.setCode(rcode);
			resp.setMessage(rmessage);
		}

		return resp;
	}

}

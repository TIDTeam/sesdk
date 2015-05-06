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
import com.seshenghuo.ui.bean.UserPrivacyBean;
import com.seshenghuo.ui.constant.Constant;
import com.seshenghuo.util.Message;

/**
 * @author carlli
 * 
 */
public class UserPrivacy extends AbstractBase<UserPrivacyBean> {

	/**
	 * 
	 */
	public UserPrivacy() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.base.AbstractBase#update(java.lang.Object)
	 */
	@Override
	public Response<Object> update(UserPrivacyBean bean) {
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
			stmt.setInt(2, bean.getQqPrivacyState());
			stmt.setInt(3, bean.getGmailPrivacyState());
			stmt.setInt(4, bean.getMsnPrivacyState());
			stmt.setInt(5, bean.getWeiboPrivacyState());
			stmt.setInt(6, bean.getTqqPrivacyState());
			stmt.setInt(7, bean.getTaobaoPrivacyState());
			stmt.setInt(8, bean.getBirthdayPrivacyState());
			stmt.setInt(9, bean.getGenderPrivacyState());
			stmt.setInt(10, bean.getEduPrivacyState());
			stmt.setInt(11, bean.getOccupationPrivacyState());
			stmt.setInt(12, bean.getSkillsPrivacyState());
			stmt.setInt(13, bean.getEmploymentPrivacyState());

			stmt.executeUpdate();

			rs = stmt.getResultSet();

			if (rs.next()) {
				result = rs.getInt(1);
			}

			L.info(UserPrivacy.class, "update()", "INFO", "Result is " + result);

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

			L.error(UserPrivacy.class, "update()", rcode, rmessage);
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
	public Response<Object> add(UserPrivacyBean bean) {
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
			stmt.setInt(2, bean.getQqPrivacyState());
			stmt.setInt(3, bean.getGmailPrivacyState());
			stmt.setInt(4, bean.getMsnPrivacyState());
			stmt.setInt(5, bean.getWeiboPrivacyState());
			stmt.setInt(6, bean.getTqqPrivacyState());
			stmt.setInt(7, bean.getTaobaoPrivacyState());
			stmt.setInt(8, bean.getBirthdayPrivacyState());
			stmt.setInt(9, bean.getGenderPrivacyState());
			stmt.setInt(10, bean.getEduPrivacyState());
			stmt.setInt(11, bean.getOccupationPrivacyState());
			stmt.setInt(12, bean.getSkillsPrivacyState());
			stmt.setInt(13, bean.getEmploymentPrivacyState());

			stmt.executeUpdate();

			rs = stmt.getResultSet();

			if (rs.next()) {
				result = rs.getInt(1);
			}

			L.info(UserPrivacy.class, "add()", "INFO", "Result is " + result);

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

			L.error(UserPrivacy.class, "add()", rcode, rmessage);
		} finally {
			db.close(stmt, rs);
			resp.setCode(rcode);
			resp.setMessage(rmessage);
		}

		return resp;
	}
}

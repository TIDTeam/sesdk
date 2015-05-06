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
import com.seshenghuo.ui.bean.UserEducationBean;
import com.seshenghuo.ui.bean.UserEmploymentBean;
import com.seshenghuo.ui.bean.UserExtraBean;
import com.seshenghuo.ui.bean.UserPrivacyBean;
import com.seshenghuo.ui.bean.UserProfileBean;
import com.seshenghuo.ui.bean.UserWorkBean;
import com.seshenghuo.ui.constant.Constant;
import com.seshenghuo.util.Message;

/**
 * @author carlli
 * 
 */
public class UserProfile extends AbstractBase<UserProfileBean> {

	/**
	 * 
	 */
	public UserProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.ui.base.AbstractBase#query(int)
	 */
	@Override
	public Response<UserProfileBean> query(final int uniqueId) {
		// TODO Auto-generated method stub
		String sql = SQL.getSQL(Constant.SQL.SP_QRY_USER_PROFILE);
		Connection conn = db.getConnection();

		int code = DBUtil.UNKNOWN_ERROR;
		String rcode = "";
		String rmessage = "";
		Response<UserProfileBean> resp = new Response<UserProfileBean>();
		CallableStatement stmt = null;
		ResultSet rs = null;
		UserProfileBean profile = new UserProfileBean();

		UserBasicBean basic = new UserBasicBean();
		UserExtraBean extra = new UserExtraBean();
		UserWorkBean work = new UserWorkBean();
		UserPrivacyBean privacy = new UserPrivacyBean();
		ArrayList<UserEducationBean> educationList = new ArrayList<UserEducationBean>();
		ArrayList<UserEmploymentBean> employmentList = new ArrayList<UserEmploymentBean>();
		UserEducationBean edu = null;
		UserEmploymentBean emp = null;

		try {
			stmt = conn.prepareCall(sql);

			stmt.setInt(1, uniqueId);

			rs = stmt.executeQuery();

			// basic
			if (rs.next()) {
				basic.setUserId(rs.getInt(1));
				basic.setUserName(rs.getString(2));
				basic.setNickName(rs.getString(3));
				basic.setUserEmail(rs.getString(4));
				basic.setRegDate(rs.getString(5));
				basic.setRegIp(rs.getString(6));
				basic.setLoginDate(rs.getString(7));
				basic.setLoginIp(rs.getString(8));
				basic.setUserStatus(rs.getInt(9));
				basic.setUserAvator(rs.getString(10));
			}
			profile.setBasic(basic);

			db.close(null, rs);

			// education
			if (stmt.getMoreResults()) {
				rs = stmt.getResultSet();

				while (rs.next()) {
					edu = new UserEducationBean();

					edu.setEduId(rs.getInt(1));
					edu.setUserId(rs.getInt(2));
					edu.setSchool(rs.getString(3));
					edu.setMajorStudy(rs.getString(4));
					edu.setEntryDate(rs.getString(5));
					edu.setEndDate(rs.getString(6));
					edu.setDesc(rs.getString(7));
					edu.setCurrent(rs.getBoolean(8));

					educationList.add(edu);
				}

				profile.setEducationList(educationList);

				db.close(null, rs);
			}

			// employment
			if (stmt.getMoreResults()) {
				rs = stmt.getResultSet();

				while (rs.next()) {
					emp = new UserEmploymentBean();

					emp.setEmploymentId(rs.getInt(1));
					emp.setUserId(rs.getInt(2));
					emp.setEmployer(rs.getString(3));
					emp.setJobTitle(rs.getString(4));
					emp.setStartDate(rs.getString(5));
					emp.setEndDate(rs.getString(6));
					emp.setCurrent(rs.getBoolean(7));
					emp.setDesc(rs.getString(8));

					employmentList.add(emp);
				}

				profile.setEmploymentList(employmentList);

				db.close(null, rs);
			}

			// extra
			if (stmt.getMoreResults()) {
				rs = stmt.getResultSet();

				if (rs.next()) {
					extra.setUserId(rs.getInt(1));
					extra.setUserQQ(rs.getInt(2));
					extra.setUserGmail(rs.getString(3));
					extra.setUserMSN(rs.getString(4));
					extra.setUserWeibo(rs.getString(5));
					extra.setUserTQQ(rs.getString(6));
					extra.setUserWeixin(rs.getString(7));
					extra.setUserTaobao(rs.getString(8));
					extra.setBirthday(rs.getString(9));
					extra.setGender(rs.getInt(10));
					extra.setCover(rs.getString(11));
				}

				profile.setExtra(extra);

				db.close(null, rs);
			}

			// privacy
			if (stmt.getMoreResults()) {
				rs = stmt.getResultSet();

				if (rs.next()) {
					privacy.setUserId(rs.getInt(1));
					privacy.setQqPrivacyState(rs.getInt(2));
					privacy.setGmailPrivacyState(rs.getInt(3));
					privacy.setMsnPrivacyState(rs.getInt(4));
					privacy.setWeiboPrivacyState(rs.getInt(5));
					privacy.setTqqPrivacyState(rs.getInt(6));
					privacy.setTaobaoPrivacyState(rs.getInt(7));
					privacy.setBirthdayPrivacyState(rs.getInt(8));
					privacy.setGenderPrivacyState(rs.getInt(9));
					privacy.setEduPrivacyState(rs.getInt(10));
					privacy.setOccupationPrivacyState(rs.getInt(11));
					privacy.setSkillsPrivacyState(rs.getInt(12));
					privacy.setEmploymentPrivacyState(rs.getInt(13));
				}

				profile.setPrivacy(privacy);

				db.close(null, rs);
			}

			// work
			if (stmt.getMoreResults()) {
				rs = stmt.getResultSet();

				if (rs.next()) {
					work.setUserId(rs.getInt(1));
					work.setOccupation(rs.getString(2));
					work.setSkills(rs.getString(3));
					work.setSummary(rs.getString(4));
				}

				profile.setWork(work);

				db.close(null, rs);
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

			L.error(UserProfile.class, "query()", rcode, rmessage);
		} finally {
			db.close(stmt, rs);

			resp.setCode(rcode);
			resp.setMessage(rmessage);
			resp.setResponse(profile);
		}

		return resp;
	}

}

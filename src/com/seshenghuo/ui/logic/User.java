package com.seshenghuo.ui.logic;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.seshenghuo.base.Response;
import com.seshenghuo.logger.L;
import com.seshenghuo.messagecode.Alarm;
import com.seshenghuo.ui.base.UserBasic;
import com.seshenghuo.ui.base.UserEducation;
import com.seshenghuo.ui.base.UserEmployment;
import com.seshenghuo.ui.base.UserExtra;
import com.seshenghuo.ui.base.UserPrivacy;
import com.seshenghuo.ui.base.UserProfile;
import com.seshenghuo.ui.base.UserWork;
import com.seshenghuo.ui.bean.UserBasicBean;
import com.seshenghuo.ui.bean.UserEducationBean;
import com.seshenghuo.ui.bean.UserEmploymentBean;
import com.seshenghuo.ui.bean.UserExtraBean;
import com.seshenghuo.ui.bean.UserPrivacyBean;
import com.seshenghuo.ui.bean.UserProfileBean;
import com.seshenghuo.ui.bean.UserWorkBean;
import com.seshenghuo.util.CookieUtil;
import com.seshenghuo.util.Message;
import com.seshenghuo.util.RSA;
import com.seshenghuo.util.Util;

/**
 * @author carlli
 * 
 */
public class User extends AbstractLogic {
	public static final int USER_STATUS_NORMAL = 0;
	public static final int USER_STATUS_NOT_ALLOW = 1;
	

	/**
	 * 
	 */
	public User(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated constructor stub		
		super(req, resp);		
	}
	
	public Response<Object> getUserProfile(int uniqueId) {
		long s = System.currentTimeMillis();
		Response<Object> ret = new Response<Object>();
		
		if(checkToken() && uniqueId > 0){
			UserProfile profile = new UserProfile();
			Response<UserProfileBean> resp = profile.query(uniqueId);
			ret = resp.toObject();
		}else{
			ret = timeout();
		}
		
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", User.class, "getUserProfile()", ret.getCode(),
				ret.getMessage(), clientIp, serverIp, "UID: " + uniqueId,
				source, cost);

		return ret;
	}

	public Response<Object> getUserProfile() {
		int userId = this.getIntCookie("uid", 0);
		
		return getUserProfile(userId);
	}

	public Response<Object> checkUserName(String userName) {
		long s = System.currentTimeMillis();
		UserBasic basic = new UserBasic();
		Response<Object> resp = basic.checkUserName(userName);
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", User.class, "checkUserName()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp, "Chceck User: "
						+ userName, source, cost);

		return resp;
	}

	public Response<Object> checkUserName() {
		String userName = this.getStringParameter("user_name");

		return checkUserName(userName);
	}

	public Response<Object> checkNickName(String nickName) {
		long s = System.currentTimeMillis();
		UserBasic basic = new UserBasic();
		Response<Object> resp = basic.checkNickName(nickName);
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", User.class, "checkNickName()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp, "Chceck User: "
						+ nickName, source, cost);

		return resp;
	}

	public Response<Object> checkNickName() {
		String nickName = this.getStringParameter("nick_name");

		return checkNickName(nickName);
	}

	public Response<Object> checkUserEmail(String email) {
		long s = System.currentTimeMillis();
		UserBasic basic = new UserBasic();
		Response<Object> resp = basic.checkUserEmail(email);
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", User.class, "checkUserEmail()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp, "Chceck User: " + email,
				source, cost);

		return resp;
	}

	public Response<Object> checkUserEmail() {
		String email = this.getStringParameter("user_email");

		return checkUserEmail(email);
	}

	public Response<Object> regiest(UserBasicBean bean) {
		long s = System.currentTimeMillis();
		UserBasic basic = new UserBasic();
		Response<Object> resp = basic.add(bean);
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", User.class, "regiest()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp,
				"Regiest User: " + bean.getUserName(), source, cost);

		return resp;
	}

	public Response<Object> regiest() {
		UserBasicBean bean = new UserBasicBean();

		bean.setUserName(this.getStringParameter("user_name"));
		bean.setNickName(this.getStringParameter("nick_name"));
		bean.setUserPasswd(this.getStringParameter("user_passwd"));
		bean.setUserEmail(this.getStringParameter("user_email"));
		bean.setUserAvator(this.getStringParameter("user_avator"));
		bean.setUserStatus(User.USER_STATUS_NORMAL);
		bean.setRegIp(clientIp);
		bean.setRegDate(Util.getDateString());
		bean.setLoginIp(clientIp);
		bean.setLoginDate(Util.getDateString());

		return regiest(bean);
	}

	public Response<Object> update(UserBasicBean bean) {
		long s = System.currentTimeMillis();
		Response<Object> resp = null;
		
		if(checkToken() && bean.getUserId() > 0){
			UserBasic basic = new UserBasic();
			resp = basic.update(bean);
		}else{
			resp = timeout();
		}
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", User.class, "update()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp,
				"Update user basic info: " + bean.getUserId(), source, cost);

		return resp;
	}

	public Response<Object> update() {
		UserBasicBean bean = new UserBasicBean();

		bean.setUserId(this.getIntCookie("uid", 0));
		bean.setUserAvator(this.getStringParameter("user_avator"));
		bean.setUserStatus(this.getIntParameter("user_status"));

		return update(bean);
	}

	private Response<ArrayList<UserBasicBean>> write(String userName) {
		if(null == seed || "".equals(seed)){
			seed = null;
		}
		
		UserBasicBean bean = new UserBasicBean();

		bean.setUserId(0);
		bean.setUserName(userName);
		bean.setUserEmail("");
		bean.setUserStatus(USER_STATUS_NORMAL);

		Response<ArrayList<UserBasicBean>> resp = this.query(bean, 0, 0);

		if ("0".equals(resp.getCode())) {
			CookieUtil cookieUtil = new CookieUtil(request, response);

			ArrayList<UserBasicBean> list = (ArrayList<UserBasicBean>) resp
					.getResponse();
			L.info(User.class, "write()", "INFO", "User List: " + list.size());

			bean = list.get(0);

			int userId = bean.getUserId();
			L.info(User.class, "write()", "INFO", "User ID: " + userId);
			
			String src = userId + "|" + userName;
			String sign = null == seed ? RSA.sign(src) : RSA.sign(seed, src);

			L.info(User.class, "write()", "INFO", "Set Cookie Begin");

			cookieUtil.setCookie("uid", "" + userId, "seshenghuo.com");
			cookieUtil.setCookie("un", userName, "seshenghuo.com");
			cookieUtil.setCookie("token", sign, "seshenghuo.com");
			
			session.setAttribute("token", sign);
			
			L.info(User.class, "write()", "INFO", "Set Session: " + session);
			
			L.info(User.class, "write()", "INFO", "Set Cookie End");

			cookieUtil = null;
		}

		return resp;
	}

	private String verifyUserPasswd(String userName, String passwd) {
		long s = System.currentTimeMillis();
		
		if(null == seed || "".equals(seed)){
			seed = null;
		}
		
		UserBasic basic = new UserBasic();
		Response<String> resp = basic.getUserPasswd(userName,
				USER_STATUS_NORMAL);
		String result = null;
		String verify = null;

		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", User.class, "verifyUserPasswd()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp, "User: " + userName,
				source, cost);

		if ("0".equals(resp.getCode())) {
			result = (String) resp.getResponse();
		}
		
//		L.info(User.class, "verifyUserPasswd()", "INFO", "DR: " + result);
//		L.info(User.class, "verifyUserPasswd()", "INFO", "IR: " + passwd);
//		
//		L.info(User.class, "verifyUserPasswd()", "INFO", "Seed: " + seed);
		
		if (null != result && !"".equals(result)) {
			String dr = null == seed ? RSA.decrypt(result) : RSA.decrypt(seed, result);
			String pp = null == seed ? RSA.decrypt(passwd) : RSA.decrypt(seed, passwd);

//			 L.info(User.class, "verifyUserPasswd()", "INFO", "D: " + dr);
//			 L.info(User.class, "verifyUserPasswd()", "INFO", "I: " + pp);

			if (null != dr && null != pp && !"".equals(dr) && !"".equals(pp) && dr.equals(pp)) {
				verify = dr;
			}
		}

		return verify;
	}

	private String verifyUserPasswd(int userId, String passwd) {
		long s = System.currentTimeMillis();
		
		if(null == seed || "".equals(seed)){
			seed = null;
		}
		
		UserBasic basic = new UserBasic();
		Response<String> resp = basic.getUserPasswd(userId, USER_STATUS_NORMAL);
		String result = null;
		String verify = null;

		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", User.class, "verifyUserPasswd()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp, "User: " + userId,
				source, cost);

		if ("0".equals(resp.getCode())) {
			result = (String) resp.getResponse();
		}

		if (null != result && !"".equals(result)) {
			String dr = null == seed ? RSA.decrypt(result) : RSA.decrypt(seed, result);
			String pp = null == seed ? RSA.decrypt(passwd) : RSA.decrypt(seed, passwd);
			
			// L.info(User.class, "verifyUserPasswd()", "INFO", "D: " + dr);
			// L.info(User.class, "verifyUserPasswd()", "INFO", "I: " + pp);
			if (null != dr && null != pp && !"".equals(dr) && !"".equals(pp) && dr.equals(pp)) {
				verify = dr;
			}
		}

		return verify;
	}
	
	public Response<Object> login(UserBasicBean bean) {
		long s = System.currentTimeMillis();
		Response<Object> resp = null;
		Response<ArrayList<UserBasicBean>> info = null;
		String verify = verifyUserPasswd(bean.getUserName(), bean.getUserPasswd());
		
		L.info(User.class, "login()", "INFO", "Verify: " + (null != verify));
		
		if (null != verify) {
			bean.setUserPasswd(verify);
			UserBasic basic = new UserBasic();
			resp = basic.login(bean);

			if ("0".equals(resp.getCode())) {
				info = this.write(bean.getUserName());
				resp = info.toObject();
			}
		} else {
			resp = verify();
		}

		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", User.class, "login()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp,
				"Login: " + bean.getUserName(), source, cost);

		return resp;
	}
	
	public Response<Object> login() {
		UserBasicBean bean = new UserBasicBean();

		bean.setUserName(this.getStringParameter("user_name"));
		bean.setUserPasswd(this.getStringParameter("user_passwd"));
		bean.setUserStatus(User.USER_STATUS_NORMAL);
		bean.setLoginIp(clientIp);
		bean.setLoginDate(Util.getDateString());
		
		String seed = this.getStringParameter("seed");
		setSeed(seed);
		
		return login(bean);
	}

	public Response<Object> passwd(int userId, String passwd, String newPasswd) {
		long s = System.currentTimeMillis();
		
		Response<Object> resp = null;
		
		if(checkToken() && userId > 0){
			String verify = verifyUserPasswd(userId, passwd);
			
			if(null != verify){
				UserBasic basic = new UserBasic();
				resp = basic.passwd(userId, passwd, newPasswd);
			}else{
				resp = verify();
			}
		}else{
			resp = timeout();
		}
		
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", User.class, "passwd()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp, "Modify Passwd: "
						+ userId, source, cost);

		return resp;
	}


	public Response<Object> passwd() {
		int userId = this.getIntCookie("uid", 0);
		String passwd = this.getStringParameter("passwd");
		String newPasswd = this.getStringParameter("new_passwd");
		
		String seed = this.getStringParameter("seed");
		setSeed(seed);
		
		return passwd(userId, passwd, newPasswd);
	}

	public Response<ArrayList<UserBasicBean>> query(UserBasicBean bean, int pageIndex,
			int pageSize) {
		long s = System.currentTimeMillis();
		UserBasic basic = new UserBasic();
		Response<ArrayList<UserBasicBean>> resp = basic.query(bean, pageIndex, pageSize);
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", User.class, "query()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp, "Query List: "
						+ pageIndex + ", " + pageSize, source, cost);

		return resp;
	}

	public Response<ArrayList<UserBasicBean>> query() {
		UserBasicBean bean = new UserBasicBean();

		bean.setUserId(this.getIntParameter("user_id"));
		bean.setUserName(this.getStringParameter("user_name"));
		bean.setUserEmail(this.getStringParameter("user_email"));
		bean.setUserStatus(this.getIntParameter("user_status",
				USER_STATUS_NORMAL));

		int pageIndex = this.getIntParameter("page_index");
		int pageSize = this.getIntParameter("page_size");

		return query(bean, pageIndex, pageSize);
	}

	private UserEducationBean parseEduInfo(String info) {
		UserEducationBean bean;
		Gson g = new Gson();

		bean = g.fromJson(info, UserEducationBean.class);

		return bean;
	}

	public Response<Object> updateEducation(UserEducationBean bean) {
		long s = System.currentTimeMillis();
		
		Response<Object> resp = null;
		
		if(checkToken() && bean.getUserId() > 0){		
			UserEducation edu = new UserEducation();
			resp = edu.update(bean);
		}else{
			resp = timeout();
		}
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", User.class, "updateEducation()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp, "Update Education: "
						+ bean.getUserId() + ", " + bean.getEduId(), source,
				cost);

		return resp;
	}

	public Response<Object> updateEducation() {
		UserEducationBean bean = new UserEducationBean();

		bean.setUserId(this.getIntCookie("uid", 0));
		bean.setEduId(this.getIntParameter("edu_id"));
		bean.setSchool(this.getStringParameter("school"));
		bean.setMajorStudy(this.getStringParameter("major_study"));
		bean.setEntryDate(this.getStringParameter("entry_date"));
		bean.setEndDate(this.getStringParameter("end_date"));
		bean.setDesc(this.getStringParameter("desc"));
		bean.setCurrent(this.getBooleanParameter("is_current"));

		return updateEducation(bean);
	}

	public Response<Object> batchUpdateEducation() {
		long s = System.currentTimeMillis();
		String pck = this.getStringParameter("pck");
		String[] items = pck.split("&");
		String item = null;
		Response<Object> resp = new Response<Object>();
		int size = items.length;
		int err = 0;

		for (int i = 0; i < size; i++) {
			item = items[i];

			resp = updateEducation(parseEduInfo(item));

			if ("0" != resp.getCode()) {
				err++;
			}
		}

		if (err == size) {
			resp.setCode(Alarm.Level.UI, Alarm.Type.UI, Alarm.Module.USER,
					Alarm.Logic.USER_EDU_BATCH_UPDATE_FAILED);
		} else {
			if (err > 0) {
				resp.setCode(Alarm.Level.UI, Alarm.Type.UI, Alarm.Module.USER,
						Alarm.Logic.USER_EDU_BATCH_UPDATE_PART_FAILED);
			}
		}

		resp.setMessage(Message.getMessage(resp.getCode()));

		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", User.class, "batchUpdateEducation()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp,
				"Batch Update Education", source, cost);

		return resp;
	}

	public Response<Object> addEducation(UserEducationBean bean) {
		long s = System.currentTimeMillis();

		Response<Object> resp = null;
		
		if(checkToken() && bean.getUserId() > 0){		
			UserEducation edu = new UserEducation();
			resp = edu.add(bean);
		}else{
			resp = timeout();
		}
		
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", User.class, "addEducation()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp,
				"Add Education: " + bean.getUserId(), source, cost);

		return resp;
	}

	public Response<Object> addEducation() {
		UserEducationBean bean = new UserEducationBean();

		bean.setUserId(this.getIntCookie("uid", 0));
		bean.setSchool(this.getStringParameter("school"));
		bean.setMajorStudy(this.getStringParameter("major_study"));
		bean.setEntryDate(this.getStringParameter("entry_date"));
		bean.setEndDate(this.getStringParameter("end_date"));
		bean.setDesc(this.getStringParameter("desc"));
		bean.setCurrent(this.getBooleanParameter("is_current"));

		return addEducation(bean);
	}

	public Response<Object> batchAddEducation() {
		long s = System.currentTimeMillis();
		String pck = this.getStringParameter("pck");
		String[] items = pck.split("&");
		String item = null;
		Response<Object> resp = new Response<Object>();
		int size = items.length;
		int err = 0;

		for (int i = 0; i < size; i++) {
			item = items[i];

			resp = addEducation(parseEduInfo(item));

			if ("0" != resp.getCode()) {
				err++;
			}
		}

		if (err == size) {
			resp.setCode(Alarm.Level.UI, Alarm.Type.UI, Alarm.Module.USER,
					Alarm.Logic.USER_EDU_BATCH_ADD_FAILED);
		} else {
			if (err > 0) {
				resp.setCode(Alarm.Level.UI, Alarm.Type.UI, Alarm.Module.USER,
						Alarm.Logic.USER_EDU_BATCH_ADD_PART_FAILED);
			}
		}

		resp.setMessage(Message.getMessage(resp.getCode()));

		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", User.class, "batchAddEducation()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp, "Batch Add Education",
				source, cost);

		return resp;
	}

	private UserEmploymentBean parseEmpInfo(String info) {
		UserEmploymentBean bean;
		Gson g = new Gson();

		bean = g.fromJson(info, UserEmploymentBean.class);

		return bean;
	}

	public Response<Object> updateEmployment(UserEmploymentBean bean) {
		long s = System.currentTimeMillis();
		
		Response<Object> resp = null;
		
		if(checkToken() && bean.getUserId() > 0){		
			UserEmployment emp = new UserEmployment();
			resp = emp.update(bean);
		}else{
			resp = timeout();
		}
		
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", User.class, "updateEmployment()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp, "Update Employment: "
						+ bean.getUserId() + ", " + bean.getEmploymentId(),
				source, cost);

		return resp;
	}

	public Response<Object> updateEmployment() {
		UserEmploymentBean bean = new UserEmploymentBean();

		bean.setUserId(this.getIntCookie("uid", 0));
		bean.setEmploymentId(this.getIntParameter("employment_id"));
		bean.setEmployer(this.getStringParameter("employer"));
		bean.setJobTitle(this.getStringParameter("job_title"));
		bean.setStartDate(this.getStringParameter("start_date"));
		bean.setEndDate(this.getStringParameter("end_date"));
		bean.setDesc(this.getStringParameter("desc"));
		bean.setCurrent(this.getBooleanParameter("is_current"));

		return updateEmployment(bean);
	}

	public Response<Object> batchUpdateEmployment() {
		long s = System.currentTimeMillis();
		String pck = this.getStringParameter("pck");
		String[] items = pck.split("&");
		String item = null;
		Response<Object> resp = new Response<Object>();
		int size = items.length;
		int err = 0;

		for (int i = 0; i < size; i++) {
			item = items[i];

			resp = updateEmployment(parseEmpInfo(item));

			if ("0" != resp.getCode()) {
				err++;
			}
		}

		if (err == size) {
			resp.setCode(Alarm.Level.UI, Alarm.Type.UI, Alarm.Module.USER,
					Alarm.Logic.USER_EMP_BATCH_UPDATE_FAILED);
		} else {
			if (err > 0) {
				resp.setCode(Alarm.Level.UI, Alarm.Type.UI, Alarm.Module.USER,
						Alarm.Logic.USER_EMP_BATCH_UPDATE_PART_FAILED);
			}
		}

		resp.setMessage(Message.getMessage(resp.getCode()));

		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", User.class, "batchUpdateEmployment()",
				resp.getCode(), resp.getMessage(), clientIp, serverIp,
				"Batch Update Employment", source, cost);

		return resp;
	}

	public Response<Object> addEmployment(UserEmploymentBean bean) {
		long s = System.currentTimeMillis();
		
		Response<Object> resp = null;
		
		if(checkToken() && bean.getUserId() > 0){		
			UserEmployment emp = new UserEmployment();
			resp = emp.add(bean);
		}else{
			resp = timeout();
		}
		
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", User.class, "addEmployment()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp, "Add Employment: "
						+ bean.getUserId(), source, cost);

		return resp;
	}

	public Response<Object> addEmployment() {
		UserEmploymentBean bean = new UserEmploymentBean();

		bean.setUserId(this.getIntCookie("uid", 0));
		bean.setEmployer(this.getStringParameter("employer"));
		bean.setJobTitle(this.getStringParameter("job_title"));
		bean.setStartDate(this.getStringParameter("start_date"));
		bean.setEndDate(this.getStringParameter("end_date"));
		bean.setDesc(this.getStringParameter("desc"));
		bean.setCurrent(this.getBooleanParameter("is_current"));

		return addEmployment(bean);
	}

	public Response<Object> batchAddEmployment() {
		long s = System.currentTimeMillis();
		String pck = this.getStringParameter("pck");
		String[] items = pck.split("&");
		String item = null;
		Response<Object> resp = new Response<Object>();
		int size = items.length;
		int err = 0;

		for (int i = 0; i < size; i++) {
			item = items[i];

			resp = addEmployment(parseEmpInfo(item));

			if ("0" != resp.getCode()) {
				err++;
			}
		}

		if (err == size) {
			resp.setCode(Alarm.Level.UI, Alarm.Type.UI, Alarm.Module.USER,
					Alarm.Logic.USER_EMP_BATCH_ADD_FAILED);
		} else {
			if (err > 0) {
				resp.setCode(Alarm.Level.UI, Alarm.Type.UI, Alarm.Module.USER,
						Alarm.Logic.USER_EMP_BATCH_ADD_PART_FAILED);
			}
		}

		resp.setMessage(Message.getMessage(resp.getCode()));

		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", User.class, "batchAddEmployment()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp, "Batch Add Employment",
				source, cost);

		return resp;
	}

	public Response<Object> updateExtra(UserExtraBean bean) {
		long s = System.currentTimeMillis();		
		
		Response<Object> resp = null;
		
		if(checkToken() && bean.getUserId() > 0){		
			UserExtra extra = new UserExtra();
			resp = extra.update(bean);
		}else{
			resp = timeout();
		}
		
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", User.class, "updateExtra()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp,
				"Update Extra: " + bean.getUserId(), source, cost);

		return resp;
	}

	public Response<Object> updateExtra() {
		UserExtraBean bean = new UserExtraBean();

		bean.setUserId(this.getIntCookie("uid", 0));
		bean.setUserQQ(this.getIntParameter("qq"));
		bean.setUserGmail(this.getStringParameter("gmail"));
		bean.setUserMSN(this.getStringParameter("msn"));
		bean.setUserWeibo(this.getStringParameter("weibo"));
		bean.setUserTQQ(this.getStringParameter("tqq"));
		bean.setUserWeixin(this.getStringParameter("weixin"));
		bean.setUserTaobao(this.getStringParameter("taobao"));
		bean.setBirthday(this.getStringParameter("birthday"));
		bean.setGender(this.getIntParameter("gender"));
		bean.setCover(this.getStringParameter("cover"));

		return updateExtra(bean);
	}

	public Response<Object> addExtra(UserExtraBean bean) {
		long s = System.currentTimeMillis();
		
		Response<Object> resp = null;
		
		if(checkToken() && bean.getUserId() > 0){		
			UserExtra extra = new UserExtra();
			resp = extra.add(bean);
		}else{
			resp = timeout();
		}
		
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", User.class, "addExtra()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp,
				"Add Extra: " + bean.getUserId(), source, cost);

		return resp;
	}

	public Response<Object> addExtra() {
		UserExtraBean bean = new UserExtraBean();

		bean.setUserId(this.getIntCookie("uid", 0));
		bean.setUserQQ(this.getIntParameter("qq"));
		bean.setUserGmail(this.getStringParameter("gmail"));
		bean.setUserMSN(this.getStringParameter("msn"));
		bean.setUserWeibo(this.getStringParameter("weibo"));
		bean.setUserTQQ(this.getStringParameter("tqq"));
		bean.setUserWeixin(this.getStringParameter("weixin"));
		bean.setUserTaobao(this.getStringParameter("taobao"));
		bean.setBirthday(this.getStringParameter("birthday"));
		bean.setGender(this.getIntParameter("gender"));
		bean.setCover(this.getStringParameter("cover"));

		return addExtra(bean);
	}

	public Response<Object> updatePrivacy(UserPrivacyBean bean) {
		long s = System.currentTimeMillis();
		
		Response<Object> resp = null;
		
		if(checkToken() && bean.getUserId() > 0){		
			UserPrivacy privacy = new UserPrivacy();
			resp = privacy.update(bean);
		}else{
			resp = timeout();
		}
		
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", User.class, "updatePrivacy()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp, "Update Privacy: "
						+ bean.getUserId(), source, cost);

		return resp;
	}

	public Response<Object> updatePrivacy() {
		UserPrivacyBean bean = new UserPrivacyBean();

		bean.setUserId(this.getIntCookie("uid", 0));
		bean.setQqPrivacyState(this.getIntParameter("qq_privacy"));
		bean.setGmailPrivacyState(this.getIntParameter("gmail_privacy"));
		bean.setMsnPrivacyState(this.getIntParameter("msn_privacy"));
		bean.setWeiboPrivacyState(this.getIntParameter("weibo_privacy"));
		bean.setTqqPrivacyState(this.getIntParameter("tqq_privacy"));
		bean.setTaobaoPrivacyState(this.getIntParameter("taobao_privacy"));
		bean.setBirthdayPrivacyState(this.getIntParameter("birthday_privacy"));
		bean.setGenderPrivacyState(this.getIntParameter("gender_privacy"));
		bean.setEduPrivacyState(this.getIntParameter("edu_privacy"));
		bean.setOccupationPrivacyState(this
				.getIntParameter("occupation_privacy"));
		bean.setSkillsPrivacyState(this.getIntParameter("skill_privacy"));
		bean.setEmploymentPrivacyState(this
				.getIntParameter("employment_privacy"));

		return updatePrivacy(bean);
	}

	public Response<Object> addPrivacy(UserPrivacyBean bean) {
		long s = System.currentTimeMillis();

		Response<Object> resp = null;
		
		if(checkToken() && bean.getUserId() > 0){		
			UserPrivacy privacy = new UserPrivacy();
			resp = privacy.add(bean);
		}else{
			resp = timeout();
		}
		
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", User.class, "addPrivacy()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp,
				"Add Privacy: " + bean.getUserId(), source, cost);

		return resp;
	}

	public Response<Object> addPrivacy() {
		UserPrivacyBean bean = new UserPrivacyBean();

		bean.setUserId(this.getIntCookie("uid", 0));
		bean.setQqPrivacyState(this.getIntParameter("qq_privacy"));
		bean.setGmailPrivacyState(this.getIntParameter("gmail_privacy"));
		bean.setMsnPrivacyState(this.getIntParameter("msn_privacy"));
		bean.setWeiboPrivacyState(this.getIntParameter("weibo_privacy"));
		bean.setTqqPrivacyState(this.getIntParameter("tqq_privacy"));
		bean.setTaobaoPrivacyState(this.getIntParameter("taobao_privacy"));
		bean.setBirthdayPrivacyState(this.getIntParameter("birthday_privacy"));
		bean.setGenderPrivacyState(this.getIntParameter("gender_privacy"));
		bean.setEduPrivacyState(this.getIntParameter("edu_privacy"));
		bean.setOccupationPrivacyState(this
				.getIntParameter("occupation_privacy"));
		bean.setSkillsPrivacyState(this.getIntParameter("skill_privacy"));
		bean.setEmploymentPrivacyState(this
				.getIntParameter("employment_privacy"));

		return addPrivacy(bean);
	}

	public Response<Object> updateWork(UserWorkBean bean) {
		long s = System.currentTimeMillis();
		
		Response<Object> resp = null;
		
		if(checkToken() && bean.getUserId() > 0){		
			UserWork work = new UserWork();
			resp = work.update(bean);
		}else{
			resp = timeout();
		}
		
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", User.class, "updateWork()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp,
				"Update Work: " + bean.getUserId(), source, cost);

		return resp;
	}

	public Response<Object> updateWork() {
		UserWorkBean bean = new UserWorkBean();

		bean.setUserId(this.getIntCookie("uid", 0));
		bean.setSkills(this.getStringParameter("skills"));
		bean.setOccupation(this.getStringParameter("occupation"));
		bean.setSummary(this.getStringParameter("summary"));

		return updateWork(bean);
	}

	public Response<Object> addWork(UserWorkBean bean) {
		long s = System.currentTimeMillis();
		
		Response<Object> resp = null;
		
		if(checkToken() && bean.getUserId() > 0){		
			UserWork work = new UserWork();
			resp = work.add(bean);
		}else{
			resp = timeout();
		}
		
		long e = System.currentTimeMillis();
		long cost = (e - s);

		L.info("DEFAULT", User.class, "addWork()", resp.getCode(),
				resp.getMessage(), clientIp, serverIp,
				"Add Work: " + bean.getUserId(), source, cost);

		return resp;
	}

	public Response<Object> addWork() {
		UserWorkBean bean = new UserWorkBean();

		bean.setUserId(this.getIntCookie("uid", 0));
		bean.setSkills(this.getStringParameter("skills"));
		bean.setOccupation(this.getStringParameter("occupation"));
		bean.setSummary(this.getStringParameter("summary"));

		return addWork(bean);
	}
}

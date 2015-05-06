/**
 * 
 */
package com.seshenghuo.ui.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seshenghuo.base.Response;
import com.seshenghuo.logger.L;
import com.seshenghuo.messagecode.Alarm;
import com.seshenghuo.util.Config;
import com.seshenghuo.util.CookieUtil;
import com.seshenghuo.util.INet;
import com.seshenghuo.util.Message;
import com.seshenghuo.util.RSA;

/**
 * @author carlli
 * 
 */
public abstract class AbstractLogic {
	protected HttpServletRequest request = null;
	protected HttpServletResponse response = null;
	protected HttpSession session = null;
	protected String clientIp = "0.0.0.0";
	protected String serverIp = "0.0.0.0";
	protected String source = null;
	protected String seed = null;
	
	/**
	 * 
	 */
	protected AbstractLogic(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated constructor stub

		request = req;
		response = resp;
		session = request.getSession();
		
		clientIp = INet.getClientIP(request);
		serverIp = INet.getServerIP(request);
		source = INet.getAppURI(request);
		
		session.setMaxInactiveInterval(Config.getIntValue(
				"session.timeout", 900));
		
		L.info(AbstractLogic.class, "AbstractLogic()", "INFO", "Session: " + session + ", " + session.getMaxInactiveInterval());
	}
	

	protected Response<Object> timeout(){
		Response<Object> resp = new Response<Object>();
		
		resp.setCode(Alarm.Level.LOGIC, Alarm.Type.LOGIC, Alarm.Module.COMMAON, Alarm.Logic.LOGIN_TIMEOUT);
		resp.setMessage(Message.getMessage(resp.getCode()));
		
		return resp;
	}
	
	protected Response<Object> verify(){
		Response<Object> resp = new Response<Object>();
		
		resp.setCode(Alarm.Level.LOGIC, Alarm.Type.LOGIC,
				Alarm.Module.COMMAON, Alarm.Logic.LOGIN_VERIFY_FAILED);
		resp.setMessage(Message.getMessage(resp.getCode()));
		
		return resp;
	}
	
	public void setSeed(String seed){
		this.seed = seed;
	}
	
	public void exit(){
		CookieUtil cu = new CookieUtil(request, response);
		
		cu.deleteCookie("uid", "/", "seshenghuo.com");
		cu.deleteCookie("un", "/", "seshenghuo.com");
		cu.deleteCookie("token", "/", "seshenghuo.com");
		
		cu.setCookie("JSESSIONID", "", "www.seshenghuo.com", "/", 0, true);
		
		cu = null;
		
		String stoken = (String)session.getAttribute("token");
		
		if(null != stoken){
			session.removeAttribute("token");
		}
		
		L.info(AbstractLogic.class, "exit()", "INFO", "exit. byte!");
	}
	
	public boolean checkToken() {
		if(null == seed || "".equals(seed)){
			seed = null;
		}
		
		boolean pass = false;
		
		String uid = this.getStringCookie("uid");
		String un = this.getStringCookie("un");
		String token = this.getStringCookie("token");
		String stoken = (String)session.getAttribute("token");

		String src = uid + "|" + un;
		
		L.info(AbstractLogic.class, "checkToken", "INFO", "session: " + session);
		L.info(AbstractLogic.class, "checkToken", "INFO", "stoken: " + stoken);

		if (null == token || "".equals(token) || null == stoken || "".equals(stoken) || !stoken.equals(token)) {
			pass = false;
		} else {
			pass = null == seed ? RSA.verify(src, token) : RSA.verify(seed, src, token);
		}
		
		L.info(AbstractLogic.class, "checkToken()", "INFO", "token: " + pass);
		
		if(!pass){
			exit();
		}
		
		return pass;
	}

	protected String getStringCookie(String name) {
		CookieUtil cookie = new CookieUtil(request);

		return cookie.getValue(name);
	}

	protected int getIntCookie(String name) {
		String cookie = this.getStringCookie(name);

		int v = Integer.parseInt(cookie);

		return v;
	}

	protected long getLongCookie(String name) {
		String cookie = this.getStringCookie(name);

		long v = Long.parseLong(cookie);

		return v;
	}

	protected double getDoubleCookie(String name) {
		String cookie = this.getStringCookie(name);

		double v = Double.parseDouble(cookie);

		return v;
	}

	protected float getFloatCookie(String name) {
		String cookie = this.getStringCookie(name);

		float v = Float.parseFloat(cookie);

		return v;
	}

	protected boolean getBooleanCookie(String name) {
		String cookie = this.getStringCookie(name);

		boolean v = false;

		if ("true".equalsIgnoreCase(cookie) || "1".equals(cookie)) {
			v = true;
		}

		return v;
	}

	protected String getStringParameter(String name) {
		return this.request.getParameter(name);
	}

	protected int getIntParameter(String name) {
		String value = getStringParameter(name);
		int v = Integer.parseInt(value);

		return v;

	}

	protected long getLongParameter(String name) {
		String value = getStringParameter(name);
		long v = Long.parseLong(value);

		return v;

	}

	protected double getDoubleParameter(String name) {
		String value = getStringParameter(name);
		double v = Double.parseDouble(value);

		return v;

	}

	protected float getFloatParameter(String name) {
		String value = getStringParameter(name);
		float v = Float.parseFloat(value);

		return v;

	}

	protected boolean getBooleanParameter(String name) {
		String value = getStringParameter(name);
		boolean v = false;

		if ("true".equalsIgnoreCase(value) || "1".equals(value)) {
			v = true;
		}

		return v;
	}

	protected String getStringCookie(String name, String def) {
		CookieUtil cookie = new CookieUtil(request);

		String value = cookie.getValue(name);

		if (null == value) {
			value = def;
		}

		return value;
	}

	protected int getIntCookie(String name, int def) {
		String cookie = this.getStringCookie(name);
		int v = def;

		if (null != cookie && !"".equals(cookie))
			v = Integer.parseInt(cookie);

		return v;
	}

	protected long getLongCookie(String name, long def) {
		String cookie = this.getStringCookie(name);

		long v = def;

		if (null != cookie && !"".equals(cookie))
			v = Long.parseLong(cookie);

		return v;
	}

	protected double getDoubleCookie(String name, double def) {
		String cookie = this.getStringCookie(name);

		double v = def;

		if (null != cookie && !"".equals(cookie))
			v = Double.parseDouble(cookie);

		return v;
	}

	protected float getFloatCookie(String name, float def) {
		String cookie = this.getStringCookie(name);

		float v = def;

		if (null != cookie && !"".equals(cookie))
			v = Float.parseFloat(cookie);

		return v;
	}

	protected boolean getBooleanCookie(String name, boolean def) {
		String cookie = this.getStringCookie(name);

		boolean v = false;

		if ("true".equalsIgnoreCase(cookie) || "1".equals(cookie)) {
			v = true;
		}

		if (null == cookie || "".equals(cookie)) {
			v = def;
		}

		return v;
	}

	protected String getStringParameter(String name, String def) {
		String value = this.request.getParameter(name);

		if (null == def) {
			value = def;
		}

		return value;
	}

	protected int getIntParameter(String name, int def) {
		String value = getStringParameter(name);
		int v = def;

		if (null != value && !"".equals(value))
			v = Integer.parseInt(value);

		return v;

	}

	protected long getLongParameter(String name, long def) {
		String value = getStringParameter(name);
		long v = def;

		if (null != value && !"".equals(value))
			v = Long.parseLong(value);

		return v;

	}

	protected double getDoubleParameter(String name, double def) {
		String value = getStringParameter(name);
		double v = def;

		if (null != value && !"".equals(value))
			v = Double.parseDouble(value);

		return v;

	}

	protected float getFloatParameter(String name, float def) {
		String value = getStringParameter(name);
		float v = def;

		if (null != value && !"".equals(value))
			v = Float.parseFloat(value);

		return v;

	}

	protected boolean getBooleanParameter(String name, boolean def) {
		String value = getStringParameter(name);
		boolean v = false;

		if ("true".equalsIgnoreCase(value) || "1".equals(value)) {
			v = true;
		}

		if (null == value || "".equals(value)) {
			v = def;
		}

		return v;
	}
}

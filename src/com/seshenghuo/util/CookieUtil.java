/**
 * 
 */
package com.seshenghuo.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seshenghuo.logger.L;

/**
 * @author carlli
 * 
 */
public class CookieUtil {
	private HttpServletRequest request;
	private HttpServletResponse response;

	/**
	 * 
	 */
	public CookieUtil(HttpServletRequest request) {
		// TODO Auto-generated constructor stub
		this.request = request;
	}

	public CookieUtil(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated constructor stub
		this.request = request;
		this.response = response;
	}
	
	public void setCookie(String name, String value, String domain, String path, int expired, boolean httpOnly, boolean sercure){
		L.info(CookieUtil.class, "setCookie()", "INFO", "Add Start, " + name);

		Cookie cookie = new Cookie(name, value);
		
		cookie.setDomain(domain);
		cookie.setPath(path);
		cookie.setMaxAge(expired);
		cookie.setHttpOnly(httpOnly);
		cookie.setSecure(sercure);
		
		response.addCookie(cookie);
		
		L.info(CookieUtil.class, "setCookie()", "INFO", "Cookie Name: " + name);
		L.info(CookieUtil.class, "setCookie()", "INFO", "Cookie Path: " + path);
		L.info(CookieUtil.class, "setCookie()", "INFO", "Cookie Domain: " + domain);
		L.info(CookieUtil.class, "setCookie()", "INFO", "Cookie Expired: " + expired);
		L.info(CookieUtil.class, "setCookie()", "INFO", "Cookie HttpOnly: " + httpOnly);
		L.info(CookieUtil.class, "setCookie()", "INFO", "Cookie Secure: " + sercure);
		
		L.info(CookieUtil.class, "setCookie()", "INFO", "Add End, " + name);

		cookie = null;
	}
	
	public void setCookie(String name, String value, String domain, String path, int expired, boolean httpOnly){
		setCookie(name, value, domain, path, expired, httpOnly, false);
	}
	
	public void setCookie(String name, String value, String domain, String path, int expired){
		setCookie(name, value, domain, path, expired, false);
	}
	
	public void setCookie(String name, String value, String domain, String path){
		setCookie(name, value, domain, path, -1);
	}
	
	public void setCookie(String name, String value, String domain){
		setCookie(name, value, domain, "/");
	}
	
	public void setCookie(String name, String value, String path, int expired){
		String domain = request.getRemoteHost();
		
		setCookie(name, value, domain, path, expired);
	}
	
	public void setCookie(String name, String value, int expired){
		setCookie(name, value, "/", expired);
	}

	public void setCookie(String name, String value) {
		String domain = request.getRemoteHost();		
		setCookie(name, value, domain);
	}
	
	public Cookie getCookie(String name) {
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		int size = null == cookies ? 0 : cookies.length;

		for (int i = 0; i < size; i++) {
			cookie = cookies[i];
			if ((cookie.getName()).equals(name)) {
				return cookie;
			}
		}
		return null;
	}
	
	public String getValue(String name) {
		Cookie cookie = getCookie(name);
		
		L.info(CookieUtil.class, "getValue()", "INFO", "Cookie: " + name + " = " + cookie);
		
		if (null == cookie) {
			return null;
		}
		return cookie.getValue();
	}
	
	public void deleteCookie(String name, String path, String domain){
		L.info(CookieUtil.class, "deleteCookie()", "INFO", "Cookie: " + name);
		
		
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		int size = null == cookies ? 0 : cookies.length;

		for (int i = 0; i < size; i++) {
			cookie = cookies[i];
			if ((cookie.getName()).equals(name)) {
				setCookie(name, null, domain, path, 0);
			}
		}
	}
	
	public void deleteCookie(String name, String path){
		deleteCookie(name, path, request.getRemoteHost());
	}
	
	public void deleteCookie(String name) {
		deleteCookie(name, "/");
	}
}

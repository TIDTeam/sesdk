/**
 * @file Message.java
 * @date Nov 24, 2006
 * @author lijun
 * @version 1.0.0
 */

package com.seshenghuo.util;

import java.io.UnsupportedEncodingException;

import com.seshenghuo.logger.L;

/**
 * @author lijun
 * 
 */
public class Message {
	public static final int SUCCESS = 0;

	public static final String MAIN_INFO = "main";
	public static final String SUB_INFO = "sub";

	private static final String DEFAULT_LANG = "cn";

	private Message() {

	}

	private static String getProperty(String key) {
		return PropertiesUtil.getItem("message", key);
	}

	public static String formatMessage(String format, Object... args) {
		return String.format(format, args);
	}

	public static String getMessage(String lang, String code, String type) {
		if (!MAIN_INFO.equals(type) && !SUB_INFO.equals(type)) {
			type = MAIN_INFO;
		}
		String value = getProperty("msg." + type + "." + lang + "." + code);

		if (null == value) {
			value = "";
		} else {
			try {
				value = new String(value.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				L.error(Message.class, "getMessage()", code, e.getMessage());
			}
		}
		return value;
	}

	public static String getMessage(String code, String type) {
		return getMessage(DEFAULT_LANG, code, type);
	}

	public static String getMessage(String code) {
		return getMessage(DEFAULT_LANG, code, MAIN_INFO);
	}
}

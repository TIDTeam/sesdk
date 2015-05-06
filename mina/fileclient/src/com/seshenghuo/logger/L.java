/**
 * 
 */
package com.seshenghuo.logger;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.seshenghuo.util.Util;

/**
 * @author carlli
 * 
 */
public class L {

	private static HashMap<String, Logger> loggers = new HashMap<String, Logger>();

	/**
	 * @param name
	 */
	public L(String name) {
		// TODO Auto-generated constructor stub
	}

	static {
		PropertyConfigurator.configure(Util.getRuntimePath()
				+ "log4j.properties");
	}

	private static Logger getLogger(String name) {
		Logger logger = null;

		name = name.toUpperCase();

		if (loggers.containsKey(name) && null != (logger = loggers.get(name))) {
			return logger;
		} else {
			logger = Logger.getLogger(name);

			loggers.put(name, logger);
		}

		return logger;
	}

	@SuppressWarnings("rawtypes")
	public static void info(String name, Class _class, String method,
			String code, String message, String clientIp, String serverIp,
			String user, String source, long cost) {
		Logger logger = getLogger(name + "_INFO");
		String info = "[" + _class.getName() + "." + method + "/" + cost + "]"
				+ clientIp + "|" + serverIp + "|" + user + "|" + source + "|"
				+ code + "|" + message;

		logger.info(info);
	}

	@SuppressWarnings("rawtypes")
	public static void info(String name, Class _class, String method,
			String code, String message, int cost) {
		info(name, _class, method, code, message, "0.0.0.0", "0.0.0.0",
				"system", "system", cost);
	}

	@SuppressWarnings("rawtypes")
	public static void info(String name, Class _class, String method,
			String code, String message) {
		info(name, _class, method, code, message, -1);
	}

	@SuppressWarnings("rawtypes")
	public static void info(Class _class, String method, String code,
			String message) {
		info("DEFAULT", _class, method, code, message, -1);
	}

	@SuppressWarnings("rawtypes")
	public static void error(String name, Class _class, String method,
			String code, String message, String clientIp, String serverIp,
			String user, String source, long cost) {
		Logger logger = getLogger(name + "_ERROR");
		String info = "[" + _class.getName() + "." + method + "/" + cost + "]"
				+ clientIp + "|" + serverIp + "|" + user + "|" + source + "|"
				+ code + "|" + message;

		logger.error(info);
	}

	@SuppressWarnings("rawtypes")
	public static void error(String name, Class _class, String method,
			String code, String message, int cost) {
		error(name, _class, method, code, message, "0.0.0.0", "0.0.0.0",
				"system", "system", cost);
	}

	@SuppressWarnings("rawtypes")
	public static void error(String name, Class _class, String method,
			String code, String message) {
		error(name, _class, method, code, message, -1);
	}

	@SuppressWarnings("rawtypes")
	public static void error(Class _class, String method, String code,
			String message) {
		error("DEFAULT", _class, method, code, message, -1);
	}

}

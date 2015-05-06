/**
 * 
 */
package com.seshenghuo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.seshenghuo.logger.L;

/**
 * @author carlli
 * 
 */
public class INet {

	/**
	 * 
	 */
	public INet() {
		// TODO Auto-generated constructor stub
	}

	public static String getClientIP(HttpServletRequest request) {

		String ip = request.getHeader("X-Forwarded-For");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}

	public static String getServerIP(HttpServletRequest request) {
		InetAddress inet;
		String ip = "";
		try {
			inet = InetAddress.getByName(request.getServerName());
			ip = inet.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ip;
	}

	public static String getServerIP() {
		InetAddress inet;
		String ip = "";
		try {
			inet = InetAddress.getLocalHost();
			ip = inet.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ip;
	}

	private static ArrayList<String> getUnixMacAddress() {
		String charset = System.getProperty("sun.jnu.encoding");
		ArrayList<String> mac = new ArrayList<String>();
		BufferedReader reader = null;
		Process process = null;
		String line = null;
		Pattern pattern = Pattern.compile(
				".*(HWaddr|ether) ([a-zA-Z0-9]{2}(:[a-zA-Z0-9]{2}){5})[\\s ]*$",
				Pattern.CASE_INSENSITIVE|Pattern.MULTILINE);
		Matcher matcher = null;
		
		try {
			process = Runtime.getRuntime().exec("ifconfig -a");
			reader = new BufferedReader(new InputStreamReader(
					process.getInputStream(), charset));

			while (null != (line = reader.readLine())) {
				matcher = pattern.matcher(line);
				
				if (matcher.matches()) {
					mac.add(matcher.group(2));
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			L.error(INet.class, "getUnixMacAddress()", "IOException",
					e.getMessage());
		}

		return mac;
	}

	private static ArrayList<String> getWindowsMacAddress() {
		String charset = System.getProperty("sun.jnu.encoding");
		ArrayList<String> mac = new ArrayList<String>();
		BufferedReader reader = null;
		Process process = null;
		String line = null;
		Pattern pattern = Pattern.compile(
				".*: ([a-zA-Z0-9]{2}(\\-[a-zA-Z0-9]{2}){5})$",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = null;

		try {
			process = Runtime.getRuntime().exec("ipconfig /all");
			reader = new BufferedReader(new InputStreamReader(
					process.getInputStream(), charset));

			while (null != (line = reader.readLine())) {
				matcher = pattern.matcher(line);

				if (matcher.matches()) {
					mac.add(matcher.group(1));
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			L.error(INet.class, "getWindowsMacAddress()", "IOException",
					e.getMessage());
		}

		return mac;
	}

	public static ArrayList<String> getMacAddress() {
		String os = System.getProperty("os.name").toLowerCase();
		boolean isWindows = os.startsWith("windows");
		
		
		return isWindows ? getWindowsMacAddress() : getUnixMacAddress();
	}

	public static String getFirstMacAddress() {
		ArrayList<String> mac = getMacAddress();

		return mac.size() > 0 ? mac.get(0) : "";
	}

	public static String getAppContext(HttpServletRequest request) {
		return request.getContextPath();
	}

	public static String getAppURI(HttpServletRequest request) {
		return request.getRequestURI();
	}

	public static String getAppURL(HttpServletRequest request) {
		return request.getRequestURL().toString();
	}

	public static String getAppPath(HttpServletRequest request) {
		return request.getServletPath();
	}

	public static String getParameter(HttpServletRequest request, String name) {
		String value = request.getParameter(name);

		return value;
	}

	public static int getIntParameter(HttpServletRequest request, String name)
			throws NumberFormatException {
		String value = getParameter(request, name);

		int v = Integer.parseInt(value);

		return v;
	}

	public static long getLongParameter(HttpServletRequest request, String name)
			throws NumberFormatException {
		String value = getParameter(request, name);

		long v = Long.parseLong(value);

		return v;
	}

	public static float getFloatParameter(HttpServletRequest request,
			String name) throws NumberFormatException {
		String value = getParameter(request, name);

		if (null == value) {
			throw new NumberFormatException("For input string: \"" + value
					+ "\"");
		}

		float v = Float.parseFloat(value);

		return v;
	}

	public static double getDoubleParameter(HttpServletRequest request,
			String name) throws NumberFormatException {
		String value = getParameter(request, name);

		if (null == value) {
			throw new NumberFormatException("For input string: \"" + value
					+ "\"");
		}

		double v = Double.parseDouble(value);

		return v;
	}

	public static boolean getBooleanParameter(HttpServletRequest request,
			String name) {
		String value = getParameter(request, name);

		boolean v = false;

		if ("1".equals(value) || "true".equalsIgnoreCase(value)) {
			v = true;
		}

		return v;
	}
}

/**
 * @file Util.java
 * @date Nov 17, 2006
 * @author lijun
 * @version 1.0.0
 */

package com.seshenghuo.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.seshenghuo.logger.L;

/**
 * @author lijun 公共的工具处理类
 */
public class Util {
	private Util() {

	}

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };

	/**
	 * 字符编码
	 * 
	 * @param str
	 * @param charsetName
	 * @param targetName
	 * @return
	 */
	public static String encoding(String str, String charsetName,
			String targetName) {
		String tmp = "";
		if (null == str)
			return "";
		try {
			tmp = new String(str.getBytes(charsetName), targetName);
		} catch (UnsupportedEncodingException e) {
			L.error(Util.class,
					"encoding(String str, String charsetName,String targetName)",
					"UnsupportedEncodingException", e.getMessage());
		}
		return tmp;
	}

	/**
	 * 字符编码
	 * 
	 * @param str
	 * @param targetName
	 * @return
	 */
	public static String encoding(String str, String targetName) {
		return Util.encoding(str, "iso-8859-1", targetName);
	}

	/**
	 * 字符编码
	 * 
	 * @param str
	 * @return
	 */
	public static String encoding(String str) {
		return Util.encoding(str, "iso-8859-1", "utf-8");
	}

	public static String encodeURIComponent(String s, String enc) {
		String encode = null;

		if (null == s)
			return null;

		try {
			encode = URLEncoder.encode(s, enc);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			L.error(Util.class, "encodeURIComponent()",
					"UnsupportedEncodingException", e.getMessage());
		}

		return encode;
	}

	public static String encodeURIComponent(String s) {
		return encodeURIComponent(s, "utf-8");
	}

	public static String decodeURIComponent(String s, String enc) {
		String encode = null;

		if (null == s)
			return null;

		try {
			encode = URLDecoder.decode(s, enc);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			L.error(Util.class, "decodeURIComponent()",
					"UnsupportedEncodingException", e.getMessage());
		}

		return encode;
	}

	public static String decodeURIComponent(String s) {
		return decodeURIComponent(s, "utf-8");
	}

	public static boolean matcher(String pattern, String str) {
		if (null == str)
			return false;
		Pattern pa = Pattern.compile(pattern, Pattern.MULTILINE);
		Matcher ma = pa.matcher(str);
		return ma.matches();
	}

	public static String toHexString(String src) {
		byte[] b = src.getBytes();
		return Util.byteToHexString(b);
	}

	private static String byteToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(Util.byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = Util.byteToHexString(md.digest(resultString
					.getBytes()));
		} catch (Exception ex) {
			L.error(Util.class, "MD5Encode(String origin)", "Exception",
					ex.getMessage());
		}
		return resultString;
	}

	public static String getDateString(String format) {
		Date date = new Date();
		return Util.getDateString(date, format);
	}

	public static String getDateString(Date date, String format) {
		DateFormat df = new SimpleDateFormat(format);
		String datetime = df.format(date);
		return datetime;
	}

	/**
	 * 获取日期串
	 * 
	 * @return yyyyMMddHHmmss
	 */
	public static String getDateString() {
		return Util.getDateString("yyyyMMddHHmmss");
	}

	/**
	 * 将日期串转换成日期
	 * 
	 * @param source
	 *            yyyyMMddHHmmss
	 * @param format
	 *            yyyyMMddHHmmss
	 * @return
	 */
	public static Date toDate(String source, String format) {
		DateFormat fmt = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = fmt.parse(source);
		} catch (ParseException e) {
			System.out.println("to date error ! source = " + source);
		}
		return null == date ? new Date() : date;
	}

	/**
	 * 将日期串转换成日期
	 * 
	 * @param source
	 *            yyyyMMddHHmmss
	 * @return
	 */
	public static Date toDate(String source) {
		return Util.toDate(source, "yyyyMMddHHmmss");
	}

	public static String formatDateString(String datetime) {
		return Util.formatDateString(datetime, "yyyy-MM-dd HH:mm:ss");
	}

	public static String formatDateString(String datetime, String format) {
		Date date = Util.toDate(datetime);
		String dt = Util.getDateString(date, format);
		return dt;
	}

	public static String getPath(String keyword) {
		String path = null;
		File file = null;

		try {
			URL resUrl = Util.class.getResource("Util.class");
			String jar = resUrl.getFile();
			String protocol = resUrl.getProtocol();
			URI uri = Util.class.getResource("Util.class").toURI();

			if ("jar".equalsIgnoreCase(protocol)) {
				resUrl = new URL(jar.substring(0, jar.indexOf("!")));
				uri = resUrl.toURI();
			}

			L.info(Util.class, "getPath(String keyword)", "INFO", "Runtime: "
					+ uri.toString());
			file = new File(uri);
			path = file.getAbsolutePath();

			int keywordIndex = path.indexOf(keyword);
			int startIndex = 0; // path.indexOf(File.separatorChar);
			int endIndex = keywordIndex + keyword.length() + 1;

			if (keywordIndex == -1) {
				return null;
			}

			path = path.substring(startIndex, endIndex);

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			path = null;
			L.error(Util.class, "getPath(String keyword)",
					"URISyntaxException", e.getMessage());
		} catch (MalformedURLException e) {
			path = null;
			L.error(Util.class, "getPath(String keyword)",
					"MalformedURLException", e.getMessage());
		}

		return path;
	}

	public static String getClassPath() {
		return Util.getPath("classes");
	}

	public static String getBinPath() {
		return Util.getPath("bin");
	}

	public static String getLibPath() {
		return Util.getPath("lib");
	}

	public static String getWebInfoPath() {
		return Util.getPath("WEB-INFO");
	}

	public static String getRuntimePath() {
		String lib = getLibPath();
		String bin = getBinPath();
		String cls = getClassPath();

		return (null != lib ? lib : (null != bin ? bin : cls));
	}
}

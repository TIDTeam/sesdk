/**
 * 
 */
package com.seshenghuo.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import com.seshenghuo.logger.L;

/**
 * @author carlli
 * 
 */
public class PropertiesUtil extends Properties {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8717549116243696075L;
	private static HashMap<String, Properties> maps = new HashMap<String, Properties>();

	/**
	 * 
	 */
	public PropertiesUtil() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param defaults
	 */
	public PropertiesUtil(Properties defaults) {
		super(defaults);
		// TODO Auto-generated constructor stub
	}

	private static Properties loadPropertiesFile(String name) {
		Properties p = null;
		InputStream in = null;
		L.info(PropertiesUtil.class, "loadPropertiesFile(String name)", "INFO",
				"Properties file is " + name);

		if (maps.containsKey(name) && null != (p = maps.get(name))) {
			return p;
		}

		try {
			in = new FileInputStream(Util.getRuntimePath() + name
					+ ".properties");

			p = new Properties();
			p.load(in);

			maps.put(name, p);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			// logger.error("File(" + msgFile + ") Not Found");
			// e.printStackTrace();
			L.error(PropertiesUtil.class, "loadPropertiesFile(String name)",
					"FileNotFoundException", e.getMessage());
		} catch (IOException e) {
			// logger.error("load (" + msgFile + ") stream error. message = " +
			// e.getMessage());
			// e.printStackTrace();
			L.error(PropertiesUtil.class, "loadPropertiesFile(String name)",
					"IOException", e.getMessage());
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					// logger.error("close (" + msgFile +
					// ") stream error. message = " + e.getMessage());
					L.error(PropertiesUtil.class,
							"loadPropertiesFile(String name)", "IOException",
							e.getMessage());
				}
			}
		}

		return p;
	}

	public static String getItem(String name, String key) {
		String value = "";
		Properties p = loadPropertiesFile(name);

		if (null != p) {
			value = p.getProperty(key);
		}
		// System.out.println(name +"."+ key + " = " + value);
		// L.info(PropertiesUtil.class, "getItem()", "INFO", name +"."+ key +
		// " = " + value);
		return (null == value ? "" : value).trim();
	}

}

/**
 * 
 */
package com.seshenghuo.mina;

import com.seshenghuo.util.PropertiesUtil;

/**
 * @author carlli
 * 
 */
public class MinaCodecConfig {
	private static String prefix = "mina.codec.";

	private MinaCodecConfig() {
		// TODO Auto-generated constructor stub
	}

	public static String getValue(String key) {
		return PropertiesUtil.getItem("mina_codec", prefix + key);
	}

	public static String getStringValue(String key) {
		return getValue(key);
	}

	public static int getIntValue(String key) {
		String value = getValue(key);
		int v = Integer.parseInt(value);

		return v;
	}

	public static long getLongValue(String key) {
		String value = getValue(key);
		long v = Long.parseLong(value);

		return v;
	}

	public static float getFloatValue(String key) {
		String value = getValue(key);
		float v = Float.parseFloat(value);

		return v;
	}

	public static double getDoubleValue(String key) {
		String value = getValue(key);
		double v = Double.parseDouble(value);

		return v;
	}

	public static boolean getBooleanValue(String key) {
		String value = getValue(key);

		boolean v = false;

		if ("true".equalsIgnoreCase(value) || "1".equalsIgnoreCase(value)) {
			v = true;
		}

		return v;
	}

	public static String getStringValue(String key, String def) {
		String value = getValue(key);

		if (null == value || "".equals(value)) {
			value = def;
		}

		return value;
	}

	public static int getIntValue(String key, int def) {
		String value = getValue(key);
		int v = def;

		if (null == value || "".equals(value)) {
			v = def;
		} else {
			try {
				v = Integer.parseInt(value);
			} catch (NumberFormatException e) {
				v = def;
			}
		}

		return v;
	}

	public static long getLongValue(String key, long def) {
		String value = getValue(key);
		long v = def;

		if (null == value || "".equals(value)) {
			v = def;
		} else {
			try {
				v = Long.parseLong(value);
			} catch (NumberFormatException e) {
				v = def;
			}
		}

		return v;
	}

	public static float getFloatValue(String key, float def) {
		String value = getValue(key);
		float v = def;

		if (null == value || "".equals(value)) {
			v = def;
		} else {
			try {
				v = Float.parseFloat(value);
			} catch (NumberFormatException e) {
				v = def;
			}
		}

		return v;
	}

	public static double getDoubleValue(String key, double def) {
		String value = getValue(key);
		double v = def;

		if (null == value || "".equals(value)) {
			v = def;
		} else {
			try {
				v = Double.parseDouble(value);
			} catch (NumberFormatException e) {
				v = def;
			}
		}

		return v;
	}

}

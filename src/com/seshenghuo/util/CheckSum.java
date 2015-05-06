/**
 * 
 */
package com.seshenghuo.util;

import java.io.File;
import java.security.NoSuchAlgorithmException;

import com.seshenghuo.logger.L;

/**
 * @author carlli
 * 
 */
public class CheckSum {
	public final static int MD5 = 0x01, SHA1 = 0x02;

	/**
	 * 
	 */
	public CheckSum() {
		// TODO Auto-generated constructor stub
	}

	public static String verify(int type, File file, String code) {
		String result = null;
		switch (type) {
		case MD5:
			result = verifyMD5(file, code);
			break;
		case SHA1:
			result = verifySHA1(file, code);
			break;
		}
		return result;
	}

	public static String verify(int type, String file, String code) {
		String result = null;
		switch (type) {
		case MD5:
			result = verifyMD5(file, code);
			break;
		case SHA1:
			result = verifySHA1(file, code);
			break;
		}
		return result;
	}

	public static String verify(File file, String code) {
		return verify(MD5, file, code);
	}

	public static String verify(String file, String code) {
		return verify(MD5, file, code);
	}

	private static String verifyMD5(File file, String code) {
		String sum = null;

		L.info(CheckSum.class, "verifyMD5(File file, String code)", "INFO",
				"BeginVerify: file is " + file.getAbsolutePath() + "; code is "
						+ code);

		try {
			sum = MD5CheckSum.getMD5Checksum(file);

			L.info(CheckSum.class, "verifyMD5(File file, String code)", "INFO",
					"The MD5Sum is " + sum);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			L.error(CheckSum.class, "verifyMD5(File file, String code)",
					"NoSuchAlgorithmException", e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			L.error(CheckSum.class, "verifyMD5(File file, String code)",
					"Exception", e.getMessage());
		}

		return (code.equalsIgnoreCase(sum) ? sum : null);
	}

	private static String verifyMD5(String file, String code) {
		return verifyMD5(new File(file), code);
	}

	private static String verifySHA1(File file, String code) {
		String sum = null;

		L.info(CheckSum.class, "verifySHA1(File file, String code)", "INFO",
				"BeginVerify: file is " + file.getAbsolutePath() + "; code is "
						+ code);

		try {
			sum = SHA1CheckSum.getSHA1Checksum(file);

			L.info(CheckSum.class, "verifySHA1(File file, String code)",
					"INFO", "The SHA1Sum is " + sum);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			L.error(CheckSum.class, "verifySHA1(File file, String code)",
					"NoSuchAlgorithmException", e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			L.error(CheckSum.class, "verifySHA1(File file, String code)",
					"Exception", e.getMessage());
		}

		return (code.equalsIgnoreCase(sum) ? sum : null);
	}

	private static String verifySHA1(String file, String code) {
		return verifySHA1(new File(file), code);
	}
	/**
	 * @param args
	 */
	/*
	 * public static void main(String[] args) { // TODO Auto-generated method
	 * stub System.out.println(CheckSum.verify(
	 * "D:\\QQ转账\\UI\\MPayHtml5_UI_V3.0D00630\\MPayHtml5_UI_V3.0D00630.zip",
	 * "3573919212a338860f8cd573b499fc9c"));
	 * System.out.println(CheckSum.verify(CheckSum.SHA1,
	 * "D:\\QQ转账\\UI\\MPayHtml5_UI_V3.0D00630\\MPayHtml5_UI_V3.0D00630.zip",
	 * "3573919212a338860f8cd573b499fc9c")); }
	 */
}

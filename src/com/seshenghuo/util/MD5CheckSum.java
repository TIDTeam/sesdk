/**
 * 
 */
package com.seshenghuo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author carlli
 * 
 */
public class MD5CheckSum {

	/**
	 * 
	 */
	public MD5CheckSum() {
		// TODO Auto-generated constructor stub
	}

	private static byte[] digest(InputStream in)
			throws NoSuchAlgorithmException, Exception {
		byte[] buffer = new byte[1024];
		MessageDigest complete = MessageDigest.getInstance("MD5");
		int numRead;

		do {
			numRead = in.read(buffer);
			if (numRead > 0) {
				complete.update(buffer, 0, numRead);
			}
		} while (numRead != -1);

		in.close();
		return complete.digest();
	}

	private static byte[] createChecksum(String filename)
			throws NoSuchAlgorithmException, Exception {
		InputStream fis = new FileInputStream(filename);

		return digest(fis);
	}

	private static byte[] createChecksum(File file)
			throws NoSuchAlgorithmException, Exception {
		InputStream fis = new FileInputStream(file);

		return digest(fis);
	}

	private static String md5sum(byte[] b) {
		String result = "";

		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}

	public static String getMD5Checksum(String filename)
			throws NoSuchAlgorithmException, Exception {
		byte[] b = createChecksum(filename);

		return md5sum(b);
	}

	public static String getMD5Checksum(File file)
			throws NoSuchAlgorithmException, Exception {
		byte[] b = createChecksum(file);

		return md5sum(b);
	}
}

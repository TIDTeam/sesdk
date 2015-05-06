/**
 * 
 */
package com.seshenghuo.util;

/**
 * @author Administrator
 * 
 */
public class Convert {
	public static final int INT_BYTES_LENGTH = 4;
	public static final int LONG_BYTES_LENGTH = 8;

	public static byte[] int2bytes(int num) {
		byte[] bytes = new byte[] { (byte) (num >> 24), (byte) (num >> 16),
				(byte) (num >> 8), (byte) (num) };

		return bytes;
	}

	public static int bytes2int(byte[] bytes) {
		int num = 0;

		num = (((int) ((bytes[0] & 0xFF)) << 24)
				| ((int) ((bytes[1] & 0xFF)) << 16)
				| ((int) ((bytes[2] & 0xFF)) << 8) | ((int) ((bytes[3] & 0xFF))));

		return num;
	}

	public static byte[] long2bytes(long num) {
		byte[] bytes = new byte[] { (byte) (num >> 56), (byte) (num >> 48),
				(byte) (num >> 40), (byte) (num >> 32), (byte) (num >> 24),
				(byte) (num >> 16), (byte) (num >> 8), (byte) (num) };

		return bytes;
	}

	public static long bytes2long(byte[] bytes) {
		long num = 0L;

		num = (((long) ((bytes[0] & 0xFF)) << 56)
				| ((long) ((bytes[1] & 0xFF)) << 48)
				| ((long) ((bytes[2] & 0xFF)) << 40)
				| ((long) ((bytes[3] & 0xFF)) << 32)
				| ((long) ((bytes[4] & 0xFF)) << 24)
				| ((long) ((bytes[5] & 0xFF)) << 16)
				| ((long) ((bytes[6] & 0xFF)) << 8) | ((long) ((bytes[7] & 0xFF))));

		return num;
	}
	
	public static String bytes2hex(byte[] bytes){
		int len = bytes.length;
		int low = 0;
		int high = 0;
		byte b = 0;
		
		StringBuilder sb = new StringBuilder(len * 2);  
		  
		for (int i = 0; i < len; i++){     
	        b = bytes[i];
	        
			low = b & 0xF;  
	        high = (b >> 4) & 0xF;
	        
	        sb.append(Character.forDigit(high, 16));  
	        sb.append(Character.forDigit(low, 16));  
	    } 
		
		return sb.toString();
	}
	
	public static byte[] hex2bytes(String hex){
		int len = hex.length();
		
		if(len == 0 || len % 2 != 0){
			return null;
		}
		
		byte[] bytes = new byte[len / 2];
		char hc;
		char lc;
		int low = 0;
		int heigh = 0;
		byte b;
		
	    for(int i = 0; i < len; i += 2){
	    	hc = hex.charAt(i);
	    	lc = hex.charAt(i + 1);
	    	
	    	heigh = (Character.digit(hc, 16) << 4);
	    	low = Character.digit(lc, 16);
	    	b = (byte)(heigh + low);
	    	
	    	bytes[i / 2] = b;
	    }
	    
	    return bytes;
	}	
}

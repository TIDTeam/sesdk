/**
 * 
 */
package com.seshenghuo.mina.fs;

/**
 * @author Administrator
 * 
 */
public interface Commands {
	public static final int QUIT = 0x01;
	public static final int UPLOAD = 0x02;
	public static final int UPLOADING = 0x03;
	public static final int DOWNLOAD = 0x04;
	public static final int DOWNLOADING = 0x05;
	public static final int REJECTED = 0x06;
}

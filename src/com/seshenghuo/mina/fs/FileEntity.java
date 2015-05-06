/**
 * 
 */
package com.seshenghuo.mina.fs;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

/**
 * @author carlli
 * 
 */
public class FileEntity {
	private int cmd;
	private String remoteAbsolutePath = "";
	private String localAbsolutePath = "";
	private String location = ""; // url
	private long timeStamp = System.currentTimeMillis();
	private int fileSize = 0;
	private int remainBytes = 0;
	private BufferedInputStream in = null;
	private BufferedOutputStream out = null;

	/**
	 * 
	 */
	public FileEntity() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the cmd
	 */
	public int getCmd() {
		return cmd;
	}

	/**
	 * @param cmd
	 *            the cmd to set
	 */
	public void setCmd(int cmd) {
		this.cmd = cmd;
	}

	/**
	 * @return the remoteAbsolutePath
	 */
	public String getRemoteAbsolutePath() {
		return remoteAbsolutePath;
	}

	/**
	 * @param remoteAbsolutePath
	 *            the remoteAbsolutePath to set
	 */
	public void setRemoteAbsolutePath(String remoteAbsolutePath) {
		this.remoteAbsolutePath = remoteAbsolutePath;
	}

	/**
	 * @return the localAbsolutePath
	 */
	public String getLocalAbsolutePath() {
		return localAbsolutePath;
	}

	/**
	 * @param localAbsolutePath
	 *            the localAbsolutePath to set
	 */
	public void setLocalAbsolutePath(String localAbsolutePath) {
		this.localAbsolutePath = localAbsolutePath;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the timeStamp
	 */
	public long getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp
	 *            the timeStamp to set
	 */
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the fileSize
	 */
	public int getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize
	 *            the fileSize to set
	 */
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * @return the totalBytes
	 */
	public int getTotalBytes() {
		return fileSize;
	}

	/**
	 * @param totalBytes
	 *            the totalBytes to set
	 */
	public void setTotalBytes(int totalBytes) {
		this.fileSize = totalBytes;
	}

	/**
	 * @return the remainBytes
	 */
	public int getRemainBytes() {
		return remainBytes;
	}

	/**
	 * @param remainBytes
	 *            the remainBytes to set
	 */
	public void setRemainBytes(int remainBytes) {
		this.remainBytes = remainBytes;
	}

	/**
	 * @return the in
	 */
	public BufferedInputStream getBufferedInputStream() {
		return in;
	}

	/**
	 * @param in
	 *            the in to set
	 */
	public void setBufferedInputStream(BufferedInputStream in) {
		this.in = in;
	}

	/**
	 * @return the out
	 */
	public BufferedOutputStream getBufferedOutputStream() {
		return out;
	}

	/**
	 * @param out
	 *            the out to set
	 */
	public void setBufferedOutputStream(BufferedOutputStream out) {
		this.out = out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileEntity [cmd=");
		builder.append(cmd);
		builder.append(", remoteAbsolutePath=");
		builder.append(remoteAbsolutePath);
		builder.append(", localAbsolutePath=");
		builder.append(localAbsolutePath);
		builder.append(", location=");
		builder.append(location);
		builder.append(", timeStamp=");
		builder.append(timeStamp);
		builder.append(", fileSize=");
		builder.append(fileSize);
		builder.append(", remainBytes=");
		builder.append(remainBytes);
		builder.append(", in=");
		builder.append(in);
		builder.append(", out=");
		builder.append(out);
		builder.append("]");
		return builder.toString();
	}
}

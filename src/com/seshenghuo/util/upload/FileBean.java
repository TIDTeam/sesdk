/**
 * 
 */
package com.seshenghuo.util.upload;

/**
 * @author carlli
 * 
 */
public class FileBean {
	private String fileName;
	private String srcFileName;
	private String localPath;
	private String remotePath;
	private String extendName;
	private String contentType;
	private long size;
	private long maxSize;
	private long fileSize;
	private byte[] data;

	/**
	 * 
	 */
	public FileBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the srcFileName
	 */
	public String getSrcFileName() {
		return srcFileName;
	}

	/**
	 * @param srcFileName
	 *            the srcFileName to set
	 */
	public void setSrcFileName(String srcFileName) {
		this.srcFileName = srcFileName;
	}

	/**
	 * @return the localPath
	 */
	public String getLocalPath() {
		return localPath;
	}

	/**
	 * @param localPath
	 *            the localPath to set
	 */
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	/**
	 * @return the remotePath
	 */
	public String getRemotePath() {
		return remotePath;
	}

	/**
	 * @param remotePath
	 *            the remotePath to set
	 */
	public void setRemotePath(String remotePath) {
		this.remotePath = remotePath;
	}

	/**
	 * @return the extendName
	 */
	public String getExtendName() {
		return extendName;
	}

	/**
	 * @param extendName
	 *            the extendName to set
	 */
	public void setExtendName(String extendName) {
		this.extendName = extendName;
	}

	/**
	 * @return the size
	 */
	public long getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(long size) {
		this.size = size;
	}

	/**
	 * @return the maxSize
	 */
	public long getMaxSize() {
		return maxSize;
	}

	/**
	 * @param maxSize
	 *            the maxSize to set
	 */
	public void setMaxSize(long maxSize) {
		this.maxSize = maxSize;
	}

	/**
	 * @return the fileSize
	 */
	public long getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize
	 *            the fileSize to set
	 */
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * @return the data
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(byte[] data) {
		this.data = data;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType
	 *            the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();

		buf.append("{").append("\"fileName\":").append("\"" + fileName + "\",")
				.append("\"srcFileName\":").append("\"" + srcFileName + "\",")
				.append("\"localPath\":").append("\"" + localPath + "\",")
				.append("\"remotePath\":").append("\"" + remotePath + "\",")
				.append("\"extendName\":").append("\"" + extendName + "\",")
				.append("\"contentType\":").append("\"" + contentType + "\",")
				.append("\"size\":").append(size).append(",")
				.append("\"maxSize\":").append(maxSize).append(",")
				.append("\"fileSize\":").append(fileSize).append("}");

		return buf.toString();
	}

}

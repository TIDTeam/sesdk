/**
 * 
 */
package com.seshenghuo.jfx.mina;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Administrator
 * 
 */
public class FileInfo {
	protected StringProperty fileName = new SimpleStringProperty(this,
			"fileName");
	protected StringProperty localPath = new SimpleStringProperty(this,
			"localPath");
	protected StringProperty remotePath = new SimpleStringProperty(this,
			"remotePath");
	protected StringProperty fileSize = new SimpleStringProperty(this,
			"fileSize");
	protected StringProperty lastModified = new SimpleStringProperty(this,
			"lastModified");
	protected StringProperty status = new SimpleStringProperty(this, "status");

	/**
	 * 
	 */
	public FileInfo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName.get();
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName.set(fileName);
	}

	/**
	 * @return the localPath
	 */
	public String getLocalPath() {
		return localPath.get();
	}

	/**
	 * @param localPath
	 *            the localPath to set
	 */
	public void setLocalPath(String localPath) {
		this.localPath.set(localPath);
	}

	/**
	 * @return the remotePath
	 */
	public String getRemotePath() {
		return remotePath.get();
	}

	/**
	 * @param localPath
	 *            the localPath to set
	 */
	public void setRemotePath(String remotePath) {
		this.remotePath.set(remotePath);
	}

	/**
	 * @return the fileSize
	 */
	public String getFileSize() {
		return fileSize.get();
	}

	/**
	 * @param fileSize
	 *            the fileSize to set
	 */
	public void setFileSize(String fileSize) {
		this.fileSize.set(fileSize);
	}

	/**
	 * @return the lastModified
	 */
	public String getLastModified() {

		return lastModified.get();
	}

	/**
	 * @param lastModified
	 *            the lastModified to set
	 */
	public void setLastModified(String lastModified) {
		this.lastModified.set(lastModified);
	}

	/**
	 * @return the status
	 */
	public String getStatus() {

		return status.get();
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status.set(status);
	}

}

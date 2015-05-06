/**
 * 
 */
package com.seshenghuo.util.upload;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import com.seshenghuo.logger.L;

/**
 * @author carlli
 * 
 */
public class Upload {

	public static final int FILE_UPLOAD_SUCCESS = 0;
	public static final int FILE_UPLOAD_UNKNOWN_ERROR = 1000;
	public static final int FILE_EXISTED = 1001;
	public static final int FILE_UPLOAD_IO_EXCEPTION = 1002;
	public static final int FILE_UPLOAD_MKDIRS_FAILED = 1003;

	private ArrayList<FileBean> files = new ArrayList<FileBean>();

	/**
	 * 
	 */
	public Upload() {
		// TODO Auto-generated constructor stub
	}

	private HttpServletRequest request;
	private FileForm ff;
	private int BUFFER = 1024 * 8;

	public Upload(HttpServletRequest request) {
		this.request = request;
		ff = new FileForm(this.request);
	}

	public Upload(HttpServletRequest request, int block) {
		this.request = request;
		this.BUFFER = block;
		ff = new FileForm(this.request);
	}

	public int upload(String remoteRootPath, String remoteSubPath,
			String remoteName) {
		L.info(Upload.class, "upload()", "INFO", remoteRootPath + "::"
				+ remoteSubPath + "::" + remoteName);
		int result = Upload.FILE_UPLOAD_UNKNOWN_ERROR;

		ArrayList<FileBean> list = (ArrayList<FileBean>) ff.getFileBeanList();
		FileBean fb = null;
		File file = null;
		boolean isUpload = true;
		Calendar cal = Calendar.getInstance();
		String subdir = remoteSubPath + File.separator + cal.get(Calendar.YEAR)
				+ File.separator + (cal.get(Calendar.MONTH) + 1)
				+ File.separator + cal.get(Calendar.DATE) + File.separator;

		subdir = subdir.replaceAll("\\\\", "/");

		remoteRootPath = remoteRootPath + subdir;
		remoteRootPath = remoteRootPath.replaceAll("\\\\", "/");

		for (int i = 0; null != list && i < list.size(); i++) {
			fb = list.get(i);

			if (fb.getSize() <= 0) {
				files.add(fb);
				continue;
			}

			isUpload = mkdirs(remoteRootPath);
			byte[] data = fb.getData();

			if (isUpload) {
				// fb.setFileName(fb.getSrcFileName());
				fb.setFileName(rename(remoteName) + fb.getExtendName());
				fb.setRemotePath(subdir + fb.getFileName());

				file = new File(remoteRootPath + fb.getFileName());

				if (!file.exists()) {
					try {
						L.info(Upload.class, "upload()", "INFO",
								"upload file is " + file.getAbsolutePath());
						DataOutputStream dos = new DataOutputStream(
								new FileOutputStream(file));
						DataInputStream dis = new DataInputStream(
								new ByteArrayInputStream(data));
						byte[] b = new byte[BUFFER];
						int len = 0;

						while (-1 != (len = dis.read(b))) {
							dos.write(b, 0, len);
						}

						dis.close();
						dos.flush();
						dos.close();

						files.add(fb);

						result = Upload.FILE_UPLOAD_SUCCESS;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						result = Upload.FILE_UPLOAD_IO_EXCEPTION;
						L.error(Upload.class, "upload()", "IOException",
								e.getMessage());
					}
				} else {
					result = Upload.FILE_EXISTED;
				}
			} else {
				result = Upload.FILE_UPLOAD_MKDIRS_FAILED;
			}
		}
		L.info(Upload.class, "upload()", "INFO", "[" + result + "]" + fb);

		return result;
	}

	public int upload(String remoteRootPath, String remoteSubPath) {
		return upload(remoteRootPath, remoteSubPath, "upload_");
	}

	private boolean mkdirs(String path) {
		File dir = null;
		dir = new File(path);
		boolean _is = true;
		if (!dir.exists())
			_is = dir.mkdirs();
		return _is;
	}

	private String rename(String prefix) {
		String rnd = ("" + (Math.random() * 10000000));
		rnd = rnd.substring(0, rnd.indexOf("."));

		String name = prefix + System.currentTimeMillis() + "_" + rnd;
		return name;
	}

	public String getParameter(String key) {
		return ff.getParameter(key);
	}

	public ArrayList<FileBean> getUploadFiles() {
		return files;
	}

	public FileBean getUploadFile(int index) {
		int size = files.size();
		if (size > 0 && index < size) {
			return files.get(index);
		}

		return null;
	}

	public FileBean getUploadFile(String srcFileName) {
		int size = files.size();
		FileBean bean = null;

		for (int i = 0; i < size; i++) {
			bean = files.get(i);
			if (bean.getSrcFileName().equals(srcFileName)) {
				return bean;
			}
		}

		return null;
	}
}

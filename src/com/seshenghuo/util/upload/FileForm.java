/**
 * 
 */
package com.seshenghuo.util.upload;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import com.seshenghuo.logger.L;
import com.seshenghuo.util.Config;

/**
 * @author carlli
 * 
 */
public class FileForm {

	/**
	 * 
	 */
	public FileForm() {
		// TODO Auto-generated constructor stub
	}

	private HttpServletRequest request;
	private ServletInputStream in;
	private HashMap<String, String> map = new HashMap<String, String>();
	private ArrayList<FileBean> fbs = new ArrayList<FileBean>();

	protected FileForm(HttpServletRequest request) {
		this.request = request;
		try {
			this.in = request.getInputStream();
			this.processInputData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			L.error(FileForm.class, "FileForm()", "IOException", e.getMessage());
		}
	}

	private byte[] getInputData() {
		try {
			int contentLength = request.getContentLength();
			byte[] b = new byte[contentLength];
			DataInputStream dis = new DataInputStream(in);
			dis.readFully(b);
			dis.close();
			return b;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			L.error(FileForm.class, "FileForm()", "IOException", e.getMessage());
		}
		return null;
	}

	private byte[] subBytes(byte[] b, int start, int end) {
		byte[] s = new byte[end - start];

		ByteArrayInputStream bis = new ByteArrayInputStream(b, start, end
				- start);

		try {
			bis.read(s);
			bis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			L.error(FileForm.class, "subBytes()", "IOException", e.getMessage());
		}

		bis = null;

		return s;
	}

	private int indexOf(byte[] b, byte[] s) {
		int offset = s.length;
		int length = b.length;
		byte[] r = new byte[offset];

		for (int i = 0; i < length; i++) {
			if (i + offset > length) {
				return -1;
			}

			r = subBytes(b, i, i + offset);

			if (Arrays.equals(r, s)) {
				return i;
			}
		}
		return -1;
	}

	private ArrayList<byte[]> parseInputData() {
		byte[] in = this.getInputData();
		if (null == in)
			return null;
		String contentType = request.getContentType();
		String boundary = contentType.substring(contentType
				.indexOf("boundary=") + 9);
		String startBoundary = "--" + boundary;
		String endBoundary = boundary + "--";

		ArrayList<byte[]> list = new ArrayList<byte[]>();

		byte[] startBoundaryBytes = startBoundary.getBytes();
		byte[] endBoundaryBytes = endBoundary.getBytes();
		byte[] splitBytes = ("\r\n" + startBoundary + "\r\n").getBytes();
		byte[] crlf = "\r\n".getBytes();

		byte[] fileInfoBytes = null;

		int startIndex = indexOf(in, startBoundaryBytes);
		int endIndex = indexOf(in, endBoundaryBytes) - crlf.length;
		int splitIndex = -1;

		L.info(FileForm.class, "parseInputData()", "INFO", "in: " + in.length);
		L.info(FileForm.class, "parseInputData()", "INFO", "startIndex: "
				+ startIndex);
		L.info(FileForm.class, "parseInputData()", "INFO", "endIndex: "
				+ endIndex);

		if (startIndex == -1 || endIndex == -1) {
			return list;
		}

		in = subBytes(in, startIndex, endIndex);

		L.info(FileForm.class, "parseInputData()", "INFO", "in: " + in.length);

		boolean flag = true;

		do {

			splitIndex = indexOf(in, splitBytes);
			L.info(FileForm.class, "parseInputData()", "INFO", "splitIndex: "
					+ splitIndex);
			if (splitIndex == -1) {
				splitIndex = in.length;
				L.info(FileForm.class, "parseInputData()", "INFO",
						"**splitIndex: " + splitIndex);
			}

			fileInfoBytes = subBytes(in, 0, splitIndex);
			list.add(fileInfoBytes);
			L.info(FileForm.class, "parseInputData()", "INFO",
					"fileInfoBytes: " + fileInfoBytes.length);
			if (splitIndex < in.length) {
				in = subBytes(in, splitIndex + crlf.length, in.length);
				L.info(FileForm.class, "parseInputData()", "INFO",
						"Less Than ..... ");
			} else {
				flag = false;
				L.info(FileForm.class, "parseInputData()", "INFO", "Break Loop");
			}
		} while (flag);

		return list;
	}

	private void processInputData() {
		ArrayList<byte[]> in = this.parseInputData();
		byte[] d = null;
		String filter = Config.getStringValue("upload.filter", ".+");
		long maxsize = Config.getLongValue("upload.maxsize", 2097152L);
		int maxupload = Config.getIntValue("upload.maxupload", 10);
		Pattern pattern = Pattern.compile("^(" + filter + ")$",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = null;

		String contentDisposition = null;
		String name = "";
		String value;
		String srcFileName;
		String localPath;
		String extendName;
		String contentType;
		long size;
		byte[] rnrn = "\r\n\r\n".getBytes();
		byte[] cd = null;
		byte[] c = null;
		FileBean fb = null;
		for (int i = 0; i < in.size(); i++) {
			d = in.get(i);
			cd = subBytes(d, 0, indexOf(d, rnrn));
			c = subBytes(d, indexOf(d, rnrn) + rnrn.length, d.length);

			try {
				contentDisposition = new String(
						new String(cd, "ISO-8859-1").getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				contentDisposition = new String(cd);
			}

			if (contentDisposition.indexOf("name=\"") != -1
					&& contentDisposition.indexOf("name=\"\"") == -1) {
				if (contentDisposition.indexOf("filename=\"") != -1
						&& contentDisposition.indexOf("filename=\"\"") == -1) {
					fb = new FileBean();
					name = contentDisposition.substring(
							contentDisposition.indexOf("name=\"") + 6,
							contentDisposition.indexOf("\"; filename=\""));
					localPath = contentDisposition.substring(
							contentDisposition.indexOf("filename=\"") + 10,
							contentDisposition.lastIndexOf("\""));
					srcFileName = localPath.substring(localPath
							.lastIndexOf(File.separator) + 1);
					extendName = srcFileName.substring(srcFileName
							.lastIndexOf('.'));
					contentType = contentDisposition
							.substring(contentDisposition
									.indexOf("Content-Type: ") + 14);
					size = c.length;

					matcher = pattern.matcher(contentType);

					fb.setFileSize(size);

					if (!matcher.matches() || size > maxsize || i >= maxupload) {
						size = 0;
					}

					fb.setLocalPath(localPath);
					fb.setSrcFileName(srcFileName);
					fb.setExtendName(extendName);
					fb.setData(c);
					fb.setSize(size);
					fb.setMaxSize(maxsize);
					fb.setContentType(contentType);
					fbs.add(fb);
				} else {
					name = contentDisposition.substring(
							contentDisposition.indexOf("name=\"") + 6,
							contentDisposition.lastIndexOf("\""));
					value = new String(c);
					map.put(name, value);
				}
			}
		}
	}

	/**
	 * @param name
	 * @return map
	 */
	protected String getParameter(String name) {
		return null != map ? map.get(name) : null;
	}

	/**
	 * @return fbs
	 */
	protected ArrayList<FileBean> getFileBeanList() {
		return fbs;
	}
}

/**
 * 
 */
package com.seshenghuo.ui.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seshenghuo.base.Response;
import com.seshenghuo.messagecode.Alarm;
import com.seshenghuo.util.Config;
import com.seshenghuo.util.Message;
import com.seshenghuo.util.upload.FileBean;
import com.seshenghuo.util.upload.Upload;

/**
 * @author Administrator
 * 
 */
public class UploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1946809974757357670L;

	/**
	 * 
	 */
	public UploadServlet() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String open = Config.getStringValue("upload.open");
		Response<Object> r = new Response<Object>();

		if ("on".equals(open)) {
			Upload upload = new Upload(req);
			String remoteRootPath = Config.getStringValue("upload.root");
			String remoteSubPath = req.getHeader("X-Upload-Sub");
			String remoteName = req.getHeader("X-Upload-Name");

			if (null == remoteName || "".equals(remoteName)) {
				upload.upload(remoteRootPath, remoteSubPath);
			} else {
				upload.upload(remoteRootPath, remoteSubPath, remoteName);
			}

			ArrayList<FileBean> list = upload.getUploadFiles();
			FileBean fb = null;
			for (int i = 0, size = list.size(); i < size; i++) {
				fb = list.get(i);
				fb.setData(null);
				list.set(i, fb);
			}

			r.setCode(Alarm.SUCCESS);
			r.setResponse(list);
		} else {
			r.setCode(Alarm.Level.SYSTEM, Alarm.Type.LOGIC,
					Alarm.Module.COMMAON, Alarm.Logic.SERVICE_CLOSED);
		}

		r.setMessage(Message.getMessage(r.getCode()));

		String content = r.toString();
		PrintWriter out = resp.getWriter();
		out.print(content);
		out.flush();
		out.close();
	}

}

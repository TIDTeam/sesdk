/**
 * 
 */
package com.seshenghuo.ui.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seshenghuo.base.Response;
import com.seshenghuo.ui.bean.UserBasicBean;
import com.seshenghuo.ui.logic.User;
import com.seshenghuo.util.INet;
import com.seshenghuo.util.Util;

/**
 * @author Administrator
 * 
 */
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7440499620045896711L;

	/**
	 * 
	 */
	public LoginServlet() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doHead(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = new User(req, resp);
		user.exit();		
		user = null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		User user = new User(req, resp);
			
		String userName = req.getParameter("user_name");
		String userPasswd = req.getParameter("user_passwd");
		String seed = req.getParameter("seed");
		
		UserBasicBean bean = new UserBasicBean();			

		bean.setUserName(userName);
		bean.setUserPasswd(userPasswd);
		bean.setLoginIp(INet.getClientIP(req));
		bean.setLoginDate(Util.getDateString());
		
		user.setSeed(seed);
		
		Response<Object> ret = user.login(bean);	
		
		PrintWriter out = resp.getWriter();
		out.print(ret.toString());
		out.flush();
		out.close();

		bean = null;			
		ret = null;
		user = null;
	}

}

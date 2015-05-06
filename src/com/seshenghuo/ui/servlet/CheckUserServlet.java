/**
 * 
 */
package com.seshenghuo.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seshenghuo.base.Response;
import com.seshenghuo.messagecode.Alarm;
import com.seshenghuo.ui.logic.User;

/**
 * @author carlli
 * 
 */
public class CheckUserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6681582628409024158L;

	/**
	 * 
	 */
	public CheckUserServlet() {
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
		String checkType = req.getHeader("X-User-Check");
		String value = req.getHeader("X-User-Value");
		User user = new User(req, resp);
		Response<Object> result = null;

		if (null != value && !"".equals(value)) {

			if ("name".equals(checkType)) {
				result = user.checkUserName(value);
			} else if ("nick".equals(checkType)) {
				result = user.checkNickName(value);
			} else if ("email".equals(checkType)) {
				result = user.checkUserEmail(value);
			} else {
				result = new Response<Object>();
			}
		} else {
			result = new Response<Object>();
		}

		if (Alarm.SUCCESS == result.getCode()) {
			resp.addHeader("X-User-Exist", "0");
		} else {
			resp.addHeader("X-User-Exist", "1");
		}
	}

}

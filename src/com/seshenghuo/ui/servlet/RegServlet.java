package com.seshenghuo.ui.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seshenghuo.base.Response;
import com.seshenghuo.messagecode.Alarm;
import com.seshenghuo.ui.logic.User;
import com.seshenghuo.util.Config;
import com.seshenghuo.util.Message;

public class RegServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2406948245069473580L;

	public RegServlet() {
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

		String open = Config.getStringValue("reg.open");
		Response<Object> result = null;

		if ("on".equals(open)) {
			User user = new User(req, resp);
			result = user.regiest();
		} else {
			result = new Response<Object>();
			result.setCode(Alarm.Level.SYSTEM, Alarm.Type.LOGIC,
					Alarm.Module.COMMAON, Alarm.Logic.SERVICE_CLOSED);
			result.setMessage(Message.getMessage(result.getCode()));
		}
		PrintWriter out = resp.getWriter();
		out.print(result.toString());
		out.flush();
		out.close();
	}

}

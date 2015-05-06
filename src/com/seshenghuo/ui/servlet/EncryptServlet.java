package com.seshenghuo.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seshenghuo.util.RSA;
import com.seshenghuo.util.Util;

/**
 * 
 */

/**
 * @author carlli
 * 
 */
public class EncryptServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3311524130376335497L;

	/**
	 * 
	 */
	public EncryptServlet() {
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
		String prefix = "X-Encrypt-";

		String type = req.getHeader(prefix + "Type");
		String origin = req.getHeader(prefix + "Origin");
		String key = req.getHeader(prefix + "Key");
		String seed = req.getHeader(prefix + "Seed");
		String encrypt = "";

		if (null == type || "".equals(type)) {
			type = "rsa";
		}

		if (null == key || "".equals(key)) {
			key = "Data";
		}

		if ("rsa".equalsIgnoreCase(type)) {
			if(null != seed && !"".equals(seed)){
				encrypt = RSA.encrypt(seed, origin);
			}else{
				encrypt = RSA.encrypt(origin);
			}
		} else if ("md5".equalsIgnoreCase(type)) {
			encrypt = Util.MD5Encode(origin);
		}

		if (null == encrypt) {
			encrypt = "0";
		}

		resp.addHeader(prefix + key, encrypt);
	}
}

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
import javax.servlet.http.HttpSession;

import com.seshenghuo.base.Response;
import com.seshenghuo.ui.logic.Posts;
import com.seshenghuo.util.INet;

/**
 * @author Administrator
 *
 */
public class PostServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2228630964786116751L;

	/**
	 * 
	 */
	public PostServlet() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doHead(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sess = req.getSession();
		Posts p = new Posts(req, resp);
		String ip = INet.getClientIP(req);
		String sip = null;
		String sPostId = req.getHeader("X-Post-ID");
		int postId;
		
		if(null == sPostId || sPostId.equals("")){
			postId = 0;
		}else{
			postId = Integer.parseInt(sPostId);
		}
		
		sip = (String)sess.getAttribute("uip_" + postId);
		
		if((null == sip || !ip.equals(sip)) && postId > 0){
			p.updateLoveHit(postId);
			sess.setAttribute("uip_" + postId, ip);
		}
		p = null;
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
		
		String op = req.getParameter("op");
		String type = req.getParameter("post_type");
		Response<Object> result = new Response<Object>();
		Posts p = new Posts(req, resp);
		
		if("add".equals(op)){
			if("text".equals(type)){
				result = p.addTextsPosts();
			}else if("photo".equals(type)){
				result = p.addPhotosPosts();
			}else if("event".equals(type)){
				result = p.addEventsPosts();
			}else if("life".equals(type)){
				result = p.addLifePosts();
			}
			
		}else if("update".equals(op)){
			if("text".equals(type)){
				result = p.updateTextsPosts();
			}else if("photo".equals(type)){
				result = p.updatePhotosPosts();
			}else if("event".equals(type)){
				result = p.updateEventsPosts();
			}else if("life".equals(type)){
				result = p.updateLifePosts();
			}
		}else if("query".equals(op)){
			if("text".equals(type)){
				result = p.queryTextPosts().toObject();
			}else if("photo".equals(type)){
				result = p.queryPhotosPosts().toObject();
			}else if("event".equals(type)){
				result = p.queryEventsPosts().toObject();
			}else if("life".equals(type)){
				result = p.queryLifePosts().toObject();
			}else{
				result = p.queryAllPosts();
			}
		}
		
		PrintWriter out = resp.getWriter();
		out.print(result.toString());
		out.flush();
		out.close();

		out = null;
		result = null;
		p = null;
	}

}

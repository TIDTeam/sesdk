/**
 * 
 */
package com.seshenghuo.mina.fs;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.SocketAddress;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.seshenghuo.logger.L;
import com.seshenghuo.mina.MinaServerConfig;
import com.seshenghuo.util.IPTools;

/**
 * @author carlli
 * 
 */
public class FileServerIoHandler extends IoHandlerAdapter {
	String allowIp = MinaServerConfig.getStringValue("allow_ip", null);
	String denyIp = MinaServerConfig.getStringValue("deny_ip", null);

	/**
	 * 
	 */
	public FileServerIoHandler() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.core.service.IoHandlerAdapter#sessionOpened(org.apache
	 * .mina.core.session.IoSession)
	 */
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		SocketAddress addr = session.getRemoteAddress();
		String str = addr.toString();
		String ip = str.substring(1, str.indexOf(":"));
		boolean isDeny = false;
		int dc = IPTools.checkIPList(denyIp, ip);
		int ac = IPTools.checkIPList(allowIp, ip);
//		System.out.println(denyIp);
//		System.out.println(allowIp);
//		System.out.println(ip);
//		System.out.println(dc);
//		System.out.println(ac);
		//配置了禁用IP，并且在在列表中
		if(dc == IPTools.IN_RANGE){
			isDeny = true;
		}
		
		//配置了允许IP列表，并且不在列表中
		if(ac == IPTools.OVER_RANGE){
			isDeny = true;
		}

		// 有配置禁用IP
//		if (null != denyIp && !"".equals(denyIp)) {
//			// 如果IP在限制列表中，则置为true
//			if (denyIp.indexOf(ip) != -1) {
//				isDeny = true;
//			}
//			
//		}
//
//		if (null != allowIp && !"".equals(allowIp)) {
//			// 如果IP在授权列表中，并且在限制列表中，则置为true
//			// 如果IP未在授权列表中，则置为true
//			if ((allowIp.indexOf(ip) != -1 && true == isDeny)
//					|| allowIp.indexOf(ip) == -1) {
//				isDeny = true;
//			}
//			
//		}

		if (true == isDeny) {
			System.out.println("Reject the request: " + str);
			L.info("FS", FileServerIoHandler.class, "sessionOpened()", "INFO",
					"Reject the request: " + str);

			FileEntity entity = new FileEntity();

			entity.setCmd(Commands.REJECTED);
			session.write(entity);
			session.close(true);
		} else {
			System.out.println("Client Connected: " + str);
			L.info("FS", FileServerIoHandler.class, "sessionOpened()", "INFO",
					"Client Connected: " + str);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.core.service.IoHandlerAdapter#sessionClosed(org.apache
	 * .mina.core.session.IoSession)
	 */
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		String str = session.getRemoteAddress().toString();
		System.out.println("Client Disconnected: " + str);
		L.info("FS", FileServerIoHandler.class, "sessionClosed()", "INFO",
				"Client Disconnected: " + str);

		if (session.isConnected()) {
			session.close(false).awaitUninterruptibly();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.core.service.IoHandlerAdapter#messageReceived(org.apache
	 * .mina.core.session.IoSession, java.lang.Object)
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		// TODO Auto-generated method stub

		FileEntity entity = (FileEntity) message;
		int cmd = entity.getCmd();
		String str = session.toString();

		System.out.println("Server Received(" + cmd + "): " + str);
		L.info("FS", FileServerIoHandler.class, "sessionClosed()", "INFO",
				"Server Received(" + cmd + "): " + str);

		if (cmd == Commands.UPLOAD) {
			String remotePath = entity.getRemoteAbsolutePath();
			BufferedInputStream in = entity.getBufferedInputStream();
			BufferedOutputStream out = null;
			int offset = 0;
			byte[] buffer = new byte[MinaServerConfig
					.getIntValue("buffer_size")];
			String tmpPath = remotePath.substring(0,
					remotePath.lastIndexOf("/"));
			// System.out.println("r: " + remotePath);
			// System.out.println("t: " + tmpPath);
			File tmp = new File(tmpPath);

			if (!tmp.exists()) {
				tmp.mkdirs();
			}

			tmp = null;

			File file = new File(remotePath);

			out = new BufferedOutputStream(new FileOutputStream(file));

			while (-1 != (offset = in.read(buffer))) {
				out.write(buffer, 0, offset);
			}

			out.flush();

			out.close();
			in.close();

			entity.setBufferedInputStream(null);
			entity.setCmd(Commands.QUIT);
			session.write(entity);
		} else if (cmd == Commands.UPLOADING) {
			// System.out.println("receive bytes: " + entity.getRemainBytes() +
			// "/" + entity.getTotalBytes());
			// System.out.println(entity);
			session.write(entity);
		}
	}

}

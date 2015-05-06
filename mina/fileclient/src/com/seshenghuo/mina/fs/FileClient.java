/**
 * 
 */
package com.seshenghuo.mina.fs;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.seshenghuo.logger.L;
import com.seshenghuo.mina.fs.codec.FileCodecFactory;

/**
 * @author carlli
 * 
 */
public class FileClient extends IoHandlerAdapter {
	private ArrayList<FileEntity> entites = new ArrayList<FileEntity>();

	private RemainingNotify remainNotify = null;
	private IoConnector connector;
	ConnectFuture future;
	private IoSession session;

	private String serverHost = "127.0.0.1";
	private int serverPort = 8080;

	/**
	 * 
	 */
	public FileClient() {
		// TODO Auto-generated constructor stub
	}

	public FileClient(String serverHost, int serverPort) {
		this.serverHost = serverHost;
		this.serverPort = serverPort;
	}

	/**
	 * @return the serverHost
	 */
	public String getServerHost() {
		return serverHost;
	}

	/**
	 * @param host
	 *            the serverHost to set
	 */
	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	/**
	 * @return the serverPort
	 */
	public int getServerPort() {
		return serverPort;
	}

	/**
	 * @param port
	 *            the serverPort to set
	 */
	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public void setServer(String host, int port) {
		this.setServerHost(host);
		this.setServerPort(port);
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
		String str = session.getRemoteAddress().toString();
		System.out.println("Connected Server: " + str);
		L.info("FSC", FileClient.class, "sessionOpened()", "INFO",
				"Connected Server: " + str);
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
		System.out.println("Disconnected Server: " + str);
		L.info("FSC", FileClient.class, "sessionClosed()", "INFO",
				"Disconnected Server: " + str);

		this.close();
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

		L.info("FSC", FileClient.class, "messageReceived()", "INFO",
				"Client Received(" + cmd + "): " + str);

		remainNotify.message(this, cmd, entity.getRemainBytes(),
				entity.getTotalBytes());

		// if(cmd == Commands.QUIT){
		// System.out.println("send bytes: " + entity.getRemainBytes() + "/" +
		// entity.getTotalBytes());
		// this.close();
		// this.disconnect();
		// }else if(cmd == Commands.UPLOADING){
		// System.out.println("send bytes: " + entity.getRemainBytes() + "/" +
		// entity.getTotalBytes());
		// }
	}

	public void mount(FileEntity entity) {
		// this.entity = entity;
		this.entites.add(entity);
	}

	private void send(IoSession session) {
		FileEntity entity = null;

		for (int i = 0, size = entites.size(); i < size; i++) {
			entity = entites.get(i);
			System.out.println("Client Send: " + entity.toString());
			L.info("FSC", FileClient.class, "send()", "INFO", "Client Send: "
					+ entity.toString());
			session.write(entity);
		}
	}

	public void sendData() {
		if (isConnected()) {
			send(session);
		} else {
			System.out.println("not connected");
			L.info("FSC", FileClient.class, "sendData()", "INFO",
					"not connected");
		}
	}

	public void connect() {
		int port = this.getServerPort();
		String host = this.getServerHost();

		connector = new NioSocketConnector();
		DefaultIoFilterChainBuilder filter = connector.getFilterChain();

		filter.addLast("codec", new ProtocolCodecFilter(new FileCodecFactory(
				true)));

		connector.setHandler(this);
		future = connector.connect(new InetSocketAddress(host, port));
		future.awaitUninterruptibly();

		if (future.isConnected()) {
			session = future.getSession();
		} else {
			System.out.println("not connected");
			L.info("FSC", FileClient.class, "connect()", "INFO",
					"not connected");
		}
	}

	public boolean isConnected() {
		if (null != session && session.isConnected()) {
			return true;
		}

		return false;
	}

	public void disconnect() {
		L.info("FSC", FileClient.class, "disconnect()", "INFO",
				"ready disconnect");

		if (null != connector && !connector.isDisposed()) {
			connector.dispose(false);
			L.info("FSC", FileClient.class, "disconnect()", "INFO",
					"fire disconnect");
		}
	}

	public void close() {
		L.info("FSC", FileClient.class, "close()", "INFO",
				"ready session close");

		if (null != session && session.isConnected()) {
			session.close(false).awaitUninterruptibly(300);

			L.info("FSC", FileClient.class, "close()", "INFO",
					"fire session close");
		}
	}

	public FileEntity newEntity(int cmd, String remotePath, String localPath,
			String url) {
		BufferedInputStream in = null;

		try {
			in = new BufferedInputStream(new FileInputStream(
					new File(localPath)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			L.error("FSC", FileClient.class, "newEntity()",
					"FileNotFoundException", e.getMessage());
		}

		return newEntity(cmd, remotePath, localPath, url, in);
	}

	public FileEntity newEntity(int cmd, String remotePath, String localPath,
			String url, File file) {
		BufferedInputStream in = null;

		try {
			in = new BufferedInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			L.error("FSC", FileClient.class, "newEntity()",
					"FileNotFoundException", e.getMessage());
		}

		return newEntity(cmd, remotePath, localPath, url, in);
	}

	public FileEntity newEntity(int cmd, String remotePath, String localPath,
			String url, BufferedInputStream in) {
		FileEntity entity = new FileEntity();

		entity.setCmd(cmd);
		entity.setRemoteAbsolutePath(remotePath);
		entity.setLocalAbsolutePath(localPath);
		entity.setLocation(url);
		entity.setTimeStamp(System.currentTimeMillis());

		try {
			entity.setFileSize(in.available());
			entity.setBufferedInputStream(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			L.error("FSC", FileClient.class, "newEntity()", "IOException",
					e.getMessage());
		}

		return entity;
	}

	public void injectNotify(RemainingNotify rn) {
		this.remainNotify = rn;
	}

	// public static void main(String[] args){
	// FileClient client = new FileClient();
	// //FileEntity entity1 = client.newEntity(Commands.UPLOAD,
	// "c:\\sesdk1.zip", "d:\\sesdk.zip", "http://www.seshenghuo.com/");
	// FileEntity entity2 = client.newEntity(Commands.UPLOAD, "c:\\A1.jpg",
	// "d:\\A.jpg", "http://www.seshenghuo.com/");
	// //FileEntity entity3 = client.newEntity(Commands.UPLOAD,
	// "c:\\全国省分城市列表1.xml.xml", "d:\\全国省分城市列表.xml.xml",
	// "http://www.seshenghuo.com/");
	//
	// //client.mount(entity1);
	// client.mount(entity2);
	// //client.mount(entity3);
	// client.connect();
	// }

}

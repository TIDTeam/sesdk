/**
 * 
 */
package com.seshenghuo.mina.fs;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.seshenghuo.logger.L;
import com.seshenghuo.mina.MinaServerConfig;
import com.seshenghuo.mina.fs.codec.FileCodecFactory;

/**
 * @author carlli
 * 
 */
public class FileServer {

	/**
	 * @throws IOException
	 * 
	 */
	public FileServer() {
		// TODO Auto-generated constructor stub

	}

	public void startup() {
		int cpus = Runtime.getRuntime().availableProcessors();
		IoAcceptor acceptor = new NioSocketAcceptor(cpus + 1);
		DefaultIoFilterChainBuilder filter = acceptor.getFilterChain();

		filter.addLast("codec", new ProtocolCodecFilter(new FileCodecFactory(
				false)));

		FileServerIoHandler handler = new FileServerIoHandler();

		acceptor.setHandler(handler);

		int port = MinaServerConfig.getIntValue("port");
		// System.out.println("startup............");
		try {
			acceptor.bind(new InetSocketAddress(port));
			System.out.println("File Server Listen: " + port);
			L.info("FS", FileServer.class, "startup()", "INFO",
					"File Server Listen: " + port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("File Server Startup failed: " + e.getMessage());
			L.info("FS", FileServer.class, "startup()", "INFO",
					"File Server Startup failed: " + e.getMessage());
			System.exit(0);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FileServer server = new FileServer();

		server.startup();
	}

}

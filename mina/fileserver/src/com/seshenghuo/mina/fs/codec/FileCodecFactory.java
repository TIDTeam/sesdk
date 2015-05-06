/**
 * 
 */
package com.seshenghuo.mina.fs.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * @author carlli
 * 
 */
public class FileCodecFactory implements ProtocolCodecFactory {
	private ProtocolEncoder encoder;
	private ProtocolDecoder decoder;

	/**	
	 * 
	 */
	public FileCodecFactory(boolean client) {
		// TODO Auto-generated constructor stub
		if (client) {
			encoder = new FileRequestEncoder();
			decoder = new FileResponseDecoder();
		} else {
			encoder = new FileResponseEncoder();
			decoder = new FileRequestDecoder();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.filter.codec.ProtocolCodecFactory#getEncoder(org.apache
	 * .mina.core.session.IoSession)
	 */
	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		return encoder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.filter.codec.ProtocolCodecFactory#getDecoder(org.apache
	 * .mina.core.session.IoSession)
	 */
	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		return decoder;
	}

}

/**
 * 
 */
package com.seshenghuo.mina.fs.codec;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.seshenghuo.logger.L;
import com.seshenghuo.mina.MinaCodecConfig;
import com.seshenghuo.mina.fs.FileEntity;
import com.seshenghuo.util.Convert;

/**
 * @author carlli
 * 
 */
public class FileRequestEncoder implements ProtocolEncoder {

	/**
	 * 
	 */
	public FileRequestEncoder() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.filter.codec.ProtocolEncoder#encode(org.apache.mina.core
	 * .session.IoSession, java.lang.Object,
	 * org.apache.mina.filter.codec.ProtocolEncoderOutput)
	 */
	@Override
	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		// TODO Auto-generated method stub
		FileEntity entity = (FileEntity) message;
		int capacity = MinaCodecConfig.getIntValue("iobuffer_size");
		Charset charset = Charset.forName(MinaCodecConfig.getStringValue(
				"charset", "UTF-8"));
		CharsetEncoder encoder = charset.newEncoder();
		IoBuffer buf = IoBuffer.allocate(capacity, false);
		buf.setAutoExpand(true);

		int intBytesLength = Convert.INT_BYTES_LENGTH;
		int cmd = entity.getCmd();

		byte[] cmdBytes = Convert.int2bytes(cmd);
		String rap = entity.getRemoteAbsolutePath();
		// System.out.println("Encode RAP: " + rap);
		L.info("CODEC", FileRequestEncoder.class, "encode()", "INFO",
				"Request Encoder(" + cmd + "): RAP=>" + rap);
		int rapLength = rap.getBytes(charset).length;
		String lap = entity.getLocalAbsolutePath();
		// System.out.println("Encode LAP: " + lap);
		L.info("CODEC", FileRequestEncoder.class, "encode()", "INFO",
				"Request Encoder(" + cmd + "): LAP=>" + lap);
		int lapLength = lap.getBytes(charset).length;
		String loc = entity.getLocation();
		// System.out.println("Encode LOC: " + loc);
		L.info("CODEC", FileRequestEncoder.class, "encode()", "INFO",
				"Request Encoder(" + cmd + "): LOC=>" + loc);
		int locLength = loc.getBytes(charset).length;
		byte[] tmBytes = Convert.long2bytes(entity.getTimeStamp());
		int tmLength = tmBytes.length;
		byte[] fsizeBytes = Convert.int2bytes(entity.getFileSize());
		int fsizeLength = fsizeBytes.length;
		byte[] remainBytes = Convert.int2bytes(entity.getRemainBytes());
		int rbLength = remainBytes.length;
		byte[] bytes = getBytes(entity.getBufferedInputStream());
		int bytesLength = bytes.length;
		L.info("CODEC", FileRequestEncoder.class, "encode()", "INFO",
				"Request Encoder(" + cmd + "): bytes size is " + bytesLength);

		byte[] pckSize = Convert.int2bytes(intBytesLength + rapLength
				+ intBytesLength + lapLength + intBytesLength + locLength
				+ intBytesLength + tmLength + intBytesLength + fsizeLength
				+ intBytesLength + rbLength + intBytesLength + bytesLength);

		buf.put(pckSize);
		buf.put(cmdBytes);
		buf.put(Convert.int2bytes(rapLength));
		buf.putString(rap, encoder);
		buf.put(Convert.int2bytes(lapLength));
		buf.putString(lap, encoder);
		buf.put(Convert.int2bytes(locLength));
		buf.putString(loc, encoder);
		buf.put(Convert.int2bytes(tmLength));
		buf.put(tmBytes);
		buf.put(Convert.int2bytes(fsizeLength));
		buf.put(fsizeBytes);
		buf.put(Convert.int2bytes(rbLength));
		buf.put(remainBytes);
		buf.put(Convert.int2bytes(bytesLength));
		buf.put(bytes);

		// System.out.println("BBBBBBBBBBBBBB: " +
		// NumberUtil.bytes2int(remainBytes));
		String s = session.toString();
		System.out.println("Request Encoder(" + entity.getCmd() + "): " + s);
		L.info("CODEC", FileRequestEncoder.class, "encode()", "INFO",
				"Request Encoder(" + entity.getCmd() + "): " + s);
		buf.flip();
		out.write(buf);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.filter.codec.ProtocolEncoder#dispose(org.apache.mina.
	 * core.session.IoSession)
	 */
	@Override
	public void dispose(IoSession session) throws Exception {
		// TODO Auto-generated method stub

		if (null != session && session.isConnected()) {
			session.close(false).awaitUninterruptibly();
		}
	}

	private byte[] getBytes(Object buf) throws IOException {
		int bufferSize = MinaCodecConfig.getIntValue("buffer_size");
		if (buf instanceof BufferedInputStream) {
			BufferedInputStream in = (BufferedInputStream) buf;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] bufferBytes = new byte[bufferSize];
			byte[] bytes = null;
			int data = 0;

			while (-1 != (data = in.read(bufferBytes))) {
				bos.write(bufferBytes, 0, data);
			}

			bos.close();
			in.close();

			bytes = bos.toByteArray();
			return bytes;
		}

		return new byte[0];
	}

}

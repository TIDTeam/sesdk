/**
 * 
 */
package com.seshenghuo.mina.fs.codec;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.seshenghuo.logger.L;
import com.seshenghuo.mina.MinaCodecConfig;
import com.seshenghuo.mina.fs.Commands;
import com.seshenghuo.mina.fs.FileEntity;
import com.seshenghuo.util.Convert;

/**
 * @author carlli
 * 
 */
public class FileResponseDecoder extends CumulativeProtocolDecoder {
	/**
	 * 
	 */
	public FileResponseDecoder() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.mina.filter.codec.CumulativeProtocolDecoder#doDecode(org.apache
	 * .mina.core.session.IoSession, org.apache.mina.core.buffer.IoBuffer,
	 * org.apache.mina.filter.codec.ProtocolDecoderOutput)
	 */
	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		// TODO Auto-generated method stub

		FileEntity entity = new FileEntity();
		FileEntity remainEntity = new FileEntity();
		Charset charset = Charset.forName(MinaCodecConfig.getStringValue(
				"charset", "UTF-8"));

		int cmd = 0;
		String rap = "";
		String lap = "";
		String loc = "";
		long tm = 0L;
		int size = 0;
		int remain = 0;
		BufferedInputStream bin = null;

		int pckSize = 0;

		if (in.remaining() > (Convert.INT_BYTES_LENGTH * 2)) {
			byte[] pckBytes = new byte[Convert.INT_BYTES_LENGTH];
			byte[] cmdBytes = new byte[Convert.INT_BYTES_LENGTH];
			in.mark();
			in.get(pckBytes);
			pckSize = Convert.bytes2int(pckBytes);
			in.get(cmdBytes);
			cmd = Convert.bytes2int(cmdBytes);
			String s = session.toString();

			if (pckSize > in.remaining()) {
				// System.out.println("[" + (((double)in.remaining() /
				// (double)pckSize) * 100) + "%]" + in.remaining());
				if (cmd == Commands.DOWNLOAD || cmd == Commands.DOWNLOADING) {
					cmd = Commands.DOWNLOADING;

					remainEntity.setCmd(cmd);
					remainEntity.setTotalBytes(pckSize);
					remainEntity.setRemainBytes(in.remaining());
					// System.out.println("------ " +
					// remainEntity.getRemainBytes());
					out.write(remainEntity);
				}
				System.out.println("Response Decoder(" + cmd + "): "
						+ session.toString());
				L.info("CODEC", FileResponseDecoder.class, "doDecode()",
						"INFO", "Response Decoder(" + cmd + "): " + s);
				in.reset();
				return false;
			} else {
				if (cmd == Commands.DOWNLOADING) {
					cmd = Commands.DOWNLOAD;
				}
				System.out.println("Response Decoder(" + cmd + "): "
						+ session.toString());
				L.info("CODEC", FileResponseDecoder.class, "doDecode()",
						"INFO", "Response Decoder(" + cmd + "): " + s);
				// cmd = getIntValue(in, charset);
				rap = getStringVaule(in, charset);
				// System.out.println("rap: " + rap);
				L.info("CODEC", FileResponseDecoder.class, "doDecode()",
						"INFO", "Response Decoder(" + cmd + "): RAP=>" + rap);
				lap = getStringVaule(in, charset);
				// System.out.println("lap: " + lap);
				L.info("CODEC", FileResponseDecoder.class, "doDecode()",
						"INFO", "Response Decoder(" + cmd + "): LAP=>" + lap);
				loc = getStringVaule(in, charset);
				// System.out.println("loc: " + loc);
				L.info("CODEC", FileResponseDecoder.class, "doDecode()",
						"INFO", "Response Decoder(" + cmd + "): LOC=>" + loc);
				tm = getLongValue(in, charset);
				// System.out.println("tm: " + tm);
				L.info("CODEC", FileResponseDecoder.class, "doDecode()",
						"INFO", "Response Decoder(" + cmd + "): TM=>" + tm);
				size = getIntValue(in, charset);
				// System.out.println("size: " + size);
				L.info("CODEC", FileResponseDecoder.class, "doDecode()",
						"INFO", "Response Decoder(" + cmd + "): SIZE=>" + size);
				remain = getIntValue(in, charset);
				// System.out.println("remain: " + remain);
				L.info("CODEC", FileResponseDecoder.class, "doDecode()",
						"INFO", "Response Decoder(" + cmd + "): REMAIN=>"
								+ remain);
				bin = getBufferedInputStream(in, charset);

				// remain = in.remaining();
				entity.setCmd(cmd);
				entity.setRemoteAbsolutePath(rap);
				entity.setLocalAbsolutePath(lap);
				entity.setLocation(loc);
				entity.setTimeStamp(tm);
				entity.setFileSize(size);
				entity.setRemainBytes(remain <= 0 ? size : remain);
				entity.setBufferedInputStream(bin);

				out.write(entity);

				L.info("CODEC", FileResponseDecoder.class, "doDecode()",
						"INFO", "Response Decoder(" + cmd + "): write message");

				if (in.remaining() > 0) {
					return true;
				}
			}
		}

		return false;
	}

	private byte[] getBytes(IoBuffer in, Charset charset) {
		byte[] pckBytes = new byte[Convert.INT_BYTES_LENGTH];
		in.get(pckBytes);
		int bodyLength = Convert.bytes2int(pckBytes);
		byte[] bodyBytes = new byte[bodyLength];
		in.get(bodyBytes, 0, bodyLength);

		return bodyBytes;
	}

	private String getStringVaule(IoBuffer in, Charset charset) {
		return new String(getBytes(in, charset), charset);
	}

	private int getIntValue(IoBuffer in, Charset charset) {
		byte[] bytes = getBytes(in, charset);

		return Convert.bytes2int(bytes);
	}

	private long getLongValue(IoBuffer in, Charset charset) {
		byte[] bytes = getBytes(in, charset);

		return Convert.bytes2long(bytes);
	}

	private BufferedInputStream getBufferedInputStream(IoBuffer in,
			Charset charset) {
		byte[] pckBytes = new byte[Convert.INT_BYTES_LENGTH];
		in.get(pckBytes);
		int bodyLength = Convert.bytes2int(pckBytes);
		byte[] bodyBytes = new byte[bodyLength];
		in.get(bodyBytes, 0, bodyLength);

		return new BufferedInputStream(new ByteArrayInputStream(bodyBytes, 0,
				bodyLength));
	}

}

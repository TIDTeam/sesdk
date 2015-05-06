/**
 * 
 */
package com.seshenghuo.net;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.seshenghuo.logger.L;
import com.seshenghuo.messagecode.Alarm;
import com.seshenghuo.util.Message;

/**
 * @author carlli
 * 
 */
public class HttpRequest {
	public final static String GET = "GET";
	public final static String POST = "POST";

	private HttpURLConnection httpConn = null;

	private String requestURL = "";
	private String method = "GET";
	private String data = "";
	private String charset = "utf-8";
	private String content = "";
	private boolean useCache = false;
	private int httpStatus = HttpURLConnection.HTTP_UNAVAILABLE;
	private String httpStatusText = Message.getMessage(Alarm.Level.SYSTEM
			+ Alarm.Type.HTTP + Alarm.Module.HTTP + "0" + httpStatus);

	private ArrayList<HttpRequestHeader> headers = new ArrayList<HttpRequestHeader>();

	/**
	 * @throws MalformedURLException
	 * 
	 */
	public HttpRequest(String url) {
		// TODO Auto-generated constructor stub
		this.requestURL = url;
	}

	/**
	 * @throws MalformedURLException
	 * 
	 */
	public HttpRequest() {
		// TODO Auto-generated constructor stub
	}

	private String __GET(URL url, String method, String data, String charset)
			throws MalformedURLException {
		int responseCode = httpStatus;
		String responseMessage = httpStatusText;
		InputStream in = null;
		BufferedReader reader = null;
		String content = null;
		StringBuffer buffer = new StringBuffer();
		HttpRequestHeader header = null;

		this.setMethod(method);
		this.setData(data);
		this.setCharset(charset);

		try {
			this.httpConn = (HttpURLConnection) url.openConnection();

			try {
				this.httpConn.setDoOutput(true);
			} catch (IllegalStateException ise) {
				content = ise.getMessage();
				L.error(HttpRequest.class,
						"__GET(URL url, String method, String data, String charset)::setDoInput()",
						"IllegalStateException", ise.getMessage());
			}

			for (int i = 0, j = headers.size(); i < j; i++) {
				header = headers.get(i);

				this.httpConn.setRequestProperty(header.getName(),
						header.getValue());
				header = null;
			}

			this.httpConn.setRequestMethod(this.getMethod());
			this.httpConn.setUseCaches(this.isUseCache());

			responseCode = this.httpConn.getResponseCode();
			responseMessage = this.httpConn.getResponseMessage();

			in = (InputStream) this.httpConn.getInputStream();

			reader = new BufferedReader(new InputStreamReader(in,
					this.getCharset()));

			this.httpConn.connect();

			while (null != (content = reader.readLine())) {
				buffer.append(content);
			}
			content = buffer.toString();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			L.error(HttpRequest.class,
					"__GET(URL url, String method, String data, String charset)",
					"IOException", e.getMessage());
		} finally {
			// this.httpConn.disconnect();
			try {
				if (null != in) {
					in.close();
				}
				if (null != reader) {
					reader.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				L.error(HttpRequest.class,
						"__GET(URL url, String method, String data, String charset)",
						"IOException", e.getMessage());
			}
			this.httpConn = null;
			url = null;
		}

		this.setHttpStatus(responseCode);
		this.setHttpStatusText(responseMessage);

		switch (responseCode) {
		case HttpURLConnection.HTTP_OK:
			// case HttpURLConnection.HTTP_MOVED_TEMP:
		case HttpURLConnection.HTTP_NOT_MODIFIED:
			break;
		default:
			content = "Server Returned HTTP Response Code : " + responseCode
					+ " - " + responseMessage;
			break;
		}

		return content;
	}

	private String __POST(URL url, String method, String data, String charset)
			throws MalformedURLException {
		int responseCode = httpStatus;
		String responseMessage = httpStatusText;
		InputStream in = null;
		DataOutputStream dos = null;
		BufferedReader reader = null;
		String content = null;
		StringBuffer buffer = new StringBuffer();
		HttpRequestHeader header = null;

		this.setMethod(method);
		this.setData(data);
		this.setCharset(charset);

		try {
			this.httpConn = (HttpURLConnection) url.openConnection();

			try {
				this.httpConn.setDoOutput(true);
			} catch (IllegalStateException ise) {
				content = ise.getMessage();
				L.error(HttpRequest.class,
						"__POST(URL url, String method, String data, String charset)::setDoInput()",
						"IllegalStateException", ise.getMessage());
			}

			for (int i = 0, j = headers.size(); i < j; i++) {
				header = headers.get(i);

				this.httpConn.setRequestProperty(header.getName(),
						header.getValue());
				header = null;
			}

			this.httpConn.setRequestMethod(this.getMethod());
			this.httpConn.setUseCaches(this.isUseCache());
			this.httpConn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			dos = new DataOutputStream(this.httpConn.getOutputStream());
			dos.writeBytes(this.getData());

			responseCode = this.httpConn.getResponseCode();
			responseMessage = this.httpConn.getResponseMessage();

			in = (InputStream) httpConn.getInputStream();

			reader = new BufferedReader(new InputStreamReader(in,
					this.getCharset()));

			this.httpConn.connect();

			while (null != (content = reader.readLine())) {
				buffer.append(content);
			}
			content = buffer.toString();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			L.error(HttpRequest.class,
					"__POST(URL url, String method, String data, String charset)",
					"IOException", e.getMessage());
		} finally {
			// this.httpConn.disconnect();
			try {
				if (null != in) {
					in.close();
				}
				if (null != dos) {
					dos.flush();
					dos.close();
				}
				if (null != reader) {
					reader.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				L.error(HttpRequest.class,
						"__POST(URL url, String method, String data, String charset)",
						"IOException", e.getMessage());
			}
			this.httpConn = null;
			url = null;
		}
		this.setHttpStatus(responseCode);
		this.setHttpStatusText(responseMessage);

		switch (responseCode) {
		case HttpURLConnection.HTTP_OK:
			// case HttpURLConnection.HTTP_MOVED_TEMP:
		case HttpURLConnection.HTTP_NOT_MODIFIED:
			break;
		default:
			content = "Server Returned HTTP Response Code : " + responseCode
					+ " - " + responseMessage;
			break;
		}
		return content;
	}

	private String getRealRequestURL() {
		String url = this.getRequestURL();
		char ch = (url.indexOf("?") == -1 ? '?' : '&');

		return (url + ch + this.getData());
	}

	public String sendRequest(String method, String data, String charset)
			throws MalformedURLException {
		String content = "";

		URL url = null;
		if (HttpRequest.GET.equals(method)) {
			url = new URL(this.getRealRequestURL());
			content = this.__GET(url, method, data, charset);
		} else if (HttpRequest.POST.equals(method)) {
			url = new URL(this.getRequestURL());
			content = this.__POST(url, method, data, charset);
		} else {
			this.setMethod(method);
			this.setData(data);
			this.setCharset(charset);
			this.setHttpStatus(HttpURLConnection.HTTP_BAD_METHOD);
			this.setHttpStatusText(Message.getMessage(Alarm.Level.LOGIC
					+ Alarm.Type.HTTP + Alarm.Module.HTTP + "0"
					+ HttpURLConnection.HTTP_BAD_METHOD));

			content = this.getHttpStatusText();
		}

		this.setContent(content);

		L.info(HttpRequest.class,
				"sendRequest(String method, String data, String charset)",
				this.getHttpStatus() + "", this.getHttpStatusText());
		L.info(HttpRequest.class,
				"sendRequest(String method, String data, String charset)",
				this.getHttpStatus() + "", this.getCharset());
		L.info(HttpRequest.class,
				"sendRequest(String method, String data, String charset)",
				this.getHttpStatus() + "",
				this.getMethod() + " " + this.getRealRequestURL());
		L.info(HttpRequest.class,
				"sendRequest(String method, String data, String charset)",
				this.getHttpStatus() + "", this.getData());

		return content;
	}

	public String sendRequest(String method, String data)
			throws MalformedURLException {
		return this.sendRequest(method, data, this.getCharset());
	}

	public String sendRequest(String method) throws MalformedURLException {
		return this.sendRequest(method, this.getData(), this.getCharset());
	}

	public String sendRequest() throws MalformedURLException {
		return this.sendRequest(this.getMethod(), this.getData(),
				this.getCharset());
	}

	/**
	 * @return the requestURL
	 */
	public String getRequestURL() {
		return requestURL;
	}

	/**
	 * @param requestURL
	 *            the requestURL to set
	 */
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getMethod() {
		return this.method;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getData() {
		return this.data;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getCharset() {
		return this.charset;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the httpStatus
	 */
	public int getHttpStatus() {
		return httpStatus;
	}

	/**
	 * @param httpStatus
	 *            the httpStatus to set
	 */
	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	/**
	 * @return the httpStatusText
	 */
	public String getHttpStatusText() {
		return httpStatusText;
	}

	/**
	 * @param httpStatusText
	 *            the httpStatusText to set
	 */
	public void setHttpStatusText(String httpStatusText) {
		this.httpStatusText = httpStatusText;
	}

	/**
	 * @return the useCache
	 */
	public boolean isUseCache() {
		return useCache;
	}

	/**
	 * @param useCache
	 *            the useCache to set
	 */
	public void setUseCache(boolean useCache) {
		this.useCache = useCache;
	}

	public void setRequestHeader(String name, String value) {
		HttpRequestHeader header = new HttpRequestHeader();
		header.setName(name);
		header.setValue(value);

		headers.add(header);
	}

	public void setRequestHeaders(ArrayList<HttpRequestHeader> _headers) {
		headers = _headers;
	}

}

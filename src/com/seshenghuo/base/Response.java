package com.seshenghuo.base;

import com.google.gson.Gson;

public class Response<R> {

	public static final String RESP_KEY = "recordset";

	private int page = 0;
	private int pageSize = 20;
	private int recordSize = 0;
	private String code = "-1";
	private String message = "Unknown Error";
	private R response = null;

	public Response() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the recordSize
	 */
	public int getRecordSize() {
		return recordSize;
	}

	/**
	 * @param recordSize
	 *            the recordSize to set
	 */
	public void setRecordSize(int recordSize) {
		this.recordSize = recordSize;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	public void setCode(String level, String type, String module, String code) {
		setCode(level + type + module + code);
	}

	public void setCode(String level, String type, String module, int code) {
		setCode(level + type + module + code);
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the response
	 */
	public R getResponse() {
		return response;
	}

	/**
	 * @param response
	 *            the response to set
	 */
	public void setResponse(R response) {
		this.response = response;
	}
	
	public Response<Object> toObject(){
		Response<Object> resp = new Response<Object>();
		
		resp.setCode(this.getCode());
		resp.setMessage(this.getMessage());
		resp.setPage(this.getPage());
		resp.setPageSize(this.getPageSize());
		resp.setRecordSize(this.getRecordSize());
		resp.setResponse(this.getResponse());
		
		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		Gson g = new Gson();
		String json = g.toJson(this);

		g = null;

		return json;
	}

}

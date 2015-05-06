/**
 * 
 */
package com.seshenghuo.base;

/**
 * @author carlli
 * 
 */
public interface Base<Bean> {
	public static final String PAGE = "page";
	public static final String PAGESIZE = "pageSize";
	public static final String RETCODE = "retcode";
	public static final String SUBCODE = "subcode";
	public static final String RETMSG = "retmsg";
	public static final String RECORDSIZE = "recordSize";
	public static final String RESULT = "result";

	public Response<?> update(final Bean bean);

	public Response<?> delete(final Bean bean);

	public Response<?> add(final Bean bean);

	public Response<?> query(final Bean bean);

	public Response<?> query(final Bean bean, final int pageIndex,
			final int pageSize);
}

/**
 * 
 */
package com.seshenghuo.ui.base;

import com.seshenghuo.base.Base;
import com.seshenghuo.base.Response;
import com.seshenghuo.database.DBUtil;
import com.seshenghuo.ui.constant.Constant;

/**
 * @author carlli
 * 
 */
public abstract class AbstractBase<Bean> implements Base<Bean> {
	protected DBUtil db = new DBUtil(Constant.DB.DB_SESHENGHUO_V2);

	/**
	 * 
	 */
	public AbstractBase() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.base.Base#update(java.lang.Object)
	 */
	@Override
	public Response<?> update(final Bean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.base.Base#delete(java.lang.Object)
	 */
	@Override
	public Response<?> delete(final Bean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.base.Base#add(java.lang.Object)
	 */
	@Override
	public Response<?> add(final Bean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.base.Base#query(java.lang.Object, int, int)
	 */
	@Override
	public Response<?> query(final Bean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.seshenghuo.base.Base#query(java.lang.Object, int, int)
	 */
	@Override
	public Response<?> query(final Bean bean, final int pageIndex,
			final int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public Response<?> query(final int uniqueId) {
		return null;
	}

	public Response<?> query(final String uniqueKey) {
		return null;
	}

	public Response<?> query(final String keyword, final int pageIndex,
			final int pageSize) {
		return null;
	}
}

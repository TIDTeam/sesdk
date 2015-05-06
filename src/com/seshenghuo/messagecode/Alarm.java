/**
 * 
 */
package com.seshenghuo.messagecode;

/**
 * @author Administrator
 * 
 */
public interface Alarm {
	public static final String SUCCESS = "0";

	interface Level {
		public static final String SYSTEM = "1";
		public static final String LOGIC = "2";
		public static final String UI = "3";
	}

	interface Type {
		public static final String LOGIC = "01";
		public static final String SQL = "02";
		public static final String HTTP = "03";
		public static final String UI = "04";
	}

	interface Module {
		public static final String COMMAON = "0000";
		public static final String SQL = "0001";
		public static final String HTTP = "0002";
		public static final String UPLOAD = "0003";
		public static final String USER = "1001";
		public static final String POSTS = "1002";
	}

	interface Logic {
		public static final String SERVICE_CLOSED = "9999";
		public static final String LOGIN_TIMEOUT = "1000";
		public static final String LOGIN_VERIFY_FAILED = "1001";
		public static final String USER_EDU_BATCH_UPDATE_PART_FAILED = "0000";
		public static final String USER_EDU_BATCH_UPDATE_FAILED = "0001";
		public static final String USER_EDU_BATCH_ADD_PART_FAILED = "0002";
		public static final String USER_EDU_BATCH_ADD_FAILED = "0003";
		public static final String USER_EMP_BATCH_UPDATE_PART_FAILED = "0004";
		public static final String USER_EMP_BATCH_UPDATE_FAILED = "0005";
		public static final String USER_EMP_BATCH_ADD_PART_FAILED = "0006";
		public static final String USER_EMP_BATCH_ADD_FAILED = "0007";
	}
}

/**
 * @file DatabaseManager.java
 * @date Nov 2, 2006
 * @author lijun
 * @version 1.0.0
 */

package com.seshenghuo.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import com.seshenghuo.logger.L;
import com.seshenghuo.util.PropertiesUtil;

/**
 * @author lijun 数据库连接管理
 */
public class DBUtil {
	// 操作成功
	public static final int SUCCESS = 0;
	public static final int NOT_MATCHED = 1000;
	// 未知错误
	public static final int UNKNOWN_ERROR = 1001;
	// 数据库错误 SQL Exception
	public static final int SQL_EXCEPTION = 1002;
	public static final int SP_PARAMETER_EMPTY = 1003;

	private String username = "";
	private String password = "";
	private String url = "";
	private String driver = "";
	private String validationQuery = null;
	private int maxActive = 100;
	private int maxIdle = 30;
	private long maxWait = 30000;

	private Connection con = null;
	private BasicDataSource bds = null;
	
	private String dbname = null;
	private static HashMap<String, Connection> conn = new HashMap<String, Connection>();
	private static HashMap<String, BasicDataSource> ds = new HashMap<String, BasicDataSource>();

	public DBUtil(final String name) {
		this.init(name);
		this.dbname = name;
	}

	private String getItem(String name, String key) {
		return PropertiesUtil.getItem("db", "db." + name + "." + key);
	}

	private void init(final String name) {		
		if(!ds.isEmpty() && ds.containsKey(name) && null != (bds = ds.get(name))){
			L.info(DBUtil.class, "init()", "1", "Load History DataSource("
					+ name + ")");
		}else{		
			username = getItem(name, "username").trim();
			password = getItem(name, "password").trim();
			url = getItem(name, "url").trim();
			driver = getItem(name, "driverClassName").trim();
			validationQuery = getItem(name, "validationQuery").trim();
			maxActive = Integer.valueOf(getItem(name, "maxActive").trim())
					.intValue();
			maxIdle = Integer.valueOf(getItem(name, "maxIdle").trim()).intValue();
			maxWait = Long.valueOf(getItem(name, "maxWait").trim()).longValue();
	
			bds = new BasicDataSource();
			bds.setMaxActive(maxActive);
			bds.setMaxIdle(maxIdle);
			bds.setMaxWait(maxWait);
			bds.setDriverClassName(driver);
			bds.setUrl(url);
			bds.setUsername(username);
			bds.setPassword(password);
			bds.setValidationQuery(validationQuery);
			
			L.info(DBUtil.class, "init()", "0", "Initialization DataSource("
					+ name + ")");
		}		
	}

	public synchronized Connection getConnection() {
		if(!conn.isEmpty() && conn.containsKey(this.dbname) && (null != (con = conn.get(this.dbname)))){
			L.info(DBUtil.class, "getConnection()", "1", "Load History Connection");
		}else{
			try {
				con = bds.getConnection();
				
				L.info(DBUtil.class, "getConnection()", "0", "Get New Connection");
			} catch (SQLException e) {
				L.error(DBUtil.class, "getConnection()", e.getErrorCode() + "",
						e.getSQLState() + "; " + e.getMessage());
			}
		}
		return con;
	}

	public synchronized void close() {
		if (null != con) {
			try {
				con.close();
			} catch (SQLException e) {
				L.error(DBUtil.class, "close()", e.getErrorCode() + "",
						e.getSQLState() + "; " + e.getMessage());
			}
		}
	}

	public synchronized void destroy() {
		if (null != bds) {
			try {
				bds.close();
				
				if(ds.containsKey(this.dbname))
					ds.remove(this.dbname);
			} catch (SQLException e) {
				L.error(DBUtil.class, "destroy()", e.getErrorCode() + "",
						e.getSQLState() + "; " + e.getMessage());
			}
		}
	}

	public synchronized void close(final Statement stmt, final ResultSet rs) {
		try {
			if (null != rs && !rs.isClosed())
				rs.close();
		} catch (SQLException e) {
			L.error(DBUtil.class, "close(Statement stmt, ResultSet rs)",
					e.getErrorCode() + "",
					e.getSQLState() + "; " + e.getMessage());
		}
		try {
			if (null != stmt && !stmt.isClosed())
				stmt.close();
		} catch (SQLException e) {
			L.error(DBUtil.class, "close(Statement stmt, ResultSet rs)",
					e.getErrorCode() + "",
					e.getSQLState() + "; " + e.getMessage());
		}
	}
}

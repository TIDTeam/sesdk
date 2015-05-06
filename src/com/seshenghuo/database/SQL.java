/**
 * 
 */
package com.seshenghuo.database;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.seshenghuo.util.Util;
import com.seshenghuo.util.XMLUtil;

/**
 * @author lijun
 * 
 */
public class SQL {

	public static class CMD {
		public static final String SELECT = "select"; // 查询
		public static final String INSERT = "insert"; // 插入
		public static final String DELETE = "delete"; // 删除
		public static final String UPDATE = "update"; // 更新
	}

	public static class Types {
		public static final String SQL = "sql"; // 普通SQL
		public static final String SP = "sp"; // 存储过程
	}

	private static Document doc = null;

	public SQL() {

	}

	static {
		doc = XMLUtil.load(Util.getRuntimePath() + "sql.xml");
	}

	private static Node getNode(String xpath) {
		Node node = XMLUtil.getNode(doc, xpath);

		return node;
	}

	public static String getSQLCommand(String sqlId) {
		Node node = getNode("/mysql/sql[@id=\"" + sqlId + "\"]");
		Element el = null != node ? (Element) node : null;
		String cmd = null != el ? el.getAttribute("cmd") : null;

		return cmd;
	}

	public static String getSQLType(String sqlId) {
		Node node = getNode("/mysql/sql[@id=\"" + sqlId + "\"]");
		Element el = null != node ? (Element) node : null;
		String type = null != el ? el.getAttribute("type") : null;

		return type;
	}

	public static String getSQL(String sqlId) {
		Node node = getNode("/mysql/sql[@id=\"" + sqlId + "\"]");
		String text = null != node ? node.getTextContent() : null;
		String sql = null != text ? text.trim() : null;

		if (SQL.Types.SP.equalsIgnoreCase(getSQLType(sqlId))) {
			sql = "{CALL " + sql + "}";
		}

		return sql;
	}
}

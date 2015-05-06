/**
 * 
 */
package com.seshenghuo.util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.seshenghuo.logger.L;

/**
 * @author Administrator
 * 
 */
public class XMLUtil {
	private static DocumentBuilderFactory factory = null;

	/**
	 * 
	 */
	public XMLUtil() {
		// TODO Auto-generated constructor stub

	}

	static {
		factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
	}

	public static Document load(File file) {
		Document doc = null;
		DocumentBuilder builder = null;

		try {
			builder = factory.newDocumentBuilder();

			try {
				doc = builder.parse(file);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				L.error(XMLUtil.class, "load(File file)", "SAXException",
						e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				L.error(XMLUtil.class, "load(File file)", "IOException",
						e.getMessage());
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			L.error(XMLUtil.class, "load(File file)",
					"ParserConfigurationException", e.getMessage());
		}

		L.info(XMLUtil.class, "load(File file)", "INFO", file.getAbsolutePath());
		return doc;
	}

	public static Document load(String docPath) {
		File file = new File(docPath);

		return load(file);
	}

	public static Node getNode(Document doc, String xpath) {
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpathInstance = xpathFactory.newXPath();
		Node node = null;

		try {
			XPathExpression expression = xpathInstance.compile(xpath);
			node = (Node) expression.evaluate(doc, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			L.error(XMLUtil.class, "getNode(Document doc, String xpath)",
					"XPathExpressionException", e.getMessage());
		}

		L.info(XMLUtil.class, "getNode(Document doc, String xpath)", "INFO",
				xpath + "::" + node);

		return node;
	}

	public static NodeList getNodeList(Document doc, String xpath) {
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpathInstance = xpathFactory.newXPath();
		NodeList nodeList = new NodeList() {

			@Override
			public Node item(int index) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getLength() {
				// TODO Auto-generated method stub
				return 0;
			}
		};

		try {
			XPathExpression expression = xpathInstance.compile(xpath);
			nodeList = (NodeList) expression.evaluate(doc,
					XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			L.error(XMLUtil.class, "getNodeList(Document doc, String xpath)",
					"XPathExpressionException", e.getMessage());
		}

		L.info(XMLUtil.class, "getNodeList(Document doc, String xpath)",
				"INFO", xpath + "::" + nodeList.getLength());

		return nodeList;
	}

	public static String getNodeValue(Node node) {
		String value = "";
		String nodeName = "";

		try {
			value = (null != node ? node.getNodeValue() : "");
			nodeName = (null != node ? node.getNodeName() : "");
		} catch (DOMException e) {
			L.error(XMLUtil.class, "getNodeValue(Document doc, String xpath)",
					"DOMException", e.getMessage());
		}

		L.info(XMLUtil.class, "getNodeValue(Document doc, String xpath)",
				"INFO", nodeName + " = " + value);

		return (null == value ? "" : value).trim();
	}

	public static String getStringValue(Node node) {
		return getNodeValue(node);
	}

	public static int getIntValue(Node node) {
		String value = getNodeValue(node);
		int v = Integer.parseInt(value);

		return v;
	}

	public static long getLongValue(Node node) {
		String value = getNodeValue(node);
		long v = Long.parseLong(value);

		return v;
	}

	public static float getFloatValue(Node node) {
		String value = getNodeValue(node);
		float v = Float.parseFloat(value);

		return v;
	}

	public static double getDoubleValue(Node node) {
		String value = getNodeValue(node);
		double v = Double.parseDouble(value);

		return v;
	}

	public static boolean getBooleanValue(Node node) {
		String value = getNodeValue(node);

		boolean v = false;

		if ("true".equalsIgnoreCase(value) || "1".equalsIgnoreCase(value)) {
			v = true;
		}

		return v;
	}

	public static String getStringValue(Node node, String def) {
		String value = getNodeValue(node);

		if (null == value || "".equals(value)) {
			value = def;
		}

		return value;
	}

	public static int getIntValue(Node node, int def) {
		String value = getNodeValue(node);
		int v = def;

		if (null == value || "".equals(value)) {
			v = def;
		} else {
			try {
				v = Integer.parseInt(value);
			} catch (NumberFormatException e) {
				v = def;
			}
		}

		return v;
	}

	public static long getLongValue(Node node, long def) {
		String value = getNodeValue(node);
		long v = def;

		if (null == value || "".equals(value)) {
			v = def;
		} else {
			try {
				v = Long.parseLong(value);
			} catch (NumberFormatException e) {
				v = def;
			}
		}

		return v;
	}

	public static float getFloatValue(Node node, float def) {
		String value = getNodeValue(node);
		float v = def;

		if (null == value || "".equals(value)) {
			v = def;
		} else {
			try {
				v = Float.parseFloat(value);
			} catch (NumberFormatException e) {
				v = def;
			}
		}

		return v;
	}

	public static double getDoubleValue(Node node, double def) {
		String value = getNodeValue(node);
		double v = def;

		if (null == value || "".equals(value)) {
			v = def;
		} else {
			try {
				v = Double.parseDouble(value);
			} catch (NumberFormatException e) {
				v = def;
			}
		}

		return v;
	}
}

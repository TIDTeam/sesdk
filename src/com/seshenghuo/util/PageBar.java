/**
 * 
 */
package com.seshenghuo.util;

/**
 * @author carlli
 * 
 */
public class PageBar {

	/**
	 * 
	 */
	public PageBar() {
		// TODO Auto-generated constructor stub
	}

	private String appendParameter(String uri, int page) {
		String s = "";
		if (uri.indexOf("?") != -1) {
			s = uri + "&page=" + page;
		} else {
			s = uri + "?page=" + page;
		}
		return s;
	}

	private String getSelected(int page, int curIndex) {
		String cls = "";
		if (page == curIndex) {
			cls = " class=\"on\"";
		}
		return cls;
	}

	private String getPageItem(String uri, int page, int index) {
		return "<a href=\"" + appendParameter(uri, index) + "\""
				+ getSelected(page, index) + ">" + index + "</a>";
	}

	public String output(int page, int recordSize) {
		return output(page, recordSize, 20, "");
	}

	public String output(int page, int recordSize, int pageSize) {
		return output(page, recordSize, pageSize, "");
	}

	public String output(int page, int recordSize, String uri) {
		return output(page, recordSize, 20, uri);
	}

	public String output(int page, int recordSize, int pageSize, String uri) {
		int totalPage = (recordSize % pageSize != 0 ? (recordSize / pageSize) + 1
				: recordSize / pageSize);
		int radius = 4;
		int viewSize = radius * 2 + 1;
		int defSize = 9;
		int offset = 1;

		StringBuffer buf = new StringBuffer();
		buf.append("<!-- 翻页[[ -->");
		buf.append("<div class=\"page page-align-right\">");
		buf.append("  <div class=\"page-wrap\">");
		buf.append("    <div class=\"page-links\">");
		if (page == 1) {
			buf.append("      <a href=\"#none\" class=\"page-previous page-previous-disabled\">上一页</a>");
		} else {
			buf.append("      <a href=\"" + appendParameter(uri, page - 1)
					+ "\" class=\"page-previous\">上一页</a>");
		}
		buf.append("      <span class=\"page-number\">");
		if (totalPage <= defSize + 1) {
			for (int i = 1; i <= totalPage; i++) {
				buf.append(getPageItem(uri, page, i));
			}
		} else {
			if (page >= viewSize) {
				for (int i = 1; i <= offset; i++) {
					buf.append(getPageItem(uri, page, i));
				}
				buf.append("        <a href=\"#none\">…</a>");

				if (totalPage - defSize > 0 && totalPage - defSize <= offset) {
					for (int i = totalPage - defSize + offset; i <= totalPage; i++) {
						buf.append(getPageItem(uri, page, i));
					}
				} else {
					if (totalPage - page <= radius) {
						for (int i = totalPage - viewSize; i <= totalPage; i++) {
							buf.append(getPageItem(uri, page, i));
						}
					} else {
						for (int i = page - radius; i <= page + radius; i++) {
							buf.append(getPageItem(uri, page, i));
						}
						buf.append("        <a href=\"#none\">…</a>");
						for (int i = totalPage; i <= totalPage; i++) {
							buf.append(getPageItem(uri, page, i));
						}
					}
				}
			} else {
				for (int i = 1; i <= viewSize; i++) {
					buf.append(getPageItem(uri, page, i));
				}
				buf.append("        <a href=\"#none\">…</a>");
				for (int i = totalPage; i <= totalPage; i++) {
					buf.append(getPageItem(uri, page, i));
				}
			}
		}
		buf.append("      </span>");
		if (page == totalPage) {
			buf.append("      <a href=\"#none\" class=\"page-next page-next-disabled\">下一页</a>");
		} else {
			buf.append("      <a href=\"" + appendParameter(uri, (page + 1))
					+ "\" class=\"page-next\">下一页</a>");
		}
		buf.append("    </div>");
		buf.append("  </div>");
		buf.append("</div>");
		buf.append("<!-- 翻页]] -->");

		return buf.toString();
	}

	/*
	 * public static void main(String[] str){ PageBar pb = new PageBar();
	 * System.out.println(pb.output(1, 4365, 10, ""));
	 * System.out.println(pb.output(8, 4365, 10, ""));
	 * System.out.println(pb.output(9, 4365, 10, ""));
	 * System.out.println(pb.output(432, 4365, 10, ""));
	 * System.out.println(pb.output(300, 4365, 10, "")); }
	 */
}

/**
 * 
 */
package com.seshenghuo.open.taobao;

import com.google.gson.Gson;
import com.seshenghuo.logger.L;
import com.taobao.api.ApiException;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaGeoipGetRequest;
import com.taobao.api.request.AppipGetRequest;
import com.taobao.api.request.TimeGetRequest;
import com.taobao.api.response.AlibabaGeoipGetResponse;
import com.taobao.api.response.AppipGetResponse;
import com.taobao.api.response.TimeGetResponse;

/**
 * @author Administrator
 * 
 */
public class ToolsAPI {
	TaobaoClient client = null;

	/**
	 * 
	 */
	public ToolsAPI(String key) {
		// TODO Auto-generated constructor stub
		client = Client.getInstance(key);
	}

	public String getGeoIP(AlibabaGeoipGetRequest param) {
		AlibabaGeoipGetResponse resp = null;
		String body = null;
		Gson gson = new Gson();

		L.info(ToolsAPI.class, "getGeoIP(AlibabaGeoipGetRequest param)",
				"INFO", gson.toJson(param));

		try {
			resp = client.execute(param);
			body = null == resp ? null : resp.getBody();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			L.error(ToolsAPI.class, "getGeoIP(AlibabaGeoipGetRequest param)",
					"ApiException", e.getMessage());
		}

		return body;
	}

	public String getAppIP(AppipGetRequest param) {
		AppipGetResponse resp = null;
		String body = null;
		Gson gson = new Gson();

		L.info(ToolsAPI.class, "getAppIP(AppipGetRequest param)", "INFO",
				gson.toJson(param));

		try {
			resp = client.execute(param);
			body = null == resp ? null : resp.getBody();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			L.error(ToolsAPI.class, "getAppIP(AppipGetRequest param)",
					"ApiException", e.getMessage());
		}

		return body;
	}

	public String getTaobaoTime(TimeGetRequest param) {
		TimeGetResponse resp = null;
		String body = null;
		Gson gson = new Gson();

		L.info(ToolsAPI.class, "getTaobaoTime(TimeGetRequest param)", "INFO",
				gson.toJson(param));

		try {
			resp = client.execute(param);
			body = null == resp ? null : resp.getBody();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			L.error(ToolsAPI.class, "getTaobaoTime(TimeGetRequest param)",
					"ApiException", e.getMessage());
		}

		return body;
	}

}

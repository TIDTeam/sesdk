/**
 * 
 */
package com.seshenghuo.open.taobao;

import com.google.gson.Gson;
import com.seshenghuo.logger.L;
import com.taobao.api.ApiException;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.ItemGetRequest;
import com.taobao.api.response.ItemGetResponse;

/**
 * @author carlli
 * 
 */
public class GoodsAPI {
	TaobaoClient client = null;

	/**
	 * 
	 */
	public GoodsAPI(String key) {
		// TODO Auto-generated constructor stub
		client = Client.getInstance(key);
	}

	public String getItemDetail(ItemGetRequest param) {
		ItemGetResponse resp = null;
		String body = null;
		Gson gson = new Gson();

		L.info(GoodsAPI.class, "getProductDetail(ItemGetRequest param)",
				"INFO", gson.toJson(param));

		try {
			resp = client.execute(param);
			body = null == resp ? null : resp.getBody();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			L.error(TaoBaoKeAPI.class,
					"getProductDetail(ItemGetRequest param)", "ApiException",
					e.getMessage());
		}

		return body;
	}

}

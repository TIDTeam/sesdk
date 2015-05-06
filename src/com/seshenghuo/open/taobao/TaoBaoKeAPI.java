/**
 * 
 */
package com.seshenghuo.open.taobao;

import com.google.gson.Gson;
import com.seshenghuo.logger.L;
import com.taobao.api.ApiException;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TaobaokeRebateAuthGetRequest;
import com.taobao.api.request.TaobaokeRebateAuthorizeGetRequest;
import com.taobao.api.request.TbkItemsDetailGetRequest;
import com.taobao.api.request.TbkItemsGetRequest;
import com.taobao.api.request.TbkMobileItemsConvertRequest;
import com.taobao.api.request.TbkMobileShopsConvertRequest;
import com.taobao.api.request.TbkShopsDetailGetRequest;
import com.taobao.api.request.TbkShopsGetRequest;
import com.taobao.api.response.TaobaokeRebateAuthGetResponse;
import com.taobao.api.response.TaobaokeRebateAuthorizeGetResponse;
import com.taobao.api.response.TbkItemsDetailGetResponse;
import com.taobao.api.response.TbkItemsGetResponse;
import com.taobao.api.response.TbkMobileItemsConvertResponse;
import com.taobao.api.response.TbkMobileShopsConvertResponse;
import com.taobao.api.response.TbkShopsDetailGetResponse;
import com.taobao.api.response.TbkShopsGetResponse;

/**
 * @author Administrator
 * 
 */
public class TaoBaoKeAPI {

	TaobaoClient client = null;

	/**
	 * 
	 */
	public TaoBaoKeAPI(String key) {
		// TODO Auto-generated constructor stub
		client = Client.getInstance(key);
	}

	public String getProductDetails(TbkItemsDetailGetRequest param) {
		TbkItemsDetailGetResponse resp = null;
		String body = null;
		Gson gson = new Gson();

		L.info(TaoBaoKeAPI.class,
				"getProductDetails(TbkItemsDetailGetRequest param)", "INFO",
				gson.toJson(param));

		try {
			resp = client.execute(param);
			body = null == resp ? null : resp.getBody();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			L.error(TaoBaoKeAPI.class,
					"getProductDetails(TbkItemsDetailGetRequest param)",
					"ApiException", e.getMessage());
		}

		return body;
	}

	public String getProductList(TbkItemsGetRequest param) {
		TbkItemsGetResponse resp = null;
		String body = null;
		Gson gson = new Gson();

		L.info(TaoBaoKeAPI.class, "getProductList(TbkItemsGetRequest param)",
				"INFO", gson.toJson(param));

		try {
			resp = client.execute(param);
			body = null == resp ? null : resp.getBody();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			L.error(TaoBaoKeAPI.class,
					"getProductList(TbkItemsGetRequest param)", "ApiException",
					e.getMessage());
		}

		return body;
	}

	public String getShopDetails(TbkShopsDetailGetRequest param) {
		TbkShopsDetailGetResponse resp = null;
		String body = null;
		Gson gson = new Gson();

		L.info(TaoBaoKeAPI.class,
				"getShopDetails(TbkShopsDetailGetRequest param)", "INFO",
				gson.toJson(param));

		try {
			resp = client.execute(param);
			body = null == resp ? null : resp.getBody();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			L.error(TaoBaoKeAPI.class,
					"getShopDetails(TbkShopsDetailGetRequest param)",
					"ApiException", e.getMessage());
		}

		return body;
	}

	public String getShopList(TbkShopsGetRequest param) {
		TbkShopsGetResponse resp = null;
		String body = null;
		Gson gson = new Gson();

		L.info(TaoBaoKeAPI.class, "getShopList(TbkShopsGetRequest param)",
				"INFO", gson.toJson(param));

		try {
			resp = client.execute(param);
			body = null == resp ? null : resp.getBody();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			L.error(TaoBaoKeAPI.class, "getShopList(TbkShopsGetRequest param)",
					"ApiException", e.getMessage());
		}

		return body;
	}

	public String getBusinessRebate(TaobaokeRebateAuthorizeGetRequest param) {
		TaobaokeRebateAuthorizeGetResponse resp = null;
		String body = null;
		Gson gson = new Gson();

		L.info(TaoBaoKeAPI.class,
				"getBusinessRebate(TaobaokeRebateAuthorizeGetRequest param)",
				"INFO", gson.toJson(param));

		try {
			resp = client.execute(param);
			body = null == resp ? null : resp.getBody();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			L.error(TaoBaoKeAPI.class,
					"getBusinessRebate(TaobaokeRebateAuthorizeGetRequest param)",
					"ApiException", e.getMessage());
		}

		return body;
	}

	public String getBusinessRebateList(TaobaokeRebateAuthGetRequest param) {
		TaobaokeRebateAuthGetResponse resp = null;
		String body = null;
		Gson gson = new Gson();

		L.info(TaoBaoKeAPI.class,
				"getBusinessRebateList(TaobaokeRebateAuthGetRequest param)",
				"INFO", gson.toJson(param));

		try {
			resp = client.execute(param);
			body = null == resp ? null : resp.getBody();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			L.error(TaoBaoKeAPI.class,
					"getBusinessRebateList(TaobaokeRebateAuthGetRequest param)",
					"ApiException", e.getMessage());
		}

		return body;
	}

	public String getConvertProductDetails(TbkMobileItemsConvertRequest param) {
		TbkMobileItemsConvertResponse resp = null;
		String body = null;
		Gson gson = new Gson();

		L.info(TaoBaoKeAPI.class,
				"getConvertProductDetails(TbkMobileItemsConvertRequest param)",
				"INFO", gson.toJson(param));

		try {
			resp = client.execute(param);
			body = null == resp ? null : resp.getBody();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			L.error(TaoBaoKeAPI.class,
					"getConvertProductDetails(TbkMobileItemsConvertRequest param)",
					"ApiException", e.getMessage());
		}

		return body;
	}

	public String getConvertShops(TbkMobileShopsConvertRequest param) {
		TbkMobileShopsConvertResponse resp = null;
		String body = null;
		Gson gson = new Gson();

		L.info(TaoBaoKeAPI.class,
				"getConvertShops(TbkMobileShopsConvertRequest param)", "INFO",
				gson.toJson(param));

		try {
			resp = client.execute(param);
			body = null == resp ? null : resp.getBody();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			L.error(TaoBaoKeAPI.class,
					"getConvertShops(TbkMobileShopsConvertRequest param)",
					"ApiException", e.getMessage());
		}

		return body;
	}
}

package com.seshenghuo.open.taobao;

import java.util.HashMap;

import com.seshenghuo.logger.L;
import com.seshenghuo.util.Config;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;

public class Client {
	private static String appKey = null;
	private static String appSecret = null;

	private static String serverUrl = Config
			.getStringValue("open.taobao.serverUrl");
	private static String format = Config.getStringValue("open.taobao.format");
	private static String signMethod = Config
			.getStringValue("open.taobao.signMethod");
	private static int connectTimeout = Config.getIntValue(
			"open.taobao.connectTimeout", 3000); // 3秒
	private static int readTimeout = Config.getIntValue(
			"open.taobao.readTimeout", 15000);// 15秒

	private static HashMap<String, TaobaoClient> clientMap = new HashMap<String, TaobaoClient>();

	private Client() {

	}

	public static TaobaoClient getInstance(String key) {
		TaobaoClient client = null;

		if (clientMap.containsKey(key) && null != (client = clientMap.get(key))) {
			L.info(Client.class, "getInstance(String key)", "CACHE",
					"The app flag is " + key);

			return client;
		} else {
			appKey = Config.getStringValue("open.taobao." + key + ".appKey");
			appSecret = Config.getStringValue("open.taobao." + key
					+ ".appSecret");

			client = new DefaultTaobaoClient(serverUrl, appKey, appSecret,
					format, connectTimeout, readTimeout, signMethod);
			clientMap.put(key, client);

			L.info(Client.class, "getInstance(String key)", "NEW",
					"The app flag is " + key);
		}

		return client;
	}
}

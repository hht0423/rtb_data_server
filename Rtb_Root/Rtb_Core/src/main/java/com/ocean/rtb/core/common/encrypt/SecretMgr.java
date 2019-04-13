package com.ocean.rtb.core.common.encrypt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.brosmasher.ad4j.http.HttpClient;
import com.brosmasher.ad4j.http.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ocean.core.common.system.SystemContext;

/**
 * 
 * @Description: 定时查询密钥
 * @author z543j 
 * @date 2018年10月30日 下午3:18:59
 */
@Component("secretMgr")
public class SecretMgr {

	private static final Logger logger = LoggerFactory.getLogger(SecretMgr.class);
	
	private static final String PROPERTY_KEY_SECRET_URL = "secret.url";
	
	private static Map<String, SecretInfo> SECRET_MAP = new HashMap<String, SecretInfo>();;
	
	public static SecretInfo getSecretInfo(String pkey) {
		return SECRET_MAP.get(pkey);
	}
	
	@PostConstruct
	public void init() {
		task();
	}

	/**
	 * 
	 * @Description: 查询密钥
	 * @author z543j
	 * @date 2018年10月30日 下午3:21:30 
	 * @throws
	 */
	@Scheduled(cron = "0 0/5 * * * ?")
	public void task() {
		long start = System.currentTimeMillis();
		try {
			taskImpl();
		} catch (Throwable e) {
			logger.error("secret data task error:", e);
		}
		logger.error("secret data task consume:{}", System.currentTimeMillis() - start);
	}

	private void taskImpl() {
		String url = SystemContext.getDynamicPropertyHandler().get(PROPERTY_KEY_SECRET_URL);
		Response resp = HttpClient.getInstance().get(url);
		if(resp.getStatus() != HttpStatus.SC_OK) {
			logger.error("secret data task query secret status error:", resp.getStatus());
			return;
		}
		
		List<SecretInfo> secrets = new Gson().fromJson(resp.getBody(), new TypeToken<List<SecretInfo>>() {}.getType());
		if(CollectionUtils.isEmpty(secrets)) {
			logger.error("secret data task query secret is empty:");
			return;
		}
		
		logger.error("secrets:{}", new Gson().toJson(secrets));
		
		SECRET_MAP.clear();
		for(SecretInfo secret : secrets) {
			SECRET_MAP.put(secret.getPkey(), secret);
		}
	}
}

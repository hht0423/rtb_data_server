package com.ocean.rtb.profile.service.api;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.UniversalUtils;
import com.ocean.core.common.http.Bean2Utils;
import com.ocean.core.common.http.HttpClient;
import com.ocean.core.common.http.HttpInvokeException;
import com.ocean.core.common.system.SystemContext;
import com.ocean.rtb.persist.common.RtbLogManager;
import com.ocean.rtb.profile.bean.api.GeoApiReq;
import com.ocean.rtb.profile.bean.api.GeoApiResp;
import com.ocean.rtb.profile.common.ProfileConstants;
@Component
public class GeoService implements IGeoService {
	private  static final Logger  LOGGER = RtbLogManager.getBusinessLogger();
	@Override
	public GeoApiResp getGeoinfo(String imei, String ip, int imsiFlag) throws HttpInvokeException {
		// TODO Auto-generated method stub
		
		StringBuilder url=new StringBuilder(SystemContext.getDynamicPropertyHandler().get(ProfileConstants.GEO_API_URL));

		GeoApiReq req=new GeoApiReq();
		req.setImei(imei);
		req.setIp(ip);
		req.setIsimsi(imsiFlag);
		url.append("?").append(Bean2Utils.toHttpParams(req));
		
		LOGGER.info("Taskrequest[post] url:{}  param:{}",url,JsonUtils.toJson(req));
		String strResult =HttpClient.getInstance().get(url.toString());
		if(StringUtils.isEmpty(strResult)){
			LOGGER.error("GeoService get geo info from api is empty,imei:{},ip:{}",imei,ip);
			return null;
		}
		LOGGER.info("GeoService imei:{} ,ip:{} Task  return source data:{} ",imei,ip,strResult);
		return JsonUtils.toBean(strResult, GeoApiResp.class);
		
		
	}

}

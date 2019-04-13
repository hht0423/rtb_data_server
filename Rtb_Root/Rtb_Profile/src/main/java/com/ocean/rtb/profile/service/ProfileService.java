package com.ocean.rtb.profile.service;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.HttpInvokeException;
import com.ocean.rtb.persist.bean.thrift.profile.RtbUserInfo;
import com.ocean.rtb.persist.common.RtbConstants;
import com.ocean.rtb.persist.common.RtbException;
import com.ocean.rtb.persist.common.RtbLogManager;
import com.ocean.rtb.persist.dao.BasicDaoFactory;
import com.ocean.rtb.persist.dao.cache.RtbCacheDao;
import com.ocean.rtb.profile.bean.api.GeoApiResp;
import com.ocean.rtb.profile.bean.api.GeoCacheBan;
import com.ocean.rtb.profile.common.ProfileConstants;
import com.ocean.rtb.profile.service.api.IGeoService;
import com.ocean.rtb.profile.task.data.ProfileCacheTask;
import com.ocean.rtb.profile.task.server.ProfileCacheSchedualerHandler;

@Component
public class ProfileService implements IProfileService {
	private  static final Logger  LOGGER = RtbLogManager.getBusinessLogger();
	private static final String GEO_TAG_ID="0";
	@Autowired
	private IGeoService geoService;

	@Override
	public RtbUserInfo searchProfile(RtbUserInfo user) {
		// TODO Auto-generated method stub
		
		if(StringUtils.isEmpty(user.getImei())||StringUtils.isEmpty(user.getClient_ip())){
			return user;
		}
		//1-get geo info from cache
		GeoApiResp geo=this.getCacheDate(user.getImei(), user.getClient_ip());
		
		//2-get geo info from api
		if(geo==null){
			int imsiFlag=StringUtils.isEmpty(user.getImsi())?1:0;
			try {
				geo=geoService.getGeoinfo(user.getImei(), user.getClient_ip(), imsiFlag);
			} catch (HttpInvokeException e) {
				// TODO Auto-generated catch block
				throw new RtbException(MessageFormat.format("ProfileService get geo info from api error<imei:{0},ip:{1}>,error msg:{2} code:{3}",user.getImei(), user.getClient_ip(), e.getMessage(),e.getCode()));
			}
		}
		if(geo==null){
			throw new RtbException(MessageFormat.format("ProfileService get geo info from api empty<imei:{0},ip:{1}>",user.getImei(), user.getClient_ip()));
		}
		user.setCity(geo.getCity());
		user.setCity_name(geo.getName());
		if(StringUtils.isEmpty(user.getImsi())){
			user.setImsi(geo.getImsi());
		}
		if(StringUtils.isEmpty(user.getLat())||StringUtils.isEmpty(user.getLon())){
			user.setLat(geo.getLat());
			user.setLon(geo.getLng());
		}
		Map<String,List<String>> tags=new HashMap<String,List<String>>();
		if(StringUtils.isNotEmpty(geo.getTags())){
			tags.put(GEO_TAG_ID,Arrays.asList(geo.getTags().split(",")));
			user.setTag(tags);
		}
		
		
		//cache to redis
		cacheGeo( geo, user.getImei(), user.getClient_ip());
		return user;
	}
	
	private GeoApiResp getCacheDate(String imei,String ip){
		try{
			RtbCacheDao dao=BasicDaoFactory.getCatcheDao();
			String data=dao.getStrValue(getGeoCacheKey(imei,ip));
			GeoCacheBan geoBean=JsonUtils.toBean(data, GeoCacheBan.class);
			if(geoBean!=null){
				return geoBean.getGeo();
			}
			LOGGER.debug("ProfileService get cache data:{}",data);
		}catch(Throwable e){
			LOGGER.error("ProfileService get stat info from cache error,imei:{},ip::{}",imei,ip,e);
		}
		return null;
		

	}
	private String getGeoCacheKey(String imei,String ip){
		return ProfileConstants.PROFILE_GEO_CACHE_KEY_PREFIX+imei+RtbConstants.CACHE_SPLIT+ip;
	}
	
	private void cacheGeo(GeoApiResp geo,String imei,String ip){
		try{
			ProfileCacheTask task=new ProfileCacheTask();
			GeoCacheBan geoBean=new GeoCacheBan(imei,ip);
			geoBean.setGeo(geo);
			task.setData(geoBean);
			
			ProfileCacheSchedualerHandler.builder().getServer().addTask(task);
		}catch(Throwable e){
			LOGGER.error("ProfileService cache geo info to redis error,imei:{},ip::{}",imei,ip,e);
		}

	}
}

package com.ocean.rtb.persist.service.rtbcahe;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inveno.base.BaseModel;
import com.inveno.util.CollectionUtils;
import com.ocean.core.common.JsonUtils;
import com.ocean.rtb.persist.bean.ad.AdInfo;
import com.ocean.rtb.persist.bean.ad.AdOrientation;
import com.ocean.rtb.persist.bean.ad.AdSpace;
import com.ocean.rtb.persist.bean.ad.AdVideo;
import com.ocean.rtb.persist.bean.ad.AppInfo;
import com.ocean.rtb.persist.bean.ad.Material;
import com.ocean.rtb.persist.bean.cache.AdProTimeCacheBean;
import com.ocean.rtb.persist.common.DbSyncException;
import com.ocean.rtb.persist.common.GsonUtils;
import com.ocean.rtb.persist.dao.BasicDaoFactory;
import com.ocean.rtb.persist.dao.cache.RtbCacheDao;

@Component(value="rtbCacheService")
public class RtbCacheService implements IRtbCacheService {
    private static final int  SOURCE_TYPE_SELF = 0;
	@Override
	public List<AdSpace> getAllSpaceInfo() {
		// TODO Auto-generated method stub
		RtbCacheDao dao=BasicDaoFactory.getCatcheDao();
		Map<Object,Object> spaceMap=dao.getAllSpaceInfo();
		if(CollectionUtils.isEmpty(spaceMap)){
			return null;
		}
		List<String> spaceJsonList=(List)(new ArrayList<Object>(spaceMap.values()));
		List<AdSpace> spaceList=new ArrayList<AdSpace>(spaceJsonList.size());
		
		for(String spaceStr:spaceJsonList){
			AdSpace space=JsonUtils.toBean(spaceStr, AdSpace.class);
			spaceList.add(space);
		}
		return spaceList;
	}

	@Override
	public List<String> getAdIdsByPage(int spaceId, int page,int source) {
		// TODO Auto-generated method stub
		RtbCacheDao dao=BasicDaoFactory.getCatcheDao();
		List<String> adIds=null;
		if(source==SOURCE_TYPE_SELF){
			adIds=dao.getAdIdsByPage(spaceId, page);
		}else{
			//throw
			
			adIds=dao.getThirdSourceIdByPage(spaceId, page);
		}
				
		if(CollectionUtils.isEmpty(adIds)){
			return Collections.EMPTY_LIST;
		}
		return adIds;
	}

	@Override
	public List<AdInfo> getSelfAdInfoBySpaceId(String spaceId,List<String> adIds)
			throws DbSyncException {
		// TODO Auto-generated method stub
		if(CollectionUtils.isEmpty(adIds)){
			return null;
		}
		RtbCacheDao dao=BasicDaoFactory.getCatcheDao();
		HashMap<Object,Object> objAdMap=dao.getAdInfoBySpaceId(spaceId);
		if(CollectionUtils.isEmpty(objAdMap)){
			return null;
		}
		HashMap<String,String> strAdMap=(HashMap)objAdMap; 
		List<AdInfo> adList=new ArrayList<AdInfo> (adIds.size());
		for(String adId:adIds){
			String adStr=strAdMap.get(adId);
			if(StringUtils.isNotEmpty(adStr)){
				//adList.add(JsonUtils.toBean(adStr, AdInfo.class));
				adList.add(GsonUtils.getFormatGson().fromJson(adStr, AdInfo.class));
			}
		}
		return adList;
	}

/*	@Override
	public List<AdInfo> getAppInfo(List<AdInfo> adList) {
		// TODO Auto-generated method stub
		RtbCacheDao dao=BasicDaoFactory.getCatcheDao();
		for(AdInfo adInfo:adList){
			//app
			if(adInfo.getAppId()>0){
				Object appObj=dao.getSpecificCommonData(RtbCacheDao.COMMON_CACHE_TYPE_APP, String.valueOf(adInfo.getAppId()));
				if(appObj!=null){
					adInfo.setApp(JsonUtils.toBean((String)appObj, AppInfo.class));
				}
			}
			//video
			if(adInfo.getVdId()>0){
				Object vdObj=dao.getSpecificCommonData(RtbCacheDao.COMMON_CACHE_TYPE_VIDEO, String.valueOf(adInfo.getVdId()));
				if(vdObj!=null){
					adInfo.setVideo(JsonUtils.toBean((String)vdObj, AdVideo.class));
				}
			}
			//material
			if(adInfo.getVdId()>0){
				Object mateObj=dao.getSpecificCommonData(RtbCacheDao.COMMON_CACHE_TYPE_MATERIAL, String.valueOf(adInfo.getMateId()));
				if(mateObj!=null){
					adInfo.setMaterial(JsonUtils.toBean((String)mateObj, Material.class));
				}
			}
		}
		
		return null;
	}*/
	
	public  Object getObectInfo(Class<? extends  BaseModel> clazz,String id){
		RtbCacheDao dao=BasicDaoFactory.getCatcheDao();
		if(clazz.getName().equals(AppInfo.class.getName())){
			Object appObj=dao.getSpecificCommonData(RtbCacheDao.COMMON_CACHE_TYPE_APP,id);
			if(appObj!=null){
				return JsonUtils.toBean((String)appObj, AppInfo.class);
			}
		}else if(clazz.getName().equals(AdVideo.class.getName())){
			Object vdObj=dao.getSpecificCommonData(RtbCacheDao.COMMON_CACHE_TYPE_VIDEO, id);
			if(vdObj!=null){
				return JsonUtils.toBean((String)vdObj, AdVideo.class);
			}
		}else if(clazz.getName().equals(Material.class.getName())){
			Object mateObj=dao.getSpecificCommonData(RtbCacheDao.COMMON_CACHE_TYPE_MATERIAL, id);
			if(mateObj!=null){
				return JsonUtils.toBean((String)mateObj, Material.class);
			}
		}
		else if(clazz.getName().equals(AdOrientation.class.getName())){
			Object ortObj=dao.getSpecificCommonData(RtbCacheDao.COMMON_CACHE_TYPE_ORIENTATION, id);
			if(ortObj!=null){
				return JsonUtils.toBean((String)ortObj, AdOrientation.class);
			}
		}
		return null;
		//throw new AdSyncException(MessageFormat.format("ad {} no such relate info",id));

		
	}

	@Override
	public AdProTimeCacheBean getProTimeInfo(String adId) {
		// TODO Auto-generated method stub
		RtbCacheDao dao=BasicDaoFactory.getCatcheDao();
		Object mateObj=dao.getSpecificCommonData(RtbCacheDao.COMMON_CACHE_TYPE_PROTIME, adId);
		if(mateObj!=null){
			return GsonUtils.getFormatGson().fromJson((String)mateObj, AdProTimeCacheBean.class);
				//	return	JsonUtils.toBean((String)mateObj, AdProTimeCacheBean.class);
		}
		throw new DbSyncException(MessageFormat.format("ad {0} no relate protime info",adId));
	}
}

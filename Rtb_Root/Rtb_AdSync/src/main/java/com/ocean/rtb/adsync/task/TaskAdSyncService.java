package com.ocean.rtb.adsync.task;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inveno.util.CollectionUtils;
import com.ocean.core.common.JsonUtils;
import com.ocean.rtb.persist.bean.ad.AdInfo;
import com.ocean.rtb.persist.bean.ad.AdProTime;
import com.ocean.rtb.persist.bean.ad.AdSpace;
import com.ocean.rtb.persist.bean.cache.AdProTimeCacheBean;
import com.ocean.rtb.persist.bean.cache.AdProTimeTemp;
import com.ocean.rtb.persist.common.DbSyncException;
import com.ocean.rtb.persist.common.GsonUtils;
import com.ocean.rtb.persist.common.RtbConstants;
import com.ocean.rtb.persist.common.RtbLogManager;
import com.ocean.rtb.persist.dao.BasicDaoFactory;
import com.ocean.rtb.persist.dao.cache.RtbCacheDao;
import com.ocean.rtb.persist.service.ad.IRtbDBService;
@Component(value="adSync")
public class TaskAdSyncService implements ITaskAdSyncService{
	private  static final Logger  LOGGER = RtbLogManager.getSchedualLogger();
	@Autowired 
	private IRtbDBService rtbDbService;
	private static final String DATE_FORMAT="yyyy-MM-dd";
	private SimpleDateFormat DateFormat=new SimpleDateFormat(DATE_FORMAT);
	
	private static final String TIME_FORMAT="hh:mm:ss";
	private SimpleDateFormat TimeFormat=new SimpleDateFormat(TIME_FORMAT);
	@Override
	public void dataSync() throws DbSyncException{
		LOGGER.debug("************************cache ad info begin**************************");
		cacheAdIds();
		cacheAdInfo();
		cacheAdProTime();
		LOGGER.debug("************************cache ad info over**************************");
		//cache third source
	}
	private void cacheAdProTime(){
		LOGGER.debug("AD PROTIME cache ad protime begin....");
		//获取投放时间详情
		List<Object>  proTimeObjList=rtbDbService.getValidProTimeInfo(false, null);
		if(CollectionUtils.isEmpty(proTimeObjList)){
			LOGGER.error(" get ad pro time detail error");
			return;
		}
		//Map<String, List<AdProTime>> ptMap= cvtPTMap((List)proTimeObjList);
		Map<String, List<AdProTimeTemp>> ptMap= cvtPTTempMap((List)proTimeObjList);
		
		RtbCacheDao dao=BasicDaoFactory.getCatcheDao();
		
		Map<String,String> ptJsonMap=new HashMap<String,String>();
		
		for(Entry<String, List<AdProTimeTemp>> entry:ptMap.entrySet()){
			AdProTimeCacheBean ptBean=new AdProTimeCacheBean();
			ptBean.setAdId(entry.getKey());
			ptBean.setProTimeList(entry.getValue());
			//ptJsonMap.put(entry.getKey(), JsonUtils.toJson(ptBean));
			ptJsonMap.put(entry.getKey(), GsonUtils.getFormatGson().toJson(ptBean));
		}
		dao.cacheCommonData(RtbCacheDao.COMMON_CACHE_TYPE_PROTIME,ptJsonMap );
		LOGGER.debug("AD PROTIME cache ad protime over....");
	}
	private void cacheAdIds()throws DbSyncException{
		LOGGER.debug("ADIDS cache ad ids begin....");
		Set<String> spaceIdSet=null;
		try{
			spaceIdSet=getSpaceIds();
		}catch(DbSyncException e){	
			LOGGER.error("get space id error {}",e.getMsg(),e);
			return;
		}catch(Throwable e){
			LOGGER.error("get space id error {}",e.getMessage(),e);
			return;
		}
		//按广告位id进行缓存
		for(String spaceId:spaceIdSet){
			cacheAdIdsBySpaceId(Integer.parseInt(spaceId),0);
		}
		LOGGER.debug("ADIDS cache ad ids over....");
	}

	private void cacheAdIdsBySpaceId(int spaceId,int from){
		try{
				List<Object> objList= rtbDbService.getValidAdData(spaceId, from, RtbConstants.PAGE_SIZE,true);// 
				RtbCacheDao dao=BasicDaoFactory.getCatcheDao();
				if(CollectionUtils.isEmpty(objList)){
					return;
				}
				//cache to redis
				dao.cacheAdIdsByPage(spaceId, from, cvtStrList(objList));
				if(objList.size()==RtbConstants.PAGE_SIZE){
					//cache to redis
					cacheAdIdsBySpaceId(spaceId,++from);
				}
			}catch(DbSyncException e){	
				LOGGER.error("task sync adIds info error:{}",e.getMsg(),e);
			}catch(Throwable e){
				LOGGER.error("task sync adIds info error,space id:{}",spaceId,e.getMessage(),e);
			}
		}
	private void cacheAdInfo()throws DbSyncException{
		LOGGER.debug("ADINFO cache ad info begin....");
		Set<String> spaceIdSet=null;
		try{
			spaceIdSet=getSpaceIds();
		}catch(DbSyncException e){	
			LOGGER.error("get space id error {}",e.getMsg(),e);
			return;
		}catch(Throwable e){
			LOGGER.error("get space id error {}",e.getMessage(),e);
			return;
		}
		for(String spaceId:spaceIdSet){
			cacheAdInfoBySpaceId(Integer.parseInt(spaceId));
		}
		LOGGER.debug("ADINFO cache ad info over....");
	}
	private void cacheAdInfoBySpaceId(int spaceId){
		try{
			//获取广告详情
			List<Object> objList= rtbDbService.getValidAdData(spaceId, 0, null,false);
			if(CollectionUtils.isEmpty(objList)){
				throw new DbSyncException("get ad info is empty,space id:"+spaceId);
			}
			//cache to redis
			RtbCacheDao dao=BasicDaoFactory.getCatcheDao();
			dao.cacheAdInfoBySpaceId(spaceId, cvtAdInfoMap((List)objList));
		}catch(DbSyncException e){
			
			LOGGER.error("task sync adinfo error:{}",e.getMsg(),e);
			
		}catch(Throwable e){
			LOGGER.error("task sync adinfo error,space id:{}",spaceId,e.getMessage(),e);
		}
	}
	private Map<String,String> cvtAdInfoMap(List<AdInfo> adInfoList){
		Map<String,String> map=new HashMap<String,String> ();
		for(AdInfo adInfo:adInfoList){
			//map.put(adInfo.getId(), JsonUtils.toJson(adInfo));
			map.put(adInfo.getId(), GsonUtils.getFormatGson().toJson(adInfo));
			
		}
		return map;
	}
/*	private void cacheAdInfoBySpaceId(int spaceId){
		//获取广告id列表
		List<Object> adIdList= rtbDbService.getValidAdData(spaceId, 0, null,true);// 
		//获取广告详情
		List<AdInfo> adInfoList=rtbDbService.getAdInfoByIds(spaceId, adIdList);
		//获取投放时间详情
		List<Object>  proTimeObjList=rtbDbService.getValidProTimeInfo(false, adIdList);
		if(CollectionUtils.isEmpty(proTimeObjList)){
			throw new AdSyncException("get ad pro time info is empty:{}"+this.cvtStrList(adIdList));
		}
		Map<String, List<AdProTime>> ptMap= cvtPTMap((List)proTimeObjList);
		
		//cache to redis
		RtbCacheDao dao=BasicDaoFactory.getCatcheDao();
		for(AdInfo adInfo:adInfoList){
			if(ptMap.get(adInfo.getId())==null){
				continue;
			}
			//set pro time detail
			adInfo.setProtimeList(ptMap.get(adInfo.getId()));

	
			
		}
	}*/
	private Map<String, List<AdProTime>> cvtPTMap(List<AdProTime>  proTimeInfoList){
		Map<String, List<AdProTime>> map=new HashMap<String,List<AdProTime>>();
		for(AdProTime pt:proTimeInfoList){
			List<AdProTime> ptList=map.get(pt.getAdId());
			if(ptList==null){
				ptList=new ArrayList<AdProTime> ();
				
			}
			ptList.add(pt);
			map.put(String.valueOf(pt.getAdId()), ptList);
		}
		return map;
			
	}
	private Map<String, List<AdProTimeTemp>> cvtPTTempMap(List<AdProTime>  proTimeInfoList){
		Map<String, List<AdProTimeTemp>> map=new HashMap<String,List<AdProTimeTemp>>();
		for(AdProTime pt:proTimeInfoList){
			List<AdProTimeTemp> ptList=map.get(pt.getAdId());
			if(ptList==null){
				ptList=new ArrayList<AdProTimeTemp> ();
				
			}
			ptList.add(cvt2PTTemp(pt));
			map.put(String.valueOf(pt.getAdId()), ptList);
		}
		return map;
			
	}
	private AdProTimeTemp cvt2PTTemp(AdProTime pt){

		AdProTimeTemp ptTemp=new AdProTimeTemp();
		ptTemp.setAdId(pt.getId());
		ptTemp.setDateStart(DateFormat.format(pt.getDateStart()));
		ptTemp.setDateEnd(DateFormat.format(pt.getDateEnd()));
		//TimeFormat
		ptTemp.setTimeStart(TimeFormat.format(pt.getTimeStart()));
		ptTemp.setTimeEnd(TimeFormat.format(pt.getTimeEnd()));
		ptTemp.setStatus(pt.getStatus());
		return ptTemp;
	}
	private Set<String> getSpaceIds(){
		//1-get space ids from cache
		RtbCacheDao dao=BasicDaoFactory.getCatcheDao();
		Set<String> spaceIdSet=dao.getSpaceIds();
		if(CollectionUtils.isEmpty(spaceIdSet)){
			//get space ids from DB
			List<Object> spaceIdList= rtbDbService.getObjeList(AdSpace.class, true);
			if(CollectionUtils.isEmpty(spaceIdList)){
				throw new DbSyncException("get spaceId list from DB is empty");
			}
			spaceIdSet=cvtStrSet( spaceIdList );
		}
		return spaceIdSet;
	}
	private Set<String>  cvtStrSet( List<Object> adIds ){
		List<String> strList=new ArrayList<String>(adIds.size());
		for(Object pkg:adIds){
			strList.add((String)((Object[])pkg)[0]);
		}
		Set<String> staffsSet = new HashSet<String>(strList);
		return staffsSet;
	}
	private List<String>  cvtStrList( List<Object> adIds ){
		List<String> strList=new ArrayList<String>(adIds.size());
		for(Object pkg:adIds){
			strList.add((String)((Object[])pkg)[0]);
		}
		return strList;
	}
}

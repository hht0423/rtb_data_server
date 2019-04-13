package com.ocean.rtb.adsync.task;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
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
		String syncode=DigestUtils.md5Hex(UUID.randomUUID().toString().replaceAll("-", "")+System.currentTimeMillis());
		cacheAdIds(syncode);
		cacheAdInfo(syncode);
		cacheAdProTime(syncode);
		
		//cacheThirdSourceIds(syncode);
		//cacheThirdAdInfo(syncode);
		
	}
	private void cacheAdProTime(String syncode){
		LOGGER.debug("AD PROTIME cache {} ad protime begin....",syncode);
		try{
			//获取投放时间详情
			List<Object>  proTimeObjList=rtbDbService.getValidProTimeInfo(false, null);
			if(CollectionUtils.isEmpty(proTimeObjList)){
				LOGGER.error("AD PROTIME cache {} get ad pro time detail error",syncode);
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
		}catch(DbSyncException e){	
			LOGGER.error("AD PROTIME cache {} cache Ad ProTime  error {}",syncode,e.getMsg(),e);
			return;
		}catch(Throwable e){
			LOGGER.error("AD PROTIME cache {} cache Ad ProTime error {}",syncode,e.getMessage(),e);
			return;
		}
		LOGGER.debug("AD PROTIME cache {} ad protime over....",syncode);
	}
	
	private void cacheAdIds(String syncode)throws DbSyncException{
		LOGGER.debug("ADIDS cache {} ad ids begin....",syncode);
		Set<String> spaceIdSet=null;
		try{
			spaceIdSet=getSpaceIds();
		}catch(DbSyncException e){	
			LOGGER.error("ADIDS cache {} get space id error {}",syncode,e.getMsg(),e);
			return;
		}catch(Throwable e){
			LOGGER.error("ADIDS cache {} get space id error {}",syncode,e.getMessage(),e);
			return;
		}
		//按广告位id进行缓存
		for(String spaceId:spaceIdSet){
			cacheAdIdsBySpaceId(Integer.parseInt(spaceId),0);
		}
		LOGGER.debug("ADIDS cache {} ad ids is over....",syncode);
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
	private void cacheThirdSourceIds()throws DbSyncException{
		LOGGER.debug("THIRD SOURCE ADIDS cache {} ids begin....");
		Set<String> spaceIdSet=null;
		try{
			spaceIdSet=getSpaceIds();
		}catch(DbSyncException e){	
			LOGGER.error("THIRD SOURCE ADIDS get space id error {}",e.getMsg(),e);
			return;
		}catch(Throwable e){
			LOGGER.error("THIRD SOURCE ADIDS get space id error {}",e.getMessage(),e);
			return;
		}
		//按广告位id进行缓存
		for(String spaceId:spaceIdSet){
			cacheThirdSourceBySpaceId(Integer.parseInt(spaceId),0);
		}
		LOGGER.debug("THIRD SOURCE ADIDS  cache  {} is over....");
	}
	private void cacheThirdSourceBySpaceId(int spaceId,int from){
		try{
				List<Object> objList= rtbDbService.getValidThirdSourcecAdData(spaceId, from, RtbConstants.PAGE_SIZE,true);// 
				RtbCacheDao dao=BasicDaoFactory.getCatcheDao();
				if(CollectionUtils.isEmpty(objList)){
					return;
				}
				//cache to redis
				dao.cacheThirdSourceAdIdsByPage(spaceId, from, cvtStrList(objList));
				if(objList.size()==RtbConstants.PAGE_SIZE){
					//cache to redis
					cacheThirdSourceBySpaceId(spaceId,++from);
				}
			}catch(DbSyncException e){	
				LOGGER.error("task sync adIds info error:{}",e.getMsg(),e);
			}catch(Throwable e){
				LOGGER.error("task sync adIds info error,space id:{}",spaceId,e.getMessage(),e);
			}
		}
	private void cacheAdInfo(String syncode)throws DbSyncException{
		LOGGER.debug("ADINFO cache ad info begin....");
		Set<String> spaceIdSet=null;
		try{
			spaceIdSet=getSpaceIds();
		}catch(DbSyncException e){	
			LOGGER.error("ADINFO cache {} get space id error {}",syncode,e.getMsg(),e);
			return;
		}catch(Throwable e){
			LOGGER.error("ADINFO cache {} get space id error {}",syncode,e.getMessage(),e);
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
			ptList.addAll(cvt2PTTemp(pt));
			map.put(String.valueOf(pt.getAdId()), ptList);
		}
		return map;
			
	}
	private List<AdProTimeTemp> cvt2PTTemp(AdProTime pt){
		List<AdProTimeTemp> list=new ArrayList<AdProTimeTemp>(2);
		AdProTimeTemp ptTemp=new AdProTimeTemp();
		ptTemp.setAdId(pt.getId());
		//才分为每一天
		Date now=new Date();
		String dateSStr=DateFormat.format(now);//这个时间要注意，要取当天，比如今天是2019-4-12，而开始时间是2019-4-11，结束时间是2019-4-12
		
		String dateEStr=DateFormat.format(pt.getDateEnd());
		ptTemp.setDateStart(dateSStr);
		ptTemp.setDateEnd(dateSStr);//一天存一个，开始和结束日期一样
		//TimeFormat
		ptTemp.setTimeStart(TimeFormat.format(pt.getTimeStart()));
		ptTemp.setTimeEnd(TimeFormat.format(pt.getTimeEnd()));
		ptTemp.setStatus(pt.getStatus());
		list.add(ptTemp);
		
		//如果dateStart 和dateEnd不相等的时候，要多取一天（只取两天内有效的广告）
		if(!dateSStr.equals(dateEStr)){
			Calendar calMax = Calendar.getInstance();//往后取一天
	        calMax.add(Calendar.DATE,1);
	        
	        AdProTimeTemp ptTemp2=new AdProTimeTemp();
	        ptTemp2.setDateStart(DateFormat.format(calMax.getTime()));
	        ptTemp2.setDateEnd(DateFormat.format(calMax.getTime()));
	        
	        ptTemp2.setTimeStart(TimeFormat.format(pt.getTimeStart()));
	        ptTemp2.setTimeEnd(TimeFormat.format(pt.getTimeEnd()));
	        list.add(ptTemp2);
		}
		
		return list;
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

package com.ocean.rtb.adsync.task;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.JsonUtils;
import com.ocean.rtb.persist.bean.ad.AdOrientation;
import com.ocean.rtb.persist.bean.ad.AdSpace;
import com.ocean.rtb.persist.bean.ad.AdVideo;
import com.ocean.rtb.persist.bean.ad.AppInfo;
import com.ocean.rtb.persist.bean.ad.Material;
import com.ocean.rtb.persist.common.DbSyncException;
import com.ocean.rtb.persist.common.RtbLogManager;
import com.ocean.rtb.persist.dao.BasicDaoFactory;
import com.ocean.rtb.persist.dao.cache.RtbCacheDao;
import com.ocean.rtb.persist.service.ad.IRtbDBService;
@Component(value="commonSync")
public class TaskCommonSyncService implements ITaskAdSyncService{
	private  static final Logger  LOGGER = RtbLogManager.getSchedualLogger();
	@Autowired 
	private IRtbDBService rtbDbService;
	@Override
	public void dataSync() throws DbSyncException{
		LOGGER.debug("**********************cache ad relate info begin*********************");
		cacheAdSpace();
		cacheAppInfo();
		cacheVideoInfo();
		cacheMaterialInfo();
		cacheOrientationInfo();
		LOGGER.debug("**********************cache ad relate info over**********************");
	}
	private void cacheAdSpace(){
		LOGGER.debug("SPACE cache ad relate info begin....");
		try{
			List<Object> objList= rtbDbService.getObjeList(AdSpace.class,false);
			if(CollectionUtils.isEmpty(objList)){
				throw new DbSyncException("task get ad space info empty!");
			}
			List<AdSpace>  adSpaceList=(List)objList;
			RtbCacheDao catcheDao =BasicDaoFactory.getCatcheDao();
			Map<String,String> spaceMap=new HashMap<String,String>(adSpaceList.size());
			Set<String> sapceIdSet=new HashSet<String>(adSpaceList.size());
			for(AdSpace adSpace:adSpaceList){
				spaceMap.put(adSpace.getId(), JsonUtils.toJson(adSpace));
				if(!sapceIdSet.contains(adSpace.getId())){
					sapceIdSet.add(adSpace.getId());
				}
			}
			catcheDao.cacheCommonData(RtbCacheDao.COMMON_CACHE_TYPE_SPACE,spaceMap);
			catcheDao.cacheSpaceIds(sapceIdSet);
		}catch(DbSyncException e){
			
			LOGGER.error("task sync space info error:{}",e.getMsg(),e);
			
		}catch(Throwable e){
			LOGGER.error("task sync space info error:{}",e.getMessage(),e);
		}

		LOGGER.debug("SPACE cache ad relate info over....");
	}
	private void cacheAppInfo(){
		LOGGER.debug("APP cache ad relate info begin....");
		try{
			List<Object> objList= rtbDbService.getObjeList(AppInfo.class,false);
			if(CollectionUtils.isEmpty(objList)){
				LOGGER.warn("task get ad app info empty!");
				return;
			}
			List<AppInfo>  appList=(List)objList;
			RtbCacheDao catcheDao =BasicDaoFactory.getCatcheDao();
			Map<String,String> spaceMap=new HashMap<String,String>(appList.size());
			for(AppInfo app:appList){
				spaceMap.put(app.getId(), JsonUtils.toJson(app));
			}
			catcheDao.cacheCommonData(RtbCacheDao.COMMON_CACHE_TYPE_APP,spaceMap);
		}catch(DbSyncException e){
			
			LOGGER.error("task sync app info error:{}",e.getMsg(),e);
			
		}catch(Throwable e){
			LOGGER.error("task sync app info error:{}",e.getMessage(),e);
		}
		LOGGER.debug("APP cache ad relate info over....");
	}
	private void cacheVideoInfo(){
		LOGGER.debug("VIDEO cache ad relate info begin....");
		try{
			List<Object> objList= rtbDbService.getObjeList(AdVideo.class,false);
			if(CollectionUtils.isEmpty(objList)){
				LOGGER.warn("task get ad video info empty!");
				return;
			}
			List<AdVideo>  vdList=(List)objList;
			RtbCacheDao catcheDao =BasicDaoFactory.getCatcheDao();
			Map<String,String> vdMap=new HashMap<String,String>(vdList.size());
			for(AdVideo vd:vdList){
				vdMap.put(vd.getId(), JsonUtils.toJson(vd));
			}
			catcheDao.cacheCommonData(RtbCacheDao.COMMON_CACHE_TYPE_VIDEO,vdMap);
		}catch(DbSyncException e){
			
			LOGGER.error("task sync video info error:{}",e.getMsg(),e);
			
		}catch(Throwable e){
			LOGGER.error("task sync video info error:{}",e.getMessage(),e);
		}
		LOGGER.debug("VIDEO cache ad relate info over....");
	}
	private void cacheMaterialInfo(){
		LOGGER.debug("MATERIAL cache ad relate info begin....");
		try{
			List<Object> objList= rtbDbService.getObjeList(Material.class,false);
			if(CollectionUtils.isEmpty(objList)){
				throw new DbSyncException("task get ad material info empty!");
			}
			List<Material>  mateList=(List)objList;
			RtbCacheDao catcheDao =BasicDaoFactory.getCatcheDao();
			Map<String,String> mateMap=new HashMap<String,String>(mateList.size());
			for(Material mate:mateList){
				mateMap.put(mate.getId(), JsonUtils.toJson(mate));
			}
			catcheDao.cacheCommonData(RtbCacheDao.COMMON_CACHE_TYPE_MATERIAL,mateMap);
		}catch(DbSyncException e){
			
			LOGGER.error("task sync material info error:{}",e.getMsg(),e);
			
		}catch(Throwable e){
			LOGGER.error("task sync material info error:{}",e.getMessage(),e);
		}
		LOGGER.debug("MATERIAL cache ad relate info over....");
	}
	private void cacheOrientationInfo(){
		LOGGER.debug("ORIENTATION cache ad relate info begin....");
		try{
			List<Object> objList= rtbDbService.getObjeList(AdOrientation.class,false);
			if(CollectionUtils.isEmpty(objList)){
				LOGGER.warn("task get ad orientation info empty!");
				return;
			}
			List<AdOrientation>  ortList=(List)objList;
			RtbCacheDao catcheDao =BasicDaoFactory.getCatcheDao();
			Map<String,String> ortMap=new HashMap<String,String>(ortList.size());
			for(AdOrientation ort:ortList){
				ortMap.put(ort.getId(), JsonUtils.toJson(ort));
			}
			catcheDao.cacheCommonData(RtbCacheDao.COMMON_CACHE_TYPE_ORIENTATION,ortMap);
		}catch(DbSyncException e){
			
			LOGGER.error("task sync orientation info error:{}",e.getMsg(),e);
			
		}catch(Throwable e){
			LOGGER.error("task sync orientation info error:{}",e.getMessage(),e);
		}
		LOGGER.debug("ORIENTATION cache ad relate info over....");
	}
}

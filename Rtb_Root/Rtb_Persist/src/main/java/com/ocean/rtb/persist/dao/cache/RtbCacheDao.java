package com.ocean.rtb.persist.dao.cache;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.rtb.persist.common.DbSyncException;
import com.ocean.rtb.persist.common.RtbConstants;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月16日 
      @version 1.0 
 */
@Repository
public class RtbCacheDao extends AbstractCacheDao{
	protected final Logger log = LoggerFactory.getLogger(RtbCacheDao.class);
	private static final String CACHE_RTB_ADSYNC_SPACEIDS_PREFIX="cache::rtb::adsync::spaceids::";
	private static final String CACHE_RTB_ADSYNC_SPACINFO_PREFIX="cache::rtb::adsync::spaceinfo::";
	
	private static final String CACHE_RTB_ADSYNC_APP_PREFIX="cache::rtb::adsync::app::";
	private static final String CACHE_RTB_ADSYNC_VIDEO_PREFIX="cache::rtb::adsync::video::";
	private static final String CACHE_RTB_ADSYNC_PROTIME_PREFIX="cache::rtb::adsync::protime::";
	private static final String CACHE_RTB_ADSYNC_MATERIAL_PREFIX="cache::rtb::adsync::material::";
	private static final String CACHE_RTB_ADSYNC_ORIENTATION_PREFIX="cache::rtb::adsync::orientation::";
	
	private static final String CACHE_RTB_ADSYNC_ADIDS_PAGE_PREFIX="cache::rtb::adsync::adids::page::";
	private static final String CACHE_RTB_ADSYNC_ADINFO_PAGE_PREFX="cache::rtb::adsync::adinfo::page::";
	private static final String CACHE_SLIPT="::";

	public  static final int  COMMON_CACHE_TYPE_SPACE=1;
	public  static final int  COMMON_CACHE_TYPE_APP=2;
	public  static final int  COMMON_CACHE_TYPE_VIDEO=3;
	public  static final int  COMMON_CACHE_TYPE_PROTIME=4;
	public  static final int  COMMON_CACHE_TYPE_MATERIAL=5;
	public  static final int  COMMON_CACHE_TYPE_ORIENTATION=6;
	@Autowired
	private StringRedisTemplate redisTemplate;
	private int getCacheTimeOut(){
		return SystemContext.getDynamicPropertyHandler().getInt(RtbConstants.REDIS_CACHE_EXPIRE,60);
	}
	//广告位信息缓存-按广告位类型存储Set key:spaceType  value:<spaceId>
	public void cacheSpaceIds(Set<String> spaceIds){
		if(CollectionUtils.isEmpty(spaceIds)){
			return;
		}
		String key=CACHE_RTB_ADSYNC_SPACEIDS_PREFIX;
		redisTemplate.opsForSet().add(key,spaceIds.toArray(new String[spaceIds.size()]));
		redisTemplate.boundSetOps(key).expire(getCacheTimeOut(), TimeUnit.SECONDS);
	
	}
	public Set<String> getSpaceIds(){
		return  redisTemplate.opsForSet().members(CACHE_RTB_ADSYNC_SPACEIDS_PREFIX);
	}
	public HashMap<Object,Object>   getAllSpaceInfo(){
		Map<Object,Object> map= redisTemplate.opsForHash().entries(CACHE_RTB_ADSYNC_SPACINFO_PREFIX);
		if(CollectionUtils.isEmpty(map)){
			return null;
		}
		return (HashMap)map;
		

	}
	public Object getSpecificCommonData(int type,String hashKey){
		String key=getCommonCacheKey( type);
		return  redisTemplate.opsForHash().get(key, hashKey);

	}
	private String getCommonCacheKey(int type){
		if(type==COMMON_CACHE_TYPE_SPACE){
			return CACHE_RTB_ADSYNC_SPACINFO_PREFIX;
		}else if(type==COMMON_CACHE_TYPE_APP){
			return CACHE_RTB_ADSYNC_APP_PREFIX;
		}else if(type==COMMON_CACHE_TYPE_VIDEO){
			return CACHE_RTB_ADSYNC_VIDEO_PREFIX;	
		}else if(type==COMMON_CACHE_TYPE_PROTIME){
			return CACHE_RTB_ADSYNC_PROTIME_PREFIX;
		}else if(type==COMMON_CACHE_TYPE_MATERIAL){
			return CACHE_RTB_ADSYNC_MATERIAL_PREFIX;
		}else if(type==COMMON_CACHE_TYPE_ORIENTATION){
			return CACHE_RTB_ADSYNC_ORIENTATION_PREFIX;
		}else{
			throw new DbSyncException("no such common cache key type:"+type);
		}
		
	}

	public void cacheCommonData(int type ,Map<String,String> objMap){
		String key=getCommonCacheKey(type);
		redisTemplate.opsForHash().putAll(key, objMap);
		redisTemplate.boundSetOps(key).expire(getCacheTimeOut()*2, TimeUnit.SECONDS);
	}
	
	//cache adids by page
	//key:page,value:adids
	public void cacheAdIdsByPage(int spaceId,int page, List<String> adIds){
		if(CollectionUtils.isEmpty(adIds)){
			return ;
		}
		String key=CACHE_RTB_ADSYNC_ADIDS_PAGE_PREFIX+spaceId+CACHE_SLIPT+page;
		redisTemplate.opsForList().rightPushAll(key, adIds);
		redisTemplate.boundSetOps(key).expire(getCacheTimeOut(), TimeUnit.SECONDS);
	}
	
	public List<String> getAdIdsByPage(int spaceId,int page){
		String key=CACHE_RTB_ADSYNC_ADIDS_PAGE_PREFIX+spaceId+CACHE_SLIPT+page;
		return redisTemplate.opsForList().range(key, 0, -1);
	}
	//cache adInfo by page
	//key:adid,value:adInfo
	public void cacheAdInfoBySpaceId(int spaceId, Map<String,String> adInfoMap){
		if(CollectionUtils.isEmpty(adInfoMap)){
			return ;
		}
		String key=CACHE_RTB_ADSYNC_ADINFO_PAGE_PREFX+spaceId;
		redisTemplate.opsForHash().putAll(key, adInfoMap);
		redisTemplate.boundSetOps(key).expire(getCacheTimeOut(), TimeUnit.SECONDS);
	}
	public Object getAdInfoBySidAndAdId(String spaceId,String adId){
		String key=CACHE_RTB_ADSYNC_ADINFO_PAGE_PREFX+spaceId;
		return  redisTemplate.opsForHash().get(key, adId);
	}
	public HashMap<Object,Object> getAdInfoBySpaceId(String spaceId){
		String key=CACHE_RTB_ADSYNC_ADINFO_PAGE_PREFX+spaceId;
		Map<Object,Object> map= redisTemplate.opsForHash().entries(key);
		if(CollectionUtils.isEmpty(map)){
			return null;
		}
		return (HashMap)map;
	}
	
	//*************************以上为广告业务相关************************
	
	public String getStrValue(String key){
		return redisTemplate.opsForValue().get(key);
	}
	public void setStrValue(String key,String value,long timeout,TimeUnit unit){
		 redisTemplate.opsForValue().set(key, value, timeout, unit);;
	}
}

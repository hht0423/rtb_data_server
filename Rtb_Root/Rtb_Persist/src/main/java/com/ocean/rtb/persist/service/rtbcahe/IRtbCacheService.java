package com.ocean.rtb.persist.service.rtbcahe;
import java.util.List;

import com.inveno.base.BaseModel;
import com.ocean.rtb.persist.bean.ad.AdInfo;
import com.ocean.rtb.persist.bean.ad.AdSpace;
import com.ocean.rtb.persist.bean.cache.AdProTimeCacheBean;
import com.ocean.rtb.persist.common.DbSyncException;
public interface IRtbCacheService {
	public List<AdSpace>   getAllSpaceInfo();
/*	public void cacheSpaceInfo(List<AdSpace> sapaceList);*/
	public List<String> getAdIdsByPage(int spaceId,int page,int source);
	public List<AdInfo>  getSelfAdInfoBySpaceId(String spaceId,List<String> adIds)throws DbSyncException;
	public Object getObectInfo(Class<? extends  BaseModel> clazz,String id);
	public  AdProTimeCacheBean getProTimeInfo(String adId);
}

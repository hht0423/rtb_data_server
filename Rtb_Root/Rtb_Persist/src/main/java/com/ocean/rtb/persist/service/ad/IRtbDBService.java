package com.ocean.rtb.persist.service.ad;

import java.util.List;

import com.inveno.base.BaseModel;
import com.ocean.rtb.persist.bean.ad.AdInfo;
import com.ocean.rtb.persist.bean.ad.AdThirdSourceInfo;
import com.ocean.rtb.persist.common.DbSyncException;

public interface IRtbDBService {
	public List<Object> getObjeList(Class<? extends  BaseModel> clazz,boolean isOnlyId) ;
	public List<Object> getThirdSourceIds(Integer spaceId,Integer from,Integer size);
	public List<AdThirdSourceInfo> getThirdSourceAdByIds(Integer spaceId,List<String> ids);
	public List<Object> getValidAdData(Integer spaceId,Integer from,Integer size,boolean isOnlyId) throws DbSyncException;
	public List<AdInfo> getAdInfoByIds(Integer spaceId,List<Object> ids);
	public List<Object> getValidProTimeInfo(boolean isOnlyId,List<String> ids);

}

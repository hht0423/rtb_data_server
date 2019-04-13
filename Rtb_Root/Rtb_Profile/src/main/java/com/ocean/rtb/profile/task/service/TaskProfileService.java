package com.ocean.rtb.profile.task.service;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ocean.core.common.system.SystemContext;
import com.ocean.rtb.persist.common.DbSyncException;
import com.ocean.rtb.persist.common.RtbConstants;
import com.ocean.rtb.persist.common.RtbLogManager;
import com.ocean.rtb.persist.dao.BasicDaoFactory;
import com.ocean.rtb.persist.dao.cache.RtbCacheDao;
import com.ocean.rtb.persist.service.ad.IRtbDBService;
@Component(value="TaskProfileService")
public class TaskProfileService implements ITaskProfileService{
	private  static final Logger  LOGGER = RtbLogManager.getSchedualLogger();
	
	@Autowired 
	private IRtbDBService rtbDbService;
	@Override
	public void dataCache(String key,String value) throws DbSyncException {
		// TODO Auto-generated method stub
		long expire=SystemContext.getDynamicPropertyHandler().getLong(RtbConstants.CACHE_EXPIRED_KEY, 1000*60);
		RtbCacheDao dao=BasicDaoFactory.getCatcheDao();
		dao.setStrValue(key, value, expire, TimeUnit.MILLISECONDS);
	}

}

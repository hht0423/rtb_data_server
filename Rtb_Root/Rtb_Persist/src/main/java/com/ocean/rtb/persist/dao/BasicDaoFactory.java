package com.ocean.rtb.persist.dao;

import com.ocean.core.common.system.SystemContext;
import com.ocean.rtb.persist.dao.cache.RtbCacheDao;

public class BasicDaoFactory {
	public static final RtbDbDao getDbDao(){
		RtbDbDao rtbDbDao = (RtbDbDao) SystemContext.getServiceHandler().getService(RtbDbDao.class);
		return rtbDbDao;
	}
	public static final RtbCacheDao getCatcheDao(){
		RtbCacheDao catcheDao = (RtbCacheDao) SystemContext.getServiceHandler().getService(RtbCacheDao.class);
		return catcheDao;
	}
}

package com.ocean.rtb.report.service.servlet;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ocean.core.common.JsonUtils;
import com.ocean.rtb.persist.common.RtbLogManager;
import com.ocean.rtb.persist.dao.BasicDaoFactory;
import com.ocean.rtb.persist.dao.cache.RtbCacheDao;
import com.ocean.rtb.report.server.bean.AdReportData;
@Component
public class RtbReportService implements IRtbReportService {
	private static final String RTB_REPORT_CACHE_PREFIX="";
	private static final Logger LOGGER= RtbLogManager.getBusinessLogger();
	@Override
	public AdReportData getCacheReportData(String bid) {
		// TODO Auto-generated method stub
		try{
			RtbCacheDao dao=BasicDaoFactory.getCatcheDao();
			String reportDJson= dao.getStrValue(RTB_REPORT_CACHE_PREFIX+bid);
			if(StringUtils.isNotEmpty(reportDJson)){
				return JsonUtils.toBean(reportDJson, AdReportData.class);
			}
		}catch(Throwable e){
			LOGGER.error("get report data from cahe error:{}",e.getMessage(),e);
		}

		return null;
	}

}

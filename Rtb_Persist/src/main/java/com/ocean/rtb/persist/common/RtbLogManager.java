package com.ocean.rtb.persist.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RtbLogManager {
	private  static final Logger LOG_BUSINESS = LogManager.getLogger("business");
	private  static final Logger LOG_SCHEDUAL= LogManager.getLogger("schedual");
	private  static final Logger LOG_REPORT= LogManager.getLogger("report");
	

    public static  Logger getBusinessLogger(){
    	return LOG_BUSINESS;
    }
    public static  Logger getSchedualLogger(){
    	return LOG_SCHEDUAL;
    }
    public static  Logger getReportLogger(){
    	return LOG_REPORT;
    }
}

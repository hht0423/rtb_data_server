package com.ocean.rtb.adsync.task.server;

import com.ocean.core.common.system.SystemContext;
import com.ocean.rtb.persist.common.RtbConstants;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月31日 
      @version 1.0 
 */
public class AdSyncSchedualerHandler {
	private   AdSyncSchedualer disServer=null;
    private AdSyncSchedualerHandler(){
    	
    }
    private static class BuilderSinglton{
    	private static final AdSyncSchedualerHandler builder=new AdSyncSchedualerHandler();
    }
    public static AdSyncSchedualerHandler builder(){
    	return BuilderSinglton.builder;
    }
	public   AdSyncSchedualer getServer(){
		try {
			if(disServer==null){
				synchronized(AdSyncSchedualerHandler.class){
					if(disServer==null){
		            
		            		long interval=SystemContext.getDynamicPropertyHandler().getLong(RtbConstants.RTB_ADSYNC_INTERVAL,1000*60);
		            		AbstractSchedualer.Args args=new AbstractSchedualer.Args();
	            	    	args.setInterval(interval);
							disServer=new AdSyncSchedualer(args);
	
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return disServer;
	}
  
}

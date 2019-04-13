package com.ocean.rtb.profile.task.server;

import com.ocean.core.common.system.SystemContext;
import com.ocean.rtb.persist.common.RtbConstants;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月31日 
      @version 1.0 
 */
public class ProfileCacheSchedualerHandler {
	private   ProfileCacheSchedualer disServer=null;
    private ProfileCacheSchedualerHandler(){
    	
    }
    private static class BuilderSinglton{
    	private static final ProfileCacheSchedualerHandler builder=new ProfileCacheSchedualerHandler();
    }
    public static ProfileCacheSchedualerHandler builder(){
    	return BuilderSinglton.builder;
    }
	public   ProfileCacheSchedualer getServer(){
		try {
			if(disServer==null){
				synchronized(ProfileCacheSchedualerHandler.class){
					if(disServer==null){
						    int queueSize=SystemContext.getDynamicPropertyHandler().getInt(RtbConstants.TASK_QUEUE_SIZE, 128);
		            		AbstractSchedualer.Args args=new AbstractSchedualer.Args();
	            	    	args.setQueueSize(queueSize);
							disServer=new ProfileCacheSchedualer(args);
	
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

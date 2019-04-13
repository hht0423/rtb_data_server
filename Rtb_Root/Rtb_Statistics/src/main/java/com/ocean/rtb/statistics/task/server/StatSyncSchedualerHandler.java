package com.ocean.rtb.statistics.task.server;

import com.ocean.core.common.system.SystemContext;
import com.ocean.rtb.persist.common.RtbConstants;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月31日 
      @version 1.0 
 */
public class StatSyncSchedualerHandler {
	private   StatSyncSchedualer disServer=null;
    private StatSyncSchedualerHandler(){
    	
    }
    private static class BuilderSinglton{
    	private static final StatSyncSchedualerHandler builder=new StatSyncSchedualerHandler();
    }
    public static StatSyncSchedualerHandler builder(){
    	return BuilderSinglton.builder;
    }
	public   StatSyncSchedualer getServer(){
		try {
			if(disServer==null){
				synchronized(StatSyncSchedualerHandler.class){
					if(disServer==null){
						    int queueSize=SystemContext.getDynamicPropertyHandler().getInt(RtbConstants.TASK_QUEUE_SIZE, 128);
		            		AbstractSchedualer.Args args=new AbstractSchedualer.Args();
	            	    	args.setQueueSize(queueSize);
							disServer=new StatSyncSchedualer(args);
	
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

package com.ocean.rtb.adsync.task.server;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月31日 
      @version 1.0 
 */
public class AdSyncSchedualerHandler {
	private   AdSyncSchedualer disServer=null;
    private  final Object lock=new Object();

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
				synchronized(lock){
					if(disServer==null){
		            		long interval=1000*60;
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

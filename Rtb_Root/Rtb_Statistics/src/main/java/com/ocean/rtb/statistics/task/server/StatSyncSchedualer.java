package com.ocean.rtb.statistics.task.server;
import org.apache.logging.log4j.Logger;

import com.ocean.core.common.lifecycle.LifecycleException;
import com.ocean.core.common.system.SystemContext;
import com.ocean.rtb.persist.common.DbSyncException;
import com.ocean.rtb.persist.common.RtbLogManager;
import com.ocean.rtb.statistics.task.service.TaskStatSyncService;
public class StatSyncSchedualer extends AbstractSchedualer{
	private  static final Logger  LOGGER = RtbLogManager.getSchedualLogger();
	 public StatSyncSchedualer(Args args) {
		super(args);
		// TODO Auto-generated constructor stub
	}
	
	/* 
	  * @see DefaultLifecycle#init0()
	  */
	 @Override
	 protected void init0() throws LifecycleException {
		try {
			 
			 //statistics asyn service
			 TaskStatSyncService statCacheService=(TaskStatSyncService)SystemContext.getServiceHandler().getService("statSync");
			 Invoke statAsynInvok=new Invoke(statCacheService);
			 this.addLifecycleListener(statAsynInvok);
			 

		} catch (DbSyncException e) {
			// TODO Auto-generated catch block
			LOGGER.error("server init ,get service error{}",e.getMsg(),e);
		}catch (Throwable e) {
			LOGGER.error("server init ,get service error{}",e.getMessage(),e);
		}
 		
	 }
	
	 /* 
	  * @see DefaultLifecycle#start0()
	  */
	 @Override
	 protected void start0() throws LifecycleException {

	 }
	
	 /* 
	  * @see DefaultLifecycle#destroy0()
	  */
	 @Override
	 protected void destroy0() throws LifecycleException {

	 }

}

package com.ocean.rtb.profile.task.server;
import org.apache.logging.log4j.Logger;

import com.ocean.core.common.lifecycle.LifecycleException;
import com.ocean.core.common.system.SystemContext;
import com.ocean.rtb.persist.common.DbSyncException;
import com.ocean.rtb.persist.common.RtbLogManager;
import com.ocean.rtb.profile.task.service.TaskProfileService;
public class ProfileCacheSchedualer extends AbstractSchedualer{
	private  static final Logger  LOGGER = RtbLogManager.getSchedualLogger();
	 public ProfileCacheSchedualer(Args args) {
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
			 TaskProfileService statCacheService=(TaskProfileService)SystemContext.getServiceHandler().getService(TaskProfileService.class);
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

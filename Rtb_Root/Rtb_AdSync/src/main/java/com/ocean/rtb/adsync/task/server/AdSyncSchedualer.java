package com.ocean.rtb.adsync.task.server;
import org.apache.logging.log4j.Logger;

import com.ocean.core.common.lifecycle.LifecycleException;
import com.ocean.core.common.system.SystemContext;
import com.ocean.rtb.adsync.task.TaskAdSyncService;
import com.ocean.rtb.adsync.task.TaskCommonSyncService;
import com.ocean.rtb.persist.common.DbSyncException;
import com.ocean.rtb.persist.common.RtbLogManager;
public class AdSyncSchedualer extends AbstractSchedualer{
	private  static final Logger  LOGGER = RtbLogManager.getSchedualLogger();
	 public AdSyncSchedualer(Args args) {
		super(args);
		// TODO Auto-generated constructor stub
	}
	
	/* 
	  * @see DefaultLifecycle#init0()
	  */
	 @Override
	 protected void init0() throws LifecycleException {
		try {
			 //ad relate info service
			 TaskCommonSyncService pkgAsynService=(TaskCommonSyncService)SystemContext.getServiceHandler().getService("commonSync");
			 Invoke pkgAsynInvoke=new Invoke(pkgAsynService);
			 this.addLifecycleListener(pkgAsynInvoke);
			 
			 //ad asyn service
			 TaskAdSyncService appListAsynService=(TaskAdSyncService)SystemContext.getServiceHandler().getService("adSync");
			 Invoke appListAsynInvok=new Invoke(appListAsynService);
			 this.addLifecycleListener(appListAsynInvok);
			 

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

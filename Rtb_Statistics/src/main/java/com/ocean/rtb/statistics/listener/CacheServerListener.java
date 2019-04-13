package com.ocean.rtb.statistics.listener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.Logger;

import com.ocean.core.common.lifecycle.LifecycleException;
import com.ocean.rtb.persist.common.RtbLogManager;
import com.ocean.rtb.statistics.task.server.StatSyncSchedualerHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2019年3月16日 
      @version 1.0 
 */
public class CacheServerListener implements ServletContextListener {
    private StatSyncSchedualerHandler serverHandler=null;
	private  final Logger logger = RtbLogManager.getSchedualLogger();
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		logger.info("schedualer server shutdown......");
		if (null != serverHandler.getServer()) {
			try {
				serverHandler.getServer().destroy();
			} catch (LifecycleException e) {
				// TODO Auto-generated catch block
				logger.info("schedualer server destroy error!");
			}
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {	
			logger.error("schedualer server shutdown error!",e);
		}
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		serverHandler=StatSyncSchedualerHandler.builder();
		logger.info("start the schedualer server......");
		try {
			serverHandler.getServer().start();
		} catch (LifecycleException e) {
			// TODO Auto-generated catch block
			logger.info("start the schedualer server error! {}",e.getMessage(),e);
		}

	}
}

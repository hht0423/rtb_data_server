package com.ocean.rtb.adsync.listener;

import java.util.concurrent.CountDownLatch;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.logging.log4j.Logger;
import com.ocean.rtb.adsync.server.AdSyncServer;
import com.ocean.rtb.adsync.server.zk.ZookeeperRegister;
import com.ocean.rtb.persist.common.RtbLogManager;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2019年3月16日 
      @version 1.0 
 */
public class AdSyncServerListener implements ServletContextListener {
    private AdSyncServer server=null;
	private  final Logger logger = RtbLogManager.getBusinessLogger();
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		logger.info("AdSyncServer shutdown......");
		if (null != server) {
			server.shutdownServer();
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {	
			logger.error("AdSyncServer shutdown error!",e);
		}
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		CountDownLatch countdownlatch=new CountDownLatch(1);
		server = new AdSyncServer(countdownlatch);
		logger.info("start the AdSyncServer......");
		server.startServer();

		try {
			countdownlatch.await();
			logger.info("AdSyncServer started succeed......");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		logger.info("register zookeeper node......");
		ZookeeperRegister regist=new ZookeeperRegister();
		regist.regist();
	}
}

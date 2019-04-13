package com.ocean.rtb.statistics.listener;

import java.util.concurrent.CountDownLatch;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.Logger;

import com.ocean.rtb.persist.common.RtbLogManager;
import com.ocean.rtb.statistics.server.StatServer;
import com.ocean.rtb.statistics.server.zk.ZookeeperRegister;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2019年3月16日 
      @version 1.0 
 */
public class StatServerListener implements ServletContextListener {
    private StatServer server=null;
	private  final Logger logger = RtbLogManager.getBusinessLogger();
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		logger.info("StatServer shutdown......");
		if (null != server) {
			server.shutdownServer();
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {	
			logger.error("StatServer shutdown error!",e);
		}
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		CountDownLatch countdownlatch=new CountDownLatch(1);
		server = new StatServer(countdownlatch);
		logger.info("start the StatServer......");
		server.startServer();

		try {
			countdownlatch.await();
			logger.info("StatServer started succeed......");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		logger.info("register zookeeper node......");
		ZookeeperRegister regist=new ZookeeperRegister();
		regist.regist();
	}
}

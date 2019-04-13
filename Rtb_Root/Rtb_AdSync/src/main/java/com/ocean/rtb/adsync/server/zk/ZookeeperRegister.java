package com.ocean.rtb.adsync.server.zk;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;

import com.inveno.distribute.client.CommonDistributeServiceManager;
import com.ocean.core.common.UniversalUtils;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.system.SystemContext;
import com.ocean.rtb.persist.common.RtbConstants;
public class ZookeeperRegister {
	protected static final Logger logger =MyLogManager.getLogger();
	private Register register;
	public  void regist(){		
		register = new Register();
		register.start();
	}
	private static class Register extends Thread{
		
		@Override
		public void run(){
			
			CommonDistributeServiceManager serviceManager = new CommonDistributeServiceManager();
			serviceManager.setZkAddress(SystemContext.getStaticPropertyHandler().get(RtbConstants.ZOOKEEPER_ADDRESS));
			serviceManager.init();
			String ip = SystemContext.getStaticPropertyHandler().get(RtbConstants.ADSYNC_ADDRESS);
			// 如果没有配置proxyip 则用本机内网地址
			if(StringUtils.isEmpty(ip)){
				ip = UniversalUtils.getHostAddress();
			}
			int port = SystemContext.getDynamicPropertyHandler().getInt(RtbConstants.THRIFT_PORT,9091);
			String proxyAddress = ip + ":" + port;
			String path =  SystemContext.getStaticPropertyHandler().get(RtbConstants.ZOOKEEPER_PATH);
			serviceManager.regist(path, proxyAddress);
			logger.info("Zookeeper register succeed,path={}, proxyAddress={}",path, proxyAddress);
		}
	}
}

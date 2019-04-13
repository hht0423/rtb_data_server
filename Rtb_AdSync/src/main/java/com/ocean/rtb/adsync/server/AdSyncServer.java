package com.ocean.rtb.adsync.server;

import java.util.concurrent.CountDownLatch;

import org.apache.logging.log4j.Logger;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.system.SystemContext;
import com.ocean.rtb.persist.bean.thrift.adsync.RtbSync;
import com.ocean.rtb.persist.common.RtbConstants;

public class AdSyncServer {
	protected final Logger log =MyLogManager.getLogger();
	private static final int DEFAULT_MAX_WORKER = 8;
	private static final int DEFAULT_IO_SELECTOR_COUNT = 4;
	private TServer server;
	private CountDownLatch countdownlatch;
	public AdSyncServer(CountDownLatch countdownlatch){
		this.countdownlatch=countdownlatch;
	}
    public void startServer(){
 		new Thread()
        {
            public void run()
            {
            	
            	try {
            		int port=SystemContext.getDynamicPropertyHandler().getInt(RtbConstants.THRIFT_PORT,9091);
            		int timeOut=SystemContext.getDynamicPropertyHandler().getInt(RtbConstants.THRIFT_TIME_OUT,100);
            		int queueBufferSize = SystemContext.getDynamicPropertyHandler().getInt(RtbConstants.THRIFT_ACCEPT_QUEUE_SIZE, 8);
            		
            		
            		int maxWorker = SystemContext.getDynamicPropertyHandler().getInt(RtbConstants.THRIFT_MAX_WORKER, DEFAULT_MAX_WORKER);
            		int selectorCount = SystemContext.getDynamicPropertyHandler().getInt(RtbConstants.THRIFT_IO_SELECTOR_COUNT, DEFAULT_IO_SELECTOR_COUNT);
            		TProcessor tProcessor=new RtbSync.Processor<AdSyncInvoker>(new AdSyncInvoker());
					TNonblockingServerSocket serverTransport=new TNonblockingServerSocket(port,timeOut);
					
	                //多线程半同步半异步  (IO thread blocking,worker thread nonblocking)
	                TThreadedSelectorServer.Args tArgs = new TThreadedSelectorServer.Args(serverTransport);  
	                tArgs.processor(tProcessor);  
	                tArgs.transportFactory(new TFramedTransport.Factory());  
	               
	                //二进制协议  
	                tArgs.protocolFactory(new TBinaryProtocol.Factory()); 
	                tArgs.acceptQueueSizePerThread(queueBufferSize);//官方缓冲队列大小
	                tArgs.workerThreads(maxWorker);//官方提供包的线程池大小
                    tArgs.selectorThreads(selectorCount);// IO线程 
	    			//tArgs.preStartCoreThread(true);// 预先启动核心池
	                server=new TThreadedSelectorServer(tArgs);
	                
	                countdownlatch.countDown();//只能放在这 了，下面的方法会一直阻塞
	                server.serve();
				} catch (TTransportException e) {
					// TODO Auto-generated catch block
					log.error("rpc ProxyServer startup fired TTransportException!",e);
				} catch (Exception e) {
 					// TODO Auto-generated catch block
 					log.error("rpc ProxyServer startup fired Exception!",e);
 				}
            }
        }.start();
 	}
    public void shutdownServer(){
		try {
			if (null != server) {
				server.stop();
				log.info("rpc ProxyServer stop success");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
    }
}

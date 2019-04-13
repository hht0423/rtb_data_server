package com.ocean.rtb.adsync.task.server;
import com.ocean.core.common.lifecycle.ILifecycleListener;
import com.ocean.core.common.lifecycle.LifeCycle;
import com.ocean.core.common.lifecycle.LifecycleEvent;
import com.ocean.core.common.lifecycle.LifecycleState;
import com.ocean.rtb.adsync.task.ITaskAdSyncService;
import com.ocean.rtb.persist.common.DbSyncException;
import com.ocean.rtb.persist.common.RtbLogManager;

import org.apache.logging.log4j.Logger;
public abstract class AbstractSchedualer extends LifeCycle{
	private  static final Logger  logger = RtbLogManager.getSchedualLogger();
	private  final Args args;
	public static class Args{
		private long interval;
		public long getInterval() {
			return interval;
		}

		public Args setInterval(long interval) {
			this.interval = interval;
			return this;
		}
	}
	public AbstractSchedualer(Args args){
		this.args=args;
		this.initServer();
	}
	private void initServer(){
		if(args==null){
			logger.error("init schedual server error:args empty!");
			throw new DbSyncException("init schedual  server error:args empty!");
		}
	}
	public class Invoke extends Thread implements ILifecycleListener{
		private ITaskAdSyncService syncService;
		
		public Invoke(ITaskAdSyncService syncService){
			this.syncService=syncService;
		}
		public void run(){
			while(true){
				if(AbstractSchedualer.this.getState()==LifecycleState.DESTROYED){
					break;
				}
				try{
					syncService.dataSync();
				}catch(DbSyncException e){
					logger.error("schedualer server running error(MonitorException):{}",e.getMsg(),e);
				}
				catch(Exception e){
					logger.error("schedualer server running error(Exception):{}",e.getMessage(),e);
				}catch(Throwable e){
					logger.error("schedualer server running error(Throwable):{}",e.getMessage(),e);
				}
				try {
					Thread.sleep(args.interval);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					logger.error("schedualer server sleeping error:{}",e.getMessage(),e);
				}
			}
		}

		@Override
		public void lifecycleEvent(LifecycleEvent event) {
			// TODO Auto-generated method stub
			if(event.getState()==LifecycleState.INITIALIZING){
				this.init();
			}else if(event.getState()==LifecycleState.INITIALIZED){
				this.start();
			}else if(event.getState()==LifecycleState.DESTROYING){
				logger.info("schedualer server destrying.....");
			}else if(event.getState()==LifecycleState.DESTROYED){
				logger.info("schedualer server destryed.....");
			}
			
		}
		
		private void init(){

		}
	}
	public Args getArgs() {
		return args;
	}
}

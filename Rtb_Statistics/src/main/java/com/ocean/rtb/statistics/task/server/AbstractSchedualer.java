package com.ocean.rtb.statistics.task.server;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.ocean.core.common.lifecycle.ILifecycleListener;
import com.ocean.core.common.lifecycle.LifeCycle;
import com.ocean.core.common.lifecycle.LifecycleEvent;
import com.ocean.core.common.lifecycle.LifecycleState;
import com.ocean.rtb.persist.common.DbSyncException;
import com.ocean.rtb.persist.common.RtbLogManager;
import com.ocean.rtb.statistics.task.data.AbstractSyncTask;
import com.ocean.rtb.statistics.task.service.ITaskStatSyncService;

import org.apache.logging.log4j.Logger;
public abstract class AbstractSchedualer extends LifeCycle{
	private  static final Logger  LOGGER = RtbLogManager.getSchedualLogger();
	private  final Args args;
	private final BlockingQueue<AbstractSyncTask> queue;
	public static class Args{
		private int queueSize=16;

		public int getQueueSize() {
			return queueSize;
		}

		public Args setQueueSize(int queueSize) {
			this.queueSize = queueSize;
			return this;
		}
	}
	public AbstractSchedualer(Args args){
		this.args=args;
		this.initServer();
		queue=new ArrayBlockingQueue(args.queueSize);
	}
	private void initServer(){
		if(args==null){
			LOGGER.error("init schedual server error:args empty!");
			throw new DbSyncException("init schedual  server error:args empty!");
		}
	}
	public void addTask(AbstractSyncTask task){
		queue.add(task);
	}
	public AbstractSyncTask getTask() throws InterruptedException{
		return queue.take();
	}
	public class Invoke extends Thread implements ILifecycleListener{
	  
		private ITaskStatSyncService syncService;
		
		public Invoke(ITaskStatSyncService syncService){
			this.syncService=syncService;
		
		}
		private AbstractSyncTask getTask() throws InterruptedException{
			return AbstractSchedualer.this.getTask();
		}
		public void run(){
			while(true){
				if(AbstractSchedualer.this.getState()==LifecycleState.DESTROYED){
					break;
				}
				try{
					AbstractSyncTask task=this.getTask();
					if(task!=null){
						LOGGER.debug("cache stat data :{}",task.formatData());
						syncService.dataCache(task.getKey(), task.formatData());
					}
				}catch(DbSyncException e){
					LOGGER.error("schedualer server running error(MonitorException):{}",e.getMsg(),e);
				}
				catch(Exception e){
					LOGGER.error("schedualer server running error(Exception):{}",e.getMessage(),e);
				}catch(Throwable e){
					LOGGER.error("schedualer server running error(Throwable):{}",e.getMessage(),e);
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
				LOGGER.info("schedualer server destrying.....");
			}else if(event.getState()==LifecycleState.DESTROYED){
				LOGGER.info("schedualer server destryed.....");
			}
			
		}
		
		private void init(){

		}
	}
	public Args getArgs() {
		return args;
	}
}

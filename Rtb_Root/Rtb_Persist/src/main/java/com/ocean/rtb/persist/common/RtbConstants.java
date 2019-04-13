package com.ocean.rtb.persist.common;

public interface RtbConstants {
	//server node index
    String PROXY_SERVER_NODE_INDEX="node.index";
	
	//thrift config
	String THRIFT_PORT="thrift_port";
	String THRIFT_TIME_OUT="thrift_time_out";
	String THRIFT_MAX_WORKER="thrift_max_worker";
	String THRIFT_MIN_WORKER="thrift_min_worker";
	String THRIFT_IO_SELECTOR_COUNT="thrift_io_selector_count";
	String THRIFT_ACCEPT_QUEUE_SIZE="accept_queue_size";
	
	//zookeeper config
	String ZOOKEEPER_ADDRESS="zookeeper.address";
	String ZOOKEEPER_PATH="zookeeper.path";
	String ZOOKEEPER_TIMEOUT="zookeeper.timeOut";
	
	//adsync server config
	String ADSYNC_ADDRESS="adsync.address";
	
	//redis cache time out
	String REDIS_CACHE_EXPIRE="cache.expire";
	//每次同步数据的一页大小
    int PAGE_SIZE=20;
	String RETURN_MSG_OK="ok";
	
	//ad type
	String AD_TYPE_THIRD="3000";
	
	//server node
	String NODE_ID="node.id";
	
	
	//stat cache expired key
	String CACHE_EXPIRED_KEY="cache.expired.key";
	
	
	//task queue size
	String TASK_QUEUE_SIZE="task.queue.size";
	
	
	String CACHE_SPLIT="::";
}

package com.ocean.rtb.statistics.service;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.JsonUtils;
import com.ocean.rtb.persist.bean.stat.SelfAdStat;
import com.ocean.rtb.persist.bean.stat.ThirdAdStat;
import com.ocean.rtb.persist.bean.stat.business.SrcBudget;
import com.ocean.rtb.persist.bean.thrift.common.RtbBalance;
import com.ocean.rtb.persist.bean.thrift.common.RtbSrcType;
import com.ocean.rtb.persist.bean.thrift.stat.RtbStatQueryData;
import com.ocean.rtb.persist.common.RtbConstants;
import com.ocean.rtb.persist.common.RtbLogManager;
import com.ocean.rtb.persist.common.RtbException;
import com.ocean.rtb.persist.dao.BasicDaoFactory;
import com.ocean.rtb.persist.dao.cache.RtbCacheDao;
import com.ocean.rtb.persist.service.stat.IStatDbService;
import com.ocean.rtb.statistics.common.StatConstants;
import com.ocean.rtb.statistics.server.bean.StatBalanceBean;
import com.ocean.rtb.statistics.server.bean.StatBusinessBean;
import com.ocean.rtb.statistics.task.data.StatCacheTask;
import com.ocean.rtb.statistics.task.server.StatSyncSchedualerHandler;
@Component
public class StatService implements IStatService{
	private  static final Logger  LOGGER = RtbLogManager.getBusinessLogger();
	private static final int INVALID=0;
	private static final int VALID=1;
	private static final int RTB_REPORT=2;
	private static final String NODEID_REPORT="1000";
	private static final String NODEID_RTB="2000";
	//上报服务，前缀1000开头;rtb服务前缀2000开头
	@Autowired
	private IStatDbService dbService;
	public RtbStatQueryData getSrcBalance(String srcId,RtbSrcType srcType){
		//获取广告主的总预算和余额
		//1-get data from cache
		RtbStatQueryData sd= getCacheDate(srcId);
		if(sd!=null){
			return sd;
		}
		
		//2-get from db
		List<SrcBudget> buget=dbService.getSrcBalance(srcId);
		if(CollectionUtils.isEmpty(buget)){
			throw new RtbException(MessageFormat.format("get balance is empty! src type:{0},srcId:{1}",srcType,srcId));
		}
		sd=new RtbStatQueryData();
		sd.setSrcId(srcId);
		sd.setBalanceTotal(buget.get(0).getTotal());
		if(srcType==RtbSrcType.SrcTypeSelf){
			//直客广告，获取竞价成功，并且成功上报到report模块统计信息
			Map<Long,RtbBalance>  rtbBlceMap=getSelfStat(srcId);
			sd.setBalanceRemain(buget.get(0).getBalance()-getTotalCost(rtbBlceMap));//流水表中的余额减去当前这个小时还没有扣款的额度
			sd.setBalance(rtbBlceMap);
		}else{
			Map<Long,RtbBalance>  rtbBlceMap=getThirdStat(srcId);
			sd.setBalanceRemain(buget.get(0).getBalance()-getTotalCost(rtbBlceMap));//流水表中的余额减去当前这个小时还没有扣款的额度
			sd.setBalance(rtbBlceMap);
		}
		
		//3-cache data to redis
		cacheData(sd);
		return sd;
	}
	private void cacheData(RtbStatQueryData sd){
		
		StatBusinessBean bb=cvtBB(sd);
		StatCacheTask task=new StatCacheTask();
		task.setData(bb);
		StatSyncSchedualerHandler.builder().getServer().addTask(task);
	}
	private StatBusinessBean cvtBB(RtbStatQueryData sd){
		StatBusinessBean bb=new StatBusinessBean();
		bb.setBalanceTotal(sd.getBalanceTotal());
		bb.setBalanceRemain(sd.getBalanceRemain());
		bb.setSrcId(sd.getSrcId());
		HashMap<Long,StatBalanceBean> statBlanceMap=new HashMap<Long,StatBalanceBean> ();
		Map<Long, RtbBalance> rtbBlanceMap=sd.getBalance();
		for(Entry<Long, RtbBalance> entry:rtbBlanceMap.entrySet()){
			RtbBalance rtbBalance=entry.getValue();
			StatBalanceBean statBalance=new StatBalanceBean();
			statBalance.setBalanceId(rtbBalance.getBalanceId());
			statBalance.setExpiredResults(rtbBalance.getExpiredResults());
			statBalance.setRtbResults(rtbBalance.getRtbResults());
			statBalance.setValidResults(rtbBalance.getValidResults());
			statBlanceMap.put(entry.getKey(), statBalance);
		}
		bb.setAdBalance(statBlanceMap);
		return bb;
	}
	private RtbStatQueryData cvtRtbStat(StatBusinessBean statBean){
		RtbStatQueryData rtbStat=new RtbStatQueryData();
		rtbStat.setBalanceTotal(statBean.getBalanceTotal());
		rtbStat.setBalanceRemain(statBean.getBalanceRemain());
		rtbStat.setSrcId(statBean.getSrcId());
		Map<Long,StatBalanceBean> statBlanceMap=statBean.getAdBalance();
		Map<Long, RtbBalance> rtbBlanceMap=new HashMap<Long, RtbBalance> ();
		for(Entry<Long,StatBalanceBean>  entry:statBlanceMap.entrySet()){
			StatBalanceBean statBalance =entry.getValue();
			RtbBalance rtbBalance=new RtbBalance();
			rtbBalance.setBalanceId(statBalance.getBalanceId());
			rtbBalance.setExpiredResults(statBalance.getExpiredResults());
			rtbBalance.setRtbResults(statBalance.getRtbResults());
			rtbBalance.setValidResults(statBalance.getValidResults());
			rtbBlanceMap.put(entry.getKey(), rtbBalance);
		}
		rtbStat.setBalance(rtbBlanceMap);
		return rtbStat;
	}
	private RtbStatQueryData getCacheDate(String srcId){
		try{
			RtbCacheDao dao=BasicDaoFactory.getCatcheDao();
			String data=dao.getStrValue(StatConstants.STAT_CACHE_KEY_PREFIX+srcId);
			StatBusinessBean statBean=JsonUtils.toBean(data, StatBusinessBean.class);
			if(statBean!=null){
				return this.cvtRtbStat(statBean);
			}
			LOGGER.debug("get cache data:{}",data);
		}catch(Throwable e){
			LOGGER.error("get stat info from cache error,srcId:{}",srcId,e);
		}
		return null;
		

	}
	private Map<Long,RtbBalance> getSelfStat(String srcId){
		//获取成功扣款额度
		List<Object> objList= dbService.getStatBySrcId(srcId, NODEID_REPORT,VALID, SelfAdStat.class);
		if(CollectionUtils.isEmpty(objList)){
			throw new RtbException(MessageFormat.format("get  stat info is empty,srcId:{0},nodeIdPrefix:{1}",srcId,NODEID_REPORT));
		}
		Map<Long,RtbBalance> vldBlceMap=this.cvtBlceMap((List)objList, VALID,null);

		//竞价成功，由于延迟还未扣费的额度
		List<Object> invldObjList= dbService.getStatBySrcId(srcId, NODEID_REPORT,INVALID, SelfAdStat.class);
		Map<Long,RtbBalance> invldBlceMap=this.cvtBlceMap((List)invldObjList, INVALID,vldBlceMap);
		
		//竞价成功，预计总预算额度
		List<Object> rtbObjList= dbService.getStatBySrcId(srcId, NODEID_RTB,null, SelfAdStat.class);
		Map<Long,RtbBalance> rtbBlceMap=this.cvtBlceMap((List)rtbObjList, RTB_REPORT,invldBlceMap);
		
		return rtbBlceMap;
		
	}
	private Map<Long,RtbBalance> getThirdStat(String srcId){
		//获取成功扣款额度
		List<Object> objList= dbService.getStatBySrcId(srcId, NODEID_REPORT,VALID, ThirdAdStat.class);
		if(CollectionUtils.isEmpty(objList)){
			throw new RtbException(MessageFormat.format("get third  stat info is empty,srcId:{0},nodeIdPrefix:{1}",srcId,NODEID_REPORT));
		}
		Map<Long,RtbBalance> vldBlceMap=this.cvtThirdBlceMap((List)objList, VALID,null);
		
		//竞价成功，由于延迟还未扣费的额度
		List<Object> invldObjList= dbService.getStatBySrcId(srcId, NODEID_REPORT,INVALID, ThirdAdStat.class);
		Map<Long,RtbBalance> invldBlceMap=this.cvtThirdBlceMap((List)invldObjList, INVALID,vldBlceMap);
		
		//竞价成功，预计总预算额度
		List<Object> rtbObjList= dbService.getStatBySrcId(srcId, NODEID_REPORT,INVALID, ThirdAdStat.class);
		Map<Long,RtbBalance> rtbBlceMap=this.cvtThirdBlceMap((List)rtbObjList, INVALID,invldBlceMap);
		return rtbBlceMap;
		
	}
	private Map<Long,RtbBalance> cvtBlceMap(List<SelfAdStat> statList,int type,Map<Long,RtbBalance> adBlceMap){
		if(type==VALID&&adBlceMap==null){
			adBlceMap=new HashMap<Long,RtbBalance> ();
		}else if(type==INVALID&&CollectionUtils.isEmpty(statList)){
			return adBlceMap;
		}
		
		for(SelfAdStat stat:statList){
			long adId=Long.parseLong(stat.getAdId());
			RtbBalance adBlce=adBlceMap.get(adId);
			if(adBlce==null){
				adBlce=new RtbBalance();
				adBlceMap.put(adId, adBlce);
			}
			adBlce.setBalanceId(String.valueOf(System.currentTimeMillis()));
			long cost=(long)(stat.getPrice()*stat.getCount());
			if(VALID==type){
				adBlce.setValidResults(adBlce.getValidResults()+cost);
			}else if(INVALID==type){
				adBlce.setExpiredResults(adBlce.getExpiredResults()+cost);
			}else if(RTB_REPORT==type){
				adBlce.setRtbResults(adBlce.getRtbResults()+cost);
			}
		}
		return adBlceMap;
	}
	private Map<Long,RtbBalance> cvtThirdBlceMap(List<ThirdAdStat> statList,int type,Map<Long,RtbBalance> adBlceMap){
		if(adBlceMap==null){
			adBlceMap=new HashMap<Long,RtbBalance> ();
		}

		for(ThirdAdStat stat:statList){
			RtbBalance adBlce=adBlceMap.get(1l);
			if(adBlce==null){
				adBlce=new RtbBalance();
				adBlceMap.put(1l, adBlce);
			}
			adBlce.setBalanceId(String.valueOf(System.currentTimeMillis()));
			long cost=(long)(stat.getPrice()*stat.getCount());
			if(VALID==type){
				adBlce.setValidResults(adBlce.getValidResults()+cost);
			}else if(INVALID==type){
				adBlce.setExpiredResults(adBlce.getExpiredResults()+cost);
			}else if(RTB_REPORT==type){
				adBlce.setRtbResults(adBlce.getRtbResults()+cost);
			}
		}
		return adBlceMap;
	}
	private long getTotalCost(Map<Long,RtbBalance> adBlceMap){
		long totalCost=0;
		for(Entry<Long,RtbBalance> entry:adBlceMap.entrySet()){
			totalCost+=entry.getValue().getValidResults();
		}
		return totalCost;
	}
}

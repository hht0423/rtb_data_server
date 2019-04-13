package com.ocean.rtb.persist.service.ad;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.inveno.base.BaseModel;
import com.inveno.util.CollectionUtils;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.rtb.persist.bean.ad.AdInfo;
import com.ocean.rtb.persist.bean.ad.AdProTime;
import com.ocean.rtb.persist.bean.ad.AdThirdSourceInfo;
import com.ocean.rtb.persist.common.DbSyncException;
import com.ocean.rtb.persist.common.RtbLogManager;
import com.ocean.rtb.persist.dao.BasicDaoFactory;
import com.ocean.rtb.persist.dao.RtbDbDao;
/**
 * 
 * @Description: DB service
 * @author Alex
 * @date 2019年3月4日
 */
@Component(value="rtbDbService")
public class RtbDBService implements IRtbDBService{
	protected static final Logger LOGGER =RtbLogManager.getBusinessLogger();
	private static final String DATE_TIME_FORMAT="yyyy-MM-dd HH:mm";
	private static final String DATE_FORMAT="yyyy-MM-dd";
	private static final int DATE_MAX=1;
	private static final int DATE_MIN=0;
	//spaceInfo,appInfo,videoInfo search
	public List<Object> getObjeList(Class<? extends  BaseModel> clazz,boolean isOnlyId) {
		// TODO Auto-generated method stub
		RtbDbDao dao=BasicDaoFactory.getDbDao();
		DetachedCriteria c=DetachedCriteria.forClass(clazz);
		if(isOnlyId){
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.groupProperty("id"));
			projectionList.add(Projections.property("id"));
			c.setProjection(projectionList);
		}
		c.add(Restrictions.eq("status", 1));
		List<Object>  spaceList=dao.findByDetachedCriteria(c);
		if(CollectionUtils.isEmpty(spaceList)){
			return null;
		}
		return spaceList;
	}

	public List<AdInfo> getAdInfoByIds(Integer spaceId,List<Object> ids){
		RtbDbDao dao=BasicDaoFactory.getDbDao();
		DetachedCriteria c=DetachedCriteria.forClass(AdInfo.class);
		if(CollectionUtils.isNotEmpty(ids)){
			c.add(Restrictions.in("id", ids));
		}
		//正常上线的广告
		c.add(Restrictions.eq("online_status", 1));
		if(spaceId!=null){//获取指定席位的广告
			c.add(Restrictions.eq("spaceId", spaceId));
		}
		List<AdInfo> appList= dao.findByDetachedCriteria(c);

		return appList;
	}	
	//获取指定投放席位的广告
	public List<Object> getValidAdData(Integer spaceId,Integer from,Integer size,boolean isOnlyId) throws DbSyncException{
		RtbDbDao dao=BasicDaoFactory.getDbDao();
		DetachedCriteria c=DetachedCriteria.forClass(AdInfo.class);
		
		List<Object> adIds=this.getValidProTimeInfo(true,null);//首先通过时间过滤
		if(CollectionUtils.isEmpty(adIds)){
			throw new DbSyncException(MessageFormat.format("get valid adIds by proTime is empty,spaceId:{0}",spaceId));
		}

		c.add(Restrictions.in("id", this.cvtStrList(adIds)));
		//正常上线的广告
		c.add(Restrictions.eq("onlineStatus", 1));
		if(spaceId!=null){//获取指定席位的广告
			c.add(Restrictions.eq("spaceId", spaceId));
		}
		if(isOnlyId){
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.groupProperty("id"));
			projectionList.add(Projections.property("id"));
			c.setProjection(projectionList);
		}
		c.addOrder(Order.asc("id"));
		List<Object> appList=null;
		if(size==null||size==0){
			appList= dao.findByDetachedCriteria(c);
		}else{
			from=from==null?0:from;
			appList= dao.findByDetachedCriteria(c, from, size);
		}
		return appList;
	}	
	public List<Object> getValidThirdSourcecAdData(Integer spaceId,Integer from,Integer size,boolean isOnlyId) throws DbSyncException{
		RtbDbDao dao=BasicDaoFactory.getDbDao();
		DetachedCriteria c=DetachedCriteria.forClass(AdThirdSourceInfo.class);
	/*	
		List<Object> adIds=this.getValidProTimeInfo(true,null);//首先通过时间过滤
		if(CollectionUtils.isEmpty(adIds)){
			throw new DbSyncException(MessageFormat.format("get valid adIds by proTime is empty,spaceId:{0}",spaceId));
		}

		c.add(Restrictions.in("id", this.cvtStrList(adIds)));*/
		//正常上线的广告
		c.add(Restrictions.eq("onlineStatus", 1));
		if(spaceId!=null){//获取指定席位的广告
			c.add(Restrictions.eq("spaceId", spaceId));
		}
		if(isOnlyId){
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.groupProperty("id"));
			projectionList.add(Projections.property("id"));
			c.setProjection(projectionList);
		}
		c.addOrder(Order.asc("id"));
		List<Object> appList=null;
		if(size==null||size==0){
			appList= dao.findByDetachedCriteria(c);
		}else{
			from=from==null?0:from;
			appList= dao.findByDetachedCriteria(c, from, size);
		}
		return appList;
	}
	//获取两天内有效的广告,如果传了ids,就是查询指定有效的广告id对应的投放时间列表详情，type=PRO_TIME_ADIDS&&ids=null时，就是获取两天内有效的广告id列表
	public List<Object> getValidProTimeInfo(boolean isOnlyId,List<String> ids){
		SimpleDateFormat format=new SimpleDateFormat(DATE_FORMAT);
		RtbDbDao dao=BasicDaoFactory.getDbDao();
		DetachedCriteria c=DetachedCriteria.forClass(AdProTime.class);
		SimpleDateFormat format2=new SimpleDateFormat(DATE_TIME_FORMAT);
/*        Calendar calMax = Calendar.getInstance();
        calMax.add(Calendar.DATE,2);//获取未来两天日期   
        Calendar calMin = Calendar.getInstance();
        calMin.add(Calendar.DATE,-1);//获取前一天的日期
*/		try {
/*			Date expDateMax = format2.parse(dataFormat(format.format(calMax.getTime()),DATE_MIN));
			c.add(Restrictions.lt("dateEnd",expDateMax));//获取所有当天有效的广告
			
			Date expDateMin = format2.parse(dataFormat(format.format(calMin.getTime()),DATE_MAX));
			c.add(Restrictions.gt("dateStart",expDateMin));//获取所有当天有效的广告
*/		
			Date now=format2.parse(dataFormat(format.format(new Date()),DATE_MIN));
			c.add(Restrictions.le("dateStart",now));//获取所有当天有效的广告
			c.add(Restrictions.ge("dateEnd",now));//获取所有当天有效的广告
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			LOGGER.error("get valid ad id list fired exception ,date parse exception.....",e);
		}
		if(CollectionUtils.isNotEmpty(ids)){
			c.add(Restrictions.in("adId", ids));
		}
		if(isOnlyId){
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.groupProperty("adId"));
			projectionList.add(Projections.property("adId"));
			c.setProjection(projectionList);
		}
		c.addOrder(Order.asc("adId"));
		List<Object> adIds= dao.findByDetachedCriteria(c);
		if(CollectionUtils.isEmpty(adIds)){
			return null;
		}
		return adIds;
	}

	private List<String> cvtStrList( List<Object> adIds ){
		List<String> strList=new ArrayList<String>(adIds.size());
		for(Object obj:adIds){
			strList.add((String)((Object[])obj)[0]);
		}
		return strList;
	}

	 private String dataFormat(String dateStr,int type){
    	 if(((String)dateStr).split(" ").length>1){
    		 return dateStr;
    	 }
		 if(DATE_MIN==type){
			 dateStr=dateStr+" 00:00";//将时间设置为截止时间的00：00分 
		 }else if(DATE_MAX==type){
			 dateStr=dateStr+" 23:59";//将时间设置为截止时间的23：59分 
		 }
		 return dateStr;
		 
	 }
	//通过指定广告源id获取广告源信息
	public List<AdThirdSourceInfo> getThirdSourceAdByIds(Integer spaceId,List<String> ids){
		RtbDbDao dao=BasicDaoFactory.getDbDao();
		DetachedCriteria c=DetachedCriteria.forClass(AdThirdSourceInfo.class);
		if(CollectionUtils.isNotEmpty(ids)){
			c.add(Restrictions.in("id", ids));
		}
		if(spaceId!=null){
			c.add(Restrictions.eq("spaceId", spaceId));
		}
		c.add(Restrictions.eq("online_status", 1));
		List<AdThirdSourceInfo> appList= dao.findByDetachedCriteria(c);
		return  appList;
	}
	//获取三方广告源id列表
	public List<Object> getThirdSourceIds(Integer spaceId,Integer from,Integer size){
		RtbDbDao dao=BasicDaoFactory.getDbDao();
		DetachedCriteria c=DetachedCriteria.forClass(AdThirdSourceInfo.class);
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.groupProperty("id"));
		projectionList.add(Projections.property("id"));
		c.setProjection(projectionList);
		
		if(spaceId!=null){
			c.add(Restrictions.eq("spaceId", spaceId));
		}
		c.add(Restrictions.eq("online_status", 1));
		List<Object> sourceList=null;
		if(size==null||size==0){
			sourceList= dao.findByDetachedCriteria(c);
		}else{
			from=from==null?0:from;
			sourceList= dao.findByDetachedCriteria(c, from, size);
		}
		return sourceList;
	}

}

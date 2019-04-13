package com.ocean.rtb.persist.service.stat;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.inveno.base.BaseModel;
import com.inveno.util.CollectionUtils;
import com.ocean.rtb.persist.bean.stat.business.SrcBudget;
import com.ocean.rtb.persist.common.RtbLogManager;
import com.ocean.rtb.persist.common.RtbException;
import com.ocean.rtb.persist.dao.BasicDaoFactory;
import com.ocean.rtb.persist.dao.RtbDbDao;
@Component(value="statDbService")
public class StatDbService implements IStatDbService {
	private static final String STATISTICS_DATE_HOUR_FORMAT="yyyy-MM-dd HH";
	
	private static final String STATISTICS_DATE_MINIT_FORMAT="yyyy-MM-dd HH:mm:ss";
	private static final int DATE_MAX=1;
	private static final int DATE_MIN=0;
	protected static final Logger LOGGER =RtbLogManager.getBusinessLogger();
	//report服务上报nodeId,前缀1000开头;rtb服务前缀2000开头
	@Override
	public List<Object>  getStatBySrcId(String srcId,String nodeIdPrefix,Integer valid,Class<? extends  BaseModel> clazz) {
		SimpleDateFormat format=new SimpleDateFormat(STATISTICS_DATE_HOUR_FORMAT);
		
		SimpleDateFormat format2=new SimpleDateFormat(STATISTICS_DATE_MINIT_FORMAT);
		// TODO Auto-generated method stub
		RtbDbDao dao=BasicDaoFactory.getDbDao();
		DetachedCriteria c=DetachedCriteria.forClass(clazz);
		c.add(Restrictions.eq("srcId", Integer.parseInt(srcId)));
		
        Calendar calMax = Calendar.getInstance();
        calMax.add(Calendar.HOUR,1);//获取后一小时日期
	        
        Calendar calMin = Calendar.getInstance();
        //calMin.add(Calendar.HOUR,-1);//获取前一小时的日期

		try {
			Date expDateMin = format2.parse(dataFormat(format.format(calMin.getTime()),DATE_MIN));
		
			
			//获取当前这个小时的预算信息
			Date expDateMax= format2.parse(dataFormat(format.format(calMax.getTime()),DATE_MIN));
			c.add(Restrictions.and(Restrictions.ge("updateTime",expDateMin),Restrictions.lt("updateTime",expDateMax)));
			
			//c.add(Restrictions.lt("updateTime",expDateMax));
			
			//c.add(Restrictions.between("updateTime", expDateMin, expDateMax));
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			LOGGER.error("search stat info error,date formate error:{}",e.getMessage(),e);
			throw new RtbException("search stat info error,date formate exception");
		}
		c.add(Restrictions.like("nodeId",nodeIdPrefix,MatchMode.START));
		if(valid!=null){
			c.add(Restrictions.eq("valid", valid));
		}

		List<Object> list= dao.findByDetachedCriteria(c);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return list;
	}

	 private String dataFormat(String dateStr,int type){
		 if(DATE_MIN==type){
			 dateStr=dateStr+":00:00";//将时间设置为截止时间的00分 
		 }else if(DATE_MAX==type){
			 dateStr=dateStr+":59:59";//将时间设置为截止时间的59分 
		 }
		 return dateStr;
		 
	 }
	@Override
	public List<SrcBudget> getSrcBalance(String srcId) {
		// TODO Auto-generated method stub
		RtbDbDao dao=BasicDaoFactory.getDbDao();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT b.total,b.waste,b.total-b.waste as balance from");
		sql.append("(");
		sql.append("select (SELECT sum(amount) as amount from rtb_waste_book where user_id=? and type=1) as total");
		sql.append(",(SELECT sum(amount) as amount from rtb_waste_book where user_id=? and type=0) as waste");
		sql.append(")");
		sql.append("b");
		List<Object> objList= dao.findBySql(sql.toString(), (List)Arrays.asList(srcId,srcId));
		if(CollectionUtils.isEmpty(objList)){
			return null;
		}
		return cvtBuget(objList);
	}
	private List<SrcBudget> cvtBuget(List<Object> objList){
		List<SrcBudget> bgtL=new ArrayList<SrcBudget>(objList.size());
		for(Object obj:objList){
			SrcBudget buget=new SrcBudget();
			String total = String.valueOf(((Object[])obj)[0]);
			String waste = String.valueOf(((Object[])obj)[1]);
			String balance = String.valueOf(((Object[])obj)[2]);
			buget.setTotal(Long.parseLong(total));
			buget.setWaste(Long.parseLong(waste));
			buget.setBalance(Long.parseLong(balance));
			bgtL.add(buget);
		}
		return bgtL;
	}
	
}

package com.ocean.rtb.persist.service.stat;

import java.util.List;

import com.inveno.base.BaseModel;
import com.ocean.rtb.persist.bean.stat.business.SrcBudget;

public interface IStatDbService{
	public List<Object>  getStatBySrcId(String srcId,String nodeIdPrefix,Integer valid,Class<? extends  BaseModel> clazz);
	public List<SrcBudget> getSrcBalance(String srcId);
}

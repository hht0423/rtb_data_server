package com.ocean.rtb.statistics.server.bean;

import java.util.HashMap;
import java.util.Map;

import com.ocean.rtb.persist.bean.cache.AbstractCacheBean;

public class StatBusinessBean extends AbstractCacheBean{
	private String srcId;
	private long balanceTotal;
	private long balanceRemain;
	private Map<Long,StatBalanceBean> adBalance;
	public String getSrcId() {
		return srcId;
	}

	@Override
	public String getCacheId() {
		// TODO Auto-generated method stub
		return this.getSrcId();
	}

	public long getBalanceTotal() {
		return balanceTotal;
	}

	public long getBalanceRemain() {
		return balanceRemain;
	}
	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}

	public void setBalanceTotal(long balanceTotal) {
		this.balanceTotal = balanceTotal;
	}

	public void setBalanceRemain(long balanceRemain) {
		this.balanceRemain = balanceRemain;
	}

	public Map<Long, StatBalanceBean> getAdBalance() {
		return adBalance;
	}

	public void setAdBalance(Map<Long, StatBalanceBean> adBalance) {
		this.adBalance = adBalance;
	}

}

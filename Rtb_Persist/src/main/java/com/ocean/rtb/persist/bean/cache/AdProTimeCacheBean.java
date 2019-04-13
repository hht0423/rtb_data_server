package com.ocean.rtb.persist.bean.cache;

import java.util.List;

import com.ocean.rtb.persist.bean.ad.AdProTime;

public class AdProTimeCacheBean {
	private String adId;
	private List<AdProTimeTemp> proTimeList;
	public List<AdProTimeTemp> getProTimeList() {
		return proTimeList;
	}
	public void setProTimeList(List<AdProTimeTemp> proTimeList) {
		this.proTimeList = proTimeList;
	}
	public String getAdId() {
		return adId;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}
}

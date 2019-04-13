package com.ocean.rtb.profile.bean.api;

import com.ocean.rtb.persist.bean.cache.AbstractCacheBean;
import com.ocean.rtb.persist.common.RtbConstants;

public class GeoCacheBan extends AbstractCacheBean {
    private GeoApiResp geo;
	private String imei;
	private String ip;
	public GeoCacheBan(String imei,String ip){
		this.imei=imei;
		this.ip=ip;
	}
	@Override
	public String getCacheId() {
		// TODO Auto-generated method stub
		return this.getImei()+RtbConstants.CACHE_SPLIT+this.getIp();
	}
	public String getImei() {
		return imei;
	}
	public String getIp() {
		return ip;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public GeoApiResp getGeo() {
		return geo;
	}
	public void setGeo(GeoApiResp geo) {
		this.geo = geo;
	}

}

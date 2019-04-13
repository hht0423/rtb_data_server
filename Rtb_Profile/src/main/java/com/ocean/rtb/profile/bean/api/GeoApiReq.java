package com.ocean.rtb.profile.bean.api;

public class GeoApiReq {
	private String ip	;

	private String imei	;

	private int isimsi	;

	public String getIp() {
		return ip;
	}

	public String getImei() {
		return imei;
	}

	public int getIsimsi() {
		return isimsi;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public void setIsimsi(int isimsi) {
		this.isimsi = isimsi;
	}
}

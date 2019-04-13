package com.ocean.rtb.profile.bean.api;

public class GeoApiResp {
	  private String city;
	  private String name;
	  private String lng;
	  private String lat;
	  private String tags;//": "a10046,a10037,a10032,a10039,a10023,a10029,a10009,a10027,a10061,a10042,a10025,a10066,a10024,a10005,a10026,a10069,a10051,a10033,a10031,a10055,a10059,a10044,a10063,a10058,a10030,a10052,a1053,a10011,a10004,a10071,a10045,a10072,a10020,a10018,a10057,a10073,a10062,a10017,a10021,a10016,a10015,a10047,a10075",
	  private String imsi;
	public String getCity() {
		return city;
	}
	public String getName() {
		return name;
	}
	public String getLng() {
		return lng;
	}
	public String getLat() {
		return lat;
	}
	public String getTags() {
		return tags;
	}
	public String getImsi() {
		return imsi;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
}

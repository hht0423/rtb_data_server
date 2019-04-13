package com.ocean.rtb.persist.bean.cache;
/**
 * @Date 2019年3月12日
 * @Program rtb
 * @Author Alex
 * @Version V1.0
 */

public class AdProTimeTemp{
	private String id;	
	private String adId;
	private int status;
	private String dateStart;
	private String dateEnd;
	private String timeStart;
	private String timeEnd;
	public String getId() {
		return id;
	}
	public String getAdId() {
		return adId;
	}
	public int getStatus() {
		return status;
	}
	public String getDateStart() {
		return dateStart;
	}
	public String getDateEnd() {
		return dateEnd;
	}
	public String getTimeStart() {
		return timeStart;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	
}

package com.ocean.rtb.persist.bean.ad;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.inveno.base.BaseModel;
/**
 * @Date 2019年3月12日
 * @Program rtb
 * @Author Alex
 * @Version V1.0
 */
@Entity
@Table(name = "rtb_pro_time")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdProTime extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7732022105985921766L;

	@Id
	@Column(name = "pt_id")
	@GeneratedValue(generator = "AdProTimeGenerate", strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "AdProTimeGenerate", strategy = "native")
	private String id;	//广告id 
	
	//广告id
	@Column(name = "ad_id")
	private String adId;
	//是否有效
	@Column(name="status")
	private int status;
	//投放开始日期
	@Column(name = "date_start")
	@Temporal(TemporalType.DATE)
	private Date dateStart;
	
	//投放结束日期
	@Column(name = "date_end")
	@Temporal(TemporalType.DATE)
	private Date dateEnd;
	
	//投放开始时间
	@Column(name = "time_start")
	@Temporal(TemporalType.TIME)
	private Date timeStart;
	
	//投放结束时间
	@Column(name = "time_end")
	@Temporal(TemporalType.TIME)
	private Date timeEnd;
	
	//创建时间
	@Column(name = "create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}
	public Date getTimeStart() {
		return timeStart;
	}
	public Date getTimeEnd() {
		return timeEnd;
	}
	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}
	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setTimeStart(Time timeStart) {
		this.timeStart = timeStart;
	}
	public void setTimeEnd(Time timeEnd) {
		this.timeEnd = timeEnd;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getDateStart() {
		return dateStart;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAdId() {
		return adId;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}
}

package com.ocean.rtb.persist.bean.stat;

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
@Entity
@Table(name = "rtb_statistics_third")
@org.hibernate.annotations.Entity(dynamicInsert = false, dynamicUpdate = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ThirdAdStat   extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 735053515877010063L;

	@Id
	@Column(name = "st_id")
	@GeneratedValue(generator = "ThirdAdStatGenerate", strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "ThirdAdStatGenerate", strategy = "native")
	private String id;

	@Column(name = "node_id")
	private String nodeId;
	@Column(name = "update_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	@Column(name = "src_id")
	private int srcId;
	@Column(name = "price_ucode")
	private String priceUCode;
	@Column(name = "price")
	private int price;
	@Column(name = "valid")
	private Integer valid;
	@Column(name = "cnt")
	private Integer count;
	public String getNodeId() {
		return nodeId;
	}
	public int getSrcId() {
		return srcId;
	}
	public String getPriceUCode() {
		return priceUCode;
	}
	public Integer getValid() {
		return valid;
	}
	public Integer getCount() {
		return count;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public void setSrcId(int srcId) {
		this.srcId = srcId;
	}
	public void setPriceUCode(String priceUCode) {
		this.priceUCode = priceUCode;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}

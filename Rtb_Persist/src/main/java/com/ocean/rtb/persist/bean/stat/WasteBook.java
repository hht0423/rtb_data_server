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
@Table(name = "rtb_waste_book")
@org.hibernate.annotations.Entity(dynamicInsert = false, dynamicUpdate = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WasteBook   extends BaseModel {
	private static final long serialVersionUID = 267139964005501258L;

	@Id
	@Column(name = "wb_id")
	@GeneratedValue(generator = "WasteBookGenerate", strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "WasteBookGenerate", strategy = "native")
	private String id;

	@Column(name = "user_id")
	private String userId;
	@Column(name = "amount")
	private Long amount;
	@Column(name = "type")
	private Integer type;
	@Column(name = "remark")
	private String remark;
	@Column(name = "create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	public String getId() {
		return id;
	}
	public String getUserId() {
		return userId;
	}
	public Integer getType() {
		return type;
	}
	public String getRemark() {
		return remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}

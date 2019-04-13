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

@Entity
@Table(name = "rtb_finance")
@org.hibernate.annotations.Entity(dynamicInsert = false, dynamicUpdate = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SrcFinance {
	@Id
	@Column(name = "fnc_id")
	@GeneratedValue(generator = "SrcFinanceStatGenerate", strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "SrcFinanceStatGenerate", strategy = "native")
	private String id;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "balance")
	private Float balance;
	// 创建时间
	@Column(name = "create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	// 修改时间
	@Column(name = "update_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	public String getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public Float getBalance() {
		return balance;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}

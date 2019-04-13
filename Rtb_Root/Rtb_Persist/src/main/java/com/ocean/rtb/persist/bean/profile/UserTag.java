package com.ocean.rtb.persist.bean.profile;

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
@Table(name = "rtb_user_tag")
@org.hibernate.annotations.Entity(dynamicInsert = false, dynamicUpdate = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserTag {
	
	@Id
	@Column(name = "ut_id")
	@GeneratedValue(generator = "UserTagGenerate", strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "UserTagGenerate", strategy = "native")
	private String id;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "pt_id")
	private String ptId;
	@Column(name = "tag_name")
	private String tagName;
	@Column(name = "uid")
	private String tagValue;

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

	public String getPtId() {
		return ptId;
	}

	public String getTagName() {
		return tagName;
	}

	public String getTagValue() {
		return tagValue;
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

	public void setPtId(String ptId) {
		this.ptId = ptId;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


}

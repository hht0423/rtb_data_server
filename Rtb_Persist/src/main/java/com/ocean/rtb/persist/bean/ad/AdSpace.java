package com.ocean.rtb.persist.bean.ad;

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
@Table(name = "rtb_ad_space")
@org.hibernate.annotations.Entity(dynamicInsert = false, dynamicUpdate = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdSpace extends BaseModel{
	  /**
	 * 
	 */
	private static final long serialVersionUID = -6295639599088246681L;

	@Id
	  @Column(name = "space_id")
	  @GeneratedValue(generator = "AdSpaceGenerate", strategy = GenerationType.IDENTITY)
	  @GenericGenerator(name = "AdSpaceGenerate", strategy = "native")
	  private String id;
	  
	  @Column(name="name")
	  private String name;
	  
	  @Column(name="space_type")
	  private int spaceType;
	  
	  @Column(name="width")
	  private int width;//填的是比例值
	  
	  @Column(name="height")
	  private int height;
	  
	  @Column(name="status")
	  private int status;
	  
	  @Column(name="nopass_reason")
	  private String nopassReason;
	  
	  //创建时间
	  @Column(name = "create_time")
	  @Temporal(TemporalType.TIMESTAMP)
	  private Date createTime;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getSpaceType() {
		return spaceType;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getStatus() {
		return status;
	}

	public String getNopassReason() {
		return nopassReason;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSpaceType(int spaceType) {
		this.spaceType = spaceType;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setNopassReason(String nopassReason) {
		this.nopassReason = nopassReason;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


}

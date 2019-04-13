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
/**
 * @Date 2019年3月12日
 * @Program rtb
 * @Author Alex
 * @Version V1.0
 */
@Entity
@Table(name = "rtb_orientation")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdOrientation extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3446975070447371380L;

	/***
     *主键
     */
	@Id
	@Column(name = "ort_id")
	@GeneratedValue(generator = "AdOrientationGenerate", strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "AdOrientationGenerate", strategy = "native")
	private String id;
	
	@Column(name="user_id")
    private String userId;
    
    @Column(name="tag_ids")
    private String tagIds; 
    
    @Column(name="area_ids")
    private String areaIds;
   
    @Column(name="area_shield_type")
    private int asType;
 
    @Column(name="phone_model")
    private String phoneModel;
    
    @Column(name="model_shield_type")
    private int msType;
    
    @Column(name="brand")
    private String brand;
    @Column(name="brand_shield_type")
    private int bsType;
    
    @Column(name="os_type")
    private String 	osType;
    
    @Column(name="net_type")
    private String netType;
    
    //创建时间
	@Column(name = "create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	//修改时间
	@Column(name = "update_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	
	@Column(name="status")
	private int status;
    public String getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public String getTagIds() {
		return tagIds;
	}

	public String getAreaIds() {
		return areaIds;
	}
	public String getPhoneModel() {
		return phoneModel;
	}
	public String getBrand() {
		return brand;
	}

	public String getNetType() {
		return netType;
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

	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}

	public void setAreaIds(String areaIds) {
		this.areaIds = areaIds;
	}
	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setNetType(String netType) {
		this.netType = netType;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getAsType() {
		return asType;
	}

	public int getMsType() {
		return msType;
	}

	public int getBsType() {
		return bsType;
	}

	public void setAsType(int asType) {
		this.asType = asType;
	}

	public void setMsType(int msType) {
		this.msType = msType;
	}

	public void setBsType(int bsType) {
		this.bsType = bsType;
	}


}

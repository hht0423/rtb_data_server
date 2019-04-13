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
import javax.persistence.Transient;

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
@Table(name = "rtb_material")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Material extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3130119003362615687L;

	/***
     *文件信息主键
     */
	@Id
	@Column(name = "mate_id")
	@GeneratedValue(generator = "MaterialGenerate", strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "MaterialGenerate", strategy = "native")
	private String id;	
	
	//用户id
    @Column(name="user_id")
    private String userId;
    
    @Column(name="mate_type")
    private String mateType;

    @Column(name="name")
    private String name;

    //图片地址
    @Column(name="mate_url")
    private String mateUrl;
    
    //状态
    @Column(name="status")
    private int status;
    
     //文件大小
    @Column(name="size")
    private String  size;
    
    //文件宽度
    @Column(name="width")
    private Integer  width;
    
    //文件高度
    @Column(name="height")
    private Integer  height;
    
    public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}


	public void setHeight(Integer height) {
		this.height = height;
	}


	//创建时间
	@Column(name = "create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	//修改时间
	@Column(name = "update_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	
 
    @Column(name="format")
    private String format;


	public String getId() {
		return id;
	}


	public String getUserId() {
		return userId;
	}


	public String getMateType() {
		return mateType;
	}


	public String getName() {
		return name;
	}


	public String getMateUrl() {
		return mateUrl;
	}


	public String getSize() {
		return size;
	}

	public Date getCreateTime() {
		return createTime;
	}


	public Date getUpdateTime() {
		return updateTime;
	}


	public String getFormat() {
		return format;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public void setMateType(String mateType) {
		this.mateType = mateType;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setMateUrl(String mateUrl) {
		this.mateUrl = mateUrl;
	}


	public void setSize(String size) {
		this.size = size;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public void setFormat(String format) {
		this.format = format;
	}

	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}

}

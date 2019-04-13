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
@Table(name = "rtb_ad_video")
@org.hibernate.annotations.Entity(dynamicInsert = false, dynamicUpdate = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdVideo extends BaseModel{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 3623896416379282891L;

	@Id
	  @Column(name = "vd_id")
	  @GeneratedValue(generator = "AdVideoGenerate", strategy = GenerationType.IDENTITY)
	  @GenericGenerator(name = "AdVideoGenerate", strategy = "native")
	  private String id;
	
	  @Column(name="user_id")
	  private String userId;
	  //名称
	  @Column(name="name")
	  private String name;
	  
	  @Column(name="video_desc")
	  private String videoDesc;
	  
	  //视频路径
	  @Column(name="src")
	  private String src;
	  
	  //图片路径
	  @Column(name="img")
	  private Integer img;
	  
	  @Column(name="duration")
	  private int duration;
	  //视屏大小
	  @Column(name="size")
	  private int size;
	
	  @Column(name="format")
	  private String format;
	
	  @Column(name="status")
	  private int status;//1-待审核，2-审核通过，3-审核未通过，4-下线
	  
	  @Column(name="no_pass_reason")
	  private String noPassReason;
	  //创建时间
	  @Column(name = "create_time")
	  @Temporal(TemporalType.TIMESTAMP)
	  private Date createTime;
	 
	  //修改时间
	  @Column(name = "update_time")
	  @Temporal(TemporalType.TIMESTAMP)
	  private Date updateTime;

	public String getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getVideoDesc() {
		return videoDesc;
	}

	public String getSrc() {
		return src;
	}

	public int getDuration() {
		return duration;
	}

	public int getSize() {
		return size;
	}

	public String getFormat() {
		return format;
	}

	public String getNoPassReason() {
		return noPassReason;
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

	public void setName(String name) {
		this.name = name;
	}

	public void setVideoDesc(String videoDesc) {
		this.videoDesc = videoDesc;
	}

	public void setSrc(String src) {
		this.src = src;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void setNoPassReason(String noPassReason) {
		this.noPassReason = noPassReason;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getImg() {
		return img;
	}

	public void setImg(Integer img) {
		this.img = img;
	}
}

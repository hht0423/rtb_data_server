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
@Table(name = "rtb_ad_app")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppInfo extends BaseModel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 980173217879014467L;
	/***
     * 应用ID,主键
     */
	@Id
	@Column(name = "app_id")
	@GeneratedValue(generator = "AppInfoGenerate", strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "AppInfoGenerate", strategy = "native")
	private String id;
	//用户id
	@Column(name="user_id")
	private String userId;
	//应用名称
	@Column(name="app_name")
	private String appName;
	//应用包名
	@Column(name="pkg_name")
	private String pkgName;
	//应用版本
	@Column(name="version_name")
	private String versionName;
	//应用版本
	@Column(name="version_code")
	private Integer versionCode;
	//应用类型
	@Column(name="app_type")
	private String appType;
	
	//应用描述
	@Column(name="app_desc")
	private String appDesc;
	
	@Column(name="deep_link")
	private String deepLink;
	
	@Column(name="down_url")
	private String downUrl;
	
	@Column(name="icon")
	private Integer icon;
	@Column(name="imgs")
	private String imgs;
	@Column(name = "create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;//创建时间
	@Column(name = "update_time")
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;//修改时间

	@Column(name="no_pass_reason")
	private String noPassReason;

	@Column(name="status")
	private int status;
	/** 文件MD5 */
	@Column(name="md5")
	private String md5;
	
	/** 文件大小 */
	@Column(name="size")
	private Long size;

	public String getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public String getAppName() {
		return appName;
	}

	public String getPkgName() {
		return pkgName;
	}

	public String getVersionName() {
		return versionName;
	}

	public String getAppType() {
		return appType;
	}

	public String getAppDesc() {
		return appDesc;
	}

	public String getImgs() {
		return imgs;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public String getNoPassReason() {
		return noPassReason;
	}
	public String getMd5() {
		return md5;
	}

	public Long getSize() {
		return size;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setNoPassReason(String noPassReason) {
		this.noPassReason = noPassReason;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getDownUrl() {
		return downUrl;
	}

	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}

	public String getDeepLink() {
		return deepLink;
	}

	public void setDeepLink(String deepLink) {
		this.deepLink = deepLink;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getVersionCode() {
		return versionCode;
	}

	public Integer getIcon() {
		return icon;
	}

	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}

	public void setIcon(Integer icon) {
		this.icon = icon;
	}
	
	
}

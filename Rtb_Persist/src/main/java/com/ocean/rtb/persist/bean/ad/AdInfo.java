package com.ocean.rtb.persist.bean.ad;
import java.util.Date;
import java.util.List;

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
@Table(name = "rtb_ad")
@org.hibernate.annotations.Entity(dynamicInsert = false, dynamicUpdate = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdInfo extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8556830591856945699L;

	@Id
	@Column(name = "ad_id")
	@GeneratedValue(generator = "AdInfoGenerate", strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "AdInfoGenerate", strategy = "native")
	private String id; // 广告id

	// 用户id
	@Column(name = "user_id")
	private String userId;

	@Column(name = "is_test")
	private int isTest;//0-测试席位.1-正常竞价席位
	
	// 广告名称
	@Column(name = "name")
	private String name;

	// 广告文案
	@Column(name = "copywriting")
	private String copyWriting;
	
	// 创意类型
	@Column(name = "creative_type")
	private int creativeType;

	@Column(name = "inter_type")
	private int interType;

	@Column(name = "industry_type")
	private int industryType;
	
	//广告要推送的广告位类型
	@Column(name = "put_space_id")
	private int spaceId;
	
	// 广告投放开始时间
	@Column(name = "pro_start")
	@Temporal(TemporalType.DATE)
	private Date proStart;// 广告投放开始时间

	// 广告投放结束时间
	@Column(name = "pro_end")
	@Temporal(TemporalType.DATE)
	private Date proEnd;
	@Column(name = "logo")
	private Integer logo;

	@Column(name = "title")
	private String title;
	
	// 应用类广告的appid
	@Column(name = "app_id")
	private Integer appId;

	@Column(name = "vd_id")
	private Integer vdId;
	
	@Column(name = "pro_url")
	private String proUrl;

	//支持多个图片，多个分好隔开，存储素材id列表
	@Column(name = "mate_id_list")
	private String mateIdList;
	
	// 定向id
	@Column(name = "ort_id")
	private Integer ortId;

	@Column(name = "price_ucode")
	private String priceUCode;
	
	// 广告出价（元）
	@Column(name = "price")
	private Integer price;

	// 广告投放状态（1上架 ，0下架）
	@Column(name = "online_status")
	private int onlineStatus;

	// 创建时间
	@Column(name = "create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	// 修改时间
	@Column(name = "update_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	// 审核不通过原因
	@Column(name = "no_pass_reason")
	private String noPassReason;

	
	@Column(name = "h5_snippet")
	private String snippet;

	@Transient
	private List<AdProTime> protimeList;
	@Transient
	private AppInfo app;
	@Transient
	private AdVideo video;
	@Transient
	private Material material;
	public String getId() {
		return id;
	}


	public String getUserId() {
		return userId;
	}


	public String getName() {
		return name;
	}
	public Date getProStart() {
		return proStart;
	}


	public Date getProEnd() {
		return proEnd;
	}
	public String getTitle() {
		return title;
	}

	public Integer getVdId() {
		return vdId;
	}


	public String getProUrl() {
		return proUrl;
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


	public String getSnippet() {
		return snippet;
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
	public void setProStart(Date proStart) {
		this.proStart = proStart;
	}


	public void setProEnd(Date proEnd) {
		this.proEnd = proEnd;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public void setVdId(Integer vdId) {
		this.vdId = vdId;
	}


	public void setProUrl(String proUrl) {
		this.proUrl = proUrl;
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


	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}


	public List<AdProTime> getProtimeList() {
		return protimeList;
	}


	public void setProtimeList(List<AdProTime> protimeList) {
		this.protimeList = protimeList;
	}
	public Material getMaterial() {
		return material;
	}


	public void setMaterial(Material material) {
		this.material = material;
	}


	public AppInfo getApp() {
		return app;
	}


	public AdVideo getVideo() {
		return video;
	}


	public void setApp(AppInfo app) {
		this.app = app;
	}


	public void setVideo(AdVideo video) {
		this.video = video;
	}


	public int getSpaceId() {
		return spaceId;
	}


	public void setSpaceId(int spaceId) {
		this.spaceId = spaceId;
	}





	public int getIsTest() {
		return isTest;
	}


	public void setIsTest(int isTest) {
		this.isTest = isTest;
	}


	public String getCopyWriting() {
		return copyWriting;
	}


	public void setCopyWriting(String copyWriting) {
		this.copyWriting = copyWriting;
	}


	public int getCreativeType() {
		return creativeType;
	}


	public int getInterType() {
		return interType;
	}


	public int getIndustryType() {
		return industryType;
	}


	public void setCreativeType(int creativeType) {
		this.creativeType = creativeType;
	}


	public void setInterType(int interType) {
		this.interType = interType;
	}


	public void setIndustryType(int industryType) {
		this.industryType = industryType;
	}



	public String getMateIdList() {
		return mateIdList;
	}


	public void setMateIdList(String mateIdList) {
		this.mateIdList = mateIdList;
	}


	public int getOnlineStatus() {
		return onlineStatus;
	}


	public void setOnlineStatus(int onlineStatus) {
		this.onlineStatus = onlineStatus;
	}


	public Integer getLogo() {
		return logo;
	}


	public void setLogo(Integer logo) {
		this.logo = logo;
	}


	public Integer getOrtId() {
		return ortId;
	}


	public void setOrtId(Integer ortId) {
		this.ortId = ortId;
	}


	public Integer getAppId() {
		return appId;
	}


	public void setAppId(Integer appId) {
		this.appId = appId;
	}


	public String getPriceUCode() {
		return priceUCode;
	}


	public void setPriceUCode(String priceUCode) {
		this.priceUCode = priceUCode;
	}


	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}
	
}

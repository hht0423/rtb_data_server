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
@Table(name = "rtb_third_source")
@org.hibernate.annotations.Entity(dynamicInsert = false, dynamicUpdate = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdThirdSourceInfo extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2027143546451401941L;

	@Id
	@Column(name = "source_id")
	@GeneratedValue(generator = "AdThirdSourceInfoGenerate", strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "AdThirdSourceInfoGenerate", strategy = "native")
	private String id; // 广告id

	// 广告名称
	@Column(name = "source_name")
	private String sourceName;
	
	//广告要推送的广告位类型
	@Column(name = "put_space_id")
	private int spaceId;

	// 广告出价（元）
	@Column(name = "price")
	private Float price;

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

	public String getId() {
		return id;
	}

	public String getSourceName() {
		return sourceName;
	}
	public Float getPrice() {
		return price;
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

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(int onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public int getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(int spaceId) {
		this.spaceId = spaceId;
	}

	
}

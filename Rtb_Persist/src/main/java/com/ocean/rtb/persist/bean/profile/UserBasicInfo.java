package com.ocean.rtb.persist.bean.profile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.inveno.base.BaseModel;

@Entity
@Table(name = "rtb_profile_user")
@org.hibernate.annotations.Entity(dynamicInsert = false, dynamicUpdate = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserBasicInfo   extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7193561112873794887L;
	@Id
	@Column(name = "pu_id")
	@GeneratedValue(generator = "UserBasicInfoGenerate", strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "UserBasicInfoGenerate", strategy = "native")
	private String id;
	@Column(name = "uid")
	private String uid;
	@Column(name = "imei")
	private String imei;
	@Column(name = "imsi")
	private String imsi;
	@Column(name = "mac")
	private String mac;
	@Column(name = "idfa")
	private String idfa;
	@Column(name = "idfv")
	private String idfv;
	@Column(name = "tel")
	private String tel;
	@Column(name = "phone_model")
	private String phoneModel;
	@Column(name = "os")
	private String os;
	@Column(name = "os_version_name")
	private String osVersionName;
	@Column(name = "os_version_level")
	private String osVersionLevel;
	@Column(name = "channel")
	private String channel;
	@Column(name = "city")
	private String city;
	@Column(name = "gender")
	private String gender;
	@Column(name = "net_type")
	private String  netType;
	public String getId() {
		return id;
	}
	public String getUid() {
		return uid;
	}
	public String getImei() {
		return imei;
	}
	public String getImsi() {
		return imsi;
	}
	public String getMac() {
		return mac;
	}
	public String getIdfa() {
		return idfa;
	}
	public String getIdfv() {
		return idfv;
	}
	public String getTel() {
		return tel;
	}
	public String getPhoneModel() {
		return phoneModel;
	}
	public String getOs() {
		return os;
	}
	public String getOsVersionName() {
		return osVersionName;
	}
	public String getOsVersionLevel() {
		return osVersionLevel;
	}
	public String getChannel() {
		return channel;
	}
	public String getCity() {
		return city;
	}
	public String getGender() {
		return gender;
	}
	public String getNetType() {
		return netType;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public void setIdfv(String idfv) {
		this.idfv = idfv;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public void setOsVersionName(String osVersionName) {
		this.osVersionName = osVersionName;
	}
	public void setOsVersionLevel(String osVersionLevel) {
		this.osVersionLevel = osVersionLevel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setNetType(String netType) {
		this.netType = netType;
	}
}

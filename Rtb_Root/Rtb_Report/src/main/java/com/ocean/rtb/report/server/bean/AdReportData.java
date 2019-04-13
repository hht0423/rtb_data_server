package com.ocean.rtb.report.server.bean;
public class AdReportData{
	private String nodeId;//上报服务，前缀1000开头;rtb服务前缀2000开头
	private String etype;//事件类型
	private String bid;//竞价id
	private String bidtm;//竞价成功时间戳
	private String srcId;//直客传代理商子账号ID,如果是三方平台传dsp的id
	private String srcType;//竞价广告来源类型 0-直客  1-三方
	private String srcPt;//0-测试席位  1-正常竞价席位
	private String spaceId;//
	private String spaceType;//席位类
	private String adId;//广告id
	private String price;
	private String uid;//用户id
	private String ip;
	private String valid;//1-有效上报 ;0-无效上报
	private String tm;//时间戳
	private String priceUCode;//广告每次修改价格的版本序列号
	;
	public static enum AdReprotEnum{
		NODEID(1, "nodeId"),//上报服务，前缀1000开头;rtb服务前缀2000开头
		ETYPE(2, "etype"),//事件类型
		REQID(3, "bid"),//竞价id
		BIDTIMESTAMP(4,"bidtm"),//竞价成功时间戳
		SRCID(5, "srcId"),//直客传代理商子账号ID,如果是三方平台传dsp的id
		SRCTYPE(6, "srcType"),//竞价广告来源类型
		SRCPROPERTY(7,"srcPt"),//0-测试席位  1-正常竞价席位
		SPACEID(8,"spaceId"),//
		SPACETYPE(9,"spaceType"),//席位类
		ADID(10, "adId"),//广告id
		PRICEUCODE(11, "priceUCode"),//广告id
		
		PRICE(12, "price"),
		UID(13, "uid"),//用户id
		IP(14, "ip"),
		VALID(15, "valid"),//1-有效上报 ;0-无效上报
		TIMESTAMP(16, "tm"),//时间戳
		;
		
		private int value;
		private String paramName;
		public int getIndex() {
			return this.value - 1;
		}
		
		public String getParamName() {
			return paramName;
		}

		public int getVialue() {
			return value;
		}

		private AdReprotEnum(int value, String paramName) {
			this.value = value;
			this.paramName = paramName;
		}
	}
	public String getNodeId() {
		return nodeId;
	}
	public String getEtype() {
		return etype;
	}
	public String getBid() {
		return bid;
	}
	public String getBidtm() {
		return bidtm;
	}
	public String getSrcId() {
		return srcId;
	}
	public String getSrcType() {
		return srcType;
	}
	public String getSrcPt() {
		return srcPt;
	}
	public String getSpaceId() {
		return spaceId;
	}
	public String getSpaceType() {
		return spaceType;
	}
	public String getAdId() {
		return adId;
	}
	public String getPrice() {
		return price;
	}
	public String getUid() {
		return uid;
	}
	public String getIp() {
		return ip;
	}
	public String getValid() {
		return valid;
	}
	public String getTm() {
		return tm;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public void setEtype(String etype) {
		this.etype = etype;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public void setBidtm(String bidtm) {
		this.bidtm = bidtm;
	}
	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}
	public void setSrcType(String srcType) {
		this.srcType = srcType;
	}
	public void setSrcPt(String srcPt) {
		this.srcPt = srcPt;
	}
	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}
	public void setSpaceType(String spaceType) {
		this.spaceType = spaceType;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	public void setTm(String tm) {
		this.tm = tm;
	}
	public String getPriceUCode() {
		return priceUCode;
	}
	public void setPriceUCode(String priceUCode) {
		this.priceUCode = priceUCode;
	}

}

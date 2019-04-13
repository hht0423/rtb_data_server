package com.ocean.rtb.statistics.server.bean;

public class StatBalanceBean{
	private String balanceId;//后台流水id
    private long  validResults; //成功扣款额度
	private long  rtbResults; //竞价服务上报的总额度
	private long expiredResults; //上报失效的浪费额度
	//竞价成功，但未上报的延迟额度：rtbResults-validResults-expiredResults;
	public String getBalanceId() {
		return balanceId;
	}
	public long getValidResults() {
		return validResults;
	}
	public long getRtbResults() {
		return rtbResults;
	}
	public long getExpiredResults() {
		return expiredResults;
	}
	public void setBalanceId(String balanceId) {
		this.balanceId = balanceId;
	}
	public void setValidResults(long validResults) {
		this.validResults = validResults;
	}
	public void setRtbResults(long rtbResults) {
		this.rtbResults = rtbResults;
	}
	public void setExpiredResults(long expiredResults) {
		this.expiredResults = expiredResults;
	}
}
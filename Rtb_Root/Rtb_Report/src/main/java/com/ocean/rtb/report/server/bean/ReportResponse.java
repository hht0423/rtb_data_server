package com.ocean.rtb.report.server.bean;

public class ReportResponse {
	private String msg;
	private int errorCode;
	public String getMsg() {
		return msg;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}

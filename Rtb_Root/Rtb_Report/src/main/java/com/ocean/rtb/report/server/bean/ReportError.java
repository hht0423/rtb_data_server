package com.ocean.rtb.report.server.bean;

public enum ReportError {
		SUCCEED(0, "OK"),
		PARAM_ERROR(401, "parameter error"),
		ENCRYPT_ERROR(402, "encrypt error"),
		
		;
		
		private int value;
		private String msg;
		private ReportError(int value, String msg) {
			this.value = value;
			this.msg = msg;
		}
		public int getValue() {
			return value;
		}
		public String getMsg() {
			return msg;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
	
}

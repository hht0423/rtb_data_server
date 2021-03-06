package com.ocean.rtb.persist.common;

public class DbSyncException extends RuntimeException {

	private static final long serialVersionUID = -1830917455348L;

	private int code;
	
	private String msg;
	
	public DbSyncException(int code){
		
		this(code, "业务处理异常");
	}
	public DbSyncException(String msg){
		
		this(0, msg);
	}
	public DbSyncException(int code, String msg){
		
		super();
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}

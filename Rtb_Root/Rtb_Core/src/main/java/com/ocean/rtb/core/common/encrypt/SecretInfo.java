package com.ocean.rtb.core.common.encrypt;

public class SecretInfo {
	
	private String pkey;
	
	private String psecret;
	
	private String encryptkey;
	
	private String iv;

	public String getPkey() {
		return pkey;
	}

	public void setPkey(String pkey) {
		this.pkey = pkey;
	}

	public String getPsecret() {
		return psecret;
	}

	public void setPsecret(String psecret) {
		this.psecret = psecret;
	}

	public String getEncryptkey() {
		return encryptkey;
	}

	public void setEncryptkey(String encryptkey) {
		this.encryptkey = encryptkey;
	}

	public String getIv() {
		return iv;
	}

	public void setIv(String iv) {
		this.iv = iv;
	}

}

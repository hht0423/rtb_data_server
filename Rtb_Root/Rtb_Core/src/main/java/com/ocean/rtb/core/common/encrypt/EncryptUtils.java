package com.ocean.rtb.core.common.encrypt;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.system.SystemContext;

public class EncryptUtils {

	public static final String KEY_AEC_FROM = "aes.from";

	public static final String KEY_AEC_KEY = "aes.key";

	public static final String KEY_AEC_IV = "aes.iv";

	public static byte[] encryptContent(byte[] bytes) throws UnsupportedEncodingException {
		String from =  SystemContext.getDynamicPropertyHandler().get(KEY_AEC_FROM, "");
		String key =  SystemContext.getDynamicPropertyHandler().get(KEY_AEC_KEY, "");
		String iv =  SystemContext.getDynamicPropertyHandler().get(KEY_AEC_IV, "");
		return AESUtility.encode(bytes, from, key, iv).getBytes("utf-8");
	}

	public static byte[] encryptContent(byte[] bytes, String from, String key, String iv) throws UnsupportedEncodingException {
		return AESUtility.encode(bytes, from, key, iv).getBytes("utf-8");
	}

	public static byte[] decryptContent(byte[] bytes) throws UnsupportedEncodingException {
		String from = SystemContext.getDynamicPropertyHandler().get(KEY_AEC_FROM, "");
		String key = SystemContext.getDynamicPropertyHandler().get(KEY_AEC_KEY, "");
		String iv = SystemContext.getDynamicPropertyHandler().get(KEY_AEC_IV, "");
		return AESUtility.decode(new String(bytes, "utf-8"), from, key, iv);
	}

	public static byte[] decryptContent(byte[] bytes, String from, String key, String iv) throws UnsupportedEncodingException {
		return AESUtility.decode(new String(bytes, "utf-8"), from, key, iv);
	}

	public static byte[] AESEncrypt(byte[] bytes) throws UnsupportedEncodingException {
		String from = SystemContext.getDynamicPropertyHandler().get(KEY_AEC_FROM, "");
		String key = SystemContext.getDynamicPropertyHandler().get(KEY_AEC_KEY, "");
		String iv = SystemContext.getDynamicPropertyHandler().get(KEY_AEC_IV, "");
		return AESUtility.AESEncode(bytes, from, key, iv);
	}

	public static byte[] AESDecrypt(byte[] bytes) throws UnsupportedEncodingException {
		String from = SystemContext.getDynamicPropertyHandler().get(KEY_AEC_FROM, "");
		String key = SystemContext.getDynamicPropertyHandler().get(KEY_AEC_KEY, "");
		String iv = SystemContext.getDynamicPropertyHandler().get(KEY_AEC_IV, "");
		return AESUtility.AESDecode(bytes, from, key, iv);
	}

	public static byte[] decryptContent(byte[] bytes, String pkey) throws UnsupportedEncodingException {
		if (StringUtils.isEmpty(pkey) || SecretMgr.getSecretInfo(pkey) == null) {
			String from = SystemContext.getDynamicPropertyHandler().get(KEY_AEC_FROM, "");
			String key = SystemContext.getDynamicPropertyHandler().get(KEY_AEC_KEY, "");
			String iv = SystemContext.getDynamicPropertyHandler().get(KEY_AEC_IV, "");
			return AESUtility.decode(new String(bytes, "utf-8"), from, key, iv);
		}
		
		SecretInfo info = SecretMgr.getSecretInfo(pkey);
		return AESUtility.decode(new String(bytes, "utf-8"), pkey, info.getEncryptkey(), info.getIv()); 
	}

	public static byte[] encryptContent(byte[] bytes, String pkey) throws UnsupportedEncodingException {
		if (StringUtils.isEmpty(pkey) || SecretMgr.getSecretInfo(pkey) == null) {
			String from = SystemContext.getDynamicPropertyHandler().get(KEY_AEC_FROM, "");
			String key = SystemContext.getDynamicPropertyHandler().get(KEY_AEC_KEY, "");
			String iv = SystemContext.getDynamicPropertyHandler().get(KEY_AEC_IV, "");
			return AESUtility.encode(bytes, from, key, iv).getBytes("utf-8");
		}
		
		SecretInfo info = SecretMgr.getSecretInfo(pkey);
		return AESUtility.encode(bytes, pkey, info.getEncryptkey(), info.getIv()).getBytes("utf-8");
	}
}

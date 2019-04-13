package com.ocean.rtb.core.common.encrypt;

/*
 * Copyright (C) 2015 Baidu Inc. All rights reserved.
 */

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * 参考《百度数据加密算法》 
 * http://m.baidu.com/api?bdi_docs=1&action=intro&source=intro_extrainfo2&cur=intro
 * @author yuanxingzhong
 * @since 2015年12月16日
 */
public final class AESUtility {

    /**
     * 加密，先aes，再base64再urlencode
     * @param rawStr
     * @param key
     * @param iv
     * @return
     */
    public static String encode(byte[] rawStr, String pkey, String psecret, String iv) {
        if (null == rawStr || null == psecret || rawStr.length == 0 || psecret.isEmpty()) {
            System.err.println("加密的必要参数为空，加密失败！");
            return null;
        }
        byte[] aesBytes = AESEncode(rawStr, pkey, psecret, iv);
        if (null == aesBytes) {
            System.err.println("aes加密失败");
            return null;
        }

        String base64Encode = AndroidBase64.encodeToString(aesBytes, AndroidBase64.NO_WRAP);
        					  
        String urlEncodeStr =  URLEncoder.encode(base64Encode);
        return urlEncodeStr;
    }
    
    /**
     * 解密
     * @param rawStr
     * @param from
     * @param key
     * @param iv
     * @return
     */
    public static byte[] decode(String rawStr, String pkey, String psecret, String iv) {
    	if (null == rawStr || null == psecret || rawStr.isEmpty() || psecret.isEmpty()) {
            System.err.println("解密的必要参数为空，加密失败！");
            return null;
        }
    	byte[] str = AndroidBase64.decode(URLDecoder.decode(rawStr), AndroidBase64.DEFAULT);
        byte[] aesBytes = AESDecode(str, pkey, psecret, iv);
        if (null == aesBytes) {
            System.err.println("aes加密失败");
            return null;
        }
        return aesBytes;
    }
    
    
    public static byte[] AESDecode(byte[] rawStr, String from, String key, String iv) {
        try {  
        	IvParameterSpec ivParam = null;
            if (null != iv && !iv.isEmpty()) {
                ivParam = new IvParameterSpec(generateIV(iv));
            }

            String strMd5 = toMd5(from + key);
            if (null == strMd5) {
                System.err.println("生成key失败！");
                return null;
            }
            byte[] strBytes = strMd5.getBytes("utf-8");
            SecretKeySpec cipherKey = new SecretKeySpec(strBytes, strBytes.length / 2, strBytes.length / 2, "AES");
            Cipher decodeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            if (null != ivParam) {
                decodeCipher.init(Cipher.DECRYPT_MODE, cipherKey, ivParam);
            } else {
                decodeCipher.init(Cipher.DECRYPT_MODE, cipherKey);
            }
            return decodeCipher.doFinal(rawStr);
        } catch (Exception ex) {  
            System.out.println(ex.toString());  
            return null;  
        }  
    }
    
    /**
     * 对字符串进行aes加密
     * @param rawStr
     * @param key
     * @param iv
     * @return
     */
    public static byte[] AESEncode(byte [] rawStr, String from, String key, String iv) {
        try {
            IvParameterSpec ivParam = null;
            if (null != iv && !iv.isEmpty()) {
                ivParam = new IvParameterSpec(generateIV(iv));
            }

            String strMd5 = toMd5(from + key);
            if (null == strMd5) {
                System.err.println("生成key失败！");
                return null;
            }
            byte[] strBytes = strMd5.getBytes("utf-8");

            SecretKeySpec cipherKey = new SecretKeySpec(strBytes, strBytes.length / 2, strBytes.length / 2, "AES");

            Cipher encodeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            if (null != ivParam) {
                encodeCipher.init(Cipher.ENCRYPT_MODE, cipherKey, ivParam);
            } else {
                encodeCipher.init(Cipher.ENCRYPT_MODE, cipherKey);
            }

            return encodeCipher.doFinal(rawStr);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] generateIV(String iv) {
        if (null == iv || iv.isEmpty()) {
            return null;
        }
        try {
            byte[] ivBytes = iv.getBytes("utf-8");
            return Arrays.copyOf(ivBytes, 16);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取字符串的md5
     * @param str
     * @return
     */
    public static String toMd5(String str){  
        try {  
            MessageDigest md5 = MessageDigest.getInstance("MD5");  
            byte[] bytes = md5.digest(str.getBytes("utf-8"));
            if (null != bytes) {
                return parseByte2HexStr(bytes);
            }
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 将二进制转换成16进制 
     * @param buf 
     * @return 
     */  
    public static String parseByte2HexStr(byte buf[]) {  
        if (null == buf) {
            return null;
        }
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < buf.length; i++) {  
            String hex = Integer.toHexString(buf[i] & 0xFF);  
            if (hex.length() == 1) {  
                hex = '0' + hex;  
            }  
            sb.append(hex.toUpperCase());  
        }  
        return sb.toString();  
    } 

}
package com.ocean.rtb.report;

import java.io.UnsupportedEncodingException;

import com.ocean.rtb.core.common.encrypt.AESUtility;

public class ReprotTest {
	static String AES_FROM="1005";
	static String AES_KEY="bgt56yhn2wsxtyhnbg";
	static String AES_IV="zaq12wsxcde34rfv";
	public static void main(String args[]){
		try {
			String encStr=new String(AESUtility.encode("bid=12345&srcid=1".getBytes(), AES_FROM, AES_KEY, AES_IV).getBytes("utf-8"));
			System.out.println(encStr);
			
			String decStr= new String(AESUtility.decode(new String(encStr.getBytes(), "utf-8"),AES_FROM, AES_KEY, AES_IV));
			System.out.print(decStr);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
/*	
	AESUtility*/
}

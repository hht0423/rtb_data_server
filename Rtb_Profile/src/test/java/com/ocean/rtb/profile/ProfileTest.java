package com.ocean.rtb.profile;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import com.ocean.rtb.persist.bean.thrift.profile.RtbPortrait;
import com.ocean.rtb.persist.bean.thrift.profile.RtbUserInfo;
import com.ocean.rtb.persist.bean.thrift.profile.RtbUserPortraitReq;

public class ProfileTest {
	public static final String SERVER_IP = "127.0.0.1";
	public static final int SERVER_PORT = 9094;
	public static final int TIMEOUT = 10000;


	public static void main(String args[]){
		TTransport transport  = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT));
		TProtocol protocol = new TBinaryProtocol(transport);
		try {
			transport.open();
	
			RtbPortrait.Client client=new RtbPortrait.Client(protocol);
			RtbUserPortraitReq req=new RtbUserPortraitReq();
			RtbUserInfo userInfo=new RtbUserInfo();
			userInfo.setImei("869845030104176");
			userInfo.setClient_ip("122.159.238.209");
			req.setUserInfo(userInfo);
			
			System.out.print(client.getUserInfo(req));
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

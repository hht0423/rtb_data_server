package com.ocean.rtb.stat;

import java.util.Arrays;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.ocean.rtb.persist.bean.thrift.common.RtbSrcType;
import com.ocean.rtb.persist.bean.thrift.stat.RtbStat;
import com.ocean.rtb.persist.bean.thrift.stat.RtbStatQueryReq;

public class StatTest {
	public static final String SERVER_IP = "127.0.0.1";
	public static final int SERVER_PORT = 9096;
	public static final int TIMEOUT = 10000;


	public static void main(String args[]){
		TTransport transport  = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT));
		TProtocol protocol = new TBinaryProtocol(transport);
		try {
			transport.open();
	
			RtbStat.Client client=new RtbStat.Client(protocol);
			RtbStatQueryReq req=new RtbStatQueryReq();
			req.setSrcIds(Arrays.asList("1"));
			req.setSrcType(RtbSrcType.SrcTypeSelf);
			System.out.print(client.statQueryData(req));
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

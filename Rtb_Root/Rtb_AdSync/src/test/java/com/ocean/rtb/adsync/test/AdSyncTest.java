package com.ocean.rtb.adsync.test;

import java.util.Arrays;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.ocean.rtb.persist.bean.thrift.adsync.QueryRtbSrcByIdReq;
import com.ocean.rtb.persist.bean.thrift.adsync.QueryRtbSrcResp;
import com.ocean.rtb.persist.bean.thrift.adsync.RtbSync;

public class AdSyncTest {
	//public static final String SERVER_IP = "114.215.134.12";
	public static final String SERVER_IP = "127.0.0.1";
	public static final int SERVER_PORT = 9094;
	public static final int TIMEOUT = 10000;
	public static void main(String args[]){
		TTransport transport  = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT));
		TProtocol protocol = new TBinaryProtocol(transport);

		try {
			transport.open();

			RtbSync.Client client=new RtbSync.Client(protocol);
			/*System.out.print(client.getSpaceConfig());*/
			
			
			
/*			QueryRtbSrcIdsReq idsReq=new QueryRtbSrcIdsReq();
			idsReq.setStart(0);
			
			idsReq.setSpaceId(1);
			idsReq.setSrcType(RtbSrcType.SrcTypeSelf);
			System.out.print(client.getSrcIds(idsReq));
			
*/
			QueryRtbSrcByIdReq adInfoReq=new QueryRtbSrcByIdReq();
			adInfoReq.setSpaceId(1);
			adInfoReq.setSrcIds(Arrays.asList("11223456677"));
			QueryRtbSrcResp resp=client.getADByIds(adInfoReq);
			System.out.print(resp);
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

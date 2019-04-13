package com.ocean.rtb.statistics.server;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.thrift.TException;

import com.ocean.core.common.system.SystemContext;
import com.ocean.rtb.persist.bean.thrift.common.AdResponseCode;
import com.ocean.rtb.persist.bean.thrift.stat.RtbStatQueryData;
import com.ocean.rtb.persist.bean.thrift.stat.RtbStatQueryReq;
import com.ocean.rtb.persist.bean.thrift.stat.RtbStatQueryResp;
import com.ocean.rtb.persist.common.RtbLogManager;
import com.ocean.rtb.persist.common.RtbException;
import com.ocean.rtb.statistics.service.StatService;
public class StatInvoker extends AbstractStatInvoker{
	private  static final Logger  LOGGER = RtbLogManager.getBusinessLogger();
	private StatService  getSyncService(){
		return (StatService)SystemContext.getServiceHandler().getService(StatService.class);
	}
	@Override
	public void ping() throws TException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RtbStatQueryResp statQueryData(RtbStatQueryReq req)
			throws TException {
		// TODO Auto-generated method stub
		long ts = System.currentTimeMillis();
		RtbStatQueryResp resp=null;
		try{
			
			Map<String, RtbStatQueryData> dataMap=new HashMap<String, RtbStatQueryData>();
			for(String srcId:req.getSrcIds()){
				RtbStatQueryData data=getSyncService().getSrcBalance(srcId, req.getSrcType());
				if(data!=null){
					dataMap.put(srcId, data);
				}
				
			}
			resp=new RtbStatQueryResp();
			resp.setData(dataMap);
		}catch(RtbException e){
			LOGGER.error("Api reportQueryData get stat info error(StatisticsException):{}",e.getMsg(),e);
			resp=this.getErrorStatResp(AdResponseCode.RC_ERROR.getValue(), e.getMsg());
			
		}catch(Exception e){
			LOGGER.error("Api reportQueryData get stat info error(Exception):{}",e.getMessage(),e);
			resp=this.getErrorStatResp(AdResponseCode.RC_ERROR.getValue(), e.getMessage());
		}finally{
			if(resp==null){
				resp=this.getErrorStatResp(AdResponseCode.RC_NODATA.getValue(), "no data return");
			}
		
			long ls = System.currentTimeMillis();
			LOGGER.info("Api reportQueryData get stat info time cost:{}",ls-ts);
		}
		return resp;
	}
	private  RtbStatQueryResp getErrorStatResp(int errorCode,String msg){
		RtbStatQueryResp resp=new RtbStatQueryResp();
		resp.setErrorCode(errorCode);
		resp.setErrorMsg(msg);
		return resp;
	}


}

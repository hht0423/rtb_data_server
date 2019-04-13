package com.ocean.rtb.adsync.server;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.system.SystemContext;
import com.ocean.rtb.adsync.service.AdSyncService;
import com.ocean.rtb.persist.bean.thrift.adsync.QueryRtbSrcByIdReq;
import com.ocean.rtb.persist.bean.thrift.adsync.QueryRtbSrcIdsReq;
import com.ocean.rtb.persist.bean.thrift.adsync.QueryRtbSrcIdsResp;
import com.ocean.rtb.persist.bean.thrift.adsync.QueryRtbSrcResp;
import com.ocean.rtb.persist.bean.thrift.adsync.QuerySpaceConfigResp;
import com.ocean.rtb.persist.bean.thrift.common.AdResponseCode;
import com.ocean.rtb.persist.common.DbSyncException;

public class AdSyncInvoker extends AbstractAdSyncInvoker{
	protected static final Logger logger =MyLogManager.getLogger();
/*	@Autowired
	private AdSyncService adSyncService;*/
	public void ping() throws TException {
		
	}
	private AdSyncService  getSyncService(){
		return (AdSyncService)SystemContext.getServiceHandler().getService(AdSyncService.class);
	}
	@Override
	public QuerySpaceConfigResp getSpaceConfig() throws TException {
		// TODO Auto-generated method stub
		long ts = System.currentTimeMillis();
		QuerySpaceConfigResp resp=null;
		String requestId=DigestUtils.md5Hex(UUID.randomUUID().toString().replaceAll("-", "")+ts);
		try{
			resp= getSyncService().getSpaceConfig();
		}catch(DbSyncException e){
			logger.error("Api {} getSpaceConfig error(AdSyncException):{}",requestId,e.getMsg(),e);
			resp=this.getErrorSpaceResp(AdResponseCode.RC_ERROR.getValue(), e.getMsg());
			
		}catch(Exception e){
			logger.error("Api {} getSpaceConfig info error(Exception):{}",requestId,e.getMessage(),e);
			resp=this.getErrorSpaceResp(AdResponseCode.RC_ERROR.getValue(), e.getMessage());
		}finally{
			if(resp==null){
				resp=this.getErrorSpaceResp(AdResponseCode.RC_NODATA.getValue(), "no data return");
			}
		
			long ls = System.currentTimeMillis();
			logger.info("Api {} getSpaceConfig time cost:{}",requestId,ls-ts);
		}

		return resp;
	}
	@Override
	public QueryRtbSrcIdsResp getSrcIds(QueryRtbSrcIdsReq request)
			throws TException {
		// TODO Auto-generated method stub
		long ts = System.currentTimeMillis();
		String requestId=DigestUtils.md5Hex(UUID.randomUUID().toString().replaceAll("-", "")+ts);
		QueryRtbSrcIdsResp resp=null;
		try{
			int spaceId=request.getSpaceId();
			logger.info("Api {} getSrcIds request parameter ,space id:{}|type:{}|stat:{}",requestId,spaceId,request.getSrcType(),request.getStart());
			
			if(spaceId<=0){
				logger.error("Api {} getSrcIds request parameter error,space id:{}|type:{}|stat:{}",requestId,spaceId,request.getSrcType(),request.getStart());
				return getErrorAdIdsResp(AdResponseCode.RC_PARAM.getValue(),MessageFormat.format("parameter spaceId error:{0}",spaceId));
			}		
			resp=getSyncService().getSrcIds(request);
			
		}catch(DbSyncException e){
			logger.error("Api {} getSrcIds error(AdSyncException):{}|space id:{}|type:{}|stat:{}",requestId,e.getMsg(),request.getSpaceId(),request.getSrcType(),request.getStart(),e);
			resp=this.getErrorAdIdsResp(AdResponseCode.RC_ERROR.getValue(), e.getMsg());
			
		}catch(Exception e){
			logger.error("Api {} getSrcIds error(Exception):{}|space id:{}|type:{}|stat:{}",requestId,e.getMessage(),request.getSpaceId(),request.getSrcType(),request.getStart(),e);
			resp=this.getErrorAdIdsResp(AdResponseCode.RC_ERROR.getValue(), e.getMessage());
		}finally{
			long ls = System.currentTimeMillis();
			if(resp==null){
				resp=this.getErrorAdIdsResp(AdResponseCode.RC_NODATA.getValue(), "no data return");
			}
			logger.info("Api {} getSrcIds response,adids:{}|space id:{}|type:{}|stat:{}|time cost:{}",requestId,resp.getSrcIds(),request.getSpaceId(),request.getSrcType(),request.getStart(),ls-ts);
		}
		return resp;
	}
	@Override
	public QueryRtbSrcResp getADByIds(QueryRtbSrcByIdReq request)
			throws TException {
		// TODO Auto-generated method stub
		long ts = System.currentTimeMillis();
		String requestId=DigestUtils.md5Hex(UUID.randomUUID().toString().replaceAll("-", "")+ts);
		QueryRtbSrcResp resp=null;
		try{
			int spaceId=request.getSpaceId();
			List<String> adIds=request.getSrcIds();
			logger.info("Api {} getADByIds request parameter ,space id:{}|adIds:{}|srcType:{}",requestId,spaceId,adIds,request.getSrcType());

			if(spaceId<=0||CollectionUtils.isEmpty(adIds)){
				logger.error("Api {} getADByIds request parameter error,space id:{}|srcIds|srcType:{}",requestId,spaceId,adIds,request.getSrcType());
				return getErrorAdResp(AdResponseCode.RC_PARAM.getValue(),MessageFormat.format("parameter spaceId|srcIds error:{0}|{1}|{2}",spaceId,adIds,request.getSrcType()));
			}
			resp=getSyncService().getADByIds(request);
			logger.info("Api {} getSrcIds response:{}",requestId,resp);
		}catch(DbSyncException e){
			logger.error("Api {} getADByIds error(AdSyncException):{}",requestId,e.getMsg(),e);
			resp=this.getErrorAdResp(AdResponseCode.RC_ERROR.getValue(), e.getMsg());
			
		}catch(Exception e){
			logger.error("Api {} getADByIds error(Exception):{}",requestId,e.getMessage(),e);
			resp=this.getErrorAdResp(AdResponseCode.RC_ERROR.getValue(), e.getMessage());
		}finally{
			if(resp==null){
				resp=getErrorAdResp(AdResponseCode.RC_NODATA.getValue(),"no data return ");
			}
			long ls = System.currentTimeMillis();
			logger.info("Api {} getADByIds  time cost:{}",requestId,ls-ts);
		}
		return resp;
	}
	private  QuerySpaceConfigResp getErrorSpaceResp(int errorCode,String msg){
		QuerySpaceConfigResp resp=new QuerySpaceConfigResp();
		resp.setErrorCode(errorCode);
		resp.setErrorMsg(msg);
		return resp;
	}
	private  QueryRtbSrcIdsResp getErrorAdIdsResp(int errorCode,String msg){
		QueryRtbSrcIdsResp resp=new QueryRtbSrcIdsResp();
		resp.setErrorCode(errorCode);
		resp.setErrorMsg(msg);
		return resp;
	}
	private  QueryRtbSrcResp getErrorAdResp(int errorCode,String msg){
		QueryRtbSrcResp resp=new QueryRtbSrcResp();
		resp.setErrorCode(errorCode);
		resp.setErrorMsg(msg);
		return resp;
	}
}

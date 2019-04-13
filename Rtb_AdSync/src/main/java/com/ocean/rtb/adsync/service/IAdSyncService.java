package com.ocean.rtb.adsync.service;
import com.ocean.rtb.persist.bean.thrift.adsync.QueryRtbSrcByIdReq;
import com.ocean.rtb.persist.bean.thrift.adsync.QueryRtbSrcIdsReq;
import com.ocean.rtb.persist.bean.thrift.adsync.QueryRtbSrcIdsResp;
import com.ocean.rtb.persist.bean.thrift.adsync.QueryRtbSrcResp;
import com.ocean.rtb.persist.bean.thrift.adsync.QuerySpaceConfigResp;
import com.ocean.rtb.persist.common.DbSyncException;

public interface IAdSyncService {

	public QuerySpaceConfigResp getSpaceConfig() throws DbSyncException ;

	public QueryRtbSrcIdsResp getSrcIds(QueryRtbSrcIdsReq request)
			throws DbSyncException;
	
	public QueryRtbSrcResp getADByIds(QueryRtbSrcByIdReq request)
			throws DbSyncException;

}

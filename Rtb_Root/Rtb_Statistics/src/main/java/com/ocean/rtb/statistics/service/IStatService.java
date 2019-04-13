package com.ocean.rtb.statistics.service;

import com.ocean.rtb.persist.bean.thrift.common.RtbSrcType;
import com.ocean.rtb.persist.bean.thrift.stat.RtbStatQueryData;
import com.ocean.rtb.statistics.server.bean.StatBusinessBean;

public interface IStatService {
	public RtbStatQueryData getSrcBalance(String srcId,RtbSrcType srcType);
}

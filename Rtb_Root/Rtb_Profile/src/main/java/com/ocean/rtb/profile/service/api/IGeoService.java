package com.ocean.rtb.profile.service.api;

import com.ocean.core.common.http.HttpInvokeException;
import com.ocean.rtb.profile.bean.api.GeoApiResp;

public interface IGeoService {
	public GeoApiResp  getGeoinfo(String imei,String ip,int imsiFlag)  throws HttpInvokeException;
}

package com.ocean.rtb.profile.service;

import com.ocean.rtb.persist.bean.thrift.profile.RtbUserInfo;

public interface IProfileService {
	public RtbUserInfo searchProfile(RtbUserInfo user);
}

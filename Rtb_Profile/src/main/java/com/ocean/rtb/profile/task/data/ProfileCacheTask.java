package com.ocean.rtb.profile.task.data;

import com.ocean.core.common.JsonUtils;
import com.ocean.rtb.profile.common.ProfileConstants;
public class ProfileCacheTask extends AbstractCacheTask{

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return ProfileConstants.PROFILE_GEO_CACHE_KEY_PREFIX+this.getData().getCacheId();
	}
	@Override
	public String formatData() {
		// TODO Auto-generated method stub
		return JsonUtils.toJson(this.getData());
	}

}

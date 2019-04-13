package com.ocean.rtb.statistics.task.data;

import com.ocean.core.common.JsonUtils;
import com.ocean.rtb.statistics.common.StatConstants;

public class StatCacheTask extends AbstractSyncTask{

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return StatConstants.STAT_CACHE_KEY_PREFIX+this.getData().getCacheId();
	}
	@Override
	public String formatData() {
		// TODO Auto-generated method stub
		return JsonUtils.toJson(this.getData());
	}

}

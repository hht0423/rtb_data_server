package com.ocean.rtb.statistics.task.data;

import com.ocean.rtb.persist.bean.cache.AbstractCacheBean;

public abstract class AbstractSyncTask {
	protected AbstractCacheBean data;
	public abstract String getKey();
	public abstract String formatData();
	public AbstractCacheBean getData() {
		return data;
	}
	public void setData(AbstractCacheBean data) {
		this.data = data;
	}
}

package com.ocean.rtb.statistics.task.service;

import com.ocean.rtb.persist.common.DbSyncException;

public interface ITaskStatSyncService {
    public void dataCache(String key,String value) throws DbSyncException;
}

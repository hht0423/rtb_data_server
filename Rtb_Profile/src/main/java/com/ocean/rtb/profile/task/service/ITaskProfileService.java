package com.ocean.rtb.profile.task.service;

import com.ocean.rtb.persist.common.DbSyncException;

public interface ITaskProfileService {
    public void dataCache(String key,String value) throws DbSyncException;
}

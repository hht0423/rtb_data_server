package com.ocean.rtb.adsync.task;

import com.ocean.rtb.persist.common.DbSyncException;

public interface ITaskAdSyncService {
    public void dataSync() throws DbSyncException;
}

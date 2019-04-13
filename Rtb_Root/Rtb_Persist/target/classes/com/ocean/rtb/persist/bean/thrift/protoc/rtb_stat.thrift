include "ad_rtb_data.thrift"

namespace java com.ocean.rtb.persist.bean.thrift.stat

//查询上报信息请求
struct RtbStatQueryReq {
    1: required ad_rtb_data.RtbSrcType srcType;//
    2: required list<string> srcIds; //广告源id
}

struct RtbStatQueryData {
    1: required string srcId; //广告源id
    2: required i64 balanceTotal; //广告主的总预算
    3: required i64 balanceRemain; //广告主的剩余预算
    4: optional map<i64, ad_rtb_data.RtbBalance> balance; //key: 广告id，每个广告ID预算情况统计, 如果是三方，默认key为1
}

//查询上报信息返回
struct RtbStatQueryResp {
    1: required i32 errorCode;
    2: optional string errorMsg;
    3: optional map<string, RtbStatQueryData> data; //预算数据, key: 广告源ID
}

//统计查询服务
service RtbStat {
    void ping(),

    //查询
    RtbStatQueryResp reportQueryData(1: RtbStatQueryReq req),
}

include "ad_rtb_data.thrift"

namespace java  com.ocean.rtb.persist.bean.thrift.adsync

//一个竞价席位的详细信息
struct RtbSrcInfo {
    1: required string srcId; //直客传代理商子账号ID,如果是三方平台传dsp的id
    2: required ad_rtb_data.RtbSrcProperty property; //席位类型，正常竞价席位/测试席位
    3: required ad_rtb_data.RtbSrcType srcType; //竞价广告来源类型：直客、三方
    4: required i32 price; //单价，以分为单位
    5: optional ad_rtb_data.AdInfo ad; //直客广告源广告
}

struct SpaceidConfigData {
    1: required i32 id; //ID
}

//一种广告位类型的一个尺寸统称为一个广告位（数据库中不能有广告位类型和尺寸比例都相同的席位存在）
struct SpaceidConfig {
    1: required i32 spaceId; //广告位ID
    2: required ad_rtb_data.RtbSpaceType type;  // 广告位类型
    3: required i32 spaceidWidth; //广告位类型宽度比例
    4: required i32 spaceidHeight; //广告位类型高度比例
    5: optional SpaceidConfigData data; //广告位其他配置信息
}

//广告位类型配置
struct QuerySpaceConfigResp {
	1:  required i32 errorCode;	 //返回码  0-请求成功
	2:  optional string errorMsg;
    3:  optional list<SpaceidConfig> configs;	  //ad spaceid config
}

//获取指定条数广告源ID的请求
struct QueryRtbSrcIdsReq
{
    1:required ad_rtb_data.RtbSrcType srcType;//
    2: required i32 spaceId;      //请求的广告位ID
    3: optional i32 start;        //从哪条开始请求,
    4: optional i32 max;          //一次请求多少,如果应答结果数据少于次数，说明已经拉完
}

//获取指定条数广告源ID的返回
struct QueryRtbSrcIdsResp{
	1: required i32 errorCode;
	2: optional string errorMsg;
	3: optional list<string> srcIds; //广告源ID列表
}

//获取指定广告源请求
struct QueryRtbSrcByIdReq {
    1: required ad_rtb_data.RtbSrcType srcType;//
	2: required i32 spaceId;      //请求的广告位ID
    3: required list<string> srcIds; //指定的请求源ID列表  //创建三方广告源的时候，id要以3000开头
}

struct QueryRtbSrcResp{
	1: required i32 errorCode;
	2: optional string errorMsg;
	3: optional list<RtbSrcInfo> srcContents;
}

//同步策略采用全量轮询+改动消息通知的方式
service RtbSync {
    void ping(),

    //获取系统内的广告位类型定义列表，数据不多，可以一次性取回
    QuerySpaceConfigResp getSpaceConfig();

    //获取广告源ID列表，可分批次
    QueryRtbSrcIdsResp getSrcIds(1: QueryRtbSrcIdsReq request);

    //获取指定广告源详细信息，一次可多条
    QueryRtbSrcResp getADByIds(1: QueryRtbSrcByIdReq request);
}

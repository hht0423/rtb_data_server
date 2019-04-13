namespace java  com.ocean.rtb.persist.bean.thrift.common

/* 返回码 */
enum AdResponseCode {
    RC_SUCCESS   = 0,    // 成功
    RC_ERROR     = 1,    // 未知错误
    RC_NODATA    = 2,    // 没数据
    RC_PARAM     = 3,
    RC_DATAERROR = 4,
    RC_PUTDOWN   = 5,    // 已下架
}

enum RtbSpaceType {
	BANNER = 1,           //横幅广告
	OPENING = 2,          //开屏广告
    INTERSTITIAL = 3,     //插屏广告
    INFOFLOW = 4,         //信息流广告
    TEXTLINK = 5,         //文字链广告
	LOCKSCREEN= 6,     //锁屏广告
}

enum RtbMatType {
	TEXT = 1,           //纯文字
	IMAGE = 2,          //纯图片
    TEXT_IMAGE = 3,     //图文
    VIDEO = 4,         //视频广告
    HTML = 5,         //html广告
}

//交互类型
enum InteractionType {
	NO_INTERACTION = 0;  //不交互
	BROWSE = 1;  //跳链接
	DOWNLOAD = 2;  //下载
}

enum RtbSrcType {
    SrcTypeSelf = 0,
    SrcTypeThird = 1,
}

enum RtbSrcProperty {
	SrcPropertyTesting = 0,
    SrcPropertyNormal = 1,
   
}

struct RtbBalance {
    1: required string balanceId;//后台流水id
    2: optional i64 validResults; //成功扣款额度
	3: optional i64 delayResults; //竞价成功，由于延迟还未扣费的额度
	4: optional i64 expiredResults; //上报失效的浪费额度
}

struct UserAdSpaceAttri {
	1: optional i32 adSpaceId;
	2: optional RtbSpaceType type;
	3: required i32 spaceWidth;  //广告位宽
	4: required i32 spaceHeight; //广告位高
	5: optional i32 appId;
    6: optional i32 bidFloor; //RTB底价，以分为单位
    7: optional bool allowedHtml; //is http allowed.
}

struct AdImg {
    1: optional string formate;     // 图片格式
    2: optional i32 height;         // 高
    3: optional i32 width;          // 宽
    4: optional string src;         // 源图片url
    5: optional string alt;         // 图片位置
    6: optional string ref;         // <IMG>标识
    7: optional string matid;       // 素材唯一ID

}

struct AdVid {
    1: optional string src;         // video url
    2: optional i32 width;          // 宽
    3: optional i32 height;         // 高
	4: optional string format;     // 格式: .mp4, .swf
    5: optional i32 duration;		// in seconds
    6: optional string img_src;     // capture of the video
}

struct AdApp {
    1: optional string cpName;               // 应用广告应用的名称
    2: optional string cpAuthor;             // 应用广告应用的作者
    3: optional string cpVersion;            // 应用广告应用的版本
    4: optional string cpLanguage;           // 应用广告应用的语言
    5: optional string cpPackage;            // 应用广告应用的包名
    6: optional string cpIcon;               // 应用广告应用的图标
    7: optional string cpMemo;               // 应用广告应用的简介
    8: optional list<string> cpImgs;         // 应用广告的图集
    9: optional string cpclass;              // 应用调起类名
    10: optional string deepLinkUrl;            // deep link url
    11: optional string cpUrl;            //apk 下载url
    12: optional string cpApkMd5;  //md5 sums
    13: optional i64 cpApkSize; //file size in byte
}

struct DynamicEffect {
    1: required i32 id;
    2: required string url;
    3: optional string matid;	// 综合平台素材ID
}


struct TagInfo {
	1:  required string tag;								//标签名称
	2:	required i32 match_type;							//标签匹配模式，0:屏蔽，1:包含
	3:	optional set<i32> space_ids;							//广告位ID，若有，则表示在这些广告位的请求时才匹配此标签
}

struct LimitCondition {
    1: required i32 operateType; //0 - 包含, 1 - 反包含
    2: optional list<string> conditions;
}

struct AdInfo {
    1: required i64 adId;                        // 广告id
	3: required map<string,list<ProDateTime>> proTimeDetail;    //key:yyyyMMdd
    4: optional i32 weight;             // 权重
    5: optional map<i32, LimitCondition> limitCondition; // key: 1 - geo, 2 - net,3-tag,4-model,5-brand,6-os
	6: optional string copywriting;           // 简介
    7: required string title;                       // 广告标题
    8: required i32 interactive_type;                    // 内容类型, 交互类型
    9: optional string linkurl;              // 链接类广告的链接
    10: optional map<string, list<string> > thirdReportLinks; //key: action, eg: pv, click, download. value: urls for that specified action.
	11: optional AdImg logoImg; //logo image
	12: optional AdVid adVid; //video for ad
	13: optional AdApp adApp; //video for ad
    14: optional bool isHtmlAd;  //whether this is html type.
    15: list<AdImg> imgsList;//图片列表
    16: optional string htmlSnippet; //html snippet, directly show in webview.
    17: optional string priceUCode; //价格每次修改的版本号
}
struct ProDateTime{
    1: optional i64 startTime;       // 当天在线时长 -- 开始在线时间, 时间戳，毫秒
    2: optional i64 endTime;         // 当天在线时长 -- 下线时间, 时间戳，毫秒
}
//手机品牌和型号请求
struct PhoneBrandInfo{
    1:  required string brandId;  //手机品牌id
    2:  optional set<string> models;  //手机型号集合
    3:  optional string brandName; //brand name
}

//to save in redis
struct BrandInfo {
	1: optional list<PhoneBrandInfo> brands;
}

struct HtmlTmpl {
	1: required i32 tmpl_id;
	2: required i32 tmpl_type;
	3: required string url;
}

/** 其他回传信息 */
struct RtbRetInfo {
    1: required string bidId;
}

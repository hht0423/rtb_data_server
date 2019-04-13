include "rtb_user.thrift"

namespace cpp rtb

struct RtbUserPortraitReq {
    1: required rtb_user.RtbUserInfo userInfo,
}

struct RtbUserPortraitResp {
    1: required rtb_user.RtbUserInfo userInfo,
}

service RtbPortrait {
    void ping(),

    //获取完整的用户信息
    RtbUserPortraitResp getUserInfo(1: RtbUserPortraitReq req),
}

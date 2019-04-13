namespace cpp rtb


struct RtbUserInfo {
    1:  optional string imei,                         // 手机imei
    2:  optional string os,                           // 系统 android, ios, pc
    3:  optional string osversion,                    // 手机操作系统版本
    4:  optional string phonemodel,                   // 手机型号
    5:  optional string mobile,                       // 运营商CMCC/CUCC/CTCC
    6:  optional string client_ip,                    // 客户端IP地址
    7:  optional string city,                         // 用户所在城市 city id
    8:  optional string lon,
    9:  optional string lat,
    10: optional string ua,                           // 客户端的User-Agent值
    11: optional string aaid,   				      //advertising id
    12: optional string adid, 					      //android id, for android
    13: optional string idfa, 						  //ios id, for ios
    14: optional string brand_id,					  //device brand id, determined by phone model
    15: optional string brand_name, 					  //device's vendor
    16: optional i32 device_height, 				  //device's height
    17: optional i32 device_width, 				  //device's width
    18: optional string mac, 					  //device's mac address
    19: optional string imsi, 					  //device's imsi
    20: optional string dip,                      //手机分辨率，单位尺寸像素的密度,如401
    21: optional string density,                  //屏幕的密度，如2.0
    22: optional string city_name,                //city name
    23: optional i32 lac,								//basestation lac
    24: optional i32 cid,								//basestation cid
    25: optional string os_api_level,
    26: optional string sn,
    27: optional map<string, list<string>> tag, //user tags
    28: optional i32 dev_price_level,	//设备价格级别
    29: optional string app,                          // app简称
    30: optional string type,                         // All或者具体类型
    31: optional string version,                      // 版本号字符串
    32: optional i32 result_num,                      // 数字
    33: optional string channel,                      // 渠道号
    34: optional string net,
    35: optional string hash,
}


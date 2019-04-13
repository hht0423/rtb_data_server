package com.ocean.rtb.adsync.service;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.rtb.persist.bean.thrift.adsync.QueryRtbSrcByIdReq;
import com.ocean.rtb.persist.bean.thrift.adsync.QueryRtbSrcIdsReq;
import com.ocean.rtb.persist.bean.thrift.adsync.QueryRtbSrcIdsResp;
import com.ocean.rtb.persist.bean.thrift.adsync.QueryRtbSrcResp;
import com.ocean.rtb.persist.bean.thrift.adsync.QuerySpaceConfigResp;
import com.ocean.rtb.persist.bean.thrift.adsync.RtbSrcInfo;
import com.ocean.rtb.persist.bean.thrift.adsync.SpaceidConfig;
import com.ocean.rtb.persist.bean.thrift.common.AdImg;
import com.ocean.rtb.persist.bean.thrift.common.AdResponseCode;
import com.ocean.rtb.persist.bean.thrift.common.AdVid;
import com.ocean.rtb.persist.bean.thrift.common.LimitCondition;
import com.ocean.rtb.persist.bean.thrift.common.ProDateTime;
import com.ocean.rtb.persist.bean.thrift.common.RtbSpaceType;
import com.ocean.rtb.persist.bean.thrift.common.RtbSrcProperty;
import com.ocean.rtb.persist.bean.thrift.common.RtbSrcType;
import com.ocean.rtb.persist.bean.ad.AdInfo;
import com.ocean.rtb.persist.bean.ad.AdOrientation;
import com.ocean.rtb.persist.bean.ad.AdSpace;
import com.ocean.rtb.persist.bean.ad.AdVideo;
import com.ocean.rtb.persist.bean.ad.AppInfo;
import com.ocean.rtb.persist.bean.ad.Material;
import com.ocean.rtb.persist.bean.cache.AdProTimeCacheBean;
import com.ocean.rtb.persist.bean.cache.AdProTimeTemp;
import com.ocean.rtb.persist.common.DbSyncException;
import com.ocean.rtb.persist.common.RtbConstants;
import com.ocean.rtb.persist.service.ad.IRtbDBService;
import com.ocean.rtb.persist.service.rtbcahe.IRtbCacheService;
@Component(value="adSyncService")
public class AdSyncService implements IAdSyncService{
	@Autowired
	private IRtbDBService rtbDbService;
	@Autowired
	private IRtbCacheService rtbCacheService;
	private static final Logger logger =MyLogManager.getLogger();
	private static final String ADID_TYPE_SELF="self";
	private static final String ADID_TYPE_THIRD="third";
	private static final String DATE_FORMAT="yyyyMMdd";
	
	private static final int ORIENTATION_KEY_AREAIDS=1;
	private static final  int ORIENTATION_KEY_NET=2;
	private static final int ORIENTATION_KEY_TAG=3;
	private static final  int ORIENTATION_KEY_MODEL=4;
	private static final int ORIENTATION_KEY_BRAND=5;
	private static final  int ORIENTATION_KEY_OS=6;
	private static final String ARRAYLIST_SPLIT=";";
    SimpleDateFormat format=new SimpleDateFormat(DATE_FORMAT);
    
	private static final String TIME_FORMAT="yyyy-MM-dd HH:mm:ss";
	private SimpleDateFormat TimeFormat=new SimpleDateFormat(TIME_FORMAT);
	@Override
	public QuerySpaceConfigResp getSpaceConfig() throws DbSyncException {
		// TODO Auto-generated method stub
		//get spaceinfo from cache
		List<AdSpace> spaceList=rtbCacheService.getAllSpaceInfo();
/*		
		if(CollectionUtils.isEmpty(spaceList)){//search from db
			spaceList=(List)rtbDbService.getObjeList(AdSpace.class,true);
		}*/
		if(CollectionUtils.isEmpty(spaceList)){
			return null;
		}
		QuerySpaceConfigResp resp= getSpaceConfigResp(spaceList);
		return resp;
	}

	@Override
	public QueryRtbSrcIdsResp getSrcIds(QueryRtbSrcIdsReq request)
			throws DbSyncException {
		// TODO Auto-generated method stub
		//get ad ids from cache
		List<String> list= rtbCacheService.getAdIdsByPage(request.getSpaceId(),request.getStart(),request.getSrcType().getValue());
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		QueryRtbSrcIdsResp resp=new QueryRtbSrcIdsResp();
		resp.setErrorCode(AdResponseCode.RC_SUCCESS.getValue());
		resp.setErrorMsg(RtbConstants.RETURN_MSG_OK);
		resp.setSrcIds(list);
		return resp;
	}

	@Override
	public QueryRtbSrcResp getADByIds(QueryRtbSrcByIdReq request)
			throws DbSyncException {
		QueryRtbSrcResp resp=new QueryRtbSrcResp();
		// TODO Auto-generated method stub
		Map<String,List<String>> adIdsMap= filtAdId(request.getSrcIds());
		List<String> selfAdIds=adIdsMap.get(ADID_TYPE_SELF);
		List<RtbSrcInfo> srcList=new ArrayList<RtbSrcInfo>();
		//get self ad info
		if(CollectionUtils.isNotEmpty(selfAdIds)){
	 		
			List<AdInfo> selfAdList= rtbCacheService.getSelfAdInfoBySpaceId(String.valueOf(request.getSpaceId()),selfAdIds);
			if(CollectionUtils.isEmpty(selfAdList)){
				logger.error("get self ad info by ad ids is empty,adIds:{}",selfAdIds);
			}else{
				srcList.addAll(cvtSelfSrcInfos(selfAdList));
			}
			
		}
		List<String> thirdAdIds=adIdsMap.get(ADID_TYPE_THIRD);
		if(CollectionUtils.isNotEmpty(thirdAdIds)){
			srcList.addAll(cvtThirdAd(thirdAdIds));
		}
		if(CollectionUtils.isEmpty(srcList)){
			return null;
		}
		resp.setErrorCode(AdResponseCode.RC_SUCCESS.getValue());
		resp.setSrcContents(srcList);
		//get third ad info
		return resp;
	}
	private List<RtbSrcInfo>  cvtThirdAd(List<String>  thirdAdIds){
		List<RtbSrcInfo> list=new ArrayList<RtbSrcInfo>();
		return list;
	}

	private List<RtbSrcInfo> cvtSelfSrcInfos(List<AdInfo> selfAdList){
		List<RtbSrcInfo> rtbAdList=new ArrayList<RtbSrcInfo>(selfAdList.size());
		for(AdInfo adInfo: selfAdList){
			RtbSrcInfo rtbAd=this.cvtSelfSrcInfo(adInfo);
			rtbAdList.add(rtbAd);
		}
		return rtbAdList;
	}
	private RtbSrcInfo cvtSelfSrcInfo(AdInfo adInfo){
		RtbSrcInfo rtbSrc=new RtbSrcInfo();
		rtbSrc.setSrcId(adInfo.getUserId());
		rtbSrc.setProperty(RtbSrcProperty.findByValue(adInfo.getIsTest()));
		rtbSrc.setSrcType(RtbSrcType.SrcTypeSelf);
		rtbSrc.setPrice(adInfo.getPrice());
		rtbSrc.setAd(cvtSelfAd(adInfo));
		
		return rtbSrc;
	}
	private com.ocean.rtb.persist.bean.thrift.common.AdInfo cvtSelfAd(AdInfo adInfo){
		com.ocean.rtb.persist.bean.thrift.common.AdInfo rtbAd= new com.ocean.rtb.persist.bean.thrift.common.AdInfo();
		rtbAd.setAdId(Long.parseLong(adInfo.getId()));
		rtbAd.setCopywriting(adInfo.getCopyWriting());
		rtbAd.setTitle(adInfo.getTitle());
		rtbAd.setInteractive_type(adInfo.getInterType());
		rtbAd.setLinkurl(adInfo.getProUrl());
		rtbAd.setPriceUCode(adInfo.getPriceUCode());
		if(adInfo.getLogo()>0){
			Material mate=(Material)rtbCacheService.getObectInfo(Material.class,  String.valueOf(adInfo.getLogo()));
			
			AdImg logo=new AdImg();
			logo.setSrc(mate.getMateUrl());
			rtbAd.setLogoImg(logo);
		}
		
		if(StringUtils.isNotEmpty(adInfo.getSnippet())){
			rtbAd.setIsHtmlAd(true);
			rtbAd.setHtmlSnippet(adInfo.getSnippet());
		}else if(adInfo.getAppId()!=null&&adInfo.getAppId()>0){//get self ad relate app info
			AppInfo app=(AppInfo)rtbCacheService.getObectInfo(AppInfo.class, String.valueOf(adInfo.getAppId()));
			if(app==null){
				logger.error("get app info empty,adId:{}",adInfo.getId());
			}else{
				rtbAd.setAdApp(cvtRtbApp(app));
			}
			
		}else if(adInfo.getVdId()!=null&&adInfo.getVdId()>0){//get self ad relate video info
			AdVideo video=(AdVideo)rtbCacheService.getObectInfo(AdVideo.class,  String.valueOf(adInfo.getVdId()));
			if(video==null){
				logger.error("get video info empty,adId:{}",adInfo.getId());
			}else{
				rtbAd.setAdVid(this.cvtRtbVideo(video));
			}
		}
		if(StringUtils.isNotEmpty(adInfo.getMateIdList())){//get self ad relate material info
			//Material mate=(Material)rtbCacheService.getObectInfo(Material.class,  String.valueOf(adInfo.getMateId()));
			rtbAd.setImgsList(cvtRtbImgs(adInfo.getMateIdList().split(ARRAYLIST_SPLIT)));
		}
		if(adInfo.getOrtId()!=null&&adInfo.getOrtId()>0){//定向信息
			AdOrientation ort=(AdOrientation)rtbCacheService.getObectInfo(AdOrientation.class,  String.valueOf(adInfo.getOrtId()));
			rtbAd.setLimitCondition(cvtRtbOrientation(ort));
		}
		
		//投放时间
		AdProTimeCacheBean proTimeBean=rtbCacheService.getProTimeInfo(adInfo.getId());
		rtbAd.setProTimeDetail(this.cvtPTBeanRtbProTime(proTimeBean));
		return rtbAd;
	}
	private Map<Integer, LimitCondition> cvtRtbOrientation(AdOrientation ort){
		Map<Integer, LimitCondition> ortMap=new HashMap<Integer, LimitCondition>();
		final int OPERATE_TYPE_FORWORD=0;
		final int OPERATE_TYPE_REVERSE=1; 
		if(StringUtils.isNotEmpty(ort.getAreaIds())){
			LimitCondition limit=new LimitCondition();
			limit.setOperateType(ort.getAsType()==0?OPERATE_TYPE_FORWORD:OPERATE_TYPE_REVERSE);
			limit.setConditions(Arrays.asList(ort.getAreaIds().split(ARRAYLIST_SPLIT)));
			ortMap.put(ORIENTATION_KEY_AREAIDS, limit);
		}
		if(StringUtils.isNotEmpty(ort.getNetType())){
			LimitCondition limit=new LimitCondition();
			
			limit.setOperateType(OPERATE_TYPE_FORWORD);
			limit.setConditions(Arrays.asList(ort.getNetType().split(ARRAYLIST_SPLIT)));
			ortMap.put(ORIENTATION_KEY_NET, limit);
		}
		if(StringUtils.isNotEmpty(ort.getTagIds())){
			LimitCondition limit=new LimitCondition();
			
			limit.setOperateType(OPERATE_TYPE_FORWORD);
			limit.setConditions(Arrays.asList(ort.getTagIds().split(ARRAYLIST_SPLIT)));
			ortMap.put(ORIENTATION_KEY_TAG, limit);
		}
		if(StringUtils.isNotEmpty(ort.getPhoneModel())){
			LimitCondition limit=new LimitCondition();
			
			limit.setOperateType(ort.getMsType()==0?OPERATE_TYPE_FORWORD:OPERATE_TYPE_REVERSE);
			limit.setConditions(Arrays.asList(ort.getPhoneModel().split(ARRAYLIST_SPLIT)));
			ortMap.put(ORIENTATION_KEY_MODEL, limit);
		}
		if(StringUtils.isNotEmpty(ort.getBrand())){
			LimitCondition limit=new LimitCondition();
			
			limit.setOperateType(ort.getBsType()==0?OPERATE_TYPE_FORWORD:OPERATE_TYPE_REVERSE);
			limit.setConditions(Arrays.asList(ort.getBrand().split(ARRAYLIST_SPLIT)));
			ortMap.put(ORIENTATION_KEY_BRAND, limit);
		}
		if(StringUtils.isNotEmpty(ort.getOsType())){
			LimitCondition limit=new LimitCondition();
			
			limit.setOperateType(OPERATE_TYPE_FORWORD);
			limit.setConditions(Arrays.asList(ort.getOsType().split(ARRAYLIST_SPLIT)));
			ortMap.put(ORIENTATION_KEY_OS, limit);
		}
		return ortMap;
	}
	private Map<String,List<ProDateTime>> cvtPTBeanRtbProTime(AdProTimeCacheBean proTimeBean){
		
		List<AdProTimeTemp> proTimeList=proTimeBean.getProTimeList();
		if(CollectionUtils.isEmpty(proTimeList)){

			throw new DbSyncException(MessageFormat.format("get ad pro time detail by adId {0} is empty",proTimeBean.getAdId()));
		}
		Map<String,List<ProDateTime>> map=new HashMap<String,List<ProDateTime>> ();
		for(AdProTimeTemp proTime:proTimeList){
			String key=proTime.getDateStart();
			
			if(!map.containsKey(key)){
				List<ProDateTime> rtbProList=new ArrayList<ProDateTime>();
				map.put(key, rtbProList);
			}
			map.get(key).add(cvtRtbProTime(proTime));
		}
		return map;
	}
/*	private AdProTimeTemp cvt2PT(AdProTime pt){

		AdProTimeTemp ptTemp=new AdProTimeTemp();
		ptTemp.setAdId(pt.getId());
		ptTemp.setDateStart(DateFormat.format(pt.getDateStart()));
		ptTemp.setDateEnd(DateFormat.format(pt.getDateEnd()));
		//TimeFormat
		ptTemp.setTimeStart(TimeFormat.format(pt.getTimeStart()));
		ptTemp.setTimeEnd(TimeFormat.format(pt.getTimeEnd()));
		ptTemp.setStatus(pt.getStatus());
		return ptTemp;
	}*/
/*	private ProDateTime cvtRtbProTime(AdProTime proTime){
		ProDateTime rtbProTime=new ProDateTime();
		rtbProTime.setStartTime(proTime.getTimeStart().getTime());
		rtbProTime.setEndTime(proTime.getTimeEnd().getTime());
		return rtbProTime;
	}*/
	private ProDateTime cvtRtbProTime(AdProTimeTemp proTime){
		ProDateTime rtbProTime=new ProDateTime();
		
		try {
			rtbProTime.setStartTime(TimeFormat.parse(proTime.getDateStart()+" "+proTime.getTimeStart()).getTime());
			rtbProTime.setEndTime(TimeFormat.parse(proTime.getDateStart()+" "+proTime.getTimeEnd()).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rtbProTime;
	}
	private List<AdImg>  cvtRtbImgs(String[] mateIdArr ){

		List<AdImg>  imgList=new ArrayList<AdImg> ();
		for(String str:mateIdArr){
			Material mate=(Material)rtbCacheService.getObectInfo(Material.class, str);
			if(mate!=null){
				AdImg img=new AdImg();
				img.setSrc(mate.getMateUrl());
				if(mate.getWidth()!=null&&mate.getHeight()!=null){
					img.setWidth(mate.getWidth());
					img.setHeight(mate.getHeight());
				}

				imgList.add(img);
			}else{
				logger.error("serch mate info by mate id {} is empty",str);
			}
			
		}
	   return imgList;
	
	}
	private AdVid cvtRtbVideo(AdVideo video){
		AdVid rtbVd=new AdVid();
		rtbVd.setSrc(video.getSrc());
		rtbVd.setFormat(video.getFormat());
		rtbVd.setDuration(video.getDuration());
		Material mate=(Material)rtbCacheService.getObectInfo(Material.class,  String.valueOf(video.getImg()));
		if(mate!=null){
			rtbVd.setImg_src(mate.getMateUrl());
			if(mate.getWidth()!=null&&mate.getHeight()!=null){
				rtbVd.setWidth(mate.getWidth());
				rtbVd.setHeight(mate.getHeight());
			}
		}
		return rtbVd;
	}
	private com.ocean.rtb.persist.bean.thrift.common.AdApp cvtRtbApp(AppInfo app){
		com.ocean.rtb.persist.bean.thrift.common.AdApp rtbApp=new com.ocean.rtb.persist.bean.thrift.common.AdApp();
		rtbApp.setCpName(app.getAppName());
		rtbApp.setCpVersion(app.getVersionName());
		rtbApp.setCpPackage(app.getPkgName());
		Material iconMate=(Material)rtbCacheService.getObectInfo(Material.class,  String.valueOf(app.getIcon()));
		if(iconMate!=null){
			rtbApp.setCpIcon(iconMate.getMateUrl());
		}
		
		if(StringUtils.isNotEmpty(app.getImgs())){
			List<String> cpImgs=new ArrayList<String>(app.getImgs().split(ARRAYLIST_SPLIT).length);
			for(String str:app.getImgs().split(ARRAYLIST_SPLIT)){
				Material mate=(Material)rtbCacheService.getObectInfo(Material.class, str);
				if(mate!=null){
					cpImgs.add(mate.getMateUrl());
				}
				
			}
			rtbApp.setCpImgs(cpImgs);
		}
		rtbApp.setCpMemo(app.getAppDesc());
		rtbApp.setDeepLinkUrl(app.getDeepLink());
		rtbApp.setCpUrl(app.getDownUrl());
		rtbApp.setCpApkMd5(app.getMd5());
		rtbApp.setCpApkSize(app.getSize());
		return rtbApp;
	}
	private Map<String,List<String>> filtAdId(List<String> adIds){
		Map<String,List<String>>  map=new HashMap<String,List<String>> ();
		List<String> selfList=new ArrayList<String>();
		List<String> thridList=new ArrayList<String>();
		for(String adId:adIds){
			//String adIdStr=String.valueOf(adId);
			if(adId.startsWith(RtbConstants.AD_TYPE_THIRD)){
				thridList.add(adId);
			}else{
				selfList.add(adId);
			}
		}
		map.put(ADID_TYPE_SELF, selfList);
		map.put(ADID_TYPE_THIRD, thridList);
	    return map;	
	}
	private QuerySpaceConfigResp getSpaceConfigResp(List<AdSpace> spaceList){
		QuerySpaceConfigResp resp=new QuerySpaceConfigResp();
		resp.setErrorCode(AdResponseCode.RC_SUCCESS.getValue());
		resp.setErrorMsg(RtbConstants.RETURN_MSG_OK);
		List<SpaceidConfig> configs=new ArrayList<SpaceidConfig>();
		for(AdSpace adSpace:spaceList){
			configs.add(cvtSpaceInfo(adSpace));
		}
		resp.setConfigs(configs);
		return resp;
	}
	private SpaceidConfig cvtSpaceInfo(AdSpace adSpace){
		SpaceidConfig spaceR=new SpaceidConfig();
		spaceR.setSpaceId(Integer.parseInt(adSpace.getId()));
		spaceR.setSpaceidHeight(adSpace.getHeight());
		spaceR.setSpaceidWidth(adSpace.getWidth());
		spaceR.setType(cvtSpaceType(adSpace.getSpaceType()));
		return spaceR;
	}
	private RtbSpaceType cvtSpaceType(int type){
		//1-横幅广告,2-开屏广告,3-插屏广告,4-信息流广告,5-文字链广告,6-锁屏广告
		switch(type){
		   case 1:
			   return RtbSpaceType.BANNER;
		   case 2:
			   return RtbSpaceType.OPENING;
		   case 3:
			   return RtbSpaceType.INTERSTITIAL;
		   case 4:
			   return RtbSpaceType.INFOFLOW;
		   case 5:
			   return RtbSpaceType.TEXTLINK;
		   case 6:
			   return RtbSpaceType.LOCKSCREEN;
		   default:
			   throw new DbSyncException("no such space type:"+type);
		}
	}


}

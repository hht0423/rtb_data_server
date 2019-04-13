package com.ocean.rtb.profile.server;
import org.apache.logging.log4j.Logger;
import org.apache.thrift.TException;
import com.ocean.core.common.system.SystemContext;
import com.ocean.rtb.persist.bean.thrift.profile.RtbUserInfo;
import com.ocean.rtb.persist.bean.thrift.profile.RtbUserPortraitReq;
import com.ocean.rtb.persist.bean.thrift.profile.RtbUserPortraitResp;
import com.ocean.rtb.persist.common.RtbException;
import com.ocean.rtb.persist.common.RtbLogManager;
import com.ocean.rtb.profile.service.IProfileService;
public class ProfileInvoker extends AbstractProfileInvoker{
	private  static final Logger  LOGGER = RtbLogManager.getBusinessLogger();
	@Override
	public void ping() throws TException {
		// TODO Auto-generated method stub

	}

	@Override
	public RtbUserPortraitResp getUserInfo(RtbUserPortraitReq req)
			throws TException {
		// TODO Auto-generated method stub
		RtbUserPortraitResp resp=new RtbUserPortraitResp();
		IProfileService profileService=(IProfileService) SystemContext.getServiceHandler().getService(IProfileService.class);
		try{
			RtbUserInfo returnUser =profileService.searchProfile(req.getUserInfo());
			if(returnUser==null){
				resp.setUserInfo(req.getUserInfo());
			}else{
				resp.setUserInfo(returnUser);
			}
		}catch(RtbException e){
			LOGGER.error("get profile info error(RtbException):{}",e.getMsg(),e);
		}catch(Throwable e){
			LOGGER.error("get profile info error(Throwable):{}",e.getMessage(),e);
			resp.setUserInfo(req.getUserInfo());
		}

		
		return resp;
	}
}

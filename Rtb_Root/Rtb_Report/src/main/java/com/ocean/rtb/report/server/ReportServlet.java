package com.ocean.rtb.report.server;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.jboss.netty.handler.codec.http.QueryStringDecoder;
import org.springframework.util.CollectionUtils;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.UniversalUtils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.rtb.core.common.encrypt.EncryptUtils;
import com.ocean.rtb.persist.common.RtbConstants;
import com.ocean.rtb.persist.common.RtbLogManager;
import com.ocean.rtb.report.common.BasicServlet;
import com.ocean.rtb.report.server.bean.AdReportData;
import com.ocean.rtb.report.server.bean.ReportError;
import com.ocean.rtb.report.server.bean.ReportResponse;
import com.ocean.rtb.report.service.servlet.RtbReportService;

public class ReportServlet extends BasicServlet {
	private static final long serialVersionUID = -8804124251850514230L;
	private final static Logger LOGGER=RtbLogManager.getBusinessLogger();
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final String SPLIT="|";
	private RtbReportService getReportService(){
		return (RtbReportService)SystemContext.getServiceHandler().getService(RtbReportService.class);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Enumeration<String> pNames = req.getParameterNames();
			StringBuilder sb = new StringBuilder(150);
			String name = null;
			while (pNames.hasMoreElements()) {
				name = pNames.nextElement();
				sb.append(name).append("=").append(req.getParameter(name)).append(SPLIT);
			}
			LOGGER.debug("rtb report event params:" + sb.toString());
			//先从redis中获取该竞价的日志信息
			String bid = req.getParameter(AdReportData.AdReprotEnum.REQID.getParamName());
			
			//1-校验参数，检验必传参数是否正确
			String data = req.getParameter("data");
			if(data == null) {
				response(req, resp,ReportError.PARAM_ERROR);
				LOGGER.error("rtb report parameter error,data is empty:{}",sb.toString());
				return;
			}
			
			//2-校验上报数据，检测加密是否正确
			data = URLEncoder.encode(data, DEFAULT_CHARSET);
			data = new String(EncryptUtils.decryptContent(data.getBytes()));
			Map<String, List<String>> paramMap = new QueryStringDecoder(data, false).getParameters();
			
			if(!checkEncrypt(paramMap)){
				response(req, resp,ReportError.ENCRYPT_ERROR);
				LOGGER.error("report encrypt error,data is empty:{}",sb.toString());
				return;
			}
			response(req, resp,ReportError.SUCCEED);
			
			//3-校验上报是否过期
			AdReportData reportData=getReportService().getCacheReportData(bid);
			if(reportData==null){
				report(this.getInvalidData(req, paramMap));
			}else{
				report(this.getValidData(req,reportData));
			}
			
		} catch (Throwable e) {
			LOGGER.error("rtb  event log error:{},req :{}",e.getMessage(),req.getQueryString(), e);
		}
	}
	private boolean checkEncrypt(Map<String, List<String>> paramMap ){
		List<String> values = paramMap.get(AdReportData.AdReprotEnum.REQID.getParamName());
		if(CollectionUtils.isEmpty(values)) {
			return false;
		}
		return true;
	}
	private String [] getValidData(HttpServletRequest req,AdReportData reportData){
		AdReportData.AdReprotEnum [] fields = AdReportData.AdReprotEnum.values();
		String [] params = new String[fields.length];
		String nowFormat = format.format(new Date());
		String etype = req.getParameter(AdReportData.AdReprotEnum.ETYPE.getParamName());
		String nodeId=SystemContext.getStaticPropertyHandler().get(RtbConstants.NODE_ID);
		params[AdReportData.AdReprotEnum.NODEID.getIndex()] = nodeId;
		params[AdReportData.AdReprotEnum.ETYPE.getIndex()] = UniversalUtils.filterNullAndTrim(etype);
		params[AdReportData.AdReprotEnum.TIMESTAMP.getIndex()] = nowFormat;
		params[AdReportData.AdReprotEnum.VALID.getIndex()] = "1";
		
		params[AdReportData.AdReprotEnum.REQID.getIndex()]=UniversalUtils.filterNullAndTrim(reportData.getBid());
		params[AdReportData.AdReprotEnum.BIDTIMESTAMP.getIndex()]=UniversalUtils.filterNullAndTrim(reportData.getBidtm());
		params[AdReportData.AdReprotEnum.SRCID.getIndex()]=UniversalUtils.filterNullAndTrim(reportData.getSrcId());
		params[AdReportData.AdReprotEnum.SRCTYPE.getIndex()]=UniversalUtils.filterNullAndTrim(reportData.getSrcType());
		params[AdReportData.AdReprotEnum.SRCPROPERTY.getIndex()]=UniversalUtils.filterNullAndTrim(reportData.getSrcPt());
		params[AdReportData.AdReprotEnum.SPACEID.getIndex()]=UniversalUtils.filterNullAndTrim(reportData.getSpaceId());
		params[AdReportData.AdReprotEnum.SPACETYPE.getIndex()]=UniversalUtils.filterNullAndTrim(reportData.getSpaceType());
		params[AdReportData.AdReprotEnum.ADID.getIndex()]=UniversalUtils.filterNullAndTrim(reportData.getAdId());
		params[AdReportData.AdReprotEnum.PRICEUCODE.getIndex()]=UniversalUtils.filterNullAndTrim(reportData.getPriceUCode());
		params[AdReportData.AdReprotEnum.PRICE.getIndex()]=UniversalUtils.filterNullAndTrim(reportData.getPrice());
		params[AdReportData.AdReprotEnum.UID.getIndex()]=UniversalUtils.filterNullAndTrim(reportData.getUid());
		params[AdReportData.AdReprotEnum.IP.getIndex()]=UniversalUtils.filterNullAndTrim(reportData.getIp());
		return params;
		
	}

	private String []  getInvalidData(HttpServletRequest req,Map<String, List<String>> paramMap){
		AdReportData.AdReprotEnum [] fields = AdReportData.AdReprotEnum.values();
		String [] params = new String[fields.length];

		for(AdReportData.AdReprotEnum field : fields) {
			List<String> values = paramMap.get(field.getParamName());
			if(CollectionUtils.isEmpty(values)) {
				params[field.getIndex()] = "";
				continue;
			}
			params[field.getIndex()] = UniversalUtils.filterNullAndTrim(values.get(0));
		}
		String nowFormat = format.format(new Date());
		String etype = req.getParameter(AdReportData.AdReprotEnum.ETYPE.getParamName());
		String nodeId=SystemContext.getStaticPropertyHandler().get(RtbConstants.NODE_ID);
		
		params[AdReportData.AdReprotEnum.NODEID.getIndex()] = nodeId;
		params[AdReportData.AdReprotEnum.ETYPE.getIndex()] = etype;
		params[AdReportData.AdReprotEnum.TIMESTAMP.getIndex()] = nowFormat;
		params[AdReportData.AdReprotEnum.VALID.getIndex()] = "0";
		return params;
	}
	private void report(String [] params){
		StringBuilder sb = new StringBuilder();
		//实时日志的记录
		sb.delete(0, sb.length());
		sb.append(params[AdReportData.AdReprotEnum.TIMESTAMP.getIndex()]).append(SPLIT);
		sb.append(params[AdReportData.AdReprotEnum.NODEID.getIndex()]).append(SPLIT);
		sb.append(params[AdReportData.AdReprotEnum.ETYPE.getIndex()]).append(SPLIT);
		sb.append(params[AdReportData.AdReprotEnum.REQID.getIndex()]).append(SPLIT);
		sb.append(params[AdReportData.AdReprotEnum.BIDTIMESTAMP.getIndex()]).append(SPLIT);
		sb.append(params[AdReportData.AdReprotEnum.SRCID.getIndex()]).append(SPLIT);
		sb.append(params[AdReportData.AdReprotEnum.SRCTYPE.getIndex()]).append(SPLIT);
		sb.append(params[AdReportData.AdReprotEnum.SRCPROPERTY.getIndex()]).append(SPLIT);
		sb.append(params[AdReportData.AdReprotEnum.SPACEID.getIndex()]).append(SPLIT);
		sb.append(params[AdReportData.AdReprotEnum.SPACETYPE.getIndex()]).append(SPLIT);
		sb.append(params[AdReportData.AdReprotEnum.ADID.getIndex()]).append(SPLIT);
		sb.append(params[AdReportData.AdReprotEnum.PRICE.getIndex()]).append(SPLIT);
		sb.append(params[AdReportData.AdReprotEnum.UID.getIndex()]).append(SPLIT);
		sb.append(params[AdReportData.AdReprotEnum.IP.getIndex()]).append(SPLIT);
		sb.append(params[AdReportData.AdReprotEnum.VALID.getIndex()]).append(SPLIT);
		RtbLogManager.getReportLogger().info(sb.toString());
	}
	private void response(HttpServletRequest req, HttpServletResponse resp,ReportError re) {
		ReportResponse response=new ReportResponse();
		response.setMsg(re.getMsg());
		response.setErrorCode(re.getValue());
		try {
			String out =JsonUtils.toJson(response);
			String jsonp = req.getParameter("jsonp");
			if (StringUtils.isEmpty(jsonp)) {
				jsonp = req.getParameter("callback");
			}
			if (!StringUtils.isEmpty(jsonp)) {
				out = jsonp + "(" + out + ")";
			}
			resp.getWriter().append(out);// 尽早做相应
		} catch (Exception e) {
			LOGGER.error("appdist jsonp error:" + e.getMessage(), e);
		}
	}
}

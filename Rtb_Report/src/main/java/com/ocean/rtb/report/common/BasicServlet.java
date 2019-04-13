package com.ocean.rtb.report.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.ByteString;
import com.ocean.rtb.persist.common.RtbLogManager;
public class BasicServlet extends HttpServlet {
	private static final long serialVersionUID = -6576651873124781913L;
	private static final Logger logger = RtbLogManager.getBusinessLogger();
	public static final String DEFAULT_CHARSET = "UTF-8";
	protected void outputJson(Object json, HttpServletResponse response) throws IOException {
		String text = JSONObject.toJSONString(json);
		response.getWriter().append(text);
		response.getWriter().flush();
	}

	public String getHttpBodyContentAsString(HttpServletRequest request) throws IOException {
		byte[] buf = getHttpBodyContentAsBytes(request);
		return new String(buf, 0, buf.length, DEFAULT_CHARSET);
	}

	public byte[] getHttpBodyContentAsBytes(HttpServletRequest request) throws IOException {
		int contentLen = request.getContentLength();
		if (contentLen <= 0) {
			return getContentsWhenLenNotKnow(request);
		}
		byte[] buf = new byte[contentLen];
		IOUtils.readFully(request.getInputStream(), buf);
		return buf;
	}

	private byte[] getContentsWhenLenNotKnow(HttpServletRequest request) throws IOException {
		InputStream is = request.getInputStream();
		List<Byte> byteList = new ArrayList<Byte>();
		int data = 0;
		while ((data = is.read()) != -1) {
			byteList.add((byte) data);
		}
		if (byteList.size() == 0) {
			logger.error("body empty:url={}", request.getRequestURL().toString());
			return new byte[] {};
		}
		byte[] bytes = new byte[byteList.size()];
		int i = 0;
		for (Byte b : byteList) {
			bytes[i++] = b;
		}
		return bytes;
	}

	@SuppressWarnings("unused")
	private boolean isChunked(HttpServletRequest request) {
		if ("chunked".equals(request.getHeader("transfer-encoding"))) {
			return true;
		}
		return false;
	}

	public void outputProtobuf(ByteString bytes, HttpServletResponse response) throws IOException {
		byte[] content = bytes.toByteArray();
		response.getOutputStream().write(content);
		response.getOutputStream().close();
	}

	protected String getRemoteIp(HttpServletRequest req) {
		//慎用头域信息
		String forwardIp = req.getHeader("X-Forwarded-For");
		if(!StringUtils.isEmpty(forwardIp)) {
			return forwardIp;
		}
		return req.getRemoteAddr();
	}

	protected String getPeriodFromAdreq(String gtm, long logTime) {
		Long gtmValue = null;
		try {
			gtmValue = Long.valueOf(gtm);
			return String.valueOf((logTime - gtmValue) / (1000 * 60));
		} catch (Throwable e) {
			logger.error("convert gtm error:gtm={}", gtm);
		}
		return "";
	}
}

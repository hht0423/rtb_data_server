package com.ocean.rtb.persist.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtils {
	public static Gson getFormatGson(){
	    Gson gson = new GsonBuilder()
	    .setDateFormat("yyyy-MM-dd  hh:mm:ss")
	    .create();
	    return gson;
	}
}

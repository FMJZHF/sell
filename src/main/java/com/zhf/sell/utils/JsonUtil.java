package com.zhf.sell.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 将数据格式化为json
 */
public class JsonUtil {

    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}

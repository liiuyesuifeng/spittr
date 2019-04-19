package com.mvc.utils;

import com.google.gson.Gson;

public class JsonUtil {
    private JsonUtil(){

    }
    private static final Gson gson;
    static {
        gson = new Gson();
    }
    public static synchronized Gson getGson(){
        return gson;
    }
    public static String formatJson(Object ob){
        return gson.toJson(ob);
    }
    public static <T> T formJson(String json,Class<T> tClass){
        return gson.fromJson(json, tClass);
    }
}

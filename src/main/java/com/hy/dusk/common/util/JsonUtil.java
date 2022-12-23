package com.hy.dusk.common.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.TypeReference;

/**
 * @author: ZhaoZhan
 * @date: 2022-12-23 15:58
 */
public class JsonUtil {
    public static <T> T read(String json, String path, T defaultVal) {
        final T val = (T) JSONPath.read(json, path);
        if (val == null) {
            return defaultVal;
        } else {
            return val;
        }
    }

    public static <T> T read(String json, String path) {
        return (T) JSONPath.read(json, path);
    }

    public static <T> T read(JSONObject jsonObject, String path, T defaultVal) {
        return read(jsonObject.toString(), path, defaultVal);
    }

    public static <T> T read(JSONObject jsonObject, String path) {
        return read(jsonObject.toString(), path);
    }

    public static void set(JSONObject jsonObject, String path, Object value) {
        JSONPath.set(jsonObject, path, value);
    }

    public static <T> T str2obj(String jsonStr, Class<T> clazz) {
        return JSONObject.parseObject(jsonStr, clazz);
    }

    public static <T> T str2obj(String jsonStr, TypeReference<T> typeReference) {
        return JSONObject.parseObject(
                jsonStr,
                typeReference
        );
    }

    public static JSONObject str2JsonObj(String jsonStr) {
        return JSONObject.parseObject(jsonStr);
    }

    public static String obj2JsonStr(Object obj) {
        return JSON.toJSONString(obj);
    }
    public static JSONObject obj2JsonObj(Object obj) {
        return str2JsonObj(obj2JsonStr(obj));
    }
}

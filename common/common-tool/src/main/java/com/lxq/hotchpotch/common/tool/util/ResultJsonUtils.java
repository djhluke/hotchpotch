package com.lxq.hotchpotch.common.tool.util;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author luxinqiang
 */
public class ResultJsonUtils {

    public static void successMap(Map<String, Object> map) {
        if (map == null) {
            map = Maps.newHashMap();
        }
        map.putIfAbsent("success", true);
        map.putIfAbsent("code", 200);
        map.putIfAbsent("status", 200);
        map.putIfAbsent("msg", "成功");
    }

    public static void errorMap(Map<String, Object> map) {
        errorMap(map, null);
    }

    public static void errorMap(Map<String, Object> map, Exception e) {
        if (map == null) {
            map = Maps.newHashMap();
        }
        map.putIfAbsent("success", false);
        map.putIfAbsent("code", 500);
        map.putIfAbsent("status", 500);
        map.putIfAbsent("msg", "异常");
        if (e != null) {
            e.printStackTrace();
            map.putIfAbsent("error", e.getMessage());
        }
    }

}

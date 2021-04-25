//package com.lxq.hotchpotch.common.tool.util;
//
//import org.springframework.cglib.beans.BeanMap;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class ConvertUtils {
//
//    public static Map<String, Object> convertEntityToMap(Object obj) {
//        Map<String, Object> result = new HashMap<>();
//        BeanMap beanMap = BeanMap.create(obj);
//        for (Object key : beanMap.keySet()) {
//            result.put(key + "", beanMap.get(key));
//        }
//        return result;
//    }
//
//}

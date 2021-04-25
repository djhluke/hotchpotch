//package com.lxq.hotchpotch.common.tool.util;
//
//import com.google.common.collect.Lists;
//import com.lxq.hotchpotch.common.tool.annotation.Dict;
//
//import java.lang.reflect.Field;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//public class DictUtils {
//
//    public static <T> void dictLabel(List<T> list, Map<String, List<DictDto>> dictDtoListMap) {
//        for (T t : list) {
//            dictLabel(t, dictDtoListMap);
//        }
//    }
//
//    public static <T> void dictLabel(T t, Map<String, List<DictDto>> dictDtoListMap) {
//        Field[] fields = t.getClass().getDeclaredFields();
//        for (Field field : fields) {
//            try {
//                boolean filedHasAnnotation = field.isAnnotationPresent(Dict.class);
//                if (filedHasAnnotation) {
//                    field.setAccessible(true);
//                    String fieldName = field.getName();
//                    Object fieldValue = field.get(t);
//                    Object labelFieldValue = fieldValue;
//                    Dict annotation = field.getAnnotation(Dict.class);
//                    String code = annotation.code();
//                    String labelFieldName = annotation.labelField();
//                    if ("".equals(labelFieldName)) {
//                        labelFieldName = fieldName + "Label";
//                    }
//                    List<DictDto> dictDtoList = dictDtoListMap.get(code);
//                    if (dictDtoList != null) {
//                        Optional<DictDto> first = dictDtoList.stream().filter(dictDto -> dictDto.getValue().equals(fieldValue)).findFirst();
//                        if (first.isPresent()) {
//                            labelFieldValue = first.get().getLabel();
//                        }
//                    }
//                    Field labelField = t.getClass().getDeclaredField(labelFieldName);
//                    labelField.setAccessible(true);
//                    labelField.set(t, labelFieldValue);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static <T> List<String> findCodeList(T t) {
//        List<String> codeList = Lists.newArrayList();
//        Field[] fields = t.getClass().getDeclaredFields();
//        for (Field field : fields) {
//            try {
//                boolean filedHasAnnotation = field.isAnnotationPresent(Dict.class);
//                if (filedHasAnnotation) {
//                    field.setAccessible(true);
//                    Dict annotation = field.getAnnotation(Dict.class);
//                    String code = annotation.code();
//                    codeList.add(code);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return codeList;
//    }
//}

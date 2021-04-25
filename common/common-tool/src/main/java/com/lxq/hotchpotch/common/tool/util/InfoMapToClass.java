package com.lxq.hotchpotch.common.tool.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/**
 * @author luxinqiang
 */
public class InfoMapToClass {

    /**
     * 传入参数实体类  dom4j element
     *
     * @param object
     * @return
     */
    public static Object getObjectParam(Object object, Map<String, Object> map) throws IllegalArgumentException, ReflectiveOperationException {
        if (null == map || map.size() == 0) {
            return object;
        }
        if (object != null) {
            Class<?> clz = object.getClass();
            Field[] fields = clz.getDeclaredFields();
            for (Field field : fields) {
                //System.out.println(field.getGenericType().toString());
                //System.out.println(field.getName());
                field.setAccessible(true);//设置可以访问私有对象
                String filedName = field.getName();
                if (null != map.get(filedName) && map.size() > 0) {
                    //System.out.println(map.get(field.getName()));
                    if ("class int".equals(field.getGenericType().toString())) {
                        field.set(object, Integer.valueOf(map.get(filedName).toString()));
                    } else if ("class java.lang.Integer".equals(field.getGenericType().toString())) {
                        field.set(object, Integer.valueOf(map.get(filedName).toString()));
                    } else if ("class java.lang.Double".equals(field.getGenericType().toString())) {
                        field.set(object, Double.valueOf(map.get(filedName).toString()));
                    } else if ("class java.math.BigDecimal".equals(field.getGenericType().toString())) {
                        field.set(object, BigDecimal.valueOf(Double.valueOf(map.get(filedName).toString())));
                    } else {
                        field.set(object, StringUtils.null2String(map.get(filedName)));
                    }
                }
            }
        }
        return object;
    }

    /**
     * 将实体转换成MAP对象
     *
     * @param object
     * @return
     */
    public static Map getMapFromObject(Object object) throws IllegalArgumentException, ReflectiveOperationException {
        Map<String, Object> map = new HashMap<String, Object>();
        if (null == object) {
            return map;
        }
        if (object != null) {
            Class<?> clz = object.getClass();
            Field[] fields = clz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);//设置可以访问私有对象
                String filedName = field.getName();

                try {
                    if (filedName.equalsIgnoreCase("serialVersionUID")) {
                        continue;
                    }
                    PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clz);
                    Method getMethod = pd.getReadMethod();
                    Object fieldVal = getMethod.invoke(object);
                    map.put(filedName.toLowerCase(), fieldVal);
                } catch (IntrospectionException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }
        return map;
    }

}

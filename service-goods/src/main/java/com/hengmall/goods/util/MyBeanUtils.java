package com.hengmall.goods.util;

import org.springframework.util.CollectionUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 对象和map转换
 */
public class MyBeanUtils {
    /**
     * bean 转成 map
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> bean2Map(Object obj) {

        if (obj == null) {
            return new HashMap<>();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    if(null == value){
                        value = "";
                    }
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }

        return map;

    }

    /**
     * bean 转成 map
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> bean2Map(Object obj, List<String> includeParam) {

        if (obj == null) {
            return new HashMap<>();
        }
        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (includeParam.contains(key)) {
                    // 过滤class属性
                    if (!key.equals("class")) {
                        // 得到property对应的getter方法
                        Method getter = property.getReadMethod();
                        Object value = getter.invoke(obj);
                        if(null == value){
                            value = "";
                        }
                        map.put(key, value);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }

        return map;
    }

    /**
     * bean 转成 map
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> bean2Map(Object obj, String[] excludeParam) {

        if (obj == null) {
            return new HashMap<>();
        }
        List<String> excludeParams = Arrays.asList(excludeParam);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (!excludeParams.contains(key)) {
                    // 过滤class属性
                    if (!key.equals("class") && !"metaClass".equals(key)) {
                        // 得到property对应的getter方法
                        Method getter = property.getReadMethod();
                        Object value = getter.invoke(obj);

                        if(null == value){
                            value = "";
                        }
                        map.put(key, value);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }

        return map;

    }

    /**
     * list 返回值优化
     *
     * @param vlist        源list
     * @param includeParam 需要包含的的字段名
     * @return
     */
    public static List<Map<String, Object>> formatResult(List vlist, List<String> includeParam) {
        if (CollectionUtils.isEmpty(vlist)) {
            return new ArrayList<>();
        }
        List<Map<String, Object>> res = new ArrayList<>(vlist.size());
        for (Object obj : vlist) {
            res.add(bean2Map(obj, includeParam));
        }
        return res;
    }
    /**
     * Object 返回值优化
     *
     * @param vlist        源list
     * @param includeParam 需要包含的的字段名
     * @return
     */
    public static Map<String, Object> formatObjResult(Object obj, List<String> includeParam) {
        if (obj == null) {
            return new HashMap<String, Object>();
        }
        Map<String, Object> map = bean2Map(obj, includeParam);
        return map;
    }
}

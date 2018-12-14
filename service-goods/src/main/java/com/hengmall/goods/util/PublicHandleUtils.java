package com.hengmall.goods.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hengmall.goods.model.SResourcesEntity;
import com.hengmall.goods.model.constitute.Coupon;

/**
 * 公共处理工具类
 */
public class PublicHandleUtils {

    public static Logger logger = LoggerFactory.getLogger(PublicHandleUtils.class);

    /**
     * 从配置文件中获取对应的cosPath
     *
     * @param cosPaths cosPath配置参数
     * @param status   状态码
     * @return
     */
    public static String getCosPath(String cosPaths, int status) {

        //默认cosPath
        String cosPath = "/client-img/";

        JSONObject jsonObjects = JSONObject.parseObject(cosPaths);
        JSONArray path = jsonObjects.getJSONArray("path");
        for (Object o : path) {
            JSONObject json = JSONObject.parseObject(o + "");
            int status_value = json.getIntValue("status");
            if (status_value == status) {
                cosPath = json.getString("cosPath");
            }
        }
        logger.info("cosPath值：{}", cosPath);

        return cosPath;
    }


    /**
     * 判断上传的文件类型
     *
     * @param suffixName 上传的文件后缀名
     * @return 1:图片,2:视频
     */
    public static int getType(String suffixName) {

        String[] imgType = SResourcesEntity.ImgType;
        for (String s : imgType) {
            if (s.equalsIgnoreCase(suffixName)) return 1;
        }
        return 2;
    }


    //获取时间戳
    public static String createTimeStamp() {
        long timeStamp = System.currentTimeMillis();
        long stamp = timeStamp / 1000;
        return stamp + "";
    }


    /**
     * 优惠券时间处理
     *
     * @param coupons 优惠券列表
     */
    public static List<Coupon> handleCouponsTime(List<Coupon> coupons) {
        for (Coupon coupon : coupons) {
            String start_time = coupon.getStart_time();
            String end_time = coupon.getEnd_time();
            start_time = start_time.substring(0, start_time.length() - 5);
            end_time = end_time.substring(0, end_time.length() - 5);
            coupon.setStart_time(start_time);
            coupon.setEnd_time(end_time);
        }
        return coupons;
    }


}

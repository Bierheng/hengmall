package com.hengmall.welfare.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.hengmall.welfare.redis.RedisService;

import io.swagger.annotations.Api;

/**
 * 钓鱼相关Controller
 *
 * @author 海乐乐
 * @date 2018年8月30日
 */
@Api(value = "钓鱼controller", tags = {"钓鱼API"})
@RestController
@RequestMapping(value = "/welfare")
public class WelfareController {

    private static Logger log = LoggerFactory.getLogger(WelfareController.class);

    @Autowired
    private RedisService redisService;

    public static void main(String[] args) {
//        String s = "{\"dialRate\":{16:5,2:10,4:20,8:10},\"vipInterval\":60,\"count\":8,\"interval\":1800,\"vipCount\":10}";
//        {5:500,10:1000,15:2000,25:3000}

        Map<String, Object> map = new HashMap<>();
        //{"interval": 1800,"count":8,"vipInterval":60,"vipCount":10,"two":20,"four":20,"eight":20,"sixteen":10}
        map.put("interval", 1800);
        map.put("count", 8);
        map.put("vipInterval", 60);
        map.put("vipCount", 10);
        Map<Integer, Integer> i = new HashMap<>();
        i.put(2, 10);
        System.out.println("");
        i.put(4, 20);
        i.put(8, 10);
        i.put(16, 5);
        map.put("dialRate", i);
        String s = JSONObject.toJSONString(map);
        System.out.println(s);
    }

}

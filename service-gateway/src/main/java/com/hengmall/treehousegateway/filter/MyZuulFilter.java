package com.hengmall.treehousegateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


//@Component
public class MyZuulFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(MyZuulFilter.class);
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        try {
            String requestBody=new MyRequestWrapper(request).getBody();
            JSONObject object= JSON.parseObject(requestBody);
            if(object.get("userId") == null) {
                log.warn("userId is empty");
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(401);
                try {
                    ctx.getResponse().getWriter().write("userId is empty");
                }catch (Exception e){

                }
            }
            log.info("ok");
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*Object accessToken = request.getParameter("userId");
        if(accessToken == null) {
            log.warn("userId is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("userId is empty");
            }catch (Exception e){

            }

            return null;
        }*/
        return null;
    }
}
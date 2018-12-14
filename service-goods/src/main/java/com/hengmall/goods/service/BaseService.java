package com.hengmall.goods.service;

import org.springframework.beans.factory.annotation.Value;

public abstract class BaseService {

    @Value("${cos.appId}")
    protected long cosAppId;
    @Value("${cos.secretId}")
    protected String cosSecretId;
    @Value("${cos.secretKey}")
    protected String cosSecretKey;
    @Value("${cos.region}")
    protected String cosRegion;

}

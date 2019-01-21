package com.hengmall.user.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hengmall.user.service.CommonService;

/**
 * @author  wuhengbin
 */
@Component
public class AutoGoodsOnJob {

    final static Logger log= LoggerFactory.getLogger(AutoGoodsOnJob.class);

    @Autowired
    private CommonService commonService;

    /**
     * 每1个小时判断是否有商品需要自动上架
     * @throws Exception 
     */
    @Scheduled(cron = "0 0 */1 * * ?")
    public void run() throws Exception {
        log.info("执行了自动上架商品定时任务");
        commonService.autoGoodsOn();
    }
}

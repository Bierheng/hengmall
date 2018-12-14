package com.hengmall.goods.constant;

/**
 * 建筑常量类
 *
 * @author 吴恒斌
 * @date 2018年10月31日
 */
public class BuildingConstant {

    /**
     * 建筑升级所需时间
     **/
    public static final Integer BUILDING_UPGRADE_DATETIME = 30 * 60;
    
    /**
     * 建筑是否产出金币：是
     **/
    public static final Integer BUILDING_OUTPUT_TRUE = 1;
    /**
     * 建筑是否产出金币：否
     **/
    public static final Integer BUILDING_OUTPUT_FALSE = 2;
    /**
     * 建筑产出上限
     **/
    public static final Integer BUILDING_OUTPUT_LIMIT = 99999;
    
    
    /**
     * 建筑开启条件：达到对应等级开启
     **/
    public static final Integer BUILDING_OPEN_GRADE = 1;
    /**
     * 建筑开启条件：结婚后开启
     **/
    public static final Integer BUILDING_OPEN_MARRY = 2;
    

    /**
     * 建筑类型：婚房
     **/
    public static final Integer BUILDING_TYPE_MARRIAGE = 1;
    /**
     * 建筑类型：教堂
     **/
    public static final Integer BUILDING_TYPE_CHURCH = 2;
    /**
     * 建筑类型：铁匠铺
     **/
    public static final Integer BUILDING_TYPE_SMITHY = 3;
    /**
     * 建筑类型：宠物店
     **/
    public static final Integer BUILDING_TYPE_PETSHOP = 4;
    /**
     * 建筑类型：酒馆
     **/
    public static final Integer BUILDING_TYPE_WINESHOP = 5;
    /**
     * 建筑类型：雕像
     **/
    public static final Integer BUILDING_TYPE_STATUE = 6;
    
}

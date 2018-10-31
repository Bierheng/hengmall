package com.hengmall.user.constant;

public class StatusConstant {

    //打工场景解锁状态 1：解锁
    public static final int WORK_TYPE_UNLOCK=1;

    //打工场景锁定状态 0：锁住
    public static final int WORK_TYPE_LOCK=0;



    //工位解锁状态 1：解锁
    public static final int WORK_POSITION_UNLOCK=1;

    //工位锁定状态 0：锁住
    public static final int WORK_POSITION_LOCK=0;

    //工位工作状态 0：空闲
    public static final int WORK_POSITION_IDLE=0;

    //工位工作状态 1：工作中
    public static final int WORK_POSITION_WORKING=1;





    //宅友状态 0:空闲中
    public static final int USER_WORKER_IDLE=0;

    //宅友状态 1：派工中
    public static final int USER_WORKER_ASSIGN=1;

    //宅友状态 2：保护中
    public static final int USER_WORKER_PROTECT=2;




    //用户状态 0: 自由身
    public static final int USER_FREE=0;

    //用户状态 1: 工作中
    public static final int USER_WORK=1;

    //用户在线状态 0：离线
    public static final int USER_OFFLINE=0;

    //用户在线状态 1：在线
    public static final int USER_ONLINE=1;

    //用户状态 11：矿洞-工作中
    public static final int WORK_ON_MINE =11;

    //用户状态 21：甜品-工作中
    public static final int WORK_ON_SWEETMEATS=21;

    //用户状态 31：花店-工作中
    public static final int WORK_ON_FLOWER=31;

    //用户状态 41：当铺-工作中
    public static final int WORK_ON_HOCKSHOP=41;

    //用户状态 51：酒楼-工作中
    public static final int WORK_ON_BOITE=51;





    //好友状态 0：已请求，待确认
    public static final int FRIEND_TO_BE_CONFIRMED=0;

    //好友状态 1：已同意，成为好友
    public static final int FRIEND_CONFIRMED_AGREE=1;



}

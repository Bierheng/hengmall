package com.hengmall.welfare.enums;




/**
 * 钓鱼场景倍率
 *
 * @author zzy
 * @date 2018年9月26日
 */
public enum WelfareMultiplyEnum {
    //1倍是默认解锁的，2、3、10倍是锁住的，25级解锁2倍，50级解锁3倍，75级解锁10倍
    ONCE(1, 1),

    TWICE(4, 2),

    TRIPLE(6, 3),

    SIXFOLD(8, 6),

    NONUPLE(10, 9);


    // 对应树屋等级
    private Integer level;
    // 对应树屋最大的倍率
    private Integer multiply;


    WelfareMultiplyEnum(Integer level, Integer multiply) {
        this.level = level;
        this.multiply = multiply;
    }

    public static Integer getValidMultiply(Integer level) {
        int multiply = 0;
        for (WelfareMultiplyEnum welfareMultiply : WelfareMultiplyEnum.values()) {
            if (welfareMultiply.getLevel() <= level) {
                if (welfareMultiply.getMultiply() > multiply) {
                    multiply = welfareMultiply.getMultiply();
                }
            }
        }
        return multiply;
    }


    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getMultiply() {
        return multiply;
    }

    public void setMultiply(Integer multiply) {
        this.multiply = multiply;
    }
}

package com.hengmall.welfare.po;

import java.io.Serializable;

public class FishingPO implements Serializable {

    private String userId;

    private int multiple;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getMultiple() {
        return multiple;
    }

    public void setMultiple(int multiple) {
        this.multiple = multiple;
    }
}

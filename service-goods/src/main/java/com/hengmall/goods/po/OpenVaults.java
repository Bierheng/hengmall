package com.hengmall.goods.po;

import java.io.Serializable;

public class OpenVaults implements Serializable {

    private String userId;

    // 力量
    private Integer  strength;

    private String friendUserId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public String getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(String friendUserId) {
        this.friendUserId = friendUserId;
    }
}

package com.hengmall.welfare.po;

import java.io.Serializable;

public class DialPO implements Serializable {

    private String userId;

    private String dropCoinsJson;

    private String configJson;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getConfigJson() {
        return configJson;
    }

    public void setConfigJson(String configJson) {
        this.configJson = configJson;
    }

    public String getDropCoinsJson() {
        return dropCoinsJson;
    }

    public void setDropCoinsJson(String dropCoinsJson) {
        this.dropCoinsJson = dropCoinsJson;
    }
}

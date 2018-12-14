package com.hengmall.goods.model.constitute;

import lombok.Data;

@Data
public class JsPayResult {

    private String resultCode;
    private String paySign;
    private String packageStr;
    private String appId;
    private String timeStamp;
    private String nonceStr;
    private String signType;

}

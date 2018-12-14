package com.hengmall.goods.model.api;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 活动模块实体类
 */
@Data
@ApiModel
public class CombineUserInfo implements Serializable {

    @ApiModelProperty(value = "用户头像")
    String headImg;
    @ApiModelProperty(value = "用户昵称")
    String nickname;
    @ApiModelProperty(value = "拼主，0：拼单者，1：发起者")
    int initiator;
}

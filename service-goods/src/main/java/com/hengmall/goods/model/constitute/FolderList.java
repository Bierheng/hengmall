package com.hengmall.goods.model.constitute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/30.
 */
@Data
@ApiModel
public class FolderList implements Serializable {

    @ApiModelProperty(value = "状态码；1：获取图片目录，2：获取视频目录")
    private int status;

}

package com.hengmall.goods.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by Administrator on 2018/5/25.
 */
@ApiModel
public class DelFile {

    @ApiModelProperty(value = "文件名称")
    String fileName;


    @Override
    public String toString() {
        return "DelFile{" +
                "fileName='" + fileName + '\'' +
                '}';
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

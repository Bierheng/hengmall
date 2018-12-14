package com.hengmall.goods.model.api;

import java.util.List;

import com.hengmall.goods.model.constitute.PayParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class WxPay {

    //1：单独购买（商品），2：参与拼单（商品），3：钱包充值，4：限时抢购,5:组合购买
    public static final int Separate_Purchase = 1; //1：单独购买（商品）
    public static final int Combine_Order = 2; //2：参与拼单（商品）
    public static final int Purse_Recharge = 3; //3：钱包充值
    public static final int Flash_Sale = 4; //4：限时抢购
    public static final int Group_Sale = 5; //5:组合购买

    @ApiModelProperty(value = "token认证")
    String token;
    @ApiModelProperty(value = "下单所需参数")
    List<PayParameter> payParameters;
    @ApiModelProperty(value = "收货地址ID")
    int addressId;
    @ApiModelProperty(value = "状态码；1：单独购买（商品），2：发起拼单（商品），3：钱包充值，4：限时抢购,5:组合购买,6：发起拼单")
    int buyStatus;
    @ApiModelProperty(value = "钱包所充值的金额数（单位：元）")
    int money;
    @ApiModelProperty(value = "限时抢购主键ID（flash_sale）")
    String flashSaleId;
    @ApiModelProperty(value = "拼单的创建人ID")
    int initiatorId;
    @ApiModelProperty(value = "店铺ID")
    int shopsId;
    @ApiModelProperty(value = "组合ID")
    int groupId;
    @ApiModelProperty(value = "购物车主键ID集合")
    List<Integer> ShoppingCartIds;
    @ApiModelProperty(value = "使用优惠券主键ID集合")
    List<Integer> couponIds;

}

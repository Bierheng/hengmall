package com.hengmall.goods.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hengmall.goods.dao.SProductDao;
import com.hengmall.goods.dao.SShoppingcartDao;
import com.hengmall.goods.dao.UsersDao;
import com.hengmall.goods.dao.WalletDetailsDao;
import com.hengmall.goods.model.SProductEntity;
import com.hengmall.goods.model.UsersEntity;
import com.hengmall.goods.util.PublicUtil;


@Service
public class UserHandleService {

    Logger logger = LoggerFactory.getLogger(UserHandleService.class);

    @Autowired
    UsersDao usersDao;
    @Autowired
    WalletDetailsDao walletDetailsDao;
    @Autowired
    SProductDao sProductDao;
    @Autowired
    SShoppingcartDao sShoppingcartDao;


    /**
     * 增加/减少 钱包总可用值及记录新增
     *
     * @param id     用户ID
     * @param money  用户钱包充值的金额（单位：分）
     * @param status 状态码；1：收入，2：支出
     */
    @Transactional
    public void changeUserMoney(int id, int money, int status) {

        //查询钱包改变前的值
        int old_val = 0;
        UsersEntity oldUsersInfo = usersDao.queryById(id);
        if (PublicUtil.isNotEmpty(oldUsersInfo)) old_val = oldUsersInfo.getMoney();

        if (status == 1) {
            //钱包充值
            usersDao.addMoney(money, id);
        } else if (status == 2) {
            //钱包扣除
            usersDao.reduceMoney(money, id);
        }

        //查询改变后的值
        int new_val = 0;
        UsersEntity newUsersInfo = usersDao.queryById(id);
        if (PublicUtil.isNotEmpty(newUsersInfo)) new_val = newUsersInfo.getMoney();

        walletDetailsDao.insertToWalletDetails(id, money, old_val, new_val, status);
    }


    /**
     * 根据 商品ID 查询 SKU 并根据SKU索引找到 用户购买的SKU值
     *
     * @param sku_index  SKU索引值
     * @param product_id 商品ID
     * @throws Exception
     */
    public JSONObject checkSKUBySKUIndex(int sku_index, int product_id) throws Exception {
        SProductEntity sProductEntity = sProductDao.queryBySKUIndex(product_id);

        if (PublicUtil.isNotEmpty(sProductEntity)) {
            String attribute = sProductEntity.getAttribute();

            //转换为数组
            JSONArray attrs = JSONObject.parseArray(attribute);
            if (PublicUtil.isNotEmpty(attrs)) {
                for (Object attr : attrs) {
                    JSONObject json = JSONObject.parseObject(attr + "");
                    int index = json.getIntValue("index");
                    if (index == sku_index) {
                        return json;
                    }
                }
            }
        } else {
            throw new Exception("无此商品");
        }
        return null;
    }


    /**
     * 指定id删除购物车列表集合
     *
     * @param ids 购物车id集合
     */
    public void delByShoppingCartId(List<Integer> ids) {
        String id = "0";
        if (PublicUtil.isNotEmpty(ids)) id = StringUtils.strip(ids.toString(), "[]");
        logger.info("需要删除的购物车id集合：{}", id);
        sShoppingcartDao.delById(id);
    }


}

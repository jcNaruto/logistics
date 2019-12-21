package com.jiangyue.service;

import com.jiangyue.entity.WareHouse;

import java.util.List;

/**
 * create by jiacheng on 2019/12/18
 */
public interface IWareHouseService {

    public void insertWareHouse(String wareHouseName);

    public String deleteWareHouse(int wareHouseId);

    /**
     * @Author zhaojiacheng
     * @Description  用户下单
     * @Date 21:41
     */
    public WareHouse userOrder(Integer wareHouseId);
    /**
     * @Author zhaojiacheng
     * @Description  仓库出库
     * @Date 21:41
     */
    public WareHouse wareHouseOut(Integer wareHouseId);
    /**
     * @Author zhaojiacheng
     * @Description  快递公司揽件
     * @Date 21:41
     */
    public WareHouse collected(Integer wareHouseId);
    /**
     * @Author zhaojiacheng
     * @Description  用户收货
     * @Date 21:41
     */
    public WareHouse userRecive(Integer wareHouseId);

    List<WareHouse> findAll();

}

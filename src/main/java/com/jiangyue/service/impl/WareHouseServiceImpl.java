package com.jiangyue.service.impl;

import com.jiangyue.dao.IWareHouseRepository;
import com.jiangyue.entity.WareHouse;
import com.jiangyue.exception.LogisticsException;
import com.jiangyue.service.IWareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * create by jiacheng on 2019/12/19
 */
@Service
@Transactional
public class WareHouseServiceImpl implements IWareHouseService {

    @Autowired
    private IWareHouseRepository wareHouseRepository;



    @Override
    @Transactional(readOnly = true)
    public List<WareHouse> findAll() {
        return wareHouseRepository.findAll();
    }

    @Override
    public void insertWareHouse(String wareHouseName) {
        WareHouse wareHouseExist = wareHouseRepository.findByWareHouseName(wareHouseName);
        if(wareHouseExist != null){
            throw new LogisticsException("仓库已存在!!");
        }
        WareHouse wareHouse = new WareHouse();
        wareHouse.setOutCount(0);
        wareHouse.setOrderCount(0);
        wareHouse.setAppropriateCount(0);
        wareHouse.setCollectCount(0);
        wareHouse.setWareHouseName(wareHouseName);
        wareHouse.setCreateTime(new Date());
        wareHouse.setUpdateTime(wareHouse.getCreateTime());

        wareHouseRepository.save(wareHouse);
    }

    @Override
    public String deleteWareHouse(int wareHouseId) {
        WareHouse wareHouse = checkWarehouse(wareHouseId);
        wareHouseRepository.deleteById(wareHouseId);
        return wareHouse.getWareHouseName();
    }

    /**
     * @Author zhaojiacheng
     * @Description  下单
     * @Date 21:55
     */
    @Override
    public WareHouse userOrder(Integer wareHouseId) {
        WareHouse wareHouse = checkWarehouse(wareHouseId);
        int currOrderCount = wareHouse.getOrderCount();
        wareHouse.setOrderCount(currOrderCount + 1);
        wareHouse.setUpdateTime(new Date());
        wareHouseRepository.save(wareHouse);
        return wareHouse;
    }

    /**
     * @Author zhaojiacheng
     * @Description  出库
     * @Date 21:59
     */
    @Override
    public WareHouse wareHouseOut(Integer wareHouseId) {
        WareHouse wareHouse = checkWarehouse(wareHouseId);
        int currOutCount = wareHouse.getOutCount();
        if(currOutCount >= wareHouse.getOrderCount()){
            throw new LogisticsException("出库量大于订单量!!");
        }
        wareHouse.setOutCount(currOutCount + 1);
        wareHouse.setUpdateTime(new Date());
        wareHouseRepository.save(wareHouse);
        return wareHouse;
    }

    /**
     * @Author zhaojiacheng
     * @Description 揽件
     * @Date 22:02
     */
    @Override
    public WareHouse collected(Integer wareHouseId) {
        WareHouse wareHouse = checkWarehouse(wareHouseId);
        int currCollecCount = wareHouse.getCollectCount();
        if(currCollecCount >= wareHouse.getOutCount()){
            throw new LogisticsException("揽件量大于出库量!!");
        }
        wareHouse.setUpdateTime(new Date());
        wareHouse.setCollectCount(currCollecCount + 1);
        wareHouseRepository.save(wareHouse);
        return wareHouse;
    }

    /**
     * @Author zhaojiacheng
     * @Description  用户接收
     * @Date 22:04
     */
    @Override
    public WareHouse userRecive(Integer wareHouseId) {
        WareHouse wareHouse = checkWarehouse(wareHouseId);
        int currAppropriateCount = wareHouse.getAppropriateCount();
        if(currAppropriateCount >= wareHouse.getCollectCount()){
            throw new LogisticsException("妥投量大于揽件量!!");
        }
        wareHouse.setAppropriateCount(currAppropriateCount + 1);
        wareHouse.setUpdateTime(new Date());
        wareHouseRepository.save(wareHouse);
        return wareHouse;
    }

    /**
     * @Author zhaojiacheng
     * @Description  check Warehouse 是否存在
     * @Date 21:47
     */
    private WareHouse checkWarehouse(Integer wareHouseId){
        WareHouse wareHouse = null;
        try {
            wareHouse = wareHouseRepository.findById(wareHouseId).get();
        } catch (NoSuchElementException e) {
            throw new LogisticsException(e, "仓库id" + wareHouseId + "不存在!");
        }
        return wareHouse;
    }
}

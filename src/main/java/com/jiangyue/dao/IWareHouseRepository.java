package com.jiangyue.dao;

import com.jiangyue.entity.WareHouse;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * create by jiacheng on 2019/12/19
 */
public interface IWareHouseRepository extends JpaRepository<WareHouse,Integer> {

    WareHouse findByWareHouseName(String wareHouseName);

}

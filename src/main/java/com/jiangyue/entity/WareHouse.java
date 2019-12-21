package com.jiangyue.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * create by jiacheng on 2019/12/19
 */
@Data
@Entity(name = "warehouse")
public class WareHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id")
    private Integer wareHouseId;
    @Column(name = "warehouse_name")
    private String wareHouseName;
    //下单量
    @Column
    private Integer orderCount;
    //出库量
    @Column
    private Integer outCount;
    //揽收量
    private Integer collectCount;
    //妥投量
    @Column
    private Integer appropriateCount;
    @Column
    private Date createTime;
    @Column
    private Date updateTime;

}

package com.jiangyue.controller;

import com.jiangyue.common.AbstractUserAuthorizationType;
import com.jiangyue.common.Result;
import com.jiangyue.common.StatusCode;
import com.jiangyue.entity.WareHouse;
import com.jiangyue.service.IUserService;
import com.jiangyue.service.IWareHouseService;
import com.jiangyue.util.TransformResultCodeUtil;
import com.jiangyue.util.UserAuthorizationUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * create by jiacheng on 2019/12/19
 */
@CrossOrigin
@RestController
@RequestMapping("/warehouse")
public class WareHouseController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IWareHouseService wareHouseService;

    @GetMapping("/")
    @ResponseBody
    public Result findAll(HttpServletRequest request){
        int statusCode = UserAuthorizationUtil.userAuthorization(request, AbstractUserAuthorizationType.COMMON_AUTHORIZATION_KEY);
        if(statusCode != StatusCode.OK){
            return TransformResultCodeUtil.trans(statusCode);
        }

        List<WareHouse> wareHouses = wareHouseService.findAll();
        return new Result(true, StatusCode.OK,"物流信息查询成功", wareHouses);
    }

    @DeleteMapping("/{warehouseId}")
    @ResponseBody
    public Result deleteWareHouse(@PathVariable int warehouseId, HttpServletRequest request){
        int statusCode = UserAuthorizationUtil.userAuthorization(request, AbstractUserAuthorizationType.ADMIN_AUTHORIZATION_KEY);
        if(statusCode != StatusCode.OK){
            return TransformResultCodeUtil.trans(statusCode);
        }

        String wareHouseName = wareHouseService.deleteWareHouse(warehouseId);
        return new Result(true, StatusCode.OK,wareHouseName+"仓库删除成功");
    }

    @PutMapping("/update/ordercount/{warehouseId}")
    @ResponseBody
    public Result userOrder(@PathVariable Integer warehouseId, HttpServletRequest request){
        int statusCode = UserAuthorizationUtil.userAuthorization(request, AbstractUserAuthorizationType.USER_AUTHORIZATION_KEY);
        if(statusCode != StatusCode.OK){
            return TransformResultCodeUtil.trans(statusCode);
        }

        if(warehouseId == null){
            return new Result(false, StatusCode.ERROR, "warehouseId为空");
        }
        WareHouse wareHouse = wareHouseService.userOrder(warehouseId);
        return new Result(true, StatusCode.OK, wareHouse.getWareHouseName() + "仓库被下单", wareHouse);
    }

    @PostMapping("/{wareHouseName}")
    @ResponseBody
    public Result userOrder(@PathVariable String wareHouseName, HttpServletRequest request){
        int statusCode = UserAuthorizationUtil.userAuthorization(request, AbstractUserAuthorizationType.ADMIN_AUTHORIZATION_KEY);
        if(statusCode != StatusCode.OK){
            return TransformResultCodeUtil.trans(statusCode);
        }
        if(StringUtils.isBlank(wareHouseName)){
            return new Result(false, StatusCode.ERROR, "仓库名称为空");
        }
        wareHouseService.insertWareHouse(wareHouseName);
        return new Result(true, StatusCode.OK, wareHouseName + "仓库新增成功");
    }

    @PutMapping("/update/warehousecount/{warehouseId}")
    @ResponseBody
    public Result wareHouseOut(@PathVariable Integer warehouseId, HttpServletRequest request){
        int statusCode = UserAuthorizationUtil.userAuthorization(request, AbstractUserAuthorizationType.USER_AUTHORIZATION_KEY);
        if(statusCode != StatusCode.OK){
            return TransformResultCodeUtil.trans(statusCode);
        }

        if(warehouseId == null){
            return new Result(false, StatusCode.ERROR, "warehouseId为空");
        }
        WareHouse wareHouse = wareHouseService.wareHouseOut(warehouseId);
        return new Result(true, StatusCode.OK, wareHouse.getWareHouseName() + "仓库出库成功", wareHouse);
    }

    @PutMapping("/update/collectcount/{warehouseId}")
    @ResponseBody
    public Result collectCount(@PathVariable Integer warehouseId, HttpServletRequest request){
        int statusCode = UserAuthorizationUtil.userAuthorization(request, AbstractUserAuthorizationType.USER_AUTHORIZATION_KEY);
        if(statusCode != StatusCode.OK){
            return TransformResultCodeUtil.trans(statusCode);
        }

        if(warehouseId == null){
            return new Result(false, StatusCode.ERROR, "warehouseId为空");
        }
        WareHouse wareHouse = wareHouseService.collected(warehouseId);
        return new Result(true, StatusCode.OK, "揽件成功", wareHouse);
    }

    @PutMapping("/update/usercount/{warehouseId}")
    @ResponseBody
    public Result userRecieve(@PathVariable Integer warehouseId, HttpServletRequest request){
        int statusCode = UserAuthorizationUtil.userAuthorization(request, AbstractUserAuthorizationType.USER_AUTHORIZATION_KEY);
        if(statusCode != StatusCode.OK){
            return TransformResultCodeUtil.trans(statusCode);
        }

        if(warehouseId == null){
            return new Result(false, StatusCode.ERROR, "warehouseId为空");
        }
        WareHouse wareHouse = wareHouseService.userRecive(warehouseId);
        return new Result(true, StatusCode.OK, "用户收货成功",wareHouse);
    }

}

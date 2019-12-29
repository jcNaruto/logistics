package com.jiangyue.util;

import com.jiangyue.common.Result;

/**
 * create by jiacheng on 2019/12/29
 */
public class TransformResultCodeUtil {

    /**
     * @Author zhaojiacheng
     * @Description 传入code转换为对应标识的Result
     * @Date 13:13
     */
    public static Result trans(int statusCode){
        Result result = new Result(false, statusCode );
        if(20002 == statusCode){
            result.setMessage("未登陆");
        } else if(20003 == statusCode){
            result.setMessage("无权限操作");
        } else if(20004 == statusCode){
            result.setMessage("通知管理员赋权");
        }
        return result;
    }
}

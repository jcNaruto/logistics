package com.jiangyue.common;

import lombok.Data;

/**
 * create by jiacheng on 2019/12/19
 */
@Data
public class Result {

    private Boolean flag;//是否成功
    private String message;//返回信息
    private Object data;// 返回数据
    private Integer code;

    public Result(Boolean flag, Integer code) {
        this.flag = flag;
        this.code = code;
    }

    public Result(Boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result(Boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}

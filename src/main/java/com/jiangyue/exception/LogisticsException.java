package com.jiangyue.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * create by jiacheng on 2019/12/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogisticsException extends RuntimeException {
    private String message;

    public LogisticsException(Exception e,String message){
        super(e);
        this.message = message;
    }

}

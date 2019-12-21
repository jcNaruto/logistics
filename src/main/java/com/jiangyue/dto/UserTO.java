package com.jiangyue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * create by jiacheng on 2019/12/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTO {
    private String token;
    private String userName;
}

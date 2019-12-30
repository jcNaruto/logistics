package com.jiangyue.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column
    private String userName;
    @Column
    private String password;
    @Column
    private Integer role;
    @Column
    private Date createTime;
    @Column
    private Date updateTime;
    @Column
    private String comment;



}

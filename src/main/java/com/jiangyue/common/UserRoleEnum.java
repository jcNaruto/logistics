package com.jiangyue.common;

public enum UserRoleEnum {

    INIT_USER_ROLE(0,"init_user"),
    /*
     *角色为普通用户，例如学生等
     */
    USER_ROLE(2,"user"),
    /*
     *角色为管理员，允许创建教师角色，系统交付时以初始化完毕
     */
    ADMIN_ROLE(1,"admin");

    private int seq;
    private String desc;

    UserRoleEnum(int seq, String desc){
        this.seq = seq;
        this.desc = desc;
    }

    public int getSeq(){
        return seq;
    }
    public String getDesc(){
        return desc;
    }

    public static String getValue(int code) {
        for (UserRoleEnum ele : UserRoleEnum.values()) {
            if(ele.getSeq() == code) {
                return ele.getDesc();
            }
        }
        return null;
    }

}

package com.andx.micro.management.dto;

/**
 * 权限操作类型
 * 文件具有读和写权限
 * 服务具有执行、提交、审核权限
 * Created by andongxu on 16-8-25.
 */
public enum Operate {

    READ,                   //读
    WRITE,                  //写
    EXE,                    //执行
    AUDIT;                  //审核

}

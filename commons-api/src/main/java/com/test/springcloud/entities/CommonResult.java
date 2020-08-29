package com.test.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用返回结果集
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String messeage;
    private T data;

    public CommonResult(int code,String messeage){
        this.code=code;
        this.messeage=messeage;
    }
}

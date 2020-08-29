package com.test.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor          //使用lombok自动生成构造函数和get,set
public class Payment implements Serializable {
    private long id;
    private String serial;
}

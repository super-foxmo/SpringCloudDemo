package com.foxmo.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    //状态码
    private Integer code;
    //状态描述信息
    private String message;
    //返回数据
    private T data;

    public CommonResult(Integer code,String message){
        this.code = code;
        this.message = message;
        this.data = null;
    }
}

package com.rookie.mall.demo.config;

import lombok.Data;

/**
 * @author: chen
 * @date: 2021/12/31
 * @description: 响应码
 **/

public enum CodeEnum {

    SUCCESS("00000000", "交易成功"),
    ERROR("99999999", "系统错误");


    private String code;
    private String message;

    CodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

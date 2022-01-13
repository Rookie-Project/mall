package com.rookie.mall.demo.config;

import lombok.Data;
import org.checkerframework.checker.units.qual.C;

import java.io.Serializable;

/**
 * @author: chen
 * @date: 2021/12/31
 * @description: TODO 类描述
 **/
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 4581616856727450268L;

    /*
     * 错误码：交易成功（00000000）、交易失败（99999999）
     */
    private String code;

    /*
     * 交易信息
     */
    private String message;

    /*
     * 响应数据
     */
    private T data;

    public Result() {

    }

    public Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMessage(), data);
    }

    public static Result error() {
        return new Result<>(CodeEnum.ERROR.getCode(), CodeEnum.ERROR.getMessage(), null);
    }

    public static <T> Result<T> code(String code, String message) {
        return Result.code(code,message,null);
    }
    public static <T> Result<T> code(String code, String message,T data) {
        return new Result<T>(code, message, data);
    }

    public static <T> Result<T> error(T data) {
        return new Result<T>(CodeEnum.ERROR.getCode(), CodeEnum.ERROR.getMessage(), data);
    }
}

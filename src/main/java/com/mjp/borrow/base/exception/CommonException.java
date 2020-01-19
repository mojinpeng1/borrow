package com.mjp.borrow.base.exception;


import lombok.Data;

/**
 * <p>Description：自定义异常</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/17 14:12
 */
@Data
public class CommonException extends RuntimeException{

    private int errorCode;


    private String message;

    public CommonException(String s) {
        this.message = s;
    }
}

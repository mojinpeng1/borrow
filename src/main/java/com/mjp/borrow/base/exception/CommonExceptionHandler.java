package com.mjp.borrow.base.exception;

import com.mjp.borrow.base.ResultInfo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>Description：全局定义异常处理</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/17 14:13
 */
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(value = CommonException.class)
    public ResultInfo err(CommonException ex) {
        return ResultInfo.error(ex.getMessage());
    }

}

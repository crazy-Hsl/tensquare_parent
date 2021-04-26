package com.tensquare.user.controller;

import entity.ResultObject;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author crazy
 * @create 2021-04-24 11:50
 * 统一处理异常
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultObject error(Exception e) {
        e.printStackTrace();
        return new ResultObject(true, StatusCode.OK, e.getMessage());
    }
}

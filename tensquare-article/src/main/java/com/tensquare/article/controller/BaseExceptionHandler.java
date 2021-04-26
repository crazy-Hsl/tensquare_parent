package com.tensquare.article.controller;

import entity.ResultObject;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author crazy
 * @create 2021-04-22 17:12
 * 统一异常处理类
 */
@ControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultObject error(Exception e){
        e.printStackTrace();
        return new ResultObject(false, StatusCode.ERROR,"执行出错\t"+e.getMessage());
    }
}

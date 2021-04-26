package com.tensquare.qa.client;

import entity.ResultObject;
import entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 * @author crazy
 * @create 2021-04-23 10:25
 */
@Component
public class BaseClientImpl implements BaseClient{
    @Override
    public ResultObject findById(String labelId) {
        return new ResultObject(false, StatusCode.ERROR,"熔断器触发了");
    }
}

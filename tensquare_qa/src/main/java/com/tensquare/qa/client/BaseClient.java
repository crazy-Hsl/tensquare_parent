package com.tensquare.qa.client;

import entity.ResultObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



/**
 * @author crazy
 * @create 2021-04-23 10:21
 */

@Component
@FeignClient(value = "tensquare-base",fallback = BaseClientImpl.class)
public interface BaseClient  {
    @RequestMapping(value = "/label/{labelId}",method = RequestMethod.GET)
    ResultObject findById(@PathVariable("labelId")String labelId);
}

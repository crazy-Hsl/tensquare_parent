package com.tensquare.recruit.controller;

import com.tensquare.recruit.pojo.Enterprise;
import com.tensquare.recruit.pojo.Recruit;
import com.tensquare.recruit.service.EnterpriseService;
import entity.PageResult;
import entity.ResultObject;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author crazy
 * @create 2021-04-22 23:19
 */
@RestController
@CrossOrigin
@RequestMapping("/enterprise")
public class EnterpriseController {
    @Autowired
    EnterpriseService enterpriseService;
    @RequestMapping(value = "/search/hotlist",method = RequestMethod.GET)
    public  ResultObject hostList(){
        List<Enterprise> enterprises = enterpriseService.hotList("1");
        return new ResultObject(true,StatusCode.OK,"查询成功",enterprises);
    }
    //查询全部列表
    @RequestMapping(method = RequestMethod.GET)
    public ResultObject findAll(){
        return  new ResultObject(true, StatusCode.OK,"查询成功",enterpriseService.findAll());
    }

    //根据ID查询
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public  ResultObject findById(@PathVariable String id){
        return  new ResultObject(true,StatusCode.OK,"查询成功",enterpriseService.findById(id));
    }

    //增加
    @RequestMapping(method = RequestMethod.POST)
    public  ResultObject add(@RequestBody Enterprise enterprise ){
        enterpriseService.add(enterprise);
        return  new ResultObject(true,StatusCode.OK,"增加成功");
    }

    //修改
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public  ResultObject update(@RequestBody  Enterprise enterprise,@PathVariable String id){
        enterprise.setId(id);
        enterpriseService.update(enterprise);
        return new ResultObject(true,StatusCode.OK,"修改成功");

    }

    //删除
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResultObject deleteById(@PathVariable String id){
        enterpriseService.deleteById(id);
        return  new ResultObject(true,StatusCode.OK,"删除成功");
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public ResultObject  findSearch(@RequestBody Map searchMap){
        return new ResultObject(true,StatusCode.OK,"查询成功",enterpriseService.findSearch(searchMap));
    }


    //分页+条件查询
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public  ResultObject findSearch(@RequestBody Map searchMap,@PathVariable int page,@PathVariable int size){
        Page<Enterprise> search = enterpriseService.findSearch(searchMap, page, size);
        return new ResultObject(true,StatusCode.OK,"查询成功",new PageResult<>(search.getTotalElements(),search.getContent()));
    }
}

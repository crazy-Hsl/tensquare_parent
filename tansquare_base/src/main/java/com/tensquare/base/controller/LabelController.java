package com.tensquare.base.controller;


import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.ResultObject;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author crazy
 * @create 2021-04-22 16:58
 * 标签控制层
 */
@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {
    @Autowired
    LabelService labelService;

    //查询全部列表
    @RequestMapping(method = RequestMethod.GET)
    public ResultObject findAll(){
        return  new ResultObject(true, StatusCode.OK,"查询成功",labelService.findAll());
    }

    //根据ID查询标签
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public  ResultObject findById(@PathVariable String id){
        System.out.println("No.1");
        return  new ResultObject(true,StatusCode.OK,"查询成功",labelService.findById(id));
    }

    //增加标签
    @RequestMapping(method = RequestMethod.POST)
    public  ResultObject add(@RequestBody Label label){
        labelService.add(label);
        return  new ResultObject(true,StatusCode.OK,"增加成功");
    }

    //修改标签
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public  ResultObject update(@RequestBody Label label,@PathVariable String id){
        label.setId(id);
        labelService.update(label);
        return new ResultObject(true,StatusCode.OK,"修改成功");

    }

    //删除标签
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public  ResultObject deleteById(@PathVariable String id){
        labelService.deleteById(id);
        return  new ResultObject(true,StatusCode.OK,"删除成功");
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public ResultObject  findSearch(@RequestBody Map searchMap){
        return new ResultObject(true,StatusCode.OK,"查询成功",labelService.findSearch(searchMap));
    }


    //分页+条件查询
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public  ResultObject findSearch(@RequestBody Map searchMap,@PathVariable int page,@PathVariable int size){
        Page<Label> search = labelService.findSearch(searchMap, page, size);
        return new ResultObject(true,StatusCode.OK,"查询成功",new PageResult<>(search.getTotalElements(),search.getContent()));
    }

}

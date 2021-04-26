package com.tensquare.recruit.controller;



import com.tensquare.recruit.pojo.Recruit;
import com.tensquare.recruit.service.RecruitService;
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
 * 控制层
 */
@RestController
@CrossOrigin
@RequestMapping("/recruit")
public class RecruitController {
    @Autowired
    RecruitService recruitService;

    public RecruitService getRecruitService(){
        return  recruitService;
    }


    @RequestMapping(value = "/search/recommend",method = RequestMethod.GET)
    public  ResultObject recommend(){
        return  new ResultObject(true,StatusCode.OK,"查询成功",recruitService.recommend());
    }
    @RequestMapping(value = "/search/newlist",method = RequestMethod.GET)
    public  ResultObject newList(){
        return  new ResultObject(true,StatusCode.OK,"查询成功",recruitService.newList());
    }
    //查询全部列表
    @RequestMapping(method = RequestMethod.GET)
    public ResultObject findAll(){
        return  new ResultObject(true, StatusCode.OK,"查询成功",recruitService.findAll());
    }

    //根据ID查询
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public  ResultObject findById(@PathVariable String id){
        return  new ResultObject(true,StatusCode.OK,"查询成功",recruitService.findById(id));
    }

    //增加
    @RequestMapping(method = RequestMethod.POST)
    public  ResultObject add(@RequestBody Recruit recruit ){
        recruitService.add(recruit);
        return  new ResultObject(true,StatusCode.OK,"增加成功");
    }

    //修改
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public  ResultObject update(@RequestBody  Recruit recruit,@PathVariable String id){
        recruit.setId(id);
        recruitService.update(recruit);
        return new ResultObject(true,StatusCode.OK,"修改成功");

    }

    //删除
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public  ResultObject deleteById(@PathVariable String id){
        recruitService.deleteById(id);
        return  new ResultObject(true,StatusCode.OK,"删除成功");
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public ResultObject  findSearch(@RequestBody Map searchMap){
        return new ResultObject(true,StatusCode.OK,"查询成功",recruitService.findSearch(searchMap));
    }


    //分页+条件查询
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public  ResultObject findSearch(@RequestBody Map searchMap,@PathVariable int page,@PathVariable int size){
        Page< Recruit> search = recruitService.findSearch(searchMap, page, size);
        return new ResultObject(true,StatusCode.OK,"查询成功",new PageResult<>(search.getTotalElements(),search.getContent()));
    }

}

package com.tensquare.qa.controller;

import com.tensquare.qa.pojo.Reply;
import com.tensquare.qa.service.ReplyService;

import entity.PageResult;
import entity.ResultObject;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

/**
 * @author crazy
 * @create 2021-04-22 23:19
 */
@RestController
@CrossOrigin
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    ReplyService replyService;
//    @RequestMapping(value = "/search/hotlist",method = RequestMethod.GET)
//    public  ResultObject hostList(){
//        List<Reply> Replys = replyService.hotList("1");
//        return new ResultObject(true,StatusCode.OK,"查询成功",Replys);
//    }
    //查询全部列表
    @RequestMapping(method = RequestMethod.GET)
    public ResultObject findAll(){
        return  new ResultObject(true, StatusCode.OK,"查询成功",replyService.findAll());
    }

    //根据ID查询
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public  ResultObject findById(@PathVariable String id){
        return  new ResultObject(true,StatusCode.OK,"查询成功",replyService.findById(id));
    }

    //增加
    @RequestMapping(method = RequestMethod.POST)
    public  ResultObject add(@RequestBody Reply reply ){
        replyService.add(reply);
        return  new ResultObject(true,StatusCode.OK,"增加成功");
    }

    //修改
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public  ResultObject update(@RequestBody  Reply reply,@PathVariable String id){
        reply.setId(id);
        replyService.update(reply);
        return new ResultObject(true,StatusCode.OK,"修改成功");

    }

    //删除
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResultObject deleteById(@PathVariable String id){
        replyService.deleteById(id);
        return  new ResultObject(true,StatusCode.OK,"删除成功");
    }

    //根据条件查询
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public ResultObject  findSearch(@RequestBody Map searchMap){
        return new ResultObject(true,StatusCode.OK,"查询成功",replyService.findSearch(searchMap));
    }


    //分页+条件查询
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public  ResultObject findSearch(@RequestBody Map searchMap,@PathVariable int page,@PathVariable int size){
        Page<Reply> search = replyService.findSearch(searchMap, page, size);
        return new ResultObject(true,StatusCode.OK,"查询成功",new PageResult<>(search.getTotalElements(),search.getContent()));
    }
}

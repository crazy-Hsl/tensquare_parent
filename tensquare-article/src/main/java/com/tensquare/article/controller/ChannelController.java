package com.tensquare.article.controller;



import com.tensquare.article.pojo.Channel;
import com.tensquare.article.service.ChannelService;

import entity.PageResult;
import entity.ResultObject;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author crazy
 * @create 2021-04-23 16:58
 * 控制层
 */
@RestController
@CrossOrigin
@RequestMapping("/channel")
public class ChannelController {
    @Autowired
    ChannelService channelService;

    @Autowired
    HttpServletRequest request;

    //查询全部数据
    @RequestMapping(method = RequestMethod.GET)
    public ResultObject findAll(){
        return  new ResultObject(true,StatusCode.OK,"查询成功",channelService.findAll());

    }
    //根据id查询
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResultObject findById(@PathVariable String id){
        Channel channel = channelService.findById(id);
        return  new ResultObject(true,StatusCode.OK,"查询成功",channel);
    }
    //分页+查询
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public ResultObject findSearch(@RequestBody Map searchMap,@PathVariable int page,@PathVariable int size){
        Page<Channel> search = channelService.search(searchMap, page, size);
        return  new ResultObject(true,StatusCode.OK,"查询成功",new PageResult<Channel>(search.getTotalElements(),search.getContent()));
    }

    //条件查询
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public  ResultObject findSearch(@RequestBody Map searchMap){
        return  new ResultObject(true,StatusCode.OK,"查询成功",channelService.search(searchMap));
    }

    //增加
    @RequestMapping(method = RequestMethod.POST)
    public ResultObject add(@RequestBody Channel channel){
        channelService.add(channel);
        return new ResultObject(true,StatusCode.OK,"添加成功");
    }
    //修改
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public  ResultObject update(@RequestBody Channel channel,@PathVariable String id){
        channel.setId(id);
        channelService.update(channel);
        return new ResultObject(true,StatusCode.OK,"修改成功");
    }
    //删除

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public  ResultObject delete(@PathVariable String id){
        channelService.deleteById(id);
        return  new ResultObject(true,StatusCode.OK,"删除成功");
    }




}

package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.ResultObject;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.net.www.protocol.http.HttpURLConnection;

/**
 * @author crazy
 * @create 2021-04-23 20:13
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    SpitService spitService;

    @Autowired
    RedisTemplate redisTemplate;


    //查询全部数据
    @RequestMapping(method = RequestMethod.GET)
    public ResultObject findAll() {
        return new ResultObject(true, StatusCode.OK, "查询成功", spitService.findAll());

    }

    //根据Id查询
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResultObject findOne(@PathVariable String id) {
        return new ResultObject(true, StatusCode.OK, "查询成功", spitService.findById(id));
    }

    //增加
    @RequestMapping(method = RequestMethod.POST)
    public ResultObject add(@RequestBody Spit spit) {
        spitService.add(spit);
        return new ResultObject(true, StatusCode.OK, "添加成功");
    }

    //修改
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResultObject update(@RequestBody Spit spit, @PathVariable String id) {
        spit.set_id(id);
        spitService.update(spit);
        return new ResultObject(true, StatusCode.OK, "修改成功");
    }

    //删除
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResultObject delete(@PathVariable String id) {
        spitService.delete(id);
        return new ResultObject(true, StatusCode.OK, "删除成功");
    }

    //根据上级ID吐槽分页数据
    @RequestMapping(value = "/comment/{parentId}/{page}/{size}", method = RequestMethod.GET)
    public ResultObject findByParent(@PathVariable String parentId, @PathVariable int page, @PathVariable int size) {
        Page<Spit> byParentId = spitService.findByParentId(parentId, page, size);
        return new ResultObject(true, StatusCode.OK, "查询成功", new PageResult<Spit>(byParentId.getTotalElements(), byParentId.getContent()));
    }

    //点赞增加
    @RequestMapping(value = "/thumbup/{id}", method = RequestMethod.PUT)
    public ResultObject updateThumbup(@PathVariable String id) {

        String userid = "2023";
        if (redisTemplate.opsForValue().get("thumbup_" + userid + "_" + id) != null) {
            return new ResultObject(false, StatusCode.REPERROR, "你已经点过赞了");
        }
        spitService.updateThumbup(id);
        redisTemplate.opsForValue().set("thumbup_" + userid + "_" + id, "1");
        return new ResultObject(true, StatusCode.OK, "点赞成功");
    }

}

package com.tensquare.gathering.controller;

import com.tensquare.gathering.pojo.Gathering;
import com.tensquare.gathering.service.GatheringService;
import entity.PageResult;
import entity.ResultObject;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author crazy
 * @create 2021-04-23 17:01
 */
@RestController
@RequestMapping("/gathering")
@CrossOrigin
public class GatheringController {
    @Autowired
    GatheringService gatheringService;

    //查询所有数据
    @RequestMapping(method = RequestMethod.GET)
    public ResultObject findAll() {
        return new ResultObject(true, StatusCode.OK, "查询成功", gatheringService.findAll());
    }

    //根据id查询
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResultObject findById(@PathVariable String id) {
        return new ResultObject(true, StatusCode.OK, "查询成功", gatheringService.findById(id));
    }

    //增加
    @RequestMapping(method = RequestMethod.POST)
    public ResultObject add(@RequestBody Gathering gathering) {
        gatheringService.add(gathering);
        return new ResultObject(true, StatusCode.OK, "添加成功");
    }

    //修改
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResultObject update(@RequestBody Gathering gathering, @PathVariable String id) {
        gathering.setId(id);
        gatheringService.update(gathering);
        return new ResultObject(true, StatusCode.OK, "修改成功");
    }

    //删除
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResultObject deleteById(@PathVariable String id) {
        gatheringService.deleteById(id);
        return new ResultObject(true, StatusCode.OK, "删除成功");
    }

    //条件查询
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResultObject search(@RequestBody Map searchMap) {
        return new ResultObject(true, StatusCode.OK, "查询成功", gatheringService.search(searchMap));
    }

    //分页+条件查询
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public ResultObject search(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Gathering> search = gatheringService.search(searchMap, page, size);
        return new ResultObject(true, StatusCode.OK, "查询成功", new PageResult<Gathering>(search.getTotalElements(), search.getContent()));
    }

}

package com.tensquare.qa.controller;


import com.tensquare.qa.client.BaseClient;
import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.ProblemService;

import entity.PageResult;
import entity.ResultObject;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author crazy
 * @create 2021-04-22 16:58
 * 控制层
 */
@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {
    @Autowired
    ProblemService problemService;

    @Autowired
    HttpServletRequest request;
    @Autowired
    BaseClient baseClient;

    //
    @RequestMapping(value = "/label/{labelId}", method = RequestMethod.GET)
    public ResultObject findByLabelId(@PathVariable("labelId") String labelId) {
        ResultObject result = baseClient.findById(labelId);
        return result;
    }

    @RequestMapping(value = "/newlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
    public ResultObject newList(@PathVariable String labelid, @PathVariable int page, @PathVariable int size) {
        Page<Problem> newlist = problemService.newlist(labelid, page, size);
        return new ResultObject(true, StatusCode.OK, "查询成功", new PageResult<Problem>(newlist.getTotalElements(), newlist.getContent()));
    }

    @RequestMapping(value = "/newlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
    public ResultObject hotList(@PathVariable String labelid, @PathVariable int page, @PathVariable int size) {
        Page<Problem> hotList = problemService.hotlist(labelid, page, size);
        return new ResultObject(true, StatusCode.OK, "查询成功", new PageResult<Problem>(hotList.getTotalElements(), hotList.getContent()));
    }

    @RequestMapping(value = "/newlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
    public ResultObject waitList(@PathVariable String labelid, @PathVariable int page, @PathVariable int size) {
        Page<Problem> waitList = problemService.waitlist(labelid, page, size);
        return new ResultObject(true, StatusCode.OK, "查询成功", new PageResult<Problem>(waitList.getTotalElements(), waitList.getContent()));
    }


    //查询全部列表
    @RequestMapping(method = RequestMethod.GET)
    public ResultObject findAll() {
        return new ResultObject(true, StatusCode.OK, "查询成功", problemService.findAll());
    }

    //根据ID查询
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResultObject findById(@PathVariable String id) {
        return new ResultObject(true, StatusCode.OK, "查询成功", problemService.findById(id));
    }

    //增加
    @RequestMapping(method = RequestMethod.POST)
    public ResultObject add(@RequestBody Problem problem) {
        Claims claims_user = (Claims) request.getAttribute("claims_user");

        if (claims_user == null || !"".equals(claims_user)) {
            return new ResultObject(false, StatusCode.ACCESSERROR, "权限不足");
        }
        problem.setUserid(claims_user.getId());
        problemService.add(problem);
        return new ResultObject(true, StatusCode.OK, "增加成功");
    }

    //修改
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResultObject update(@RequestBody Problem problem, @PathVariable String id) {
        problem.setId(id);
        problemService.update(problem);
        return new ResultObject(true, StatusCode.OK, "修改成功");

    }

    //删除
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResultObject deleteById(@PathVariable String id) {
        problemService.deleteById(id);
        return new ResultObject(true, StatusCode.OK, "删除成功");
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResultObject findSearch(@RequestBody Map searchMap) {
        return new ResultObject(true, StatusCode.OK, "查询成功", problemService.findSearch(searchMap));
    }


    //分页+条件查询
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public ResultObject findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Problem> search = problemService.findSearch(searchMap, page, size);
        return new ResultObject(true, StatusCode.OK, "查询成功", new PageResult<>(search.getTotalElements(), search.getContent()));
    }

}

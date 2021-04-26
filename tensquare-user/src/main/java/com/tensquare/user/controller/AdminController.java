package com.tensquare.user.controller;


import com.tensquare.user.pojo.Admin;
import com.tensquare.user.service.AdminService;
import entity.PageResult;
import entity.ResultObject;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author crazy
 * @create 2021-04-25 9:31
 */
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    JwtUtil jwtUtil;

    //验证
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultObject login(@RequestBody Admin admin) {
        Admin login = adminService.login(admin);
        if (login == null) {
            //登录失败
            return new ResultObject(false, StatusCode.lOGINERROR, "登录失败");
        }
        //使得前后端可以通话的操作，采用JWT来实现
        //生成令牌
        String token = jwtUtil.createJWT(login.getId(), login.getLoginname(), "admin");
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("role", "admin");
        return new ResultObject(true, StatusCode.OK, "登录成功", map);
    }

    //查询全部数据
    @RequestMapping(method = RequestMethod.GET)
    public ResultObject findAll() {
        return new ResultObject(true, StatusCode.OK, "查询成功", adminService.findAll());
    }

    //根基id查询
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResultObject findById(@PathVariable String id) {
        return new ResultObject(true, StatusCode.OK, "查询成功", adminService.findById(id));
    }

    //分页+条件
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public ResultObject findAll(@RequestBody Map<String,String> searchMap, @PathVariable int page, @PathVariable int size) {

        Page<Admin> search = adminService.findSearch(searchMap, page, size);

        return new ResultObject(true, StatusCode.OK, "查询成功", new PageResult<Admin>(search.getTotalElements(), search.getContent()));
    }

    //根据条件查询
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResultObject findAll(@RequestBody Map<String,String> searchMap) {
        return new ResultObject(true, StatusCode.OK, "查询成功", adminService.findSearch(searchMap));
    }

    //增加
    @RequestMapping(method = RequestMethod.POST)
    public ResultObject add(@RequestBody Admin admin) {
        adminService.add(admin);
        return new ResultObject(true, StatusCode.OK, "添加成功");
    }

    //修改
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResultObject update(@RequestBody Admin admin, @PathVariable String id) {
        admin.setId(id);
        adminService.update(admin);
        return new ResultObject(true, StatusCode.OK, "修改成功");

    }

    //删除
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResultObject delete(@PathVariable String id) {
        adminService.delete(id);
        return new ResultObject(true, StatusCode.OK, "删除成功");
    }


}

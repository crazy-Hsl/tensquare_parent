package com.tensquare.user.controller;

import ch.qos.logback.classic.turbo.TurboFilter;
import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;
import entity.PageResult;
import entity.ResultObject;
import entity.StatusCode;
import org.bouncycastle.crypto.params.CramerShoupPublicKeyParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;

/**
 * @author crazy
 * @create 2021-04-24 14:48
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    JwtUtil jwtUtil;


    //更新好有粉丝数和用户数
    @RequestMapping(value = "/{userid}/{friendid}/{x}",method = RequestMethod.PUT)
    public ResultObject updatefanscountandfollowcount(@PathVariable String userid,@PathVariable String friendid,@PathVariable int x){
        userService.updatefanscountfollowcount(x,userid,friendid);
        return  new ResultObject(true, StatusCode.OK,"更新成功");
    }

    //登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public  ResultObject login(@RequestBody User user){
        User login = userService.login(user.getMobile(), user.getPassword());
        if(login==null){
            return  new ResultObject(false,StatusCode.lOGINERROR,"登录失败");
        }

        String token = jwtUtil.createJWT(user.getId(), user.getMobile(), "user");

        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("roles","user");
        map.put("name",login.getNickname());//昵称
        map.put("avatar",user.getAvatar());//头像
        return  new ResultObject(true,StatusCode.OK,"登录成功",map);
    }

    //发送验证码
    @RequestMapping(value = "/sendsms/{mobile}",method = RequestMethod.POST)
    public  ResultObject sendSms(@PathVariable String mobile){
        userService.sendSms(mobile);
        return  new ResultObject(true,StatusCode.OK,"发送成功");
    }
    //注册
    @RequestMapping(value = "/register/{code}",method = RequestMethod.POST)
    public  ResultObject regist(@PathVariable String code,@RequestBody User user){
        //得到缓存的验证码
        String checkcodeRedis = (String)redisTemplate.opsForValue().get("checkcode_" + user.getMobile());
        if(!checkcodeRedis.isEmpty()){
            return  new ResultObject(false,StatusCode.OK,"请先获取手机验证码");
        }
        if(checkcodeRedis.equals(code)){
            return  new ResultObject(false,StatusCode.OK,"请输入正确的验证码");
        }
        userService.add(user);
        return  new ResultObject(true,StatusCode.OK,"注册成功");
    }

    //查询全部数据
    @RequestMapping(method = RequestMethod.GET)
    public  ResultObject findAll(){
        return  new ResultObject(true,StatusCode.OK,"查询成功",userService.findAll());
    }

    //根据id查询
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public  ResultObject findById(@PathVariable String id){
        return  new ResultObject(true,StatusCode.OK,"查询成功",userService.findById(id));
    }


    //分页+条件查询
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public  ResultObject findSearch(@RequestBody Map searchMap,@PathVariable int page,@PathVariable int size){

        Page<User> userPage = userService.findsearch(searchMap, page, size);
        return  new ResultObject(true,StatusCode.OK,"查询成功",new PageResult<User>(userPage.getTotalElements(),userPage.getContent()));
    }

    //根据条件查询
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public  ResultObject findSearch(@RequestBody Map searchMap){
        return  new ResultObject(true,StatusCode.OK,"查询成功",userService.findsearch(searchMap));
    }

    //增加
    @RequestMapping(method = RequestMethod.POST)
    public  ResultObject add(@RequestBody User user){
        userService.add(user);
        return  new ResultObject( true,StatusCode.OK,"添加成功");
    }
    //修改

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public  ResultObject update(@RequestBody User user,@PathVariable String id){
        user.setId(id);
        userService.update(user);
        return  new ResultObject( true,StatusCode.OK,"修改成功");
    }
    //删除  必须有admin角色才能删除
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public  ResultObject deleteById(@PathVariable String id){
        userService.delete(id);
        return  new ResultObject( true,StatusCode.OK,"删除成功");
    }
}

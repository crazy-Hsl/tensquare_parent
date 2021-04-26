package com.tensquare.friend.controller;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.service.FriendService;
import entity.ResultObject;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author crazy
 * @create 2021-04-25 17:23
 */
@CrossOrigin
@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    FriendService friendService;

    @Autowired
    HttpServletRequest request;
    @Autowired
    UserClient userClient;


    //添加好友
    @RequestMapping(value = "/like/{friendid}/{type}", method = RequestMethod.PUT)
    public ResultObject add(@PathVariable String friendid, @PathVariable String type) {
        //验证是否登录，并且拿到当前登录的用户id
        Claims claims = (Claims) request.getAttribute("user_claims");
        if (claims == null) {
            //说明当前用户没有user角色
            return new ResultObject(false, StatusCode.ACCESSERROR, "无权访问");
        }
        //如果是喜欢
        if (type!=null&&type.equals("1")) {
            if (friendService.addFriend(claims.getId(), friendid) == 0) {
                return new ResultObject(false, StatusCode.REPERROR, "已经添加改好友");
            }
        }else {
            //添加非好友
            int i = friendService.addNoFriend(claims.getId(), friendid);//向不喜欢列表中添加记录

            if(i==0){
                return new ResultObject(true,StatusCode.OK,"重复添加好友");
            }else if(i==1){
                return new ResultObject(true,StatusCode.OK,"添加成功");
            }

        }
        return new ResultObject(true, StatusCode.OK, "操作成功");
    }
    @RequestMapping(value = "/{friendid}",method = RequestMethod.DELETE)
    public  ResultObject remove(@PathVariable String friendid){
        //验证是否登录，并且拿到当前登录的用户id
        Claims claims = (Claims)request.getAttribute("user_claims");
        if(claims==null){
            //说明当前用户没有user角色
            return  new ResultObject(false,StatusCode.ACCESSERROR,"无权访问");
        }
        //得到当前登录的用户id
        friendService.deleteFriend(claims.getId(),friendid);
        return  new ResultObject(true,StatusCode.OK,"删除成功");

    }


}

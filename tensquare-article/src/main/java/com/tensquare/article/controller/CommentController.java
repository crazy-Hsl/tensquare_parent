package com.tensquare.article.controller;

import com.tensquare.article.pojo.Comment;
import com.tensquare.article.service.CommentService;
import entity.ResultObject;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Action;

/**
 * @author crazy
 * @create 2021-04-23 21:06
 */
@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping(method = RequestMethod.POST)
    public ResultObject save(@RequestBody Comment comment) {
        commentService.add(comment);
        return new ResultObject(true, StatusCode.OK, "提交成功");
    }

    @RequestMapping(value = "/article/{articleid}", method = RequestMethod.GET)
    public ResultObject findByArticleid(@PathVariable String articleid) {
        return new ResultObject(true, StatusCode.OK, "查询成功", commentService.findByArticleid(articleid));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public  ResultObject delete(@PathVariable String id){
        commentService.deleteById(id);
        return  new ResultObject(true,StatusCode.OK,"删除成功");
    }
}

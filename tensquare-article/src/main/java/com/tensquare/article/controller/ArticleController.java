package com.tensquare.article.controller;

import com.tensquare.article.pojo.Article;
import com.tensquare.article.service.ArticleService;
import entity.PageResult;
import entity.ResultObject;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author crazy
 * @create 2021-04-23 16:25
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value="/examine/{articleId}",method=RequestMethod.PUT)
    public ResultObject examine(@PathVariable String articleId){
        articleService.updateState(articleId);
        return new ResultObject(true,StatusCode.OK,"审核成功");
    }

    @RequestMapping(value="/thumbup/{articleId}",method=RequestMethod.PUT)
    public ResultObject thumbup(@PathVariable String articleId){
        articleService.addThumbup(articleId);
        return new ResultObject(true,StatusCode.OK,"点赞成功");

    }


    /**
     * 查询全部数据
     * @return
     */
    @RequestMapping(method= RequestMethod.GET)
    public ResultObject findAll(){
        return new ResultObject(true,StatusCode.OK,"查询成功",articleService.findAll());
    }

    /**
     * 根据ID查询
     * @param id ID
     * @return
     */
    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    public ResultObject findById(@PathVariable String id){
        return new ResultObject(true,StatusCode.OK,"查询成功",articleService.findById(id));
    }


    /**
     * 分页+多条件查询
     * @param searchMap 查询条件封装
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
    public ResultObject findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
        Page<Article> pageList = articleService.findSearch(searchMap, page, size);
        return  new ResultObject(true,StatusCode.OK,"查询成功",  new PageResult<Article>(pageList.getTotalElements(), pageList.getContent()) );
    }

    /**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public ResultObject findSearch( @RequestBody Map searchMap){
        return new ResultObject(true,StatusCode.OK,"查询成功",articleService.findSearch(searchMap));
    }

    /**
     * 增加
     * @param article
     */
    @RequestMapping(method=RequestMethod.POST)
    public ResultObject add(@RequestBody Article article  ){
        articleService.add(article);
        return new ResultObject(true,StatusCode.OK,"增加成功");
    }

    /**
     * 修改
     * @param article
     */
    @RequestMapping(value="/{id}",method= RequestMethod.PUT)
    public ResultObject update(@RequestBody Article article, @PathVariable String id ){
        article.setId(id);
        articleService.update(article);
        return new ResultObject(true, StatusCode.OK,"修改成功");
    }

    /**
     * 删除
     * @param id
     */
    @RequestMapping(value="/{id}",method= RequestMethod.DELETE)
    public ResultObject delete(@PathVariable String id ){
        articleService.delete(id);
        return new ResultObject(true,StatusCode.OK,"删除成功");
    }


}

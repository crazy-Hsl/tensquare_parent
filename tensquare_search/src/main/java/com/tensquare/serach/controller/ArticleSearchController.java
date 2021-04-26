package com.tensquare.serach.controller;

import com.tensquare.serach.pojo.Article;
import com.tensquare.serach.service.ArticleSearchService;
import entity.PageResult;
import entity.ResultObject;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author crazy
 * @create 2021-04-23 22:54
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleSearchController {


    @Autowired
    ArticleSearchService articleSearchService;


    @RequestMapping(method = RequestMethod.POST)
    public ResultObject save(@RequestBody Article article) {
        articleSearchService.add(article);
        return new ResultObject(true, StatusCode.OK, "操作成功");
    }

    @RequestMapping(value = "/search/{keywords}/{page}/{size}", method = RequestMethod.GET)
    public ResultObject findByTitleLike(@PathVariable String keywords, @PathVariable int page, @PathVariable int size) {
        Page<Article> byTitleLike = articleSearchService.findByTitleLike(keywords, page, size);
        return new ResultObject(true, StatusCode.OK, "查詢成功", new PageResult<Article>(byTitleLike.getTotalElements(), byTitleLike.getContent()));
    }
}

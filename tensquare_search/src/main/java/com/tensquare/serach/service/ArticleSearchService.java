package com.tensquare.serach.service;

import com.tensquare.serach.dao.ArticleSearchDao;
import com.tensquare.serach.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author crazy
 * @create 2021-04-23 22:52
 */
@Service
public class ArticleSearchService {
    @Autowired
    ArticleSearchDao articleSearchDao;


    //增加文章
    public void add(Article article) {
        articleSearchDao.save(article);
    }

    //搜索文章
    public Page<Article> findByTitleLike(String keywords, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return articleSearchDao.findByTitleOrContentLike(keywords, keywords, pageRequest);
    }
}

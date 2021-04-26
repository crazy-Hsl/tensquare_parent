package com.tensquare.serach.dao;

import com.tensquare.serach.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author crazy
 * @create 2021-04-23 22:51
 */
public interface ArticleSearchDao extends ElasticsearchRepository<Article,String> {

    Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);



}

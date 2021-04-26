package com.tensquare.article.dao;

import com.tensquare.article.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import sun.awt.SunHints;

/**
 * @author crazy
 * @create 2021-04-23 14:06
 */
public interface ArticleDao extends JpaRepository<Article,String>, JpaSpecificationExecutor<Article> {

    @Modifying
    @Query(value = "update tb_article set state=1 where id=?",nativeQuery = true)
    public void  updateState(String id);

    @Modifying
    @Query(value = "update tb_article set thumbup = thumbup+1 where id=?",nativeQuery = true)
    void  addThumbup(String id);

}

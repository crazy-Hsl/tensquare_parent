package com.tensquare.article.service;

import com.tensquare.article.dao.ArticleDao;
import com.tensquare.article.pojo.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author crazy
 * @create 2021-04-23 14:14
 */

@Service
@Transactional
public class ArticleService {

    @Autowired
    ArticleDao articleDao;

    @Autowired
    IdWorker idWorker;

    @Autowired
    RedisTemplate redisTemplate;

    //
    public void updateState(String id){
        articleDao.updateState(id);
    }
    //
    public void  addThumbup(String id){
        articleDao.addThumbup(id);
    }

    //添加
    public void add(Article article) {
        article.setId(idWorker.nextId() + "");
        articleDao.save(article);
    }


    //查询所有
    public List<Article> findAll() {
        return articleDao.findAll();
    }


    //修改
    public void update(Article article) {
        redisTemplate.delete("article_" + article.getId());
        articleDao.save(article);
    }


    //删除
    public void delete(String id) {
        redisTemplate.delete("article_" + id);
        articleDao.deleteById(id);
    }


    //查询实体
    public Article findById(String id) {
        //先从缓存中查询当前对象
        Article article = (Article) redisTemplate.opsForValue().get("article_" + id);
        //若是没有取到
        if (article == null) {
            //从数据库中取
            article = articleDao.findById(id).get();
            //存入緩存中
           // redisTemplate.opsForValue().set("article_" + id, article);
            redisTemplate.opsForValue().set("article_" + id, article, 10, TimeUnit.SECONDS);
        }
        return article;
    }

    //条件查询
    public List<Article> findSearch(Map searchMap) {
        Specification<Article> specification = createSpecification(searchMap);
        return articleDao.findAll(specification);
    }

    //条件+分页
    public Page<Article> findSearch(Map searchMap, int page, int size) {
        Specification<Article> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return articleDao.findAll(specification, pageRequest);
    }

    //查詢条件拼裝
    private Specification<Article> createSpecification(Map searchMap) {
        return new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                //Id
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(criteriaBuilder.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                //专栏
                if (searchMap.get("columnid") != null && !"".equals(searchMap.get("columnid"))) {
                    predicateList.add(criteriaBuilder.like(root.get("columnid").as(String.class), "%" + (String) searchMap.get("columnid") + "%"));
                }
                //用户ID
                if (searchMap.get("userid") != null && !"".equals(searchMap.get("userid"))) {
                    predicateList.add(criteriaBuilder.like(root.get("userid").as(String.class), "%" + searchMap.get("userid") + "%"));
                }
                //标题
                if (searchMap.get("title") != null && !"".equals(searchMap.get("title"))) {
                    predicateList.add(criteriaBuilder.like(root.get("title").as(String.class), "%" + searchMap.get("title") + "%"));
                }
                //文章正文
                if (searchMap.get("content") != null && !"".equals(searchMap.get("content"))) {
                    predicateList.add(criteriaBuilder.like(root.get("content").as(String.class), "%" + searchMap.get("content") + "%"));
                }
                //文章封面
                if (searchMap.get("image") != null && !"".equals(searchMap.get("image"))) {
                    predicateList.add(criteriaBuilder.like(root.get("image").as(String.class), "%" + searchMap.get("image") + "%"));
                }
                //是否公开
                if (searchMap.get("ispublic") != null && !"".equals(searchMap.get("ispublic"))) {
                    predicateList.add(criteriaBuilder.like(root.get("ispublic").as(String.class), "%" + searchMap.get("ispublic") + "%"));
                }
                //是否置顶
                if (searchMap.get("istop") != null && !"".equals(searchMap.get("istop"))) {
                    predicateList.add(criteriaBuilder.like(root.get("istop").as(String.class), "%" + searchMap.get("istop") + "%"));
                }
                //审核状态
                if (searchMap.get("state") != null && !"".equals(searchMap.get("state"))) {
                    predicateList.add(criteriaBuilder.like(root.get("state").as(String.class), "%" + searchMap.get("state") + "%"));
                }
                //所属频道
                if (searchMap.get("channelid") != null && !"".equals(searchMap.get("channelid"))) {
                    predicateList.add(criteriaBuilder.like(root.get("channelid").as(String.class), "%" + searchMap.get("channelid") + "%"));
                }
                //URL
                if (searchMap.get("url") != null && !"".equals(searchMap.get("url"))) {
                    predicateList.add(criteriaBuilder.like(root.get("url").as(String.class), "%" + searchMap.get("url") + "%"));
                }
                //类型
                if (searchMap.get("type") != null && !"".equals(searchMap.get("type"))) {
                    predicateList.add(criteriaBuilder.like(root.get("type").as(String.class), "%" + searchMap.get("tyep") + "%"));
                }

                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }


}

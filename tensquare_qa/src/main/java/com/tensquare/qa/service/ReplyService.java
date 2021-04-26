package com.tensquare.qa.service;


import com.tensquare.qa.dao.ReplyDao;

import com.tensquare.qa.pojo.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author crazy
 * @create 2021-04-22 16:49
 */
@Service
public class ReplyService {
    @Autowired
    ReplyDao replyDao;
    @Autowired
    IdWorker idWorker;


    /**
     * 查询全部标签
     *
     * @return
     */
    public List<Reply> findAll() {
        return replyDao.findAll();
    }

    //增加
    public void add(Reply reply) {
        reply.setId(idWorker.nextId() + "");//设置ID
        replyDao.save(reply);
    }

    //根据ID查询
    public Reply findById(String id) {
        return replyDao.findById(id).get();
    }

    //修改
    public void update(Reply reply) {
        replyDao.save(reply);
    }

    //删除
    public void deleteById(String id) {
        replyDao.deleteById(id);
    }

    //构建查询条件
    private Specification<Reply> createSpecification(Map searchMap) {
        return new Specification<Reply>() {
            @Override
            public Predicate toPredicate(Root<Reply> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                //存放一个集合  用于存储所有的条件
                List<Predicate> predicateList = new ArrayList<>();

                //ID
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    //根据标签的名称，模糊查询
                    predicateList.add(criteriaBuilder.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));

                }
                if (searchMap.get("problemid") != null && !"".equals(searchMap.get("problemid"))) {
                    //等值查询标签的状态
                    predicateList.add(criteriaBuilder.equal(root.get("problemid").as(String.class), "%" + (String) searchMap.get("problemid") + "%"));
                }
                if (searchMap.get("content") != null && !"".equals(searchMap.get("content"))) {
                    predicateList.add(criteriaBuilder.equal(root.get("content").as(String.class), "%" + (String) searchMap.get("content") + "%"));
                }
//                if (searchMap.get("createtime") != null && !"".equals(searchMap.get("createtime"))) {
//                    //根据标签的名称，模糊查询
//                    predicateList.add(criteriaBuilder.like(root.get("createtime").as(String.class), "%" + (String) searchMap.get("createtime") + "%"));
//
//                }
//                if (searchMap.get("updatetime") != null && !"".equals(searchMap.get("updatetime"))) {
//                    //等值查询标签的状态
//                    predicateList.add(criteriaBuilder.equal(root.get("updatetime").as(String.class), "%" + (String) searchMap.get("updatetime") + "%"));
//                }
                if (searchMap.get("userid") != null && !"".equals(searchMap.get("userid"))) {
                    predicateList.add(criteriaBuilder.equal(root.get("userid").as(String.class), "%" + (String) searchMap.get("userid") + "%"));
                }
                if (searchMap.get("nickname") != null && !"".equals(searchMap.get("nickname"))) {
                    //根据标签的名称，模糊查询
                    predicateList.add(criteriaBuilder.like(root.get("nickname").as(String.class), "%" + (String) searchMap.get("nickname") + "%"));

                }

                //创建一个数组，作为最终的返回值条件，把list转为数组   返回所有条件
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };
    }

    //条件查询
    public List<Reply> findSearch(Map searchMap) {
        Specification<Reply> specification = createSpecification(searchMap);
        return replyDao.findAll(specification);
    }

    //分页条件查询
    public Page<Reply> findSearch(Map searchMap, int page, int size) {
        Specification<Reply> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return replyDao.findAll(specification, pageRequest);
    }
}

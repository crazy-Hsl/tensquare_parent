package com.tensquare.qa.service;

import com.tensquare.qa.dao.ProblemDao;

import com.tensquare.qa.pojo.Problem;
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
 * @create 2021-04-23 9:55
 */
@Service
public class ProblemService {
    @Autowired
    ProblemDao problemDao;
    @Autowired
    IdWorker idWorker;


    /**
     * 查询全部标签
     * @return
     */
    public List<Problem> findAll() {
        return problemDao.findAll();
    }

    //增加标签
    public void add(Problem problem) {
        problem.setId(idWorker.nextId() + "");//设置ID
        problemDao.save(problem);
    }

    //根据ID查询
    public Problem findById(String id) {
        return problemDao.findById(id).get();
    }

    //修改
    public void update(Problem problem) {
        problemDao.save(problem);
    }

    //删除
    public void deleteById(String id) {
        problemDao.deleteById(id);
    }

    //构建查询条件
    private Specification<Problem> createSpecification(Map searchMap) {
        return new Specification<Problem>() {
            @Override
            public Predicate toPredicate(Root<Problem> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                //存放一个集合  用于存储所有的条件
                List<Predicate> predicateList = new ArrayList<>();

                //ID
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {

                    predicateList.add(criteriaBuilder.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));

                }
                if (searchMap.get("title") != null && !"".equals(searchMap.get("title"))) {

                    predicateList.add(criteriaBuilder.equal(root.get("title").as(String.class), "%" + (String) searchMap.get("title") + "%"));
                }
                if (searchMap.get("content") != null && !"".equals(searchMap.get("content"))) {
                    predicateList.add(criteriaBuilder.equal(root.get("content").as(String.class), "%" + (String) searchMap.get("content") + "%"));
                }
//                if (searchMap.get("createtime") != null && !"".equals(searchMap.get("createtime"))) {
//
//                    predicateList.add(criteriaBuilder.like(root.get("createtime").as(String.class), "%" + (String) searchMap.get("createtime") + "%"));
//
//                }
//                if (searchMap.get("updatetime") != null && !"".equals(searchMap.get("updatetime"))) {
//
//                    predicateList.add(criteriaBuilder.equal(root.get("updatetime").as(String.class), "%" + (String) searchMap.get("updatetime") + "%"));
//                }
                if (searchMap.get("nickname") != null && !"".equals(searchMap.get("nickname"))) {
                    predicateList.add(criteriaBuilder.equal(root.get("nickname").as(String.class), "%" + (String) searchMap.get("nickname") + "%"));
                }
//                if (searchMap.get("visits") != null && !"".equals(searchMap.get("visits"))) {
//
//                    predicateList.add(criteriaBuilder.like(root.get("visits").as(String.class), "%" + (String) searchMap.get("visits") + "%"));
//
//                }
//                if (searchMap.get("thumbup") != null && !"".equals(searchMap.get("thumbup"))) {
//
//                    predicateList.add(criteriaBuilder.equal(root.get("thumbup").as(String.class), "%" + (String) searchMap.get("thumbup") + "%"));
//                }
//                if (searchMap.get("reply") != null && !"".equals(searchMap.get("reply"))) {
//                    predicateList.add(criteriaBuilder.equal(root.get("reply").as(String.class), "%" + (String) searchMap.get("reply") + "%"));
//                }
                if (searchMap.get("solve") != null && !"".equals(searchMap.get("solve"))) {

                    predicateList.add(criteriaBuilder.like(root.get("solve").as(String.class), "%" + (String) searchMap.get("solve") + "%"));

                }
                if (searchMap.get("replyname") != null && !"".equals(searchMap.get("replyname"))) {

                    predicateList.add(criteriaBuilder.like(root.get("replyname").as(String.class), "%" + (String) searchMap.get("replyname") + "%"));

                }
//                if (searchMap.get("replytime") != null && !"".equals(searchMap.get("replytime"))) {
//
//                    predicateList.add(criteriaBuilder.like(root.get("replytime").as(String.class), "%" + (String) searchMap.get("replytime") + "%"));
//
//                }
                //创建一个数组，作为最终的返回值条件，把list转为数组   返回所有条件
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };
    }

    //条件查询
    public List<Problem> findSearch(Map searchMap) {
        Specification<Problem> specification = createSpecification(searchMap);
        return problemDao.findAll(specification);
    }

    //分页条件查询
    public Page<Problem> findSearch(Map searchMap, int page, int size) {
        Specification<Problem> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return problemDao.findAll(specification, pageRequest);
    }


    //根据标签查询问题列表
    public  Page<Problem> newlist(String labelid,int page,int size){
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return problemDao.newlist(labelid,pageRequest);
    }
    //根据热门问题列表
    public  Page<Problem> hotlist(String labelid,int page,int size){
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return problemDao.hotlist(labelid,pageRequest);
    }

    //根据等待直达问题
    public  Page<Problem> waitlist(String labelid,int page,int size){
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return problemDao.waitlist(labelid,pageRequest);
    }

}

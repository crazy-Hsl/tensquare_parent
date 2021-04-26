package com.tensquare.recruit.service;


import com.tensquare.recruit.dao.RecruitDao;
import com.tensquare.recruit.pojo.Recruit;
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
public class RecruitService {
    @Autowired
    RecruitDao recruitDao;
    @Autowired
    IdWorker idWorker;


    public List<Recruit> recommend(){
        return recruitDao.findTop4ByStateOrderByCreateTimeDesc("2");

    }
    //最新职位列表
    public List<Recruit> newList(){
        return recruitDao.findTop12ByStateNotOrderByCreateTimeDesc("0");
    }

    /**
     * 查询全部标签
     * @return
     */
    public List<Recruit> findAll(){
        return  recruitDao.findAll();
    }

    //增加标签
    public  void add(Recruit label){
        label.setId(idWorker.nextId()+"");//设置ID
        recruitDao.save(label);
    }

    //根据ID查询标签
    public Recruit findById(String id){
        return recruitDao.findById(id).get();
    }

    //修改标签
    public  void  update(Recruit label){
        recruitDao.save(label);
    }

    //删除标签
    public  void deleteById(String id){
        recruitDao.deleteById(id);
    }

    //构建查询条件
    private Specification<Recruit> createSpecification(Map searchMap){
        return  new Specification<Recruit>() {
            @Override
            public Predicate toPredicate(Root<Recruit> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                //存放一个集合  用于存储所有的条件
                List<Predicate> predicateList = new ArrayList<>();

                //ID
                if(searchMap.get("id")!=null && !"".equals(searchMap.get("id"))){
                    //根据标签的名称，模糊查询
                    predicateList.add(criteriaBuilder.like(root.get("id").as(String.class),"%" + (String)searchMap.get("id")+"%"));

                }
                if(searchMap.get("jobname")!=null&&!"".equals(searchMap.get("jobname"))){
                   //等值查询标签的状态
                    predicateList.add(criteriaBuilder.equal(root.get("jobname").as(String.class),"%" + (String)searchMap.get("jobname")+"%"));
                }
                if(searchMap.get("salary")!=null&&!"".equals(searchMap.get("salary"))){
                    predicateList.add(criteriaBuilder.equal(root.get("salary").as(String.class),"%" + (String)searchMap.get("salary") +"%"));
                }
                if(searchMap.get("condition")!=null && !"".equals(searchMap.get("condition"))){
                    //根据标签的名称，模糊查询
                    predicateList.add(criteriaBuilder.like(root.get("condition").as(String.class),"%" + (String)searchMap.get("condition")+"%"));

                }
                if(searchMap.get("education")!=null&&!"".equals(searchMap.get("education"))){
                    //等值查询标签的状态
                    predicateList.add(criteriaBuilder.equal(root.get("education").as(String.class),"%" + (String)searchMap.get("education")+"%"));
                }
                if(searchMap.get("type")!=null&&!"".equals(searchMap.get("type"))){
                    predicateList.add(criteriaBuilder.equal(root.get("type").as(String.class),"%" + (String)searchMap.get("type") +"%"));
                }
                if(searchMap.get("address")!=null && !"".equals(searchMap.get("address"))){
                    //根据标签的名称，模糊查询
                    predicateList.add(criteriaBuilder.like(root.get("address").as(String.class),"%" + (String)searchMap.get("address")+"%"));

                }
                if(searchMap.get("eid")!=null&&!"".equals(searchMap.get("eid"))){
                    //等值查询标签的状态
                    predicateList.add(criteriaBuilder.equal(root.get("eid").as(String.class),"%" + (String)searchMap.get("eid")+"%"));
                }
                if(searchMap.get("createtime")!=null&&!"".equals(searchMap.get("createtime"))){
                    predicateList.add(criteriaBuilder.equal(root.get("createtime").as(String.class),"%" + (String)searchMap.get("createtime") +"%"));
                }
                if(searchMap.get("state")!=null && !"".equals(searchMap.get("state"))){
                    //根据标签的名称，模糊查询
                    predicateList.add(criteriaBuilder.like(root.get("state").as(String.class),"%" + (String)searchMap.get("state")+"%"));

                }
                if(searchMap.get("url")!=null&&!"".equals(searchMap.get("url"))){
                    //等值查询标签的状态
                    predicateList.add(criteriaBuilder.equal(root.get("url").as(String.class),"%" + (String)searchMap.get("url")+"%"));
                }
                if(searchMap.get("label")!=null&&!"".equals(searchMap.get("label"))){
                    predicateList.add(criteriaBuilder.equal(root.get("label").as(String.class),"%" + (String)searchMap.get("label") +"%"));
                }
                if(searchMap.get("content1")!=null&&!"".equals(searchMap.get("content1"))){
                    //等值查询标签的状态
                    predicateList.add(criteriaBuilder.equal(root.get("content1").as(String.class),"%" + (String)searchMap.get("content1")+"%"));
                }
                if(searchMap.get("content2")!=null&&!"".equals(searchMap.get("content2"))){
                    predicateList.add(criteriaBuilder.equal(root.get("content2").as(String.class),"%" + (String)searchMap.get("content2") +"%"));
                }
                //创建一个数组，作为最终的返回值条件，把list转为数组   返回所有条件
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };
    }

    //条件查询
    public  List<Recruit> findSearch(Map searchMap){
        Specification<Recruit> specification = createSpecification(searchMap);
        return recruitDao.findAll(specification);
    }

    //分页条件查询
    public Page<Recruit> findSearch(Map searchMap, int page , int size){
        Specification<Recruit> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return recruitDao.findAll(specification,pageRequest);
    }
}

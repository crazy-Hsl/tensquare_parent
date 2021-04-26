package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.EnterpriseDao;
import com.tensquare.recruit.pojo.Enterprise;
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
 * @create 2021-04-22 22:58
 */
@Service
public class EnterpriseService {
    @Autowired
    EnterpriseDao enterpriseDao;
    @Autowired
    IdWorker idWorker;


    /**
     * 查询全部标签
     *
     * @return
     */
    public List<Enterprise> findAll() {
        return enterpriseDao.findAll();
    }

    //增加标签
    public void add(Enterprise enterprise) {
        enterprise.setId(idWorker.nextId() + "");//设置ID
        enterpriseDao.save(enterprise);
    }

    //根据ID查询标签
    public Enterprise findById(String id) {
        return enterpriseDao.findById(id).get();
    }

    //修改标签
    public void update(Enterprise enterprise) {
        enterpriseDao.save(enterprise);
    }

    //删除标签
    public void deleteById(String id) {
        enterpriseDao.deleteById(id);
    }

    //构建查询条件
    private Specification<Enterprise> createSpecification(Map searchMap) {
        return new Specification<Enterprise>() {
            @Override
            public Predicate toPredicate(Root<Enterprise> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                //存放一个集合  用于存储所有的条件
                List<Predicate> predicateList = new ArrayList<>();

                //ID
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {

                    predicateList.add(criteriaBuilder.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));

                }
                if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {

                    predicateList.add(criteriaBuilder.equal(root.get("name").as(String.class), "%" + (String) searchMap.get("name") + "%"));
                }
                if (searchMap.get("summery") != null && !"".equals(searchMap.get("summery"))) {
                    predicateList.add(criteriaBuilder.equal(root.get("summery").as(String.class), "%" + (String) searchMap.get("summery") + "%"));
                }
                if (searchMap.get("address") != null && !"".equals(searchMap.get("address"))) {
                    predicateList.add(criteriaBuilder.like(root.get("address").as(String.class), "%" + (String) searchMap.get("address") + "%"));
                }
                if (searchMap.get("labels") != null && !"".equals(searchMap.get("labels"))) {

                    predicateList.add(criteriaBuilder.equal(root.get("labels").as(String.class), "%" + (String) searchMap.get("labels") + "%"));
                }
                if (searchMap.get("coordinate") != null && !"".equals(searchMap.get("coordinate"))) {
                    predicateList.add(criteriaBuilder.equal(root.get("coordinate").as(String.class), "%" + (String) searchMap.get("coordinate") + "%"));
                }
                if (searchMap.get("ishot") != null && !"".equals(searchMap.get("ishot"))) {
                    predicateList.add(criteriaBuilder.like(root.get("ishot").as(String.class), "%" + (String) searchMap.get("ishot") + "%"));
                }
                if (searchMap.get("logo") != null && !"".equals(searchMap.get("logo"))) {

                    predicateList.add(criteriaBuilder.equal(root.get("logo").as(String.class), "%" + (String) searchMap.get("logo") + "%"));
                }
                if (searchMap.get("jobcount") != null && !"".equals(searchMap.get("jobcount"))) {
                    predicateList.add(criteriaBuilder.equal(root.get("jobcount").as(String.class), "%" + (String) searchMap.get("jobcount") + "%"));
                }
                if (searchMap.get("url") != null && !"".equals(searchMap.get("url"))) {
                    predicateList.add(criteriaBuilder.like(root.get("url").as(String.class), "%" + (String) searchMap.get("url") + "%"));
                }
                //创建一个数组，作为最终的返回值条件，把list转为数组   返回所有条件
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };
    }

    //条件查询
    public List<Enterprise> findSearch(Map searchMap) {
        Specification<Enterprise> specification = createSpecification(searchMap);
        return enterpriseDao.findAll(specification);
    }

    //分页条件查询
    public Page<Enterprise> findSearch(Map searchMap, int page, int size) {
        Specification<Enterprise> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return enterpriseDao.findAll(specification, pageRequest);
    }

    //热门企业列表
    public  List<Enterprise> hotList(String id){
        return enterpriseDao.findByIshot(id);
    }
}

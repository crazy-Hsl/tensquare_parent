package com.tensquare.gathering.service;

import com.tensquare.gathering.dao.GatheringDao;
import com.tensquare.gathering.pojo.Gathering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
 * @create 2021-04-23 16:59
 */
@Service
public class GatheringService {
    @Autowired
    GatheringDao gatheringDao;
    @Autowired
    IdWorker idWorker;

    //查询所有
    public List<Gathering> findAll() {
        return gatheringDao.findAll();
    }

    //增加
    public void add(Gathering gathering) {
        gathering.setId(idWorker.nextId() + "");
        gatheringDao.save(gathering);
    }

    //修改
    @CacheEvict(value = "gathering",key = "#gathering.id")
    public void update(Gathering gathering) {
        gatheringDao.save(gathering);
    }

    //删除
    @CacheEvict(value = "gathering",key = "#id")
    public void deleteById(String id) {
        gatheringDao.deleteById(id);
    }

    //根据id查询
    @Cacheable(value = "gathering",key = "#id")
    public Gathering findById(String id) {
        return gatheringDao.findById(id).get();
    }

    //条件查询
    public List<Gathering> search(Map searchMap) {
        Specification<Gathering> specification = createSpecification(searchMap);
        return gatheringDao.findAll(specification);
    }

    //分页+条件查询
    public Page<Gathering> search(Map searchMap, int page, int size) {
        Specification<Gathering> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return gatheringDao.findAll(specification, pageRequest);
    }

    //条件封装
    public Specification<Gathering> createSpecification(Map searchMap) {
        return new Specification<Gathering>() {
            @Override
            public Predicate toPredicate(Root<Gathering> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();

                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicates.add(criteriaBuilder.like(root.get("id").as(String.class), "%" + searchMap.get("id") + "%"));
                }
                if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                    predicates.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + searchMap.get("name") + "%"));
                }
                if (searchMap.get("summery") != null && !"".equals(searchMap.get("summery"))) {
                    predicates.add(criteriaBuilder.like(root.get("summery").as(String.class), "%" + searchMap.get("summery") + "%"));
                }
                if (searchMap.get("detail") != null && !"".equals(searchMap.get("detail"))) {
                    predicates.add(criteriaBuilder.like(root.get("detail").as(String.class), "%" + searchMap.get("detail") + "%"));
                }
                if (searchMap.get("sponsor") != null && !"".equals(searchMap.get("sponsor"))) {
                    predicates.add(criteriaBuilder.like(root.get("sponsor").as(String.class), "%" + searchMap.get("sponsor") + "%"));
                }
                if (searchMap.get("image") != null && !"".equals(searchMap.get("image"))) {
                    predicates.add(criteriaBuilder.like(root.get("image").as(String.class), "%" + searchMap.get("image") + "%"));
                }
                if (searchMap.get("address") != null && !"".equals(searchMap.get("address"))) {
                    predicates.add(criteriaBuilder.like(root.get("address").as(String.class), "%" + searchMap.get("address") + "%"));
                }
                if (searchMap.get("state") != null && !"".equals(searchMap.get("state"))) {
                    predicates.add(criteriaBuilder.like(root.get("state").as(String.class), "%" + searchMap.get("state") + "%"));
                }
                if (searchMap.get("city") != null && !"".equals(searchMap.get("city"))) {
                    predicates.add(criteriaBuilder.like(root.get("city").as(String.class), "%" + searchMap.get("city") + "%"));
                }
                return criteriaBuilder.and(new Predicate[predicates.size()]);
            }
        };

    }


}

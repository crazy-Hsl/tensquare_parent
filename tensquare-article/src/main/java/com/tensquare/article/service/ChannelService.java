package com.tensquare.article.service;

import com.tensquare.article.dao.ChannelDao;
import com.tensquare.article.pojo.Channel;
import org.hibernate.type.descriptor.java.BasicJavaDescriptor;
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
import java.util.Optional;

/**
 * @author crazy
 * @create 2021-04-23 15:38
 */
@Service
public class ChannelService {
    @Autowired
    ChannelDao channelDao;

    @Autowired
    IdWorker idWorker;

    //查询所有
    public List<Channel> findAll(){
        return channelDao.findAll();
    }
    //添加
    public void add(Channel channel){
        channel.setId(idWorker.nextId()+"");
        channelDao.save(channel);
    }
    //修改
    public  void update(Channel channel){
        channelDao.save(channel);
    }
    //删除
    public  void  deleteById(String id){
        channelDao.deleteById(id);
    }
    //根据id查询
    public  Channel findById(String id){
       return channelDao.findById(id).get();

    }

    //根据条件查询
    public List<Channel> search(Map searchMap){
        Specification<Channel> specification = createSpecification(searchMap);
        return channelDao.findAll(specification);
    }

    //分页+ 条件查询
    public Page<Channel> search(Map searchMap,int page ,int size){
        Specification<Channel> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return channelDao.findAll(specification,pageRequest);
    }
    //条件查询装配
    public Specification<Channel> createSpecification(Map searchMap){
        return new Specification<Channel>() {
            @Override
            public Predicate toPredicate(Root<Channel> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();
                if(searchMap.get("id")!=null&&!"".equals(searchMap.get("id"))){
                    predicates.add(criteriaBuilder.like(root.get("id").as(String.class),"%"+ searchMap.get("id")+"%"));
                }
                if(searchMap.get("name")!=null&&!"".equals(searchMap.get("name"))){
                    predicates.add(criteriaBuilder.like(root.get("name").as(String.class),"%"+ searchMap.get("name")+"%"));

                }
                if(searchMap.get("state")!=null&&!"".equals(searchMap.get("state"))){
                    predicates.add(criteriaBuilder.like(root.get("state").as(String.class),"%"+searchMap.get("state")+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));


            }
        };
    }
}

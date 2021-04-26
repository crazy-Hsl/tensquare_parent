package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * @author crazy
 * @create 2021-04-23 20:02
 */
@Service
public class SpitService {

    @Autowired
    SpitDao spitDao;
    @Autowired
    IdWorker idWorker;

    @Autowired
    MongoTemplate mongoTemplate;

    //查询全部
    public List<Spit> findAll() {
        return spitDao.findAll();
    }

    //根据主键查询实体
    public Spit findById(String id) {
        Spit spit = spitDao.findById(id).get();
        return spit;
    }

    //增加
    public void add(Spit spit) {
        spit.set_id(idWorker.nextId() + "");


        spit.setPublishtime(new Date());//发布日期
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态
        if (spit.getParentid() != null && !"".equals(spit.getParentid())) {
            //如果存在上级Id评论

            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment", 1);
            mongoTemplate.updateFirst(query, update, "spit");
        }
        spitDao.save(spit);
    }

    //修改
    public void update(Spit spit) {
        spitDao.save(spit);
    }

    //删除
    public void delete(String id) {
        spitDao.deleteById(id);
    }


    //根据上级id查询吐槽列表
    public Page<Spit> findByParentId(String parentId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return spitDao.findByParentid(parentId, pageRequest);
    }

    //点赞
    public void updateThumbup(String id) {
//        Spit spit = spitDao.findById(id).get();
//        spit.setThumbup(spit.getThumbup()+1);
//        spitDao.save(spit);

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("thumbup", 1);
        mongoTemplate.updateFirst(query, update, "spit");

    }


}

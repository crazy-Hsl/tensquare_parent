package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author crazy
 * @create 2021-04-23 20:01
 */
public interface SpitDao  extends MongoRepository<Spit,String> {
    //根据上级id查询吐槽列表
    public Page<Spit> findByParentid(String parentid, Pageable pageable);
}

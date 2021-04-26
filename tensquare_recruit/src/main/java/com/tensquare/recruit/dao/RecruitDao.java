package com.tensquare.recruit.dao;


import com.tensquare.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author crazy
 * @create 2021-04-22 16:48
 */
public interface RecruitDao extends
        JpaRepository<Recruit, String>, JpaSpecificationExecutor<Recruit> {

    //查询最新职位列表（按照创建日期降序）
    public List<Recruit> findTop4ByStateOrderByCreateTimeDesc(String state);//where state=? order by createTime
    //最新职位列表
    public List<Recruit> findTop12ByStateNotOrderByCreateTimeDesc(String state);//where state!=? order by createTime
}

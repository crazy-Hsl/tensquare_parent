package com.tensquare.qa.dao;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author crazy
 * @create 2021-04-23 9:19
 *数据访问接口
 */
public interface ProblemDao extends JpaRepository<Problem,String>, JpaSpecificationExecutor<Problem> {
    //根据标签id查询最新问题列表
    @Query(value ="select *  from tb_problem ,tb_pl where id = problemid and  labelid = ? ORDER by  replytime desc", nativeQuery = true)
    Page<Problem> newlist(String labelid, Pageable pageable);
    //根据标签id查询热门问题列表
    @Query(value = "select *  from tb_problem,tb_pl where id = problemid and labelid = ? order by reply desc",nativeQuery  = true)
    Page<Problem> hotlist(String labelid,Pageable pageable);
    //根据标签id查询等待回答列表
    @Query(value = "select * from tb_problem,tb_pl where id = problemid and labelid = ? and reply = 0 order  by createtime desc",nativeQuery = true)
    Page<Problem> waitlist(String labelid,Pageable pageable);

}

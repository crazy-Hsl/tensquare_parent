package com.tensquare.qa.dao;

import com.tensquare.qa.pojo.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author crazy
 * @create 2021-04-23 9:48
 */
public interface ReplyDao extends JpaRepository<Reply,String>, JpaSpecificationExecutor<Reply> {
}

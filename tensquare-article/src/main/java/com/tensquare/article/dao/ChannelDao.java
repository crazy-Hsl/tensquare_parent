package com.tensquare.article.dao;

import com.tensquare.article.pojo.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author crazy
 * @create 2021-04-23 14:11
 */
public interface ChannelDao  extends JpaRepository<Channel,String> , JpaSpecificationExecutor<Channel> {
}

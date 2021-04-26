package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author crazy
 * @create 2021-04-25 17:35
 */
public interface NoFriendDao extends JpaRepository<NoFriend, String>/*, JpaSpecificationExecutor<NoFriend> */ {


    NoFriend findByUseridAndFriendid(String userid, String friendid);

}

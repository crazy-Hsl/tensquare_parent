package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


/**
 * @author crazy
 * @create 2021-04-25 16:52
 */
public interface FriendDao extends JpaRepository<Friend, String>, JpaSpecificationExecutor<Friend> {

    @Query(value = "select count (f)  from tb_friend f where f.userid=?1 and  f.friendid=?2", nativeQuery = true)
    int selectCount(String userid, String friendid);

    @Modifying
    @Query(value = "update tb_friend f set  f.islike=?3 where f.userid=?1 and f.friendid=?2", nativeQuery = true)
    void updateLike(String userid, String friendid, String islike);

    @Modifying
    @Query(value = "delete from Friend f where f.userid=?1 and f.friendid=?2", nativeQuery = true)
    void deleteFriend(String userid, String friendid);

    Friend findByUseridAndFriendid(String userid, String friendid);
}

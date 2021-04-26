package com.tensquare.friend.service;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

/**
 * @author crazy
 * @create 2021-04-25 17:14
 */
@Service
public class FriendService {


    @Autowired
    FriendDao friendDao;
    @Autowired
    NoFriendDao noFriendDao;

    @Autowired
    UserClient userClient;


    @Transactional
    public int addFriend(String userid, String friendid) {
        //判斷如果用戶已經添加了這個好友,则不进行任何操作，返回为0
        if (friendDao.selectCount(userid, friendid) > 0) {
            return 0;
        }
        userClient.incFanscount(userid, 1);
        userClient.incFollowcount(userid, 1);
        //向喜欢表中添加记录
        Friend friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        friendDao.save(friend);
        //判断对方是否喜欢你，如果喜欢，将isLike设置为1
        if (friendDao.selectCount(friendid, userid) > 0) {
            friendDao.updateLike(userid, friendid, "1");
            friendDao.updateLike(friendid, userid, "1");
        }
        return 1;
    }

    //向不喜歡列表中添加記錄
    public int addNoFriend(String userid, String friendid) {
        if(noFriendDao.findByUseridAndFriendid(userid,friendid)!=null){
            return 0;
        }
        NoFriend noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
        return 1;
    }


    @Transactional
    public void deleteFriend(String userid, String friendid) {
        userClient.incFollowcount(userid, -1);
        userClient.incFanscount(friendid, -1);
        //先删除好友表中userid到friendid这条数据
        friendDao.deleteFriend(userid, friendid);
        //更新friendid到userid的islike为0
        friendDao.updateLike(userid, friendid, "0");
        addNoFriend(userid, friendid);//向不喜歡表中添加記錄
    }


}

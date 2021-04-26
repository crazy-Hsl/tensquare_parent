package com.tensquare.friend.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author crazy
 * @create 2021-04-25 16:48
 */
@Entity
@Table(name = "tb_friend")
@IdClass(Friend.class)
public class Friend implements Serializable {
    @Id
    private  String userid;//ID
    @Id
    private  String friendid;//

    private  String islike;

    public Friend(String userid, String friendid, String islike) {
        this.userid = userid;
        this.friendid = friendid;
        this.islike = islike;
    }

    public String getIslike() {
        return islike;
    }

    public void setIslike(String islike) {
        this.islike = islike;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "userid='" + userid + '\'' +
                ", friendid='" + friendid + '\'' +
                ", islike='" + islike + '\'' +
                '}';
    }

    public Friend(String userid, String friendid) {
        this.userid = userid;
        this.friendid = friendid;
    }

    public Friend() {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFriendid() {
        return friendid;
    }

    public void setFriendid(String friendid) {
        this.friendid = friendid;
    }

}

package com.tensquare.friend.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author crazy
 * @create 2021-04-25 17:33
 */
@Entity
@Table(name = "tb_nofriend")
@IdClass(NoFriend.class)
public class NoFriend implements Serializable {
    @Id
    private String userid;//Id
    @Id
    private String friendid;

    public NoFriend() {
    }

    public NoFriend(String userid, String friendid) {
        this.userid = userid;
        this.friendid = friendid;
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

    @Override
    public String toString() {
        return "NoFriend{" +
                "userid='" + userid + '\'' +
                ", friendid='" + friendid + '\'' +
                '}';
    }
}

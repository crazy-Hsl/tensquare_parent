package com.tensquare.qa.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author crazy
 * @create 2021-04-23 9:13
 */
@Entity
@Table(name = "tb_reply")
public class Reply implements Serializable {
    @Id
    private String id;//ID
    private String problemid;//问题id
    private String content;//回答内容
    private Date createtime;//创建呢时间
    private Date updatetime;//更新时间
    private String userid;//回答人ID
    private String nickname;//回答人昵称

    public Reply() {
    }

    public Reply(String id, String problemid, String content, Date createtime, Date updatetime, String userid, String nickname) {
        this.id = id;
        this.problemid = problemid;
        this.content = content;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.userid = userid;
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProblemid() {
        return problemid;
    }

    public void setProblemid(String problemid) {
        this.problemid = problemid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id='" + id + '\'' +
                ", problemid='" + problemid + '\'' +
                ", content='" + content + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", userid='" + userid + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

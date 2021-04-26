package com.tensquare.qa.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author crazy
 * @create 2021-04-23 9:05
 */
@Entity
@Table(name = "tb_problem")
public class Problem  implements Serializable {
    @Id
    private  String id;//ID
    private  String title;//标题
    private  String content;//内容
    private  Date createtime;//创建时间
    private  Date updatetime;//更新时间
    private  String userid;//用户ID
    private  String nickname;//昵称
    private  Integer visits;//浏览量
    private  String thumbup;//点赞数
    private  String reply;//回复数
    private  String solve;//是否解决
    private  String replyname;//回复人昵称
    private  Date replytime;//回复日期

    public Problem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public String getThumbup() {
        return thumbup;
    }

    public void setThumbup(String thumbup) {
        this.thumbup = thumbup;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getSolve() {
        return solve;
    }

    public void setSolve(String solve) {
        this.solve = solve;
    }

    public String getReplyname() {
        return replyname;
    }

    public void setReplyname(String replyname) {
        this.replyname = replyname;
    }


    @Override
    public String toString() {
        return "Problem{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", userid='" + userid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", visits=" + visits +
                ", thumbup='" + thumbup + '\'' +
                ", reply='" + reply + '\'' +
                ", solve='" + solve + '\'' +
                ", replyname='" + replyname + '\'' +
                ", replytime='" + replytime + '\'' +
                '}';
    }

    public Date getReplytime() {
        return replytime;
    }

    public void setReplytime(Date replytime) {
        this.replytime = replytime;
    }

    public Problem(String id, String title, String content, Date createtime, Date updatetime, String userid, String nickname, Integer visits, String thumbup, String reply, String solve, String replyname, Date replytime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.userid = userid;
        this.nickname = nickname;
        this.visits = visits;
        this.thumbup = thumbup;
        this.reply = reply;
        this.solve = solve;
        this.replyname = replyname;
        this.replytime = replytime;
    }
}

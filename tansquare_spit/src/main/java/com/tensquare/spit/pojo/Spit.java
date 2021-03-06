package com.tensquare.spit.pojo;

import org.springframework.data.annotation.Id;

import java.awt.print.PrinterGraphics;
import java.util.Date;

/**
 * @author crazy
 * @create 2021-04-23 19:54
 */
public class Spit {
    @Id
    private  String _id;//Id
    private String content;//吐槽内容
    private Date publishtime;//发布日期
    private String userid;//发布人Id
    private String nickname;//发布人昵称
    private Integer visits;//浏览量
    private Integer thumbup;//点赞数
    private Integer share;//分享数
    private Integer comment;//回复数
    private String state;//是否可见
    private String parentid;//上级ID

    public Spit() {
    }

    public Spit(String _id, String content, Date publishtime, String userid, String nickname, Integer visits, Integer thumbup, Integer share, Integer comment, String state, String parentid) {
        this._id = _id;
        this.content = content;
        this.publishtime = publishtime;
        this.userid = userid;
        this.nickname = nickname;
        this.visits = visits;
        this.thumbup = thumbup;
        this.share = share;
        this.comment = comment;
        this.state = state;
        this.parentid = parentid;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
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

    public Integer getThumbup() {
        return thumbup;
    }

    public void setThumbup(Integer thumbup) {
        this.thumbup = thumbup;
    }

    public Integer getShare() {
        return share;
    }

    public void setShare(Integer share) {
        this.share = share;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    @Override
    public String toString() {
        return "Spit{" +
                "_id='" + _id + '\'' +
                ", content='" + content + '\'' +
                ", publishtime=" + publishtime +
                ", userid='" + userid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", visits=" + visits +
                ", thumbup=" + thumbup +
                ", share=" + share +
                ", comment=" + comment +
                ", state='" + state + '\'' +
                ", parentid='" + parentid + '\'' +
                '}';
    }
}

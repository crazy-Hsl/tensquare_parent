package com.tensquare.article.pojo;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author crazy
 * @create 2021-04-23 21:00
 */

public class Comment implements Serializable {
    @Id
    private String _id;//ID
    private String article;//文章ID
    private String content;//评论内容
    private String userid;//评论人Id
    private String parentid;//评论ID  //0表示文章顶级评论
    private Date publishdate;//评论日期

    public Comment() {
    }

    public Comment(String _id, String article, String content, String userid, String parentid, Date publishdate) {
        this._id = _id;
        this.article = article;
        this.content = content;
        this.userid = userid;
        this.parentid = parentid;
        this.publishdate = publishdate;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "_id='" + _id + '\'' +
                ", article='" + article + '\'' +
                ", content='" + content + '\'' +
                ", userid='" + userid + '\'' +
                ", parentid='" + parentid + '\'' +
                ", publishdate=" + publishdate +
                '}';
    }
}

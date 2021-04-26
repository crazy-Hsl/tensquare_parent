package com.tensquare.article.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author crazy
 * @create 2021-04-23 12:19
 */
@Entity
@Table(name = "tb_column")
public class Column implements Serializable {
    @Id
    private  String id;//Id
    private  String name;//专栏名称
    private  String summery;//专栏简介
    private  String userid;//用户id
    private  String createtime;//申请时间
    private  String checktime;//审核时间
    private  String state;//状态

    public Column() {
    }

    public Column(String id, String name, String summery, String userid, String createtime, String checktime, String state) {
        this.id = id;
        this.name = name;
        this.summery = summery;
        this.userid = userid;
        this.createtime = createtime;
        this.checktime = checktime;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummery() {
        return summery;
    }

    public void setSummery(String summery) {
        this.summery = summery;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getChecktime() {
        return checktime;
    }

    public void setChecktime(String checktime) {
        this.checktime = checktime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Column{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", summery='" + summery + '\'' +
                ", userid='" + userid + '\'' +
                ", createtime='" + createtime + '\'' +
                ", checktime='" + checktime + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}

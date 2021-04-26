package com.tensquare.gathering.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author crazy
 * @create 2021-04-23 16:52
 */
@Entity
@Table(name = "tb_gathering")
public class Gathering implements Serializable {
    @Id
    private String id;//ID
    private String name;//活动名称
    private String summery;//大会简介
    private String detail;//详细说明
    private String sponsor;//主办方
    private String image;//活动图片
    private Date starttime;//开始时间
    private Date endtime;//截止时间
    private String address;//举办地点
    private Date enrolltime;//报名截止
    private String state;//是否可见
    private String city;//城市

    public Gathering() {
    }

    public Gathering(String id, String name, String summery, String detail, String sponsor, String image, Date starttime, Date endtime, String address, Date enrolltime, String state, String city) {
        this.id = id;
        this.name = name;
        this.summery = summery;
        this.detail = detail;
        this.sponsor = sponsor;
        this.image = image;
        this.starttime = starttime;
        this.endtime = endtime;
        this.address = address;
        this.enrolltime = enrolltime;
        this.state = state;
        this.city = city;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getEnrolltime() {
        return enrolltime;
    }

    public void setEnrolltime(Date enrolltime) {
        this.enrolltime = enrolltime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Gathering{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", summery='" + summery + '\'' +
                ", detail='" + detail + '\'' +
                ", sponsor='" + sponsor + '\'' +
                ", image='" + image + '\'' +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", address='" + address + '\'' +
                ", enrolltime=" + enrolltime +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}

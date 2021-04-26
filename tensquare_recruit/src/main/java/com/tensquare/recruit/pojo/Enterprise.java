package com.tensquare.recruit.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author crazy
 * @create 2021-04-22 22:25
 */
@Entity
@Table(name = "tb_enterprise")
public class Enterprise implements Serializable {
    @Id
    private  String id;//ID
    private String name;//企业名称
    private String summery;//企业简介
    private String address;//企业地址
    private String labels;//标签列表
    private String coordinate;//坐标
    private String ishot;//是否热门
    private String logo;//LOGO
    private Integer jobcount;//职位数
    private String url;//URL

    @Override
    public String toString() {
        return "Enterprise{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", summery='" + summery + '\'' +
                ", address='" + address + '\'' +
                ", labels='" + labels + '\'' +
                ", coordinate='" + coordinate + '\'' +
                ", ishot='" + ishot + '\'' +
                ", logo='" + logo + '\'' +
                ", jobcount=" + jobcount +
                ", url='" + url + '\'' +
                '}';
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getIshot() {
        return ishot;
    }

    public void setIshot(String ishot) {
        this.ishot = ishot;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getJobcount() {
        return jobcount;
    }

    public void setJobcount(Integer jobcount) {
        this.jobcount = jobcount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Enterprise() {
    }

    public Enterprise(String id, String name, String summery, String address, String labels, String coordinate, String ishot, String logo, Integer jobcount, String url) {
        this.id = id;
        this.name = name;
        this.summery = summery;
        this.address = address;
        this.labels = labels;
        this.coordinate = coordinate;
        this.ishot = ishot;
        this.logo = logo;
        this.jobcount = jobcount;
        this.url = url;
    }
}

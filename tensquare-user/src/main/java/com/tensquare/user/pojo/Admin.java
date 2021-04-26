package com.tensquare.user.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author crazy
 * @create 2021-04-24 11:15
 */
@Entity
@Table(name = "tb_admin")
public class Admin implements Serializable {
    @Id
    private  String id;//
    private  String loginname;//登錄名稱
    private  String password;//密码
    private  String state;//状态

    public Admin() {
    }

    public Admin(String id, String loginname, String password, String state) {
        this.id = id;
        this.loginname = loginname;
        this.password = password;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", loginname='" + loginname + '\'' +
                ", password='" + password + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}

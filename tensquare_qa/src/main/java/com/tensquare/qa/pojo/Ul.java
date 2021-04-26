package com.tensquare.qa.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author crazy
 * @create 2021-04-23 9:26
 */
@Entity
@Table(name = "tb_ul")
public class Ul  implements Serializable {
    @Id
    private String uid;
    @Id
    private String lid;

    public Ul() {
    }

    public Ul(String uid, String lid) {
        this.uid = uid;
        this.lid = lid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    @Override
    public String toString() {
        return "Ul{" +
                "uid='" + uid + '\'' +
                ", lid='" + lid + '\'' +
                '}';
    }
}

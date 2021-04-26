package com.tensquare.serach.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * @author crazy
 * @create 2021-04-23 22:40
 */
@Document(indexName = "tensquare_article",indexStoreType = "article")
public class Article implements Serializable {

    @Id
    private  String id;//Id
    //是否索引，就是看该域是否能被搜索
    //是否分词 就表示搜索的时候是整体匹配还是单词匹配
    //是否存储 就是是否在页面上显示
    @Field(analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private  String title;//标题
    @Field(analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private  String content;//文章正文

    private  String state;//审核状态

    public Article() {
    }

    public Article(String id, String title, String content, String state) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.state = state;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}

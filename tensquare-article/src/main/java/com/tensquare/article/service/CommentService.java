package com.tensquare.article.service;

/**
 * @author crazy
 * @create 2021-04-23 21:04
 */

import com.tensquare.article.dao.CommentDao;
import com.tensquare.article.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentDao commentDao;
    @Autowired
    IdWorker idWorker;


    public void add(Comment comment){
        comment.set_id(idWorker.nextId()+"");
        commentDao.save(comment);
    }

    public List<Comment> findByArticleid(String article){
        return  commentDao.findByArticleid(article);
    }
    public void  deleteById(String id){
        commentDao.deleteById(id);
    }
}

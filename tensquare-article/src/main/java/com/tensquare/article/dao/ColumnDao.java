package com.tensquare.article.dao;

import com.tensquare.article.pojo.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author crazy
 * @create 2021-04-23 14:13
 */
public interface ColumnDao extends JpaRepository<Column,String> , JpaSpecificationExecutor<Column> {
}

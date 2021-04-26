package com.tensquare.base.dao;

import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author crazy
 * @create 2021-04-22 16:48
 */
public interface LabelDao extends
        JpaRepository<Label, String>, JpaSpecificationExecutor<Label> {
}

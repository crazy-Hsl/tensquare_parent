package com.tensquare.user.service;

import com.tensquare.user.dao.AdminDao;
import com.tensquare.user.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author crazy
 * @create 2021-04-24 14:48
 */
@Service
public class AdminService {
    @Autowired
    AdminDao adminDao;
    @Autowired
    IdWorker idWorker;
    @Autowired
    BCryptPasswordEncoder encoder;

    public Admin login(Admin admin) {
        //先根据用户名查询对象
        Admin loginname = adminDao.findByLoginname(admin.getLoginname());
        //然后拿数据库中的密码和用户名输入的密码匹配
        if (loginname != null && encoder.matches(admin.getPassword(), loginname.getPassword())) {
            //密码匹配后，登录成功
            return loginname;
        }
        //登录失败
        return null;
    }

    //查询全部
    public List<Admin> findAll() {
        return adminDao.findAll();
    }

    //分页+条件查询
    public Page<Admin> findSearch(Map searchMap, int page, int size) {
        Specification<Admin> specification = create(searchMap);
        PageRequest of = PageRequest.of(page - 1, size);
        return adminDao.findAll(specification, of);
    }

    //条件查询


    public List<Admin> findSearch(Map searchMap) {
        Specification<Admin> specification = create(searchMap);
        return adminDao.findAll(specification);
    }

    //根据id查询
    public Admin findById(String id) {
        return adminDao.findById(id).get();
    }

    //增加
    public void add(Admin admin) {
        admin.setId(idWorker.nextId() + "");
        //密码加密
        admin.setPassword(encoder.encode(admin.getPassword()));
        adminDao.save(admin);
    }

    //修改
    public void update(Admin admin) {
        adminDao.save(admin);
    }

    //删除
    public void delete(String id) {
        adminDao.deleteById(id);
    }

    //动态条件构建
    private Specification<Admin> create(Map searchMap) {
        return new Specification<Admin>() {
            @Override
            public Predicate toPredicate(Root<Admin> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                //ID
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(criteriaBuilder.like(root.get("id").as(String.class), "%" + searchMap.get("id") + "%"));
                }
                //登录名称
                if (searchMap.get("loginname") != null && !"".equals(searchMap.get("loginname"))) {
                    predicateList.add(criteriaBuilder.like(root.get("loginname").as(String.class), "%" + searchMap.get("loginname") + "%"));
                }
                //密码
                if (searchMap.get("password") != null && !"".equals(searchMap.get("password"))) {
                    predicateList.add(criteriaBuilder.like(root.get("password").as(String.class), "%" + searchMap.get("password") + "%"));
                }
                //状态
                if (searchMap.get("state") != null && !"".equals(searchMap.get("state"))) {
                    predicateList.add(criteriaBuilder.like(root.get("state").as(String.class), "%" + searchMap.get("state") + "%"));
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));


            }
        };
    }


}

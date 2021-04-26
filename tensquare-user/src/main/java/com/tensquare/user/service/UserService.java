package com.tensquare.user.service;

import com.sun.xml.internal.ws.server.sei.EndpointArgumentsBuilder;
import com.tensquare.user.dao.UserDao;
import com.tensquare.user.pojo.User;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import util.IdWorker;
import util.JwtUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.security.auth.login.LoginContext;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author crazy
 * @create 2021-04-24 11:27
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    IdWorker idWorker;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    HttpServletRequest request;
    @Autowired
    JwtUtil jwtUtil;

    //查询所有
    public List<User> findAll() {
        return userDao.findAll();
    }

    //条件查询+分页
    public Page<User> findsearch(Map searchMap, int page, int size) {
        Specification<User> specification = create(searchMap);
        PageRequest of = PageRequest.of(page - 1, size);
        return userDao.findAll(specification, of);
    }

    //条件查询
    public List<User> findsearch(Map searchMap) {
        Specification<User> specification = create(searchMap);
        return userDao.findAll(specification);
    }

    //根据id查询
    public User findById(String id) {
        return userDao.findById(id).get();
    }

    //增加
    public void add(User user) {
        user.setId(idWorker.nextId() + "");
        //密码加密
        user.setPassword(encoder.encode(user.getPassword()));

        user.setFanscount(0);//关注数
        user.setFollowcount(0);//粉丝数
        user.setOnline(0L);//在线时长
        user.setRegdate(new Date());//注册时间
        user.setUpdatedate(new Date());//更新时间
        user.setLastdate(new Date());//最后登录时间
        userDao.save(user);
    }


    //修改
    public void update(User user) {
        userDao.save(user);
    }

    //删除
    public void delete(String id) {
//        String header = request.getHeader("Authorization");
//        if (header == null || "".equals(header)) {
//            throw new RuntimeException("权限不足！");
//        }
//        if (!header.startsWith("Bearer")) {
//            throw new RuntimeException("权限不足");
//        }
//        //得到token
//        String token1 = header.substring(7);
//
//        try {
//            Claims claims = jwtUtil.parseJWT(token1);
//            String roles = (String) claims.get("roles");
//            if (roles == null || !roles.equals("admin")) {
//                throw new RuntimeException("权限不足");
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("权限不足");
//        }
        String token = (String) request.getAttribute("claims_admin");
        if (token == null) {
            throw new RuntimeException("权限不足");
        }
        userDao.deleteById(id);
    }

    public Specification<User> create(Map searchMap) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicates.add(criteriaBuilder.like(root.get("id"), "%" + searchMap.get("id") + "%"));
                }
                if (searchMap.get("mobile") != null && !"".equals(searchMap.get("mobile"))) {
                    predicates.add(criteriaBuilder.like(root.get("mobile"), "%" + searchMap.get("mobile") + "%"));
                }
                if (searchMap.get("password") != null && !"".equals(searchMap.get("password"))) {
                    predicates.add(criteriaBuilder.like(root.get("password"), "%" + searchMap.get("password") + "%"));
                }
                if (searchMap.get("nickname") != null && !"".equals(searchMap.get("nickname"))) {
                    predicates.add(criteriaBuilder.like(root.get("nickname"), "%" + searchMap.get("nickname") + "%"));
                }
                if (searchMap.get("sex") != null && !"".equals(searchMap.get("sex"))) {
                    predicates.add(criteriaBuilder.like(root.get("sex"), "%" + searchMap.get("sex") + "%"));
                }
                if (searchMap.get("avatar") != null && !"".equals(searchMap.get("avatar"))) {
                    predicates.add(criteriaBuilder.like(root.get("avatar"), "%" + searchMap.get("avatar") + "%"));
                }
                if (searchMap.get("email") != null && !"".equals(searchMap.get("email"))) {
                    predicates.add(criteriaBuilder.like(root.get("email"), "%" + searchMap.get("email") + "%"));
                }
                if (searchMap.get("interest") != null && !"".equals(searchMap.get("interest"))) {
                    predicates.add(criteriaBuilder.like(root.get("interest"), "%" + searchMap.get("interest") + "%"));
                }
                if (searchMap.get("personality") != null && !"".equals(searchMap.get("personality"))) {
                    predicates.add(criteriaBuilder.like(root.get("personality"), "%" + searchMap.get("personality") + "%"));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    public void sendSms(String mobile) {
        //生成六位随机数
        String numeric = RandomStringUtils.randomNumeric(6);
        //向缓存中放一份,,五分钟过期
        redisTemplate.opsForValue().set("checkcode_" + mobile, numeric, 5, TimeUnit.MINUTES);

        //给用户发一份
        HashMap<String, String> map = new HashMap<>();

        map.put("mobile", mobile);
        map.put("checkcode", numeric+"");

        rabbitTemplate.convertAndSend("sms",map);
        //在控制台显示【方便显示】

        System.out.println("验证码为：" + numeric);

    }

    public User login(String mobile, String password) {
        User user = userDao.findByMobile(mobile);
        if (user != null && encoder.matches(password, user.getPassword())) {
            return user;
        }

        return null;
    }


    @Transactional
    public  void  updatefanscountfollowcount(int x,String userid,String friendid){
        userDao.updatefanscount(x,friendid);
        userDao.updatefollowcount(x,userid);
    }

}

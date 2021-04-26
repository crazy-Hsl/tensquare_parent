package com.tensquare.friend.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author crazy
 * @create 2021-04-25 11:31
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        System.out.println("经过了拦截器");
        //无论如何都是放行，具体能不能操作还是具体的操作中去判断
        //拦截器只负责把请求头中包含token的令牌进行一个解析验证
        String header = request.getHeader("Authorization");
        if (header != null && !"".equals(header)) {
            //如果含有Authorization头信息，就对其进行解析
            if (header.startsWith("Bearer")) {
                //得到token
                String token = header.substring(7);
                //验证令牌
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if (roles != null && roles.equals("admin")) {//管理员
                        request.setAttribute("claims_admin", token);
                    }
                    if (roles != null && roles.equals("user")) {//用户
                        request.setAttribute("claims_user", token);
                    }

                } catch (Exception e) {
                    throw new RuntimeException("令牌不正确!");
                }
            }
        }

        return true;
    }
}

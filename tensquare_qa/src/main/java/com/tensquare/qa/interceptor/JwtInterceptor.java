package com.tensquare.qa.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author crazy
 * @create 2021-04-23 11:06
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器");
        //无论如何都是放行，具体不能不能操作还是在具体的操作中去判断
        //拦截器只是负责把头请求中包含token的令牌进行一个解析验证
        String header = request.getHeader("Authorization");

        if(handler!=null && !"".equals(header)){
            //如果有包含Authorization头信息，就对其进行解析
            if(header.startsWith("Bearer")){
                //得到token
                String token = header.substring(7);
                //验证令牌
                try{
//                    Claims claims = jwtUtil.parseJWT(token);
//                    String roles = claims.get("roles");
//                    if(roles!=null&& roles.equals("admin")){
//                        request.setAttribute("claims_admin",token);
//                    }
//                    if(roles!=null&&roles.equals("user"){
//                        request.setAttribute("claims_user",token);
//                    }

                }catch (Exception e){
                    throw  new RuntimeException("令牌不正确！");
                }
            }
        }


        return true;
    }
}

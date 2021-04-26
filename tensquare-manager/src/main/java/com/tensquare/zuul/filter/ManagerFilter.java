package com.tensquare.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author crazy
 * @create 2021-04-25 21:14
 */
@Component
public class ManagerFilter extends ZuulFilter {

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public String filterType() {//过滤器类型
        return "pre";//前置过滤器
    }

    @Override
    public int filterOrder() {
        return 0;//优先级，数字越大，优先级越低
    }

    @Override
    public boolean shouldFilter() {
        return true;//过滤器开关，true表示开启
    }

    @Override
    public Object run() throws ZuulException {

        System.out.println("Zuul过滤器");
        RequestContext currentContext = RequestContext.getCurrentContext();
        //request域
        HttpServletRequest request = currentContext.getRequest();

        if (request.getMethod().equals("OPTIONS")) {
            return null;
        }
        String url = request.getRequestURL().toString();
        if (url.indexOf("/admin/login") > 0) {
            System.out.println("登录界面" + url);
            return null;
        }

        String header = request.getHeader("Authorization");//获取头信息

        if (header != null && header.startsWith("Bearer")) {
            String token = header.substring(7);
            Claims claims = jwtUtil.parseJWT(token);

            if (claims != null) {
                if ("admin".equals(claims.get("roles"))) {
                    currentContext.addZuulRequestHeader("Authorization", header);
                    System.out.println("token验证通过,添加了头信息" + header);
                    return null;
                }
            }
        }
        currentContext.setSendZuulResponse(false);//终止运行
        currentContext.setResponseStatusCode(401);//http状态吗
        currentContext.setResponseBody("无权访问");
        currentContext.getResponse().setContentType("text/html;charset=UTF-8");
        return null;
    }
}

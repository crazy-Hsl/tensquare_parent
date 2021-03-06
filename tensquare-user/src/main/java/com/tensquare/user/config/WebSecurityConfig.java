package com.tensquare.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author crazy
 * @create 2021-04-25 11:41
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //authorizeRequests所有的security全注解配置就是实现的开端，表示开始说明需要的权限
        //需要的权限分两部分，第一部分是拦截的路径，第二部分是访问该路径需要的权限
        //antMatchers表示拦截什么路径，permitAll任何权限都可以访问
        //anyRequest()任何的请求，authenticated认证后才能访问
        //.and().csrf().disable(),固定写法  表示使csrf拦截失效
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();

    }
}

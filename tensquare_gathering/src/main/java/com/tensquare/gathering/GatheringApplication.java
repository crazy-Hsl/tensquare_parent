package com.tensquare.gathering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @author crazy
 * @create 2021-04-23 21:59
 */
@SpringBootApplication
@EnableCaching
@EnableEurekaClient
public class GatheringApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatheringApplication.class,args);
    }
    @Bean
    public IdWorker idWorker(){
        return  new IdWorker(1,1);
    }
}

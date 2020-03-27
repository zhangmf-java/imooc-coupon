package com.imooc.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 网关应用启动入口
 * @author zmf
 * @date 2020/3/27 12:37 上午
 */
//标识当前应用是zuul server
@EnableZuulProxy
//含有@SpringBootApplication
//并且启用Eureka发现服务和启用熔断器
@SpringCloudApplication
public class ZuulGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulGatewayApplication.class,args);
    }
}

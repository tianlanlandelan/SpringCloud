package com.originaldreams.publicservicecenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@EnableDiscoveryClient
@SpringBootApplication
public class PublicservicecenterApplication {
    @Bean
    @LoadBalanced
    /**
     * 引入RestTemplate Bean
     * 用来进行服务间的Http通信
     * 同时重新定义其解析时用到的字符集，防止中文乱码
     */
    RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;

    }
    public static void main(String[] args) {
        SpringApplication.run(PublicservicecenterApplication.class, args);
    }
}

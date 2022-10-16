package com.cy.store;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.servlet.MultipartConfigElement;

//@Configuration
@SpringBootApplication
@Slf4j
//MapperScan注解指定当前项目中的Mapper接口路径的位置，在项目启动的时候会自动加载所有的接口
@MapperScan("com.cy.store.mapper")
public class StoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class,args);
        log.info("项目启动成功...");
    }

//    @Bean
//    public MultipartConfigElement getMultipartConfigElement(){
//        //创建一个配置的工程类对象
//        MultipartConfigFactory factory=new MultipartConfigFactory();
//
//        //设置需要创建的对象的相关信息
//        factory.setMaxFileSize(DataSize.of(10, DataUnit.MEGABYTES));
//        factory.setMaxRequestSize(DataSize.of(10,DataUnit.MEGABYTES));
//
//        //通过工厂类来创建MultipartConfigElement对象
//        return factory.createMultipartConfig();
//    }



}

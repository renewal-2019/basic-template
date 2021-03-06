package com.zsl.template;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Hello
 *
 * @author swiftzsl
 * @date 2021/7/25 10:42
 */
@SpringBootApplication
@EnableAsync // 开启异步
@MapperScan(basePackages = {"com.zsl.template.mapper"})
public class TemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(TemplateApplication.class, args);
    }

}

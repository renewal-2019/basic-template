package com.zsl.template.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPIConfig
 * 实现接口分组,让文档更清晰
 *
 * @author swiftzsl
 * @date 2021/7/25 15:58
 */
@Configuration
public class OpenAPIConfig {
    @Bean
    public GroupedOpenApi helloApi() {
        return GroupedOpenApi.builder()
                .group("test")
                .pathsToMatch("/test/**")
                .build();
    }

    @Bean
    public GroupedOpenApi articleApi() {
        return GroupedOpenApi.builder()
                .group("article")
                .pathsToMatch("/article/**")
                .build();
    }
}

package com.mjp.borrow.base.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>Description：Swagger配置类</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/17 10:44
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder().title("物品领用接口文档").description("接口文档").build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mjp.borrow.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}

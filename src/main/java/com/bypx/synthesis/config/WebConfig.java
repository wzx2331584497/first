package com.bypx.synthesis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration("customweb")
public class WebConfig extends WebMvcConfigurationSupport {

//访问static工具类
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("/upload/**")//访问路径
                .addResourceLocations("file:D:/upload/");
        super.addResourceHandlers(registry);


    }
}

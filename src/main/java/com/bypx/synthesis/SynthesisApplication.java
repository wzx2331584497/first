package com.bypx.synthesis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bypx.synthesis.dao")//将指定包下的接口类编译后生成相应的实现类
public class SynthesisApplication {
    public static void main(String[] args) {
        SpringApplication.run(SynthesisApplication.class, args);
    }

}

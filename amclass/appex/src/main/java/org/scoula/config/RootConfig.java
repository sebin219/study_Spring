package org.scoula.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {
        "org.scoula.advice",
        "org.scoula.sample.service",
})
@EnableAspectJAutoProxy //프록시 객체 활성화
public class RootConfig {

    //db사용 --> google mybatis사용 설정
    //트랜잭션 설정
}

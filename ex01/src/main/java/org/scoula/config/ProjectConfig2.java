package org.scoula.config;


import org.scoula.domain.Parrot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig2 {

    // 설정값이 다른 동일한 클래스의 객체를 싱글톤으로 여러 개 만들어야하는 경우
    //
    @Bean
    public Parrot parrot1(){
        // mysql8 연결
        Parrot p = new Parrot();
        p.setName("Koko");
        return p;
    }

    @Bean(name = "miki") // 생성된 객체 이름 지정 가능
    public Parrot parrot2(){
        // mongoDB 연결
        Parrot p = new Parrot();
        p.setName("Miki");
        return p;
    }

    @Bean
    public Parrot parrot3(){
        // oracle12g 연결
        Parrot p = new Parrot();
        p.setName("Riki");
        return p;
    }

    // 설정이 다른 Parrot 객체를 여러 개 만들어야 하는 경우
    // 기본으로는 메서드 이름이 객체이름이 됨
    // Parrot parrot1 = new Parrot();
    // 별도로 설정도 가능함. @Bean(name = "miki"); name 말고 value 도 가능
}

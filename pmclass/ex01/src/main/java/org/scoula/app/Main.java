package org.scoula.app;

import org.scoula.config.ProjectConfig;
import org.scoula.domain.Parrot;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // 스프링을 쓰지 않으면 필요할 때 new 키워드를 써서 개발자가 객체를 생성했음
        Parrot p = new Parrot();
        p.setName("Test");
        System.out.println(p.getName());
        System.out.println(p);

        // 스프링이 객체를 생성한 것을 가지고 와서 사용해보자.
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Parrot p2 = context.getBean(Parrot.class); //Parrot 클래스로 만들어진 객체 get!
        System.out.println(p2.getName());
        System.out.println(p2); // 주소2
        Parrot p3 = context.getBean(Parrot.class);
        System.out.println(p3); // 주소3
        // 주소2와 주소3은 동일하게 나옴
        // 싱글톤은 하나이기 때문에 동일한 타입에 대해서는 1개의 bean만 추출할 수 있음
    }
}

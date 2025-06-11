package org.scoula.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Person2 {

    // 어노테이션으로 주입(조립)하는 방법 3가지
    // 1. 필드 위에 표시 --> 예전에 많이 사용함. 아주 간단!
    //    Person2 먼저 만들고 나중에 Parrot을 끼는 방식
    // 2. 생성자에 표시 --> 권장! 미리 만들어서 객체 생성할 때 주입!
    // 3. set메서드에 표시 --> 비권장. Parrot을 나중에 끼고 싶을 때 사용(가끔 사용)

    private String name;

    // 싱글톤 중에서 자동으로 Parrot2의 클래스로 만들어진 것 있으면 주입해줘
    // @Autowired // 필드위에 표시하는 첫번째 방법
    // private final Parrot2 parrot;//생성자로 주입한 것은 final을 써서
    // 처음에 주입한 parrot을 계속 사용할 수 있는 안정성을 확보!

    // 생성자 주입의 장점 (Spring 팀에서 권장)
    // 1. final을 통해 불변성을 확보 가능
    // 2. 의존성 보장 가능(순환 참조 문제 해결)
    // 3. 코드 가독성 향상 (해당 객체가 어떤 의존성을 가진지 명확히 알 수 있음)

    private Parrot2 parrot;

    //@Autowired //생성자에 표시하는 두번째 방법
    Person2(Parrot2 parrot){
        this.parrot = parrot;
    }

    public Parrot2 getParrot(){
        return parrot;
    }

    public String getName(){
        return name;
    }

    @Autowired //set메서드에 표시하는 세번째 방법
    public void setParrot(Parrot2 parrot){
        this.parrot = parrot;
    }

    public void setName(String name){
        this.name = name;
    }
}

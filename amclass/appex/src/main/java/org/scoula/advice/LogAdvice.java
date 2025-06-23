package org.scoula.advice;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect //부가적인 기능
@Log4j2
@Component // 싱글톤
public class LogAdvice {
    //어떤 타겟이 되는 클래스의 메서드를 호출해서 실행할 때
    //어떤 부가적인 기능을 넣을지 충고해주는 클래스
    // => 클래스명 위에 @Aspect를 넣어줘야 충고해주는 클래스임을 인식함

    //SampleService 클래스가 타겟 클래스
    @Before("execution(* org.scoula.sample.service.SampleService*.*(..))")
    public void logBefore(){
        log.info("===================================");
    }

    //부가적인 기능을 넣을 타겟이 되는 SampleService 클래스 안에 있는
    //JoinPoint가 되는 모든 메서드를 호출해서 실행하기 전에(이벤트라고 인식함)
    //logBefore() 메서드를 먼저 호출해라! 라고 설정
    //==> 이벤트가 발생하면 aop 프락시 객체가 logBefore() 메서드를 먼저 호출함.

    //많은 설계 기법 중 스프링에서 사용된 설계 기법 3가지
    //1. 프락시(대리인) 설계 기법(design pattern)
    //2. 싱글톤
    //3. 팩토리

    @Before("execution(* org.scoula.sample.service.SampleService*.doAdd(String, String)) && args(str1, str2)")
    //첫 String이 str1으로, 두 번째 String이 str2로 들어감. 실행 전에 잘 들어가는지 확인하는 용도
    public void logBeforeWithParam(String str1, String str2) {
        log.info("str1:" + str1);
        log.info("str2:" + str2);
    }

    //지정된 대상이 예외 발생시킨 후 동작. 발생 안하면 동작하지 않음
    @AfterThrowing(pointcut = "execution(* org.scoula.sample.service.SampleService*.*(..))", throwing="exception")
    public void logException(Exception exception) {
        log.info("Exception...!!!!");
        log.info("exception: " + exception);
    }

    //시작-끝 부분에 각각 붙여서 최종 걸리는 시간 구할 수 있음 -> 성능
    @Around("execution(* org.scoula.sample.service.SampleServiceImpl*.*(..))")
    //반환값이 아무거나 상관 없고, 해당 경로 안에 있는 SampleService로 시작하는 클래스 중
    //입력값은 상관 없이 모두 target 클래스
    //target 중에서 실제 호출되는 메서드를 JoinPoint라고 함

    //내가 호출한 핵심 메서드 잡고있는 객체 필요 -> ProceedingJoinPoint
    //around 할 때만 사용
    public Object logTime(ProceedingJoinPoint pjp) {
        //사전
        long start = System.currentTimeMillis();

        log.info("Target: " + pjp.getTarget());
        log.info("Param: " + Arrays.toString(pjp.getArgs()));

        //이때까지 doAdd()는 실행되지 않고 기다림

        Object result = null;
        try {
            result = pjp.proceed(); // 실제 메서드 호출 --> 이때 doAdd() 실행
            //앞에서 뭔가 해주고, 계속해서 불러주는 객체인 ProceedingJoinPoint가 필요
        } catch(Throwable e) {
            e.printStackTrace();
        }

        //사후
        long end = System.currentTimeMillis();

        log.info("TIME: " + (end - start));

        return result;
    }
}
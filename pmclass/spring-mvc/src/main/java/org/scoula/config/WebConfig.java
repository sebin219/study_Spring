package org.scoula.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
/*
* Web 설정
* -> 기존 자바 웹 어플리케이션의 핵심 설정 파일
* -> 서블릿 컨테이너(톰캣)가 웹 어플리케이션을 실행할 때 가장 먼저 읽는 파일
*
* */
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ServletConfig.class};
    }

    // 스프링의 FrontController인 DispatcherServlet이 담당할 Url 매핑 패턴, / : 모든 요청에 대해 매핑
    //DispatcherServlet이 담당할 url 매핑 패턴
    // "/" : 모든 요청에 대한 매핑
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    // POST body 문자 인코딩 필터 설정 - UTF-8 설정
    // http의 body로 전송되는 post방식의 데이터는
    // 프론트컨트롤러가 받기 전에 미리 utf-8로 인코딩을 먼저 한 후 받게 설정함. -> 한글 안 깨지게
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();

        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        return new Filter[]{characterEncodingFilter};
    }
}
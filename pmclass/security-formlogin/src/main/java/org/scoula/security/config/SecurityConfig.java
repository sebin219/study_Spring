package org.scoula.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@Log4j2
@MapperScan(basePackages = {"org.scoula.security.account.mapper"})
@ComponentScan(basePackages = {"org.scoula.security"})
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    // 문자셋 필터
    public CharacterEncodingFilter encodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return encodingFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override  //csrf토큰이 깨지지않도록 인코딩하는 것
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(encodingFilter(), CsrfFilter.class);

        // 경로별, 접근 권한 설정
        http.authorizeRequests()
                .antMatchers("/security/all").permitAll() //모든 권한 접근 허용
                .antMatchers("/security/admin").access("hasRole('ROLE_ADMIN')")  //ROLE_ADMIN만 접근 가능
                .antMatchers("/security/member").access("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')");//ROLE_ADMIN, ROLE_MEMBER만 접근 가능

        http.formLogin() //form 기반 로그인 활성화
                .loginPage("/security/login")   //사용자가 보게될 로그인 페이지(우리가 만든거!)
                .loginProcessingUrl("/security/login")
                .defaultSuccessUrl("/");  //로그인 성공했을 때 전환되는 곳

        http.logout()
                .logoutUrl("/security/logout") // -> Spring Security에서 로그아웃 요청을 받는 POST API
                .invalidateHttpSession(true)
                .deleteCookies("JSESSION-ID")
                .logoutSuccessUrl("/security/logout"); // GET logout 페이지로 전환

        http.sessionManagement()
                .maximumSessions(1)                        // 동시 세션 수 제한
                .maxSessionsPreventsLogin(false)           // 새 로그인시 기존 세션 만료
                .expiredUrl("/security/login?expired");    // 세션 만료시 리다이렉트

        http.rememberMe()
                .key("uniqueAndSecret")                    // 🔑 암호화 키
                .tokenValiditySeconds(86400)               // ⏰ 24시간 유효
                .userDetailsService(userDetailsService);   // 👤 사용자 정보 서비스
    }


//    // 메모리 기반의 사용자 정보 등록
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        log.info("configure .........................................");
//        //inMemoryAuthentication -> 메모리 상에 user정보를 임의로 등록
//        auth.inMemoryAuthentication()
//                .withUser("admin")

    /// /                .password("{noop}1234")    //{noop} -> security는 기본적으로 비밀번호 암호화 필수 -> 예외처리(암호화 안하고 쓸게!)
//                .password("$2a$10$EsIMfxbJ6NuvwX7MDj4WqOYFzLU9U/lddCyn0nic5dFo3VfJYrXYC")
//                .roles("ADMIN", "MEMBER"); // ROLE_ADMIN
//
//        auth.inMemoryAuthentication()
//                .withUser("member")
//                .password("{noop}1234")
//                .roles("MEMBER"); // ROLE_MEMBER
//    }

    // 직접 만든 userDetailsService를 이용해 인증을 진행하도록 설정
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.info("configure .........................................");
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // CORS 설정 객체 생성
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(List.of("*"));
//   configuration.setAllowedOriginPatterns(Arrays.asList("http://localhost:5173"));


        // 허용할 HTTP 메서드 목록 지정
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));

        // 모든 요청 헤더 허용
        configuration.setAllowedHeaders(List.of("*"));

        // 자격 증명(쿠키, Authorization 헤더 등)을 포함한 요청 허용
        configuration.setAllowCredentials(true);

        // 특정 URL 경로 패턴에 대해 위의 CORS 설정을 적용
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 경로에 적용

        // 설정된 CORS 소스를 반환 (스프링 시큐리티나 필터 체인에 의해 사용됨)
        return source;
    }

}

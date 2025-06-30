package org.scoula.security.handle;

import lombok.extern.log4j.Log4j2;
import org.scoula.security.util.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 인증 안된 사용자한테 보낼 응답을 정의하는 클래스
@Log4j2
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException, ServletException {

        log.error("========== 인증 에러 ============");
        JsonResponse.sendError(
                response,
                HttpStatus.UNAUTHORIZED,
                authException.getMessage()
        );
    }
}
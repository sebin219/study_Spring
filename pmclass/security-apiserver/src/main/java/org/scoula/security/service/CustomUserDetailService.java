package org.scoula.security.service;

import lombok.RequiredArgsConstructor;
import org.scoula.security.account.domain.CustomUser;
import org.scoula.security.account.domain.MemberVO;
import org.scoula.security.account.mapper.UserDetailsMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/*
 * 1. 사용자가 로그인(id/password 입력)
 * 2. 시큐리티 내부에서 유저네임패스워드어쩌구필터실행
 * 3.어센티케이션메니저 -> 어센티케이션프로바이더 호출
 * 4. 프로바이더가 로드유저바이유저네임() 매서드호출 - 유저디테일에 유저정보 인증정보 저장
 * 5. 씨큐리티가 유저디테일에서 꺼낸 아이디랑 비밀번호를 비교해 로그인 성공여부 판단
 */
@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserDetailsMapper mapper;

    //시큐리티로 로그인할때 폼에 입력한 id값을 이용해 사용자 정보를 가져오는 것
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberVO vo = mapper.get(username);
        if (vo == null) {
            throw new UsernameNotFoundException(username + "는 없는 ID입니다.");
        }
        return new CustomUser(vo);
    }
}

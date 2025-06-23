package org.scoula.security.account.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

//UserDetails 구현
@Getter
@Setter
public class CustomUser extends User {

    private MemberVO member;  //실질적 사용자 데이터

    //권한 갖고오기
    public CustomUser(String username, String password,
                      Collection<? extends GrantedAuthority> authorities, MemberVO member) {
        super(username, password, authorities);
    }

    public CustomUser(MemberVO vo) {
        super(vo.getUsername(), vo.getPassword(), vo.getAuthList());
        this.member = vo;
    }
}

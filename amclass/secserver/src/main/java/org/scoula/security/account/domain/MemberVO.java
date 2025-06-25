package org.scoula.security.account.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
    private String username;
    private String password;
    private String email;
    private Date regDate;
    private Date updateDate;

    //  Auth테이블의 role(auth컬럼)이 여러개가 필요
    // member의 username과 auth는 일대다 관계
    private List<AuthVO> authList;  //권한 목록, join처리 필요
}

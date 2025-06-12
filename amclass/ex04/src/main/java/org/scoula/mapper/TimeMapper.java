package org.scoula.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

    //필요한 db처리 기능을 함수로 불완전하게 써놓자.
    //추상메서드라고 함.

    //어노테이션으로 sql문 사용하는 방법
    @Select("SELECT sysdate()")
    public String getTime();

    public String getTime2();
    //TimeMapper.xml에 sql문 있는 것 실행시키는 방법 사용
}

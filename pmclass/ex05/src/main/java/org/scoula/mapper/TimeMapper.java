package org.scoula.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

    /**
     * 어노테이션 방식으로 SQL 정의
     * MySQL의 현재 시간을 조회하는 쿼리
     */
    @Select("SELECT sysdate()") // SELECT문을 수행 후 결과 반환
    public String getTime();

    /**
     * XML 매퍼에서 정의할 메서드
     * 구현체는 MyBatis가 자동으로 생성
     */
    String getTime2();
}
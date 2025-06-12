package org.scoula.persistence;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.fail;

@Log4j2
public class JDBCTest {

    //여기에 여러 설정 잘 되었는지 함수 만들어서 테스트 여러개 할 예정.
    //테스트 단위마다 함수하나 만들어주면 됨.!
    //함수 단위마다 테스트해준다고 해서 단위테스트(Unit test)
    //JUnit 라이브러리 사용 예정!

    //모든 단위테스트에서 사용할 드라이버를 설정하는 부분이 필요함.

    //아래 모든 함수 호출전에 무조건 한번 이거 실행해줘라고 할때
    //@BeforeAll --> 시작할 때 한번 실행함.
    @BeforeAll
    public static void setup() {
        //클래스 파일을 연결(예외처리, try-catch)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); //상세한 에러정보를 프린트할 수 있음.
        }
    }

    @Test
    @DisplayName("JDBC 드라이버연결이된다.")
    public void testConnection() {
        String url= "jdbc:mysql://localhost:3307/scoula_db";
        try(Connection con = DriverManager.getConnection(url, "scoula", "1234")) {
            log.info(con);
        } catch(Exception e) {
            //System.out.println(e.getMessage());
            fail(e.getMessage());
            //==> junit에게 테스트하려고 했던 것 실패했다라고 명시적으로 알려주는 역할의 함수
        }
    }
}

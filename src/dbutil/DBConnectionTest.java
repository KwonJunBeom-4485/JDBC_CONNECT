package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionTest {
    public static void main(String[] args) {
        // 1. 데이터베이스 연결을 위한 Connection 객체 선언
        Connection conn = null;

        // 2. 데이터베이스 접속 테스트
        try {
            // 1) 드리이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");  // mysql
            // Class.forName("oracle.jdbc.OracleDriver"); // 오라클 드라이버
            System.out.println("드라이버 로드 성공");

            // 2) 데이터베이스 접속 정보를 담은 Connection 객체를 생성
            // jdbc:mysql://    -> jdbc로 mysql 접속
            // localhost:3306  -> 접속할 서버 주소와 포트번호
            // /jdbc           -> 접속할 데이터베이스 이름
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/jdbc",              // mysql
                // "jdbc:oracle:thin:@//localhost:1521/FREEPDB1",  // 오라클 url
                // "jdbc:mariadb://localhost:4306/jdbc",           // mariadb
                "jdbcuser",
                "jdbcuser"
            );

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로드 실패");
        } catch (SQLException sqle) {
            System.out.println("데이터베이스 접속 실패");
        } finally {
            try {
                if(conn != null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

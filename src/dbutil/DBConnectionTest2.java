package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionTest2 {
    public static void main(String[] args) {
        // 구버전이 아닌 이상 Class.forName으로 드라이버 반드시
        // 불러올 필요 없을 거다(?)
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";

        // try ~ catch 구분에 finally를 사용해서 Connection 객체를 해제하지 않아도 됨. (Close() 쓰는거)
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("DB 연결 성공");
        } catch (Exception e) {
            System.out.println("DB 연결 실패");
            System.out.println(e.getMessage());
        }

    }
}

package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBInsertTest {
    public static void main(String[] args) {
        // 1. Connection 연결 객체 생성(DriverManager.getConnection())
        // 2. SQL 작성
        // 3. Connection 객체에서 Statement 객체를 생성
        // 4. 생성된 Statement 객체를 이용해서 SQL을 실행 (Insert)

        // 1. Connection 연결 객체 생성(DriverManager.getConnection())
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("DB 연결 성공");

            // 2. SQL 작성
            String sql = "insert into person(userId, userPw, userName, userEmail) " +
                    "values('testuser1', 'testuser1', 'testuser1', 'testuser1@test.com')";

            // 3. Statement 객체 생성.
            Statement stmt = conn.createStatement();

            // executeUpdate(sql) 의 반환 값 int -> 0이면 실패, 1이면 1개 쿼리 성공.
            int result = stmt.executeUpdate(sql);

            if (result != 0)
                System.out.println("레코드 추가 성공");
            else
                System.out.println("레코드 추가 실패");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

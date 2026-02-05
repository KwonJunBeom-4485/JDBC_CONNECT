package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBSelectTest {
    public static void main(String[] args) {
        // 구버전이 아닌 이상 Class.forName으로 드라이버 반드시
        // 불러올 필요 없을 거다(?)
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("DB 연결 성공");

            // 2. SQL 작성
            String sql = "select * from person";

            // 3. Statement 객체 생성, ResultSet 객체 생성(쿼리 결과 받을 객체).
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("id: " + rs.getLong("id"));
                System.out.println("userId: " + rs.getString("userId"));
                System.out.println("userPw: " + rs.getString("userPw"));
                System.out.println("userName: " + rs.getString("userName"));
                System.out.println("userEmail: " + rs.getString("userEmail"));
                System.out.println("phone: " + rs.getString("phone1") + "-" + rs.getString("phone2"));
                System.out.println("age: " + rs.getByte("age"));
                System.out.println("address1: " + rs.getString("address1"));
                System.out.println("address2: " + rs.getString("address2"));
                System.out.println("regDate: " + rs.getTimestamp("regDate"));
                System.out.println("modifyDate: " + rs.getTimestamp("modifyDate"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

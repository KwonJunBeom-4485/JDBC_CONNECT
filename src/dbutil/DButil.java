package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {
    // 도구 : Connection 객체를 반환하는 도구 생성...
    // method 이름 : getConnection()

    // 멤버 변수
    private static final String url = "jdbc:mysql://localhost:3306/jdbc";
    private static final String sql_user = "jdbcuser";
    private static final String password = "jdbcuser";

    // 드라이버 로드 확인
    // static {} : 은 한번 실행하고 두 번 실행 안함.
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로드 실패!");
            System.out.println(e.getMessage());
        }
    }
    

    // 메서드 생성
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, sql_user, password);
    }
}

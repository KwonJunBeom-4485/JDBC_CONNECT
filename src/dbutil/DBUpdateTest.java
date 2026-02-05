package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
// import java.text.SimpleDateFormat;
// import java.sql.Date;

public class DBUpdateTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";
        
        // 선언해서 밑에 sdf.format~~ 처럼도 가능
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("DB 연결 성공");

            // 2. SQL 작성
            String sql = "update person set phone1 = '02', phone2 = '5889991', age = 20, "
            +"modifyDate = now() where id = 1";

            // String sql = "update person set phone1 = '02', phone2 = '5889991', age = 20, "
            // +"modifyDate ='"+sdf.format(new Date())+"' where id = 1";

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

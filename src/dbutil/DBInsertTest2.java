package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import domain.PersonVO;

public class DBInsertTest2 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";

        PersonVO vo = new PersonVO();
        vo.setPerson_userId("testuser2");
        vo.setPerson_userPw("testuser2pw");
        vo.setPerson_userName("testuser2");
        vo.setPerson_userEmail("testuser2@test.com");
        vo.setPerson_phone1("02");
        vo.setPerson_phone2("9988923");
        vo.setPerson_age((byte)24);
        vo.setPerson_address1("서울 어딘가..");
        vo.setPerson_address2("강남 어딘가..");
        

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("DB 연결 성공");

            // 2. SQL 작성
            String sql = "insert into person(userId, userPw, userName, userEmail, phone1, phone2, age, address1, address2) "
            +"values('"+vo.getPerson_userId()+"', '"+vo.getPerson_userPw()+"', '"+vo.getPerson_userName()+"', '"+vo.getPerson_userEmail()+"', '"
            +vo.getPerson_phone1()+"', '"+vo.getPerson_phone2()+"', '"+vo.getPerson_age()+"', '"+vo.getPerson_address1()+"', '"+vo.getPerson_address2()+"')";

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

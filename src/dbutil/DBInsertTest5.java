package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import domain.PersonRe;

public class DBInsertTest5 {
    public static void main(String[] args) {
        // DB 연결을 위한 값 정리
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";

        // Record 객체의 역할은 값을 변경 없이 전달, 받는 역할
        // Record 객체는 setter가 없음. 변경할 수 없어용 (immutable).
        PersonRe vo = new PersonRe(
                0,  // 0 해놓으면 알아서 id 값 잘 처리 됨.
                "testuser14",
                "testuserPw",
                "testuser14",
                "testuser14@test.com",
                "032",
                "98923394",
                (byte) 34,
                "인천",
                "어딘가",
                null,
                null);

        System.out.println(vo.id());

        // DB 작업(PreparedStatement)
        // 1. Connection 객체 생성
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // 2. SQL문 작성(PreparedStatement에 사용함)
            String sql = "insert into person(userId, userPw, userName, userEmail, " +
                    "phone1, phone2, age, address1, address2) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // 3. PreparedStatement 객체 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 4. SQL에 ?에 대한 데이터를 추가
            // 왜? 인덱스가 0이 아닌 1부터 시작하는가?
            // 0에 SQL 구문이 있다고 생각하면 된다.
            pstmt.setString(1, vo.userId());
            pstmt.setString(2, vo.userPw());
            pstmt.setString(3, vo.userName());
            pstmt.setString(4, vo.userEmail());
            pstmt.setString(5, vo.phone1());
            pstmt.setString(6, vo.phone2());
            pstmt.setInt(7, vo.age());
            pstmt.setString(8, vo.address1());
            pstmt.setString(9, vo.address2());
            // PreparedStatement 사용 장점 : 1) 편의점, 2) 보안성 (값 검증 처리)
            // System.out.println(pstmt); // sql문 확인용

            // 5. SQL 실행. - 메서드에 매개변수x (이미 처리함)
            int result = pstmt.executeUpdate();

            if (result != 0)
                System.out.println("레코드 추가 성공");
            else
                System.out.println("레코드 추가 실패");

        } catch (Exception e) {
            System.out.println("DB 연결 실패");
        }
    }
}

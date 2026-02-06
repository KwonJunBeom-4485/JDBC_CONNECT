package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import domain.PersonVO;

public class DBInsertTest3 {
    public static void main(String[] args) {
        // DB 연결을 위한 값 정리
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";

        // Insert에 사용할 데이터를 생성
        PersonVO vo = new PersonVO("user3", "userPw3", "user3", "user3@naver.com", "02", "9999999", (byte) 28,
                "서울 강북구 수유동", "옆동네 초등학교 인수");

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
            pstmt.setString(1, vo.getPerson_userId());
            pstmt.setString(2, vo.getPerson_userPw());
            pstmt.setString(3, vo.getPerson_userName());
            pstmt.setString(4, vo.getPerson_userEmail());
            pstmt.setString(5, vo.getPerson_phone1());
            pstmt.setString(6, vo.getPerson_phone2());
            pstmt.setInt(7, vo.getPerson_age());
            pstmt.setString(8, vo.getPerson_address1());
            pstmt.setString(9, vo.getPerson_address2());
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

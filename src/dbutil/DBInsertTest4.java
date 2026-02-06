package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.stream.IntStream;

import domain.PersonVO2;

public class DBInsertTest4 {

    // DB 연결을 위한 값 정리
    static String url = "jdbc:mysql://localhost:3306/jdbc";
    static String user = "jdbcuser";
    static String password = "jdbcuser";

    public static void main(String[] args) {

        // Insert에 사용할 데이터를 생성
        // PersonVO2 vo = new PersonVO2.Builder()
        // .userId("user4").userPw("user4Pw").userName("user4")
        // .userEmail("user4@naver.com").age(30).build();

        // insertDB(vo);

        // Stream을 사용하여 10개의 데이터를 추가하는 코드 작성
        // PersonVO2 객체에 대한...
        IntStream.range(0, 10).forEach(
                // 0 ~ 9 : 10
                i -> insertDB(new PersonVO2.Builder()
                        .userId("user" + ((int) 5 + (int) i)).userPw("userPw" + ((int) 5 + (int) i)).userName("user" + ((int) 5 + (int) i))
                        .userEmail("user" + ((int) 5 + (int) i) + "@naver.com").phone1("02").phone2("99" + i % 9 + "123" + i % 10)
                        .address1("서울 마포구 성산동").address2("23번지 " + (1 + i)).build()));
    }

    // Insert DB() 생성...
    // DB 매개변수를 받아서 insert 메서드 구현.
    public static void insertDB(PersonVO2 vo) {  
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
            System.out.println("DB 작업 실패");
        }
    }
}

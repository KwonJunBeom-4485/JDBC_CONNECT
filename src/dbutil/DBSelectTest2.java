package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import domain.PersonVO;

public class DBSelectTest2 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";

        // DriverManager에서 자동으로 맞는 거 갖고와서 url, user, password 정보 갖고 DB 연결 시도
        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            // DB 연결 성공 시 출력
            System.out.println("DB 연결 성공");

            // 2. SQL 작성
            String sql = "select * from person";

            // 3. Statement 객체 생성, ResultSet 객체 생성(쿼리 결과 받을 객체).
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // person 테이블 객체가 1개가 아니기 때문에 PersonVO 타입의 List 선언
            List<PersonVO> list = new ArrayList<>();

            // 쿼리 결과를 ResultSet 타입으로 반환 받아서 rs로 저장한 뒤 그걸 next()로 받아서 처리
            while (rs.next()) {
                // list 변수에 넣을 PersonVO 타입 객체 vo 선언 및 데이터 지정
                PersonVO vo = new PersonVO(
                        rs.getString("userId"), // column명의 데이터를 get[타입]에 맞는 형태로 가져와서 vo 안에 저장.
                        rs.getString("userPw"),
                        rs.getString("userName"),
                        rs.getString("userEmail"),
                        rs.getString("phone1"),
                        rs.getString("phone2"),
                        rs.getByte("age"),
                        rs.getString("address1"),
                        rs.getString("address2"));

                // PersonVO 멤버변수로는 있지만, 생성자에선 id와 regDate, modifyDate를 받지 않았기 때문에
                // 따로 setter를 사용해 저장.
                vo.setPerson_id(rs.getInt("id"));
                vo.setRegDate(rs.getTimestamp("regDate"));
                vo.setModifyDate(rs.getTimestamp("modifyDate"));

                // PersonVO 타입의 vo 객체를 list에 추가(add)
                list.add(vo);
            }

            // person 테이블의 데이터를 모두 담은 PersonVO 타입의 list를 받아 출력.
            for (PersonVO p : list) {
                System.out.println(p);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.PersonVO;

public class DBSelectTest3 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";

        List<PersonVO> list = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            String sql = "select * from person where id >= ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 0);

            ResultSet rs = pstmt.executeQuery();

            // ResultSet에 들어간 쿼리 결과 처리
            while (rs.next()) {
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

                vo.setPerson_id(rs.getInt("id"));
                vo.setRegDate(rs.getTimestamp("regDate"));
                vo.setModifyDate(rs.getTimestamp("modifyDate"));

                list.add(vo);
            }

            for (PersonVO p : list) {
                System.out.println(p);
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}

package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.PersonVO2;

public class DBSelectTest4 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";

        List<PersonVO2> list = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            String sql = "select * from person where id >= ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 0);

            ResultSet rs = pstmt.executeQuery();

            // ResultSet에 들어간 쿼리 결과 처리
            while (rs.next()) {
                list.add(new PersonVO2.Builder()
                        .id(rs.getInt("id"))
                        .userId(rs.getString("userId"))
                        .userPw(rs.getString("userPw"))
                        .userName(rs.getString("userName"))
                        .userEmail(rs.getString("userEmail"))
                        .phone1(rs.getString("phone1"))
                        .phone2(rs.getString("phone2"))
                        .age(rs.getByte("age"))
                        .address1(rs.getString("address1"))
                        .address2(rs.getString("address2"))
                        .regDate(rs.getTimestamp("regDate"))
                        .modifyDate(rs.getTimestamp("modifyDate")).build());
            }

            for (PersonVO2 p : list) {
                System.out.println(p);
            }

        } catch (Exception e) {
            System.out.println("DB 작업 실패");
        }

    }
}

package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dbutil.DButil;
import domain.users.UserVO;

public class UsersDAOImpl implements Users {

    // 멤버 변수 선언
    // private String url = "jdbc:mysql://localhost:3306/jdbc";
    // private String sql_user = "jdbcuser";
    // private String password = "jdbcuser";

    @Override
    public int userAdd(UserVO user) {
        // sql insert 작업

        int result = 0;

        try (Connection conn = DButil.getConnection()) {
            String sql = "insert into person(userId, userPw, userName, userEmail, " +
                    "phone1, phone2, age, address1, address2) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, user.getPerson_userId());
            pstmt.setString(2, user.getPerson_userPw());
            pstmt.setString(3, user.getPerson_userName());
            pstmt.setString(4, user.getPerson_userEmail());
            pstmt.setString(5, user.getPerson_phone1());
            pstmt.setString(6, user.getPerson_phone2());
            pstmt.setInt(7, user.getPerson_age());
            pstmt.setString(8, user.getPerson_address1());
            pstmt.setString(9, user.getPerson_address2());

            if(pstmt.executeUpdate() != 0) {

                ResultSet rs = pstmt.getGeneratedKeys();
                if(rs.next()) {
                    long generatedId = rs.getLong(1);
                    user.setPerson_id(generatedId);
                }

                result = 2; // 임시
                System.out.println("삽입 성공");
            }

        } catch (SQLException e) {
            System.out.println("DB 작업 실패");
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public List<UserVO> userAll() {
        // sql select 전체
        List<UserVO> list = new ArrayList<>();

        try (Connection conn = DButil.getConnection()) {
            // SQL
            String sql = "select * from person";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(UserVO.builder()
                        .person_id(rs.getLong("id"))
                        .person_userId(rs.getString("userId"))
                        .person_userPw(rs.getString("userPw"))
                        .person_userName(rs.getString("userName"))
                        .person_userEmail(rs.getString("userEmail"))
                        .person_phone1(rs.getString("phone1"))
                        .person_phone2(rs.getString("phone2"))
                        .person_age(rs.getInt("age"))
                        .person_address1(rs.getString("address1"))
                        .person_address2(rs.getString("address2"))
                        .regDate(rs.getTimestamp("regDate"))
                        .modifyDate(rs.getTimestamp("modifyDate"))
                        .build());
            }

            // for (UserVO vo : list)
            // System.out.println(vo);

        } catch (SQLException e) {
            System.out.println("DB 작업 실패");
            System.out.println(e.getMessage());
        }

        return list;
    }

    @Override
    public int userDel(UserVO user) {
        // sql delete
        int result = 0;
        try (Connection conn = DButil.getConnection()) {
            String sql = "delete from person where userId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getPerson_userId());
            result = pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("DB 작업 실패");
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public int userMod(UserVO user) {

        int result = 0;

        // sql update
        try (Connection conn = DButil.getConnection()) {

            String sql = "update person set userId=?, userPw=?, userName=?, userEmail=?"
                    + ", phone1=?, phone2=?, age=?, address1=?, address2=?, modifyDate=? where id=?";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, user.getPerson_userId());
            pstmt.setString(2, user.getPerson_userPw());
            pstmt.setString(3, user.getPerson_userName());
            pstmt.setString(4, user.getPerson_userEmail());
            pstmt.setString(5, user.getPerson_phone1());
            pstmt.setString(6, user.getPerson_phone2());
            pstmt.setInt(7, user.getPerson_age());
            pstmt.setString(8, user.getPerson_address1());
            pstmt.setString(9, user.getPerson_address2());
            pstmt.setTimestamp(10, new Timestamp(System.currentTimeMillis())); // 수정 시간
            pstmt.setLong(11, user.getPerson_id()); // where절 ? 값

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("DB 작업 실패");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public List<UserVO> userSearch(String userId, String userName) {
        // sql select 조건(where)
        List<UserVO> list = new ArrayList<>();
        try (Connection conn = DButil.getConnection()) {
            String sql = "select * from person where userId = ? and userName = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, userName);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(UserVO.builder()
                        .person_id(rs.getLong("id"))
                        .person_userId(rs.getString("userId"))
                        .person_userPw(rs.getString("userPw"))
                        .person_userName(rs.getString("userName"))
                        .person_userEmail(rs.getString("userEmail"))
                        .person_phone1(rs.getString("phone1"))
                        .person_phone2(rs.getString("phone2"))
                        .person_age(rs.getInt("age"))
                        .person_address1(rs.getString("address1"))
                        .person_address2(rs.getString("address2"))
                        .regDate(rs.getTimestamp("regDate"))
                        .modifyDate(rs.getTimestamp("modifyDate"))
                        .build());
            }

            // for(UserVO vo : list)
            // System.out.println(vo);

        } catch (Exception e) {
            System.out.println("DB 작업 실패");
            System.out.println(e.getMessage());
        }

        return list;
    }

        @Override
    public Optional<UserVO> userSearch(String userEmail) {
        // sql select, where email
        Optional<UserVO> result = null;

        try (Connection conn = DButil.getConnection()) {

            // SQL
            String sql = "select * from person where userEmail=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userEmail);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                result = Optional.of(UserVO.builder()
                        .person_id(rs.getLong("id"))
                        .person_userId(rs.getString("userId"))
                        .person_userPw(rs.getString("userPw"))
                        .person_userName(rs.getString("userName"))
                        .person_userEmail(rs.getString("userEmail"))
                        .person_phone1(rs.getString("phone1"))
                        .person_phone2(rs.getString("phone2"))
                        .person_age(rs.getByte("age"))
                        .person_address1(rs.getString("address1"))
                        .person_address2(rs.getString("address2"))
                        .regDate(rs.getTimestamp("regDate"))
                        .modifyDate(rs.getTimestamp("modifyDate"))
                        .build());
            }

        } catch (SQLException e) {
            System.out.println("DB 작업 실패!!");
            System.out.println(e.getMessage());
        }

        return result;
    }

}

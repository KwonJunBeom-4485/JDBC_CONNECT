package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dbutil.DButil;
import domain.orders.OrderVO;

public class OrdersDAOImplMariadb implements Orders {
    @Override
    public boolean orderAdd(OrderVO order) {
        // 주문 추가
        // 1. 주문 정보(order)
        // 2. connection 을 통한 SQL을 실행.
        // -- 1) connection 정보는 DButil.getConnection()
        // -- 2) SQL 생성
        // -- 3) PreparedStatement 객체 생성
        // -- 4) SQL 완성
        // -- 5) 결과 확인

        boolean result = false;
        try (Connection conn = DButil.getConnection(DButil.MARIADB)) {
            String sql = "insert into orders(orderList, orderNum, price, userId) "
                    + "values(?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, order.getOrder_orderList());
            pstmt.setInt(2, order.getOrder_orderNum());
            pstmt.setInt(3, order.getOrder_price());
            pstmt.setString(4, order.getOrder_userId());

            if (pstmt.executeUpdate() != 0) {

                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    long generatedId = rs.getLong(1);
                    order.setOrder_id(generatedId);
                }

                result = true;
                System.out.println("삽입 성공");
            }

        } catch (Exception e) {
            System.out.println("DB 작업 실패 : " + e.getMessage());
        }

        return result;
    }

    @Override
    public boolean orderMod(OrderVO order) {

        boolean result = false;

        try (Connection conn = DButil.getConnection(DButil.MARIADB)) {
            String sql = "update orders set orderList=?, orderNum=?, price=?, date=? "
                    + "where id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, order.getOrder_orderList());
            pstmt.setInt(2, order.getOrder_orderNum());
            pstmt.setInt(3, order.getOrder_price());
            pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            pstmt.setLong(5, order.getOrder_id()); // id를 바꾸진 못하니 그냥 기존꺼 불러다 써

            if (pstmt.executeUpdate() != 0) {
                result = true;
                System.out.println("수정 성공");
            }

        } catch (Exception e) {
            System.out.println("DB 작업 실패 : " + e.getMessage());
        }

        return result;
    }

    @Override
    public List<OrderVO> orderAll() {

        List<OrderVO> list = new ArrayList<>();

        try (Connection conn = DButil.getConnection(DButil.MARIADB)) {
            String sql = "select * from orders";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(
                        OrderVO.builder()
                                .order_id(rs.getLong("id"))
                                .order_orderList(rs.getString("orderList"))
                                .order_orderNum(rs.getInt("orderNum"))
                                .order_price(rs.getInt("price"))
                                .order_date(rs.getTimestamp("date"))
                                .order_userId(rs.getString("userId"))
                                .build());
            }

        } catch (Exception e) {
            System.out.println("DB 작업 실패" + e.getMessage());
        }

        return list;
    }

    @Override
    public List<OrderVO> orderSearch(String userId) {

        List<OrderVO> list = new ArrayList<>();

        try (Connection conn = DButil.getConnection(DButil.MARIADB)) {
            String sql = "select * from orders where userId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(
                        OrderVO.builder()
                                .order_id(rs.getLong("id"))
                                .order_orderList(rs.getString("orderList"))
                                .order_orderNum(rs.getInt("orderNum"))
                                .order_price(rs.getInt("price"))
                                .order_date(rs.getTimestamp("date"))
                                .order_userId(rs.getString("userId"))
                                .build());
            }

        } catch (Exception e) {
            System.out.println("DB 작업 실패 : " + e.getMessage());
        }

        return list;
    }

    @Override
    public List<OrderVO> orderSearch(Timestamp date) {
        List<OrderVO> list = new ArrayList<>();

        try (Connection conn = DButil.getConnection(DButil.MARIADB)) {
            String sql = "select * from orders where date = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setTimestamp(1, date);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(
                        OrderVO.builder()
                                .order_id(rs.getLong("id"))
                                .order_orderList(rs.getString("orderList"))
                                .order_orderNum(rs.getInt("orderNum"))
                                .order_price(rs.getInt("price"))
                                .order_date(rs.getTimestamp("date"))
                                .order_userId(rs.getString("userId"))
                                .build());
            }

        } catch (Exception e) {
            System.out.println("DB 작업 실패 : " + e.getMessage());
        }

        return list;
    }

    @Override
    public Optional<OrderVO> orderSearch(int orderNum) {
        // 주문 번호를 통한 검색
        Optional<OrderVO> order = null;

        try (Connection conn = DButil.getConnection(DButil.MARIADB)) {
            String sql = "select * from orders where orderNum = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderNum);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                order = Optional.of(
                        OrderVO.builder()
                                .order_id(rs.getLong("id"))
                                .order_orderList(rs.getString("orderList"))
                                .order_orderNum(rs.getInt("orderNum"))
                                .order_price(rs.getInt("price"))
                                .order_date(rs.getTimestamp("date"))
                                .order_userId(rs.getString("userId"))
                                .build());
            }

        } catch (Exception e) {
            System.out.println("DB 연동 실패 : " + e.getMessage());
        }

        return order;
    }

    @Override
    public boolean orderDel(long id) {
        boolean result = false;

        try (Connection conn = DButil.getConnection(DButil.MARIADB)) {
            String sql = "delete from orders where id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            if (pstmt.executeUpdate() != 0) {
                result = true;
                System.out.println("삭제 성공");
            }

        } catch (Exception e) {
            System.out.println("DB 작업 실패 : " + e.getMessage());
        }

        return result;
    }
}

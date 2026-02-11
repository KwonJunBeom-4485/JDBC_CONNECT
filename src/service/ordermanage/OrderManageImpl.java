package service.ordermanage;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import domain.orders.OrderVO;
import domain.users.UserVO;
import dto.OrderDTO;
import dto.UserDTO;
import repository.Orders;
import repository.Users;
import repository.OrdersDAOImpl;
import repository.OrdersDAOImplMariadb;
import repository.OrdersDAOImplOracle;
import repository.UserDAOImplOracle;
import repository.UsersDAOImpl;
import repository.UsersDAOImplMariadb;

public class OrderManageImpl implements OrderManage {

    // Users userRepository = new UsersDAOImpl();
    // Orders orderRepository = new OrdersDAOImpl();

    // 오라클 버전
    // Users userRepository = new UserDAOImplOracle();
    // Orders orderRepository = new OrdersDAOImplOracle();

    // MariaDB 버전
    Users userRepository = new UsersDAOImplMariadb();
    Orders orderRepository = new OrdersDAOImplMariadb();

    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public boolean createOrder(OrderDTO order, UserDTO dto) {
        // 주문 생성
        OrderVO newOrder = OrderVO.builder()
                .order_orderList(order.getOrderList())
                .order_orderNum(order.getOrderNum())
                .order_price(order.getPrice())
                .order_userId(dto.getUserId())
                .build();

        return orderRepository.orderAdd(newOrder);
    }

    @Override
    public boolean deleteOrder(OrderDTO order, UserDTO dto) {
        // 삭제 작업은 Orders 테이블의 id로 삭제를 진행..
        // 사용자 확인 작업..

        Optional<UserVO> userInfo = userRepository.userSearch(dto.getUserEmail());
        if (userInfo.isPresent()) {

            // dto.getUserPw()는 삭제를 위해 입력한 패스워드를 저장.
            // userInfo.get().getUserPw()는 DB에 있는 사용자의 패스워드
            if (dto.getUserPw().equals(userInfo.get().getPerson_userPw())) {
                return orderRepository.orderDel(order.getId());
            } else {
                return false;
            }
        } else {
            return false; // 등록된 사용자가 없는 경우
        }
    }

    @Override
    public List<OrderDTO> findAll() {
        // 관리자 입장에서 모든 주문 내역을 가져오기
        List<OrderVO> ordersVOList = orderRepository.orderAll();
        List<OrderDTO> ordersList = new ArrayList<>();

        for (OrderVO vo : ordersVOList) {
            ordersList.add(OrderDTO.builder()
                    .id(vo.getOrder_id())
                    .orderList(vo.getOrder_orderList())
                    .orderNum(vo.getOrder_orderNum())
                    .price(vo.getOrder_price())
                    .date(sf.format(vo.getOrder_date()))
                    .userId(vo.getOrder_userId())
                    .build());
        }

        return ordersList;
    }

    @Override
    public List<OrderDTO> findDate(String dateString) {
        // 관리자 입장에서 날짜를 통한 주문 내역
        List<OrderVO> orderVOList = orderRepository.orderSearch(Timestamp.valueOf(dateString));
        List<OrderDTO> orderList = new ArrayList<>();

        for (OrderVO vo : orderVOList) {
            orderList.add(OrderDTO.builder()
                    .id(vo.getOrder_id())
                    .orderList(vo.getOrder_orderList())
                    .orderNum(vo.getOrder_orderNum())
                    .price(vo.getOrder_price())
                    .date(sf.format(vo.getOrder_date()))
                    .userId(vo.getOrder_userId())
                    .build());
        }

        return orderList;
    }

    @Override
    public List<OrderDTO> findList(UserDTO dto) {
        // 사용자가 주문한 주문 리스트 출력
        // 1. 사용자 정보 : userId를 불러서,
        // 2. orderRepository.orderSearch(userId)
        List<OrderVO> ordersVOList = orderRepository.orderSearch(dto.getUserId());
        List<OrderDTO> ordersList = new ArrayList<>();

        for (OrderVO vo : ordersVOList) {
            ordersList.add(OrderDTO.builder()
                    .id(vo.getOrder_id())
                    .orderList(vo.getOrder_orderList())
                    .orderNum(vo.getOrder_orderNum())
                    .price(vo.getOrder_price())
                    .date(sf.format(vo.getOrder_date()))
                    .userId(vo.getOrder_userId())
                    .build());
        }

        return ordersList;
    }

    @Override
    public List<OrderDTO> findUserId(String userId) {
        // 특정 사용자의 주문 내역
        List<OrderVO> orderVOList = orderRepository.orderSearch(userId);
        List<OrderDTO> orderList = new ArrayList<>();

        for (OrderVO vo : orderVOList) {
            orderList.add(OrderDTO.builder()
                    .id(vo.getOrder_id())
                    .orderList(vo.getOrder_orderList())
                    .orderNum(vo.getOrder_orderNum())
                    .price(vo.getOrder_price())
                    .date(sf.format(vo.getOrder_date()))
                    .userId(vo.getOrder_userId())
                    .build());
        }

        return orderList;
    }

    @Override
    public boolean modifyOrder(OrderDTO order, UserDTO dto) {
        // 회원 정보와 order 정보를 통한 수정 처리...

        Optional<UserVO> user = userRepository.userSearch(dto.getUserEmail());
        if (user.isPresent()) {
            // OrderDTO -> OrderVO
            OrderVO orderVO = OrderVO.builder()
                    .order_id(order.getId())
                    .order_orderList(order.getOrderList())
                    .order_orderNum(order.getOrderNum())
                    .order_price(order.getPrice())
                    .order_userId(order.getUserId())
                    .build();

            return orderRepository.orderMod(orderVO);
        }

        return false;
    }

}

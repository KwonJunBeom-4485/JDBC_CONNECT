package repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import domain.orders.OrderVO;

public interface Orders {
    // 주문 추가
    boolean orderAdd(OrderVO order);

    // 주문 삭제
    boolean orderDel(long order);

    // 주문 수정
    boolean orderMod(OrderVO order);

    // 주문 정보 출력
    // 1. 전체 주문 출력
    List<OrderVO> orderAll();

    // 2. 부분 주문 출력(사용자 Id)
    List<OrderVO> orderSearch(String userId);

    // 3. 날짜를 이용한 방법
    List<OrderVO> orderSearch(Timestamp date);

    // 4. 주문 번호를 이용하는 방법
    Optional<OrderVO> orderSearch(int orderNum);
}

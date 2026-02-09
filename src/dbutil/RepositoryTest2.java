package dbutil;

import java.sql.Timestamp;
import java.util.List;

import domain.orders.OrderVO;
import repository.Orders;
import repository.OrdersDAOImpl;

public class RepositoryTest2 {
    private static Orders repository = new OrdersDAOImpl();

    public static void main(String[] args) {
        OrderVO order = OrderVO.builder()
        .order_orderList("볶음밥")
        .order_orderNum(11)
        .order_price(31000)
        .order_userId("testorder1")
        .build();

        orderAdd(order);
        OrderVO searchResult = orderSearch(order.getOrder_orderNum());
        System.out.println(searchResult);

        order.setOrder_orderList("짜장볶음밥");
        orderMod(order);

        searchResult = orderSearch(order.getOrder_orderNum());
        System.out.println(searchResult);

        System.out.println(orderAll());

        orderDelete(order.getOrder_id());

        System.out.println(orderAll());
        System.out.println();

        System.out.println(orderSearch("2"));
        System.out.println();

        System.out.println(orderSearch(Timestamp.valueOf("2026-02-09 14:33:14")));
        System.out.println();

        System.out.println(orderSearch(9900));
        System.out.println();

        
    }

    public static boolean orderAdd(OrderVO order) {
        return repository.orderAdd(order);
    }

    public static boolean orderMod(OrderVO order) {
        return repository.orderMod(order);
    }

    public static OrderVO orderSearch(int orderNum) {
        return repository.orderSearch(orderNum).get();
    }

    public static List<OrderVO> orderAll() {
        return repository.orderAll();
    }

    public static List<OrderVO> orderSearch(Timestamp time) {
        return repository.orderSearch(time);
    }

    public static List<OrderVO> orderSearch(String userId) {
        return repository.orderSearch(userId);
    }

    public static boolean orderDelete(long id) {
        return repository.orderDel(id);
    }
}

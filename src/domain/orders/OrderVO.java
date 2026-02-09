package domain.orders;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderVO {

    private long order_id;
    private String order_orderList;
    private int order_orderNum;
    private int order_price;
    private Timestamp order_date;
    private String order_userId;
}

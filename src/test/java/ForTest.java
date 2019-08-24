import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * todo
 *
 * @author leiyang
 * @since 2019/8/7 15:32
 */
public class ForTest {

    @Test
    public void testFor() {
        String orderId1 = "2321837281372913";
        String userId1 = "20180701001";
        String orderId2 = "2321837281372914";
        String userId2 = "20180701002";
        String orderId3 = "2321837281372912";
        String userId3 = "20180701003";
        String orderId4 = "2321837281372918";
        String userId4 = "20180701005";
        String orderId5 = "2321837281372918";
        String userId5 = "20180701004";

        Order order = new Order();
        order.setUserId(userId1);
        order.setOrderId(orderId1);
        Order order1 = new Order();
        order1.setOrderId(orderId2);
        order1.setUserId(userId2);

        Order order2 = new Order();
        order2.setOrderId(orderId3);
        order2.setUserId(userId3);
        Order order3 = new Order();
        order3.setOrderId(orderId4);
        order3.setUserId(userId4);
        Order order4 = new Order();
        order4.setOrderId(orderId5);
        order4.setUserId(userId5);
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(order);
        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
        orderList.add(order4);

//1.jdk8 lambda排序，带参数类型
//        orderList.sort(( Order ord1, Order ord2) -> ord2.getOrderId().compareTo(ord1.getOrderId()));

//2.jdk8 lambda排序，不带参数类型
//        orderList.sort(( ord1, ord2) -> ord2.getOrderId().compareTo(ord1.getOrderId()));

//3.jdk8 升序排序，Comparator提供的静态方法
        Collections.sort(orderList, Comparator.comparing(Order::getOrderId));

//4.jdk8 降序排序，Comparator提供的静态方法
//        Collections.sort(orderList, Comparator.comparing(Order::getOrderId).reversed());

//5.jdk8 组合排序，Comparator提供的静态方法，先按orderId排序，orderId相同的按userId排序
//        Collections.sort(orderList, Comparator.comparing(Order::getOrderId).reversed().thenComparing(Order::getUserId));

        orderList.stream().forEach(str -> System.out.println(str.getOrderId()+"/" + str.getUserId()));
    }
}

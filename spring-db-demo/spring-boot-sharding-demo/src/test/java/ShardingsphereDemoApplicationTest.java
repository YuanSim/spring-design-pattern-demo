import com.yuansim.ShardingAppStart;
import com.yuansim.entity.Orders;
import com.yuansim.mapper.OrdersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = ShardingAppStart.class)
public class ShardingsphereDemoApplicationTest {

    @Autowired
    private OrdersMapper ordersMapper;

    @Test
    public void addOrders(){
        for (int i = 12; i <=100 ; i++) {
            Orders orders = new Orders();
            orders.setId(i);
            orders.setCustomerId(i);
            orders.setOrderType(i);
            orders.setAmount(1000.0*i);
            ordersMapper.insert(orders);
        }
    }
    @Test
    public void queryOrders(){
        Orders orders = ordersMapper.selectOne(1);
        System.out.println(orders);
    }

    @Test
    public void queryOrdersByDB(){
        Orders params = new Orders();
        Orders orders = ordersMapper.selectOneDB(params);
        System.out.println(orders);
    }

    /**
     * 读写分离， 从库负载均衡  round_robin
     */
    @Test
    public void queryOrdersByPage(){

        List<Orders> orders0 = ordersMapper.selectByPage(1, 3);
        System.out.println(orders0);

        List<Orders> orders1 = ordersMapper.selectByPage(1, 3);
        System.out.println(orders1);
    }
}

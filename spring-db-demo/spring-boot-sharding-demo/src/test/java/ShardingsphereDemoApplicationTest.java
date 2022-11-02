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

    @Test
    public void queryOrdersByPage(){
        List<Orders> orders = ordersMapper.selectByPage(1, 10);
        System.out.println(orders);
    }
}

package com.yuansim.mapper;

import com.yuansim.entity.Orders;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrdersMapper {

    @Insert("insert into orders(id,order_type,customer_id,amount) values(#{id},#{orderType},#{customerId},#{amount})")
    public void insert(Orders orders);

    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "orderType",column = "order_type"),
            @Result(property = "customerId",column = "customer_id"),
            @Result(property = "amount",column = "amount")
    })
    public Orders selectOne(Integer id);

    @Select("select * from orders where id = #{id} and customer_id=#{customerId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "orderType",column = "order_type"),
            @Result(property = "customerId",column = "customer_id"),
            @Result(property = "amount",column = "amount")
    })
    public Orders selectOneDB(Orders orders);



    @Select("select * from orders order by id limit #{page}, #{pageSize} ")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "orderType",column = "order_type"),
            @Result(property = "customerId",column = "customer_id"),
            @Result(property = "amount",column = "amount")
    })
    public List<Orders> selectByPage(Integer page,Integer pageSize);
}

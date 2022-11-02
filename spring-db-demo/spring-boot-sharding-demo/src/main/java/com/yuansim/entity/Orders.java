package com.yuansim.entity;


import java.util.ArrayList;
import java.util.List;

public class Orders {

    private Integer id;

    private Integer orderType;

    private Integer customerId;

    private Double amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", orderType=" + orderType +
                ", customerId=" + customerId +
                ", amount=" + amount +
                '}';
    }

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.notify();
    }
}

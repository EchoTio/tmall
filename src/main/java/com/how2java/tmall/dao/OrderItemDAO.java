package com.how2java.tmall.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.pojo.OrderItem;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.User;

public interface OrderItemDAO extends JpaRepository<OrderItem,Integer>{
    //通过订单查询
    List<OrderItem> findByOrderOrderByIdDesc(Order order);
    //根据产品获取OrderItem的方法
    List<OrderItem> findByProduct(Product product);
    List<OrderItem> findByUserAndOrderIsNull(User user);
}

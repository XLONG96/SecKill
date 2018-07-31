package com.xlong.seckill.repository;

import com.xlong.seckill.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order saveAndFlush(Order order);
}

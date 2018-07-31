package com.xlong.seckill.repository;

import com.xlong.seckill.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    Item findById(int id);

    Item saveAndFlush(Item item);
}

package com.xlong.seckill.SKProcessor;

import com.xlong.seckill.entity.Item;
import com.xlong.seckill.entity.Order;
import com.xlong.seckill.entity.User;
import com.xlong.seckill.repository.ItemRepository;
import com.xlong.seckill.repository.OrderRepository;
import com.xlong.seckill.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;



public class DefaultMsgProcessor implements MessageProcessor {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public synchronized void handleMessage(String msg) {
        // 解析消息得到用户id和商品id
        int userId = 0;
        int itemId = 0;

        try {
            userId = Integer.parseInt(msg.substring(0, msg.indexOf(":")));
            itemId = Integer.parseInt(msg.substring(msg.indexOf(":") + 1));
        } catch (Exception e) {
            e.printStackTrace();
            return ;
        }

        if (userId == 0 || itemId == 0) {
            return ;
        }

        User user = userRepository.findById(userId);
        Item item = itemRepository.findById(itemId);

        System.out.println(user);
        System.out.println(item);

        // 检查商品库存，如果库存不足，则直接返回
        if (item.getStore() == 0) {
            return ;
        }

        // 生成订单，商品库存减1，写回数据库
        Order order = new Order();
        order.setItemId(itemId);
        order.setUserId(userId);
        order.setItemname(item.getItemname());
        order.setUsername(user.getUsername());
        order.setPrice(item.getPrice());

        System.out.println(order);

        item.setStore(item.getStore() - 1);

        itemRepository.saveAndFlush(item);
        orderRepository.saveAndFlush(order);
    }
}

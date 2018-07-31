package com.xlong.seckill.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "item_order")
@Entity
public class Order implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    private int userId;

    private int itemId;

    private String username;

    private String itemname;

    private int price;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", itemId=" + itemId +
                ", username='" + username + '\'' +
                ", itemname='" + itemname + '\'' +
                ", price=" + price +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

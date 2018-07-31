package com.xlong.seckill.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "item")
@Entity
public class Item implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    private String itemname;

    private int store;

    private int price;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemname='" + itemname + '\'' +
                ", store=" + store +
                ", price=" + price +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

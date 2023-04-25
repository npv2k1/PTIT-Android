package com.example.nguyenapp.model;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Item {
    // TODO: Kiểm tra các biến và thuộc tính của Item
    private static int id = 0;
    private String title;
    private String content;
    private int price;
    private int image;

    public Item() {
    }

    // TODO: Tạo hàm khởi tạo và tăng static id
    public Item(String title, String content, int image) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.id++;
    }

    public Item(String title, String content) {
        this.title = title;
        this.content = content;
        this.id++;
    }

    public Item(int id, String title, String content, int price, int image) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.image = image;
        this.id=id;
    }


    // TODO: Tạo các hàm getter và setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Item.id = id;
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", price=" + price +
                ", image=" + image +
                '}';
    }
}

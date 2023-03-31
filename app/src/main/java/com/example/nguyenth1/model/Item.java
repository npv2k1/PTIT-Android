package com.example.nguyenth1.model;

public class Item {
    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Item.id = id;
    }

    private static int id = 0;
    private String title;
    private String content;
    private int price;
    private int image;

    public Item() {
    }

    public Item(String title) {
        this.title = title;
    }

    public Item(String title, String content, int image) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.id++;
    }

    public Item(String title, String content) {
        this.title = title;
        this.content = content;
    }

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
}

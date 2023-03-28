package com.example.nguyenth1.model;

public class ItemImage {
    private static int id;
    private int image;

    public ItemImage() {

    }

    public ItemImage(int image) {
        this.image = image;
        this.id++;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        ItemImage.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

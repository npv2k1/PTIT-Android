package com.example.nguyenth1.model;

public class SpnItem {
    private static int id;
    private int image;

    public SpnItem() {

    }

    public SpnItem(int image) {
        this.image = image;
        this.id++;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        SpnItem.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

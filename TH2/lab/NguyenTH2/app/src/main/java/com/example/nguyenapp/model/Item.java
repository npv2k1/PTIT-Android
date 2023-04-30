package com.example.nguyenapp.model;

public class Item {
    // TODO: Kiểm tra các biến và thuộc tính của Item
    private int id = 0;
    private String address;
    private String content;
    private int price;
    private int image;

    private int area;

    private int maxPeople;

    private boolean wifiService;
    private boolean dieuHoaService;
    private boolean mayGiatService;

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public Item(int id, String address, String content, int price, int image, int maxPeople, boolean wifiService, boolean dieuHoaService, boolean mayGiatService) {
        this.id = id;
        this.address = address;
        this.content = content;
        this.price = price;
        this.image = image;
        this.maxPeople = maxPeople;
        this.wifiService = wifiService;
        this.dieuHoaService = dieuHoaService;
        this.mayGiatService = mayGiatService;
    }

    public Item() {
    }

    // TODO: Tạo hàm khởi tạo và tăng static id
    public Item(String address, String content, int image) {
        this.address = address;
        this.content = content;
        this.image = image;
        this.id++;
    }

    public Item(String address, String content) {
        this.address = address;
        this.content = content;
        this.id++;
    }

    public Item(int id, String address, String content, int price, int image) {
        this.address = address;
        this.content = content;
        this.price = price;
        this.image = image;
        this.id=id;
    }

    public Item(int id, String address, String content, int price, int image, boolean wifiService, boolean dieuHoaService, boolean mayGiatService) {
        this.id = id;
        this.address = address;
        this.content = content;
        this.price = price;
        this.image = image;
        this.wifiService = wifiService;
        this.dieuHoaService = dieuHoaService;
        this.mayGiatService = mayGiatService;
    }

    // TODO: Tạo các hàm getter và setter
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isWifiService() {
        return wifiService;
    }

    public void setWifiService(boolean wifiService) {
        this.wifiService = wifiService;
    }

    public boolean isDieuHoaService() {
        return dieuHoaService;
    }

    public void setDieuHoaService(boolean dieuHoaService) {
        this.dieuHoaService = dieuHoaService;
    }

    public boolean isMayGiatService() {
        return mayGiatService;
    }

    public void setMayGiatService(boolean mayGiatService) {
        this.mayGiatService = mayGiatService;
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + address + '\'' +
                ", content='" + content + '\'' +
                ", price=" + price +
                ", image=" + image +
                '}';
    }
}

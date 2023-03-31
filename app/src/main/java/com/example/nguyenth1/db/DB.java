package com.example.nguyenth1.db;

import com.example.nguyenth1.model.Item;

import java.util.ArrayList;
import java.util.List;


public class DB {
    private static List<Item> items = new ArrayList<>();

    // CRUD
    public static void addItem(Item item) {
        items.add(item);
    }

    public static void removeItem(Item item) {
        items.remove(item);
    }

//    public static void updateItem(Item item) {
//        for (int i = 0; i < items.size(); i++) {
//            if (items.get(i).getTitle().equals(item.getTitle())) {
//                items.set(i, item);
//            }
//        }
//    }

    public static List<Item> getItems() {
        return items;
    }

    public static Item getItem(String title) {
        for (Item item : items) {
            if (item.getTitle().equals(title)) {
                return item;
            }
        }
        return null;
    }

    public static void clear() {
        items.clear();
    }

    // Search

    public static List<Item> search(String keyword) {
        List<Item> result = new ArrayList<>();
        for (Item item : items) {
            if (item.getTitle().contains(keyword)) {
                result.add(item);
            }
        }
        return result;
    }

    public static boolean updateItem(Item item) {
        // find item by id and update
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == item.getId()) {
                items.set(i, item);
                return true;
            }
        }
        return false;
    }


}

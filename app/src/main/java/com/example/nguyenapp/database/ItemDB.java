package com.example.nguyenapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.nguyenapp.model.Item;

import java.util.ArrayList;
import java.util.List;

// ItemDB SQLiteOpenHelper
public class ItemDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MyDatabase.db";

    private static final String SQL_CREATE_ITEMS_TABLE =
            "CREATE TABLE items (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "title TEXT," +
                    "content TEXT," +
                    "price INTEGER," +
                    "image INTEGER)";

    private static final String SQL_DELETE_ITEMS_TABLE =
            "DROP TABLE IF EXISTS " + "items";

    public ItemDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ITEMS_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ITEMS_TABLE);
        onCreate(db);
    }

    public void add(Item item) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", item.getTitle());
        values.put("content", item.getContent());
        values.put("price", item.getPrice());
        values.put("image", item.getImage());

        long newRowId = db.insert("items", null, values);

        db.close();
    }

    public int remove(Item item) {
        SQLiteDatabase db = getWritableDatabase();

        int rowsAffected = db.delete("items", "id = ?", new String[]{String.valueOf(item.getId())});

        db.close();

        return rowsAffected;
    }

    public void update(Item item) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", item.getTitle());
        values.put("content", item.getContent());

        values.put("price", item.getPrice());
        values.put("image", item.getImage());

        db.update("items", values, "id = ?", new String[]{String.valueOf(item.getId())});



        db.close();
    }

    public void clear() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(SQL_DELETE_ITEMS_TABLE);
        db.execSQL(SQL_CREATE_ITEMS_TABLE);
        db.close();
    }

    public void close() {
        SQLiteDatabase db = getWritableDatabase();
        db.close();
    }

    // search item by title
    public List<Item> search(String title) {
        // find item
        List<Item> items = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM items WHERE title LIKE ?", new String[]{"%" + title + "%"});
        // map data to item
        while (cursor.moveToNext()) {
            String title1 = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));
            int price = cursor.getInt(cursor.getColumnIndexOrThrow("price"));
            int image = cursor.getInt(cursor.getColumnIndexOrThrow("image"));
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            Item item = new Item(id,title1, content, price, image);
            items.add(item);
        }
        cursor.close();
        db.close();
        return items;

    }



    public List<Item> getItems() {
        // find list item
        List<Item> items = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM items", null);
        // map data to item
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));
            int price = cursor.getInt(cursor.getColumnIndexOrThrow("price"));
            int image = cursor.getInt(cursor.getColumnIndexOrThrow("image"));
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            Item item = new Item(id,title, content, price, image);
            items.add(item);
        }
        cursor.close();
        db.close();
        return items;
    }
    public Item getItem(int id) {
        // find item
        Item item = null;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM items WHERE id = ?", new String[]{String.valueOf(id)});
        // map data to item
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));
            int price = cursor.getInt(cursor.getColumnIndexOrThrow("price"));
            int image = cursor.getInt(cursor.getColumnIndexOrThrow("image"));
            item = new Item(id,title, content, price, image);
        }
        cursor.close();
        db.close();
        return item;
    }

}

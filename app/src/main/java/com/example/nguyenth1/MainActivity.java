package com.example.nguyenth1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;

import com.example.nguyenth1.model.Item;
import com.example.nguyenth1.model.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView rvItem;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hide action bar
        getSupportActionBar().hide();

        // init view
        initView();
    }

    private void initView() {
        // mapping
        rvItem = findViewById(R.id.rvItem);

        itemAdapter = new ItemAdapter(this);

        List<Item> items = new ArrayList<>();
        items.add(new Item("Item sdcsd"));
        items.add(new Item("Item 2"));
        items.add(new Item("Item 1"));
        items.add(new Item("Item cc"));
        items.add(new Item("Item 1"));
        items.add(new Item("Item 2"));
        items.add(new Item("Item 1"));
        items.add(new Item("Item 2"));
        items.add(new Item("Item 1"));
        items.add(new Item("Item 2"));

        createAdapter(itemAdapter, items);
    }

    private void createAdapter (ItemAdapter adapter, List<Item> items) {
        adapter.setItems(items);
        rvItem.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvItem.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
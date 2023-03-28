package com.example.nguyenth1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.nguyenth1.adapters.SpinerImageAdapter;
import com.example.nguyenth1.db.DB;
import com.example.nguyenth1.model.Item;
import com.example.nguyenth1.model.ItemAdapter;
import com.example.nguyenth1.model.ItemImage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Search
    private EditText etSearch;
    private ImageButton btnSearch;

    private RecyclerView rvItem;
    private ItemAdapter itemAdapter;
    private EditText etTitle, etContent, etPrice;
    private Button btnAdd;
    private Spinner sImage;

    private ArrayList<ItemImage> listItemImage = new ArrayList<>();

    // handle event


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listItemImage.add(new ItemImage(R.drawable.ic_launcher_background));
        listItemImage.add(new ItemImage(R.drawable.ic_baseline_phone_iphone_24));
        listItemImage.add(new ItemImage(R.drawable.ic_baseline_tablet_android_24));


        // hide action bar
        getSupportActionBar().hide();

        // init view
        initView();
        handleEvent();
    }

    private void initView() {
        // mapping
        rvItem = findViewById(R.id.rvItem);
        btnAdd = findViewById(R.id.btnAdd);
        etTitle = findViewById(R.id.etTitle);
        etPrice = findViewById(R.id.etPrice);
        etContent = findViewById(R.id.etContent);
        sImage = findViewById(R.id.sImage);

        sImage.setAdapter(new SpinerImageAdapter(this, listItemImage));


        itemAdapter = new ItemAdapter(this);
        itemAdapter.setHolderClickListener((view) -> {
            Item item = (Item) view.getTag();
            System.out.println("item: " + item);
//            setEditForm(item);
        });

        createAdapter(itemAdapter, DB.getItems());

        // search map view
        etSearch = findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(view -> handleSearch());
        // search when typing
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                handleSearch();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    // hande Search
    private void handleSearch() {
        if (etSearch.getText().toString().isEmpty()) {
            createAdapter(itemAdapter, DB.getItems());
            return;
        }
        String search = etSearch.getText().toString();
        List<Item> items = DB.search(search);
        createAdapter(itemAdapter, items);
    }


    private void handleAddButton() {
        if (!validateForm()) {
            return;
        }
        String title = etTitle.getText().toString();
        String content = etContent.getText().toString();
        int price = Integer.parseInt(etPrice.getText().toString());
        System.out.println("price: " + etPrice.getText().toString());

        int image = listItemImage.get(sImage.getSelectedItemPosition()).getImage();
        Item item = new Item(title, content, image);
        item.setPrice(price);
        DB.addItem(item);
        itemAdapter.setItemListener(new ItemAdapter.ItemListener() {
            @Override
            public void onClickItem(View view, int position) {
                System.out.println("position: " + position);
                setEditForm(DB.getItems().get(position));
            }
        });
        createAdapter(itemAdapter, DB.getItems());
    }

    private void handleEvent() {
        btnAdd.setOnClickListener(view -> handleAddButton());
    }


    private void setEditForm(Item item) {
        System.out.println("item: " + item);
        etTitle.setText(item.getTitle());
        etContent.setText(item.getContent());
        etPrice.setText(String.valueOf(item.getPrice()));
        System.out.println("SpinerImageAdapter: " + item.getImage() + "");
//        sImage.setSelection();
        // find image int and set selecion
        for (int i = 0; i < listItemImage.size(); i++) {
            if (listItemImage.get(i).getImage() == item.getImage()) {
                sImage.setSelection(i);
            }
        }
    }

    private boolean validateForm() {
        if (etTitle.getText().toString().isEmpty()) {
            etTitle.setError("Title is required");
            return false;
        }
        if (etContent.getText().toString().isEmpty()) {
            etContent.setError("Content is required");
            return false;
        }
        if (etPrice.getText().toString().isEmpty()) {
            etPrice.setError("Price is required");


            return false;
        }

        return true;
    }


    private void createAdapter(ItemAdapter adapter, List<Item> items) {
        adapter.setItems(items);
        rvItem.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvItem.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
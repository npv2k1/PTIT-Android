package com.example.nguyenth1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nguyenth1.adapter.SpnImageAdapter;
import com.example.nguyenth1.db.DB;
import com.example.nguyenth1.model.Item;
import com.example.nguyenth1.adapter.RVItemAdapter;
import com.example.nguyenth1.model.SpnItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // TODO: Khai báo các biến toàn cục
    // Search
    private EditText etSearch;
    private ImageButton btnSearch;

    private RecyclerView rvItem;
    private RVItemAdapter itemAdapter;
    private EditText etTitle, etContent, etPrice;
    private Button btnAdd;
    private Spinner spinnerImage;

    private ArrayList<SpnItem> listItemImage = new ArrayList<>();

    // handle event

    private Item currentSelectItem = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     // hide action bar
        getSupportActionBar().hide();

        // init view
        initView();
        makeImageSpiner();
        makeSearchBar();
        handleEvent();
    }

    // TODO: map các biến với các view trong layout
    private void initView() {
        // mapping
        rvItem = findViewById(R.id.rvItem);
        btnAdd = findViewById(R.id.btnAdd);
        etTitle = findViewById(R.id.etTitle);
        etPrice = findViewById(R.id.etPrice);
        etContent = findViewById(R.id.etContent);
        spinnerImage = findViewById(R.id.sImage);

        itemAdapter = new RVItemAdapter(this);
        itemAdapter.setItemListener(new RVItemAdapter.ItemListener() {
            @Override
            public void onClickItem(View view, int position) {
                System.out.println("position: " + position);
                setEditForm(DB.getItems().get(position));
            }
        });
        createAdapter(itemAdapter, DB.getItems());
    }

    private void makeImageSpiner(){
        // create list image for spiner
        listItemImage.add(new SpnItem(R.drawable.ic_launcher_background));
        listItemImage.add(new SpnItem(R.drawable.ic_baseline_phone_iphone_24));
        listItemImage.add(new SpnItem(R.drawable.ic_baseline_tablet_android_24));
        // set adapter for spiner
        spinnerImage.setAdapter(new SpnImageAdapter(this, listItemImage));

    }

    private void makeSearchBar(){
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
            Toast.makeText(this, "Form error", Toast.LENGTH_SHORT).show();
            return;
        }
        String title = etTitle.getText().toString();
        String content = etContent.getText().toString();
        int price = Integer.parseInt(etPrice.getText().toString());
        System.out.println("price: " + etPrice.getText().toString());

        int image = listItemImage.get(spinnerImage.getSelectedItemPosition()).getImage();
        Item item = new Item(title, content, image);
        item.setPrice(price);
        if(currentSelectItem != null) {
            item.setId(currentSelectItem.getId());
            boolean updateStatus =  DB.updateItem(item);
            currentSelectItem = null;
        } else {
            DB.addItem(item);
        }
        createAdapter(itemAdapter, DB.getItems());
        updateButtonText();
    }

    private void handleEvent() {
        btnAdd.setOnClickListener(view -> handleAddButton());
    }

    private void updateButtonText() {
        if (currentSelectItem == null) {
            btnAdd.setText("Add");
        } else {
            btnAdd.setText("Update");
        }
    }


    /**
     * It sets the edit form.
     *
     * @param item Item{id=1, title='title', content='content', price=100,
     * image=R.drawable.ic_launcher_background}
     */
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
                spinnerImage.setSelection(i);
            }
        }
        currentSelectItem = item;
        updateButtonText();

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


    private void createAdapter(RVItemAdapter adapter, List<Item> items) {
        adapter.setItems(items);
        rvItem.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvItem.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
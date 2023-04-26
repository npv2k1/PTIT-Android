package com.example.nguyenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nguyenapp.adapter.RVItemAdapter;
import com.example.nguyenapp.adapter.SpnImageAdapter;
import com.example.nguyenapp.database.Database;
import com.example.nguyenapp.database.ItemDB;
import com.example.nguyenapp.model.Item;
import com.example.nguyenapp.model.SpnItem;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {
    // TODO: Khai báo các biến toàn cục


    private EditText etTitle, etContent, etPrice;
    private Button btnAdd;
    private Spinner spinnerImage;

    private ArrayList<SpnItem> listItemImage = new ArrayList<>();

    // handle event

    private Item currentSelectItem = null;

    // ItemDB
    private ItemDB itemDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().hide();

        // init db
        itemDB = new ItemDB(this);


        // init view
        initView();
        makeImageSpiner();
        handleEvent();
        String type = getIntent().getStringExtra("type");
        System.out.println("type: " + type);
        if (type.equals("edit")) {
            currentSelectItem = itemDB.getItem(getIntent().getIntExtra("id", 0));
            System.out.println("currentSelectItem: " + currentSelectItem);
            etTitle.setText(currentSelectItem.getTitle());
            etContent.setText(currentSelectItem.getContent());
            etPrice.setText(currentSelectItem.getPrice() + "");
            btnAdd.setText("Update");
        }
    }

    // TODO: map các biến với các view trong layout
    private void initView() {
        // mapping
        btnAdd = findViewById(R.id.btnAdd);
        etTitle = findViewById(R.id.etTitle);
        etPrice = findViewById(R.id.etPrice);
        etContent = findViewById(R.id.etContent);
        spinnerImage = findViewById(R.id.sImage);

    }

    private void makeImageSpiner(){
        // create list image for spiner
        listItemImage.add(new SpnItem(R.drawable.ic_launcher_background));
        listItemImage.add(new SpnItem(R.drawable.ic_baseline_add_24));
        spinnerImage.setAdapter(new SpnImageAdapter(this, listItemImage));

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

        if(btnAdd.getText().equals("Update")){
            item.setId(currentSelectItem.getId());
            itemDB.update(item);
        } else {
            itemDB.add(item);
        }
//        itemDB.add(item);


        itemDB.getItems().forEach(item1 -> {
            System.out.println("item1: " + item1);
        });



//        if(currentSelectItem != null) {
//            item.setId(currentSelectItem.getId());
//            boolean updateStatus =  Database.updateItem(item);
//            currentSelectItem = null;
//        } else {
//            Database.addItem(item);
//        }
        updateButtonText();
        Toast.makeText(this, "Add success", Toast.LENGTH_SHORT).show();
        // Set success result
        setResult(RESULT_OK);
        finish();

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
}
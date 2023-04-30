package com.example.nguyenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nguyenapp.adapter.SpnImageAdapter;
import com.example.nguyenapp.database.ItemDB;
import com.example.nguyenapp.model.Item;
import com.example.nguyenapp.model.SpnItem;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {
    // TODO: Khai báo các biến toàn cục


    private EditText etAddress, etPrice, etMaxPeople, etArea;
    private Button btnAdd;
    private Spinner spinnerImage;

    private CheckBox cbWifi, cbDieuHoa, cbMayGiat;

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
            btnAdd.setText("Update");
            etAddress.setText(currentSelectItem.getAddress());
            etPrice.setText(String.valueOf(currentSelectItem.getPrice()));
            etArea.setText(String.valueOf(currentSelectItem.getArea()));
            etMaxPeople.setText(String.valueOf(currentSelectItem.getMaxPeople()));
            cbWifi.setChecked(currentSelectItem.isWifiService());
            cbDieuHoa.setChecked(currentSelectItem.isDieuHoaService());
            cbMayGiat.setChecked(currentSelectItem.isMayGiatService());
//            spinnerImage.setSelection(currentSelectItem.getImage());


        }
    }

    // TODO: map các biến với các view trong layout
    private void initView() {
        // mapping
        btnAdd = findViewById(R.id.btnAdd);
        etAddress = findViewById(R.id.etTitle);
        etPrice = findViewById(R.id.etPrice);
//        etContent = findViewById(R.id.etContent);
        spinnerImage = findViewById(R.id.sImage);
        cbWifi = findViewById(R.id.cbWifi);
        cbDieuHoa = findViewById(R.id.cbDieuHoa);
        cbMayGiat = findViewById(R.id.cbMayGiat);
        etMaxPeople = findViewById(R.id.etMax);
        etArea =    findViewById(R.id.etDienTich);
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
        String title = etAddress.getText().toString();
//        String content = etContent.getText().toString();
        int area = Integer.parseInt(etArea.getText().toString());
        int price = Integer.parseInt(etPrice.getText().toString());
        System.out.println("price: " + etPrice.getText().toString());

        int image = listItemImage.get(spinnerImage.getSelectedItemPosition()).getImage();

        boolean wifi = cbWifi.isChecked();
        boolean dieuHoa = cbDieuHoa.isChecked();
        boolean mayGiat = cbMayGiat.isChecked();
        int maxPeople = Integer.parseInt(etMaxPeople.getText().toString());

        Item item = new Item();
        item.setWifiService(wifi);
        item.setDieuHoaService(dieuHoa);
        item.setMayGiatService(mayGiat);
        item.setMaxPeople(maxPeople);
        item.setPrice(price);
        item.setAddress(title);
        item.setArea(area);
        item.setImage(image);


        if(btnAdd.getText().equals("Update")){
            item.setId(currentSelectItem.getId());
            itemDB.update(item);
        } else {
            itemDB.add(item);
        }
//        itemDB.add(item);


//        itemDB.getItems().forEach(item1 -> {
//            System.out.println("item1: " + item1);
//        });



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
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAddButton();
            }
        });
    }

    private void updateButtonText() {
        if (currentSelectItem == null) {
            btnAdd.setText("Add");
        } else {
            btnAdd.setText("Update");
        }
    }



//    private void setEditForm(Item item) {
//        System.out.println("item: " + item);
//        etTitle.setText(item.getAddress());
//        etContent.setText(item.getContent());
//        etPrice.setText(String.valueOf(item.getPrice()));
//        System.out.println("SpinerImageAdapter: " + item.getImage() + "");
////        sImage.setSelection();
//        // find image int and set selecion
//        for (int i = 0; i < listItemImage.size(); i++) {
//            if (listItemImage.get(i).getImage() == item.getImage()) {
//                spinnerImage.setSelection(i);
//            }
//        }
//        currentSelectItem = item;
//        updateButtonText();
//
//    }

    private boolean validateForm() {
        if (etAddress.getText().toString().isEmpty()) {
            etAddress.setError("Title is required");
            return false;
        }
        if(etArea.getText().toString().isEmpty()){
            etArea.setError("Area is required");
            return false;
        }
        if(etMaxPeople.getText().toString().isEmpty()){
            etMaxPeople.setError("Max people is required");
            return false;
        }

//        if (etContent.getText().toString().isEmpty()) {
//            etContent.setError("Content is required");
//            return false;
//        }
        if (etPrice.getText().toString().isEmpty()) {
            etPrice.setError("Price is required");
            return false;
        }

        return true;
    }
}
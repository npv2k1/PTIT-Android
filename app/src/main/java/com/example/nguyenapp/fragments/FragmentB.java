package com.example.nguyenapp.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenapp.R;
import com.example.nguyenapp.adapter.RVItemAdapter;
import com.example.nguyenapp.adapter.RVSearchItemAdapter;
import com.example.nguyenapp.database.Database;
import com.example.nguyenapp.database.ItemDB;
import com.example.nguyenapp.model.Item;

import java.util.List;

public class FragmentB extends Fragment {
    private ItemDB itemDB;
    private RecyclerView rvItem;
    private RVSearchItemAdapter itemAdapter;

    private EditText etSearch;
    private ImageButton btnSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_b, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);

        itemDB = new ItemDB(getContext());

        rvItem = view.findViewById(R.id.rvItem);
        etSearch = view.findViewById(R.id.etSearch);


        itemAdapter = new RVSearchItemAdapter(getContext());
        itemAdapter.setItemListener(new RVSearchItemAdapter.ItemListener() {
            @Override
            public void onClickItem(View view, int position) {
                System.out.println("position: " + position);
//                setEditForm(DB.getItems().get(position));
            }
        });

        createAdapter(itemAdapter, itemDB.getItems());


        btnSearch = view.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etSearch.getText().toString().isEmpty()) {
                    createAdapter(itemAdapter, itemDB.getItems());
                    return;
                }
                String search = etSearch.getText().toString();
                List<Item> items = (List<Item>) itemDB.search(search);
                createAdapter(itemAdapter, items);
            }
        });

        // search on text change
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etSearch.getText().toString().isEmpty()) {
                    createAdapter(itemAdapter, itemDB.getItems());
                    return;
                }
                String search = etSearch.getText().toString();
                List<Item> items = (List<Item>) itemDB.search(search);
                createAdapter(itemAdapter, items);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


    }

    private void createAdapter(RVSearchItemAdapter adapter, List<Item> items) {
        adapter.setItems(items);
        rvItem.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rvItem.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}

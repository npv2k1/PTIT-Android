package com.example.nguyenapp.fragments;

import android.os.Bundle;
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
import com.example.nguyenapp.database.Database;
import com.example.nguyenapp.model.Item;

import java.util.List;

public class FragmentB extends Fragment {
    private RecyclerView rvItem;
    private RVItemAdapter itemAdapter;

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

        super.onViewCreated(view, savedInstanceState);

        rvItem = view.findViewById(R.id.rvItem);


        itemAdapter = new RVItemAdapter(getContext());
        itemAdapter.setItemListener(new RVItemAdapter.ItemListener() {
            @Override
            public void onClickItem(View view, int position) {
                System.out.println("position: " + position);
//                setEditForm(DB.getItems().get(position));
            }
        });

        createAdapter(itemAdapter, Database.getItems());


        btnSearch = view.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etSearch.getText().toString().isEmpty()) {
                    createAdapter(itemAdapter, Database.getItems());
                    return;
                }
                String search = etSearch.getText().toString();
                List<Item> items = Database.search(search);
                createAdapter(itemAdapter, items);
            }
        });


    }

    private void createAdapter(RVItemAdapter adapter, List<Item> items) {
        adapter.setItems(items);
        rvItem.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rvItem.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}

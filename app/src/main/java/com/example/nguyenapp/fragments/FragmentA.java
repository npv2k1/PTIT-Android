package com.example.nguyenapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenapp.AddActivity;
import com.example.nguyenapp.MainActivity;
import com.example.nguyenapp.R;
import com.example.nguyenapp.adapter.AdapterViewpager;
import com.example.nguyenapp.adapter.RVItemAdapter;
import com.example.nguyenapp.database.Database;
import com.example.nguyenapp.database.ItemDB;
import com.example.nguyenapp.model.Item;

import java.util.List;


public class FragmentA extends Fragment {

    private Context context;
    private ItemDB itemDB;
    private RecyclerView rvItem;
    private RVItemAdapter itemAdapter;
    private ImageButton btnReload;
    private  AdapterViewpager.onReload onReload;

    public FragmentA() {
    }

    public FragmentA(Context context, AdapterViewpager.onReload onReload) {
        this.context = context;
        this.onReload = onReload;

    }

    // reload
    public void reload() {
        itemAdapter.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_a, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        itemDB = new ItemDB(getContext());
        rvItem = view.findViewById(R.id.rvItem);
        btnReload = view.findViewById(R.id.btnReload);

        itemAdapter = new RVItemAdapter(getContext());
        itemAdapter.setItemListener(new RVItemAdapter.ItemListener() {
            @Override
            public void onClickItem(View view, Item item) {
//                System.out.println("position: " + position);
                // open intent

                Intent intent = new Intent(context, AddActivity.class);
                // send data
                intent.putExtra("type", "edit");
                intent.putExtra("id", item.getId());

                startActivityForResult(intent, 1);

            }
        });
        itemAdapter.setItemDeleteListener(new RVItemAdapter.ItemDeleteListener() {
            @Override
            public void onClickItemDelete(View view, Item item) {
                itemDB.remove(item);
//                createAdapter(itemAdapter, itemDB.getItems());
                onReload.reload();
            }
        });


        createAdapter(itemAdapter, itemDB.getItems());

        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAdapter(itemAdapter, itemDB.getItems());
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

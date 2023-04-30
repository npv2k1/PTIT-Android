package com.example.nguyenapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.nguyenapp.R;
import com.example.nguyenapp.model.SpnItem;

import java.util.ArrayList;

public class SpnImageAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SpnItem> lists;

    public SpnImageAdapter(Context context, ArrayList<SpnItem> lists) {
        this.context = context;
        this.lists = lists;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<SpnItem> getLists() {
        return lists;
    }

    public void setLists(ArrayList<SpnItem> lists) {
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lists.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_item, parent, false);
        ImageView img  = itemView.findViewById(R.id.ivImage);
        img.setImageResource(lists.get(position).getImage());
        return itemView;
    }
}

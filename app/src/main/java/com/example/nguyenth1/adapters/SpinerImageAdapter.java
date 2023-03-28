package com.example.nguyenth1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.nguyenth1.R;
import com.example.nguyenth1.model.ItemImage;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class SpinerImageAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ItemImage> lists;

    public SpinerImageAdapter(Context context, ArrayList<ItemImage> lists) {
        this.context = context;
        this.lists = lists;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<ItemImage> getLists() {
        return lists;
    }

    public void setLists(ArrayList<ItemImage> lists) {
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

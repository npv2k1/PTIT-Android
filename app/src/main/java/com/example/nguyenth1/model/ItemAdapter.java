package com.example.nguyenth1.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenth1.R;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder>{

    private List<Item> items;

    private Context context;
    public ItemAdapter(Context context) {
        items = new ArrayList<>();
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
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item item = items.get(position);
        if (item == null) return;
        holder.tvTitle.setText(item.getTitle());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return items==null?0:items.size();
    }

    // Define the view holder
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitile);
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}

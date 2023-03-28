package com.example.nguyenth1.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenth1.R;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder>{

    private List<Item> items = new ArrayList<>();
    private View.OnClickListener holderClickListener;

    public View.OnClickListener getHolderClickListener() {
        return holderClickListener;
    }

    public void setHolderClickListener(View.OnClickListener holderClickListener) {
        this.holderClickListener = holderClickListener;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private Context context;
    public ItemAdapter(Context context) {
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
        holder.tvContent.setText(item.getContent());
        holder.ivImage.setImageResource(item.getImage());
        holder.tvPrice.setText(String.valueOf(item.getPrice()));
        holder.ibDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return items==null?0:items.size();
    }

    // Define the view holder
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvTitle;
        private TextView tvContent;
        private TextView tvPrice;
        private ImageView ivImage;
        private ImageButton ibDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);
            ivImage = itemView.findViewById(R.id.img);
            ibDelete = itemView.findViewById(R.id.ibDelete);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (itemListener == null) return;
            System.out.println("Click item");
            System.out.println(itemListener);
            itemListener.onClickItem(view, getAdapterPosition());
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    public interface ItemListener {
        void onClickItem (View view, int position);
    }
    private ItemListener itemListener;

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }
}

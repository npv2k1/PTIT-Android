package com.example.nguyenapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.nguyenapp.R;
import com.example.nguyenapp.database.ItemDB;
import com.example.nguyenapp.model.Item;

import java.util.ArrayList;
import java.util.List;

public class RVSearchItemAdapter extends RecyclerView.Adapter<RVSearchItemAdapter.MyViewHolder> {

    private Context context;
    private List<Item> items = new ArrayList<>();
    private ItemDB itemDB;





    // A listener for the item in the RecyclerView.
    public interface ItemListener {
        void onClickItem(View view, Item item );
    }

    public interface ItemDeleteListener {
        void onClickItemDelete(View view, Item item);
    }
    private ItemDeleteListener itemDeleteListener;

    private ItemListener itemListener;


    public RVSearchItemAdapter(Context context) {
        this.context = context;
        itemDB = new ItemDB(context);
    }



    // TODO: Định nghĩa viewHolder
    /**
     * The MyViewHolder class is a subclass of RecyclerView.ViewHolder. It implements the
     * View.OnClickListener interface
     */
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvAddress;
        private TextView tvArea;

        private TextView tvPrice;
        private ImageView ivImage;
        private ImageButton ibDelete;

        private TextView tvMaxPeople;

        private TextView tvServices;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvArea = itemView.findViewById(R.id.tvArea);
            ivImage = itemView.findViewById(R.id.img);
//            ibDelete = itemView.findViewById(R.id.ibDelete);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvMaxPeople = itemView.findViewById(R.id.tvMaxPeople);
            tvServices = itemView.findViewById(R.id.tvServices);
            itemView.setOnClickListener(this);

        }


        // Implement the onClick method of the View.OnClickListener interface
        @Override
        public void onClick(View view) {
            if (itemListener == null) return;
            System.out.println("Click item");
            System.out.println(itemListener);

            // Call the onClickItem method of the ItemListener interface
            itemListener.onClickItem(view, items.get(getAdapterPosition()));
        }
    }

    // TODO: implement các phương thức của RecyclerView.Adapter

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // TODO: Inflate the layout for the item
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Item item = items.get(position);
        if (item == null) return;
        // TODO: thay thế nôi dung của viewHolder bằng nôi dung của item
        holder.tvAddress.setText(item.getAddress());
        holder.tvArea.setText(item.getArea()+ " m2");
        holder.ivImage.setImageResource(item.getImage());
        holder.tvPrice.setText(String.valueOf(item.getPrice()) + " VND");
        holder.tvMaxPeople.setText("Max people"+String.valueOf(item.getMaxPeople()));
        String services = "";
        if(item.isWifiService()){
            services += "Wifi, ";
        }
        if(item.isDieuHoaService()){
            services += "Điều hòa, ";
        }
        if(item.isMayGiatService()){
            services += "Máy giặt, ";
        }
        holder.tvServices.setText(services);



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }


    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public void setItemDeleteListener(ItemDeleteListener itemDeleteListener) {
        this.itemDeleteListener = itemDeleteListener;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}

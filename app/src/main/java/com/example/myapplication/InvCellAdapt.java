package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InvCellAdapt  extends RecyclerView.Adapter<InvCellAdapt.ViewHolder> {

    private List<ItemDB> itemList;
    private ItemDao itemDao;

    public InvCellAdapt(List<ItemDB> itemList, ItemDao itemDao){
        this.itemList = itemList;
        this.itemDao = itemDao;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView itemQty;
        TextView itemLoc;
        //Button manageBtn;

        public ViewHolder(View itemView){
            super(itemView);
            itemName = itemView.findViewById(R.id.Item);
            itemQty = itemView.findViewById(R.id.ItemQuant);
            itemLoc = itemView.findViewById(R.id.ItemLoc);
            //manageBtn = itemView.findViewById(R.id.Item);
        }
    }

    @NonNull
    @Override
    public InvCellAdapt.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_layout, parent, false);

        return new ViewHolder(inView);
    }

    @Override
    public void onBindViewHolder(@NonNull InvCellAdapt.ViewHolder holder, int position) {
        ItemDB item = itemList.get(position);

        holder.itemName.setText(item.itemName);
        holder.itemQty.setText(String.valueOf(item.itemQuant));
        holder.itemLoc.setText(item.itemLoc);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

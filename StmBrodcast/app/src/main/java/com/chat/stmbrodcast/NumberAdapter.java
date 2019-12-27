package com.chat.stmbrodcast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.ItemViewHolder>{

    private ArrayList<IncomingNum> arrayList;

    public NumberAdapter(ArrayList<IncomingNum> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.ID.setText(Integer.toString(arrayList.get(position).getId()));
        holder.NUMBER.setText(arrayList.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView ID, NUMBER;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ID= itemView.findViewById(R.id.itemId);
            NUMBER= itemView.findViewById(R.id.numberId);
        }
    }
}

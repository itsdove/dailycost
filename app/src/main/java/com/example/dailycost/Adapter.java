package com.example.dailycost;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    List<Cost> costs;
    private Context mcontext;

    public Adapter(List<Cost> costList) {
        this.costs = costList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mcontext=parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cost cost= costs.get(position);
        holder.imageView.setImageResource(cost.getImagid());
        if(cost.getCost()==1)
        holder.textView.setText("支出");
        holder.number.setText(cost.getMoney().toString());

    }

    @Override
    public int getItemCount() {
        return costs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.textview);
            number = itemView.findViewById(R.id.number);
        }



    }
}

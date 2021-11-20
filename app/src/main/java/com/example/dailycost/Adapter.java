package com.example.dailycost;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        holder.textView.setText(cost.getReason());
        if(cost.getCost()==1)
        { holder.number.setText("-"+cost.getMoney().toString());
        holder.imageView.setImageResource(R.drawable.pay);
        holder.number.setTextColor(Color.RED);}
        else
        { holder.number.setText("+"+cost.getMoney().toString());
            holder.imageView.setImageResource(R.drawable.income);
            holder.number.setTextColor(0xff096605);}
            Log.d("asdasdad",cost.getDate()+" ");
            holder.date.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cost.getDate()));
    }

    @Override
    public int getItemCount() {
        return costs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView number;
        TextView date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.textview);
            number = itemView.findViewById(R.id.number);
            date=itemView.findViewById(R.id.date);
        }



    }
}

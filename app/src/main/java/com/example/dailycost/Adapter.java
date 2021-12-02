package com.example.dailycost;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dailycost.ui.home.DetailFragment;

import org.litepal.LitePal;

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

    public class ViewHolder extends RecyclerView.ViewHolder implements MenuItem.OnMenuItemClickListener,View.OnCreateContextMenuListener {
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
            itemView.setOnCreateContextMenuListener(this);
        }
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem menuItem1 = menu.add(Menu.NONE, 1, 1, "修改");
            menuItem1.setOnMenuItemClickListener(this);
            MenuItem menuItem2 = menu.add(Menu.NONE, 2, 2, "删除");
            menuItem2.setOnMenuItemClickListener(this);
        }
        @Override
        public boolean onMenuItemClick(MenuItem menuItem){
            int position=getAdapterPosition();
            switch (menuItem.getItemId()){
                case 1:
                    View dialagueView= LayoutInflater.from(mcontext).inflate(R.layout.dialogview,null);
                    AlertDialog.Builder alertDialog=new AlertDialog.Builder(mcontext);
                    alertDialog.setView(dialagueView);
                    EditText editText1 = dialagueView.findViewById(R.id.ed1);
                    EditText editText = dialagueView.findViewById(R.id.ed);
                    RadioButton rd1 = dialagueView.findViewById(R.id.btn1);
                    RadioButton rd2 = dialagueView.findViewById(R.id.btn2);
                    Cost cost=costs.get(position);
                    editText.setText(cost.getReason());
                    editText1.setText(cost.getMoney().toString());
                    if(cost.getCost()==1)
                        rd1.setChecked(true);
                    else
                        rd2.setChecked(true);
                    double m=cost.getMoney();
                    int n=cost.getCost();
                    alertDialog.setPositiveButton("确定",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            cost.setMoney(Double.parseDouble(editText1.getText().toString()));
                            cost.setReason(editText.getText().toString());
                            cost.setCost(rd1.isChecked()?1:0);
                            costs.set(position,cost);
                            cost.save();
                            Adapter.this.notifyItemChanged(position);
                            DetailFragment.update(n,m,cost.getCost(),cost.getMoney());
                        }
                    });
                    alertDialog.setCancelable(false).setNegativeButton ("取消",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    alertDialog.create().show();
                    break;
                case 2:
                    cost=costs.get(position);
                    costs.remove(position);
                    Adapter.this.notifyItemRemoved(position);
                    DetailFragment.update(cost.getCost(),cost.getMoney(),1,0.0);
                    cost.delete();

                    break;

            }
            return true;
        }

    }
}

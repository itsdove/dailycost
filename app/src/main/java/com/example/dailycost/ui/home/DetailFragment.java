package com.example.dailycost.ui.home;

import static android.app.Activity.RESULT_OK;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dailycost.Cost;
import com.example.dailycost.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class DetailFragment extends Fragment{

    private List<Cost> costs= new LinkedList<>();
    RecyclerView recyclerView;
    Adapter adapter;
    TextView sum;
    TextView sum2;
    Double pay=0.0;
    Double income=0.0;
    FloatingActionButton floatingActionButton;
    int m;
    int position;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LitePal.getDatabase();
        View root = inflater.inflate(R.layout.fragment_detail, container, false);
        Bundle bundle = getArguments();
        if (bundle != null){
            position = bundle.getInt("month");
        }else {
            position = 0;
        }
        recyclerView=root.findViewById(R.id.recycleview);
        sum = root.findViewById(R.id.sum);
        sum2 = root.findViewById(R.id.sum2);
            m = position;
            for (Cost c : LitePal.findAll(Cost.class)) {
                if (c.getM() == (position)) {
                    costs.add(c);
                    if (c.getCost() == 1)
                        pay += c.getMoney();
                    else
                        income += c.getMoney();
                }
            }
            floatingActionButton = root.findViewById(R.id.fbutton);
            floatingActionButton.setOnClickListener(view -> {
                      Intent intent=new Intent(getContext(),AddActivity.class);
                      launcherAdd.launch(intent);
            });
            sum.setText("￥"+pay);
            sum2.setText("￥"+income);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            adapter = new Adapter(costs);
            recyclerView.setAdapter(adapter);
            recyclerView.addItemDecoration(new SpacesItemDecoration(8));
            return root;
    }
    ActivityResultLauncher<Intent> launcherAdd = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            Intent data = result.getData();
            int resultCode = result.getResultCode();
            if(resultCode==RESULT_OK){
                if(null==data)return;
                Cost c=new Cost(data.getDoubleExtra("money",0));
                    c.setReason(data.getStringExtra("reason"));
                    c.setCost(data.getIntExtra("cost",-1));
                    c.setDate(new Date());
                    c.setM(m);
                    c.setImagid(data.getIntExtra("imagine",0));
                    costs.add(c);
                    c.save();
                adapter.notifyItemInserted(costs.size());
                update(0,0,c.getCost(),c.getMoney());
            }
            else{
                if(null==data)return;
                int pos=data.getIntExtra("pos",-1);
                Cost c=costs.get(pos);
                double mo=c.getMoney();
                int i=c.getCost();
                c.setMoney(data.getDoubleExtra("money",0));
                c.setReason(data.getStringExtra("reason"));
                c.setCost(data.getIntExtra("cost",-1));
                c.setDate(new Date());
                c.setM(m);
                c.setImagid(data.getIntExtra("imagine",0));
                costs.set(pos,c);
                c.save();
                update(i,mo,c.getCost(),c.getMoney());
                adapter.notifyItemChanged(pos);
            }
        }
    });
    public  void update(int y,double my,int i,double money){
        switch (y){
            case 1:if(i==1)
                pay-=(my-money);
            else
            { pay-=my;
                income+=money;}
                break;
            case 0:
                if(i==1)
                { pay+=money;
                income-=my; }
                else
                income-=(my-money);
                break;
        }
        sum.setText("支出:￥"+pay+"收入:￥"+income);
    }

 class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<Cost> costs;

    public Adapter(List<Cost> costList) {
        this.costs = costList;
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout, parent, false);
        return new Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        Cost cost = costs.get(position);
        holder.imageView.setImageResource(cost.getImagid());
        holder.textView.setText(cost.getReason());
        if (cost.getCost() == 1) {
            holder.number.setText("-" + cost.getMoney().toString());
            holder.number.setTextColor(Color.RED);
        } else {
            holder.number.setText("+" + cost.getMoney().toString());
            holder.number.setTextColor(0xff096605);
        }
        holder.imageView.setImageResource(cost.Imagid);
        holder.date.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cost.getDate()));
    }

    @Override
    public int getItemCount() {
        return costs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements MenuItem.OnMenuItemClickListener, View.OnCreateContextMenuListener {
        ImageView imageView;
        TextView textView;
        TextView number;
        TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.textview);
            number = itemView.findViewById(R.id.number);
            date = itemView.findViewById(R.id.date);
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                Intent intent=new Intent(getContext(),AddActivity.class);
                intent.putExtra("why",1);
                intent.putExtra("pos",position);
                intent.putExtra("money",costs.get(position).getMoney());
                intent.putExtra("reason",costs.get(position).getReason());
                intent.putExtra("ima",costs.get(position).getImagid());
                launcherAdd.launch(intent);
            });
            itemView.setOnCreateContextMenuListener(this);
        }

        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem menuItem2 = menu.add(Menu.NONE, 2, 2, "删除");
            menuItem2.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            int position = getAdapterPosition();
            Cost cost = costs.get(position);
            costs.remove(position);
            adapter.notifyItemRemoved(position);
            update(cost.getCost(), cost.getMoney(), 1, 0.0);
            cost.delete();

            return true;
        }

    }
}
    public static DetailFragment newInstance(Integer i){
        DetailFragment movieFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("month",i);
        movieFragment.setArguments(bundle);
        return movieFragment;
    }

}
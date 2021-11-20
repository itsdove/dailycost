package com.example.dailycost.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dailycost.Adapter;
import com.example.dailycost.Cost;
import com.example.dailycost.R;
import com.example.dailycost.SpacesItemDecoration;
import com.example.dailycost.databinding.FragmentHomeBinding;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.litepal.LitePal;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import com.google.type.DateTime;
public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private List<Cost> costs= new LinkedList<>();
    RecyclerView recyclerView;
    Adapter adapter;
    TextView sum;
    private int mSelectPosition;
    Double pay=0.0;
    Double income=0.0;
    FloatingActionButton floatingActionButton;
    Spinner spinner;
    int m;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
            LitePal.getDatabase();
            homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
            binding = FragmentHomeBinding.inflate(inflater, container, false);
            View root = binding.getRoot();
            sum=root.findViewById(R.id.sum);
            spinner=root.findViewById(R.id.spinner);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //选择了不同的选项，调用这个
                m=position;
                pay=0.0;
                income=0.0;
                costs.clear();
                for(Cost c:LitePal.findAll(Cost.class)){
                    if(c.getM()==(position))
                    {costs.add(c);
                        if(c.getCost()==1)
                            pay+=c.getMoney();
                        else
                            income+=c.getMoney();}
                }
                sum.setText("支出:￥"+pay+"收入:￥"+income);
                adapter = new Adapter(costs);
                recyclerView.setAdapter(adapter);
                 }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
                floatingActionButton = root.findViewById(R.id.fbutton);
                floatingActionButton.setOnClickListener(view -> {
                View dialagueView = LayoutInflater.from(getContext()).inflate(R.layout.dialogview, null);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setView(dialagueView);
                alertDialog.setPositiveButton("确定", (dialogInterface, i) -> {
                    EditText editText = dialagueView.findViewById(R.id.ed);
                    EditText editText1 = dialagueView.findViewById(R.id.ed1);
                    RadioGroup radgroup =  dialagueView.findViewById(R.id.radioGroup);
                    RadioButton rd = (RadioButton) radgroup.getChildAt(0);
                    Double money=Double.parseDouble(editText1.getText().toString());
                    Cost cost = new Cost(money);
                    if (rd.isChecked())
                    {cost.setCost(1);
                        pay+=money;}
                    else {
                        cost.setCost(0);
                        income+=money;
                    }
                   cost.setM(m);
                    cost.setDate(new Date());
                    cost.setReason(editText.getText().toString());
                    costs.add(cost);
                    cost.save();
                    sum.setText("支出:￥"+pay+"收入:￥"+income);
                    adapter.notifyItemChanged(mSelectPosition);
                });
                alertDialog.setCancelable(false).setNegativeButton("取消", (dialogInterface, i) -> {
                });
                alertDialog.create().show();
            });
            sum.setText("支出:￥"+pay+"收入:￥"+income);
            recyclerView = root.findViewById(R.id.recycleview);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            adapter = new Adapter(costs);
            recyclerView.setAdapter(adapter);
            recyclerView.addItemDecoration(new SpacesItemDecoration(8));
             return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;

    }
}
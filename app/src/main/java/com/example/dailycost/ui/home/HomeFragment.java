package com.example.dailycost.ui.home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
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

import java.util.LinkedList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private List<Cost> costs;
    RecyclerView recyclerView;
    Adapter adapter;
    private int mSelectPosition;
    FloatingActionButton floatingActionButton;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        costs=new LinkedList<>();
        floatingActionButton = (FloatingActionButton) root.findViewById(R.id.fbutton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialagueView= LayoutInflater.from(getContext()).inflate(R.layout.dialogview,null);
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(getContext());
                alertDialog.setView(dialagueView);

                alertDialog.setPositiveButton("确定",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                      EditText editText=dialagueView.findViewById(R.id.ed1);
                    Cost cost=new Cost(Double.parseDouble(editText.getText().toString()));
                    cost.setImagid(R.drawable.pay);
                    cost.setCost(1);
                    costs.add(cost);
                   adapter.notifyItemChanged(mSelectPosition);
                    }
                });
                alertDialog.setCancelable(false).setNegativeButton ("取消",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                alertDialog.create().show();
            }
        });
        recyclerView = root.findViewById(R.id.recycleview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(costs);
        recyclerView.setAdapter(adapter);
        int space = 8;
        recyclerView.addItemDecoration(new SpacesItemDecoration(space));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
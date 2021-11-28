package com.example.dailycost.ui.notifications;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dailycost.Cost;
import com.example.dailycost.R;
import com.example.dailycost.databinding.FragmentNotificationsBinding;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.litepal.LitePal;

import java.util.List;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        PieChart pieChart=root.findViewById(R.id.piechart);
        List<Cost> l= LitePal.where("cost=?","1").find(Cost.class);
        Double allmoney=0.0;
        Double money1=0.0;
        Double money2=0.0;
        Double money3=0.0;
        Double money4=0.0;
        Double money5=0.0;
        for(Cost c:l){
            allmoney+=c.getMoney();
            if(c.getReason().equals("交通"))
                money1+=c.getMoney();
            if(c.getReason().equals("餐饮"))
                money2+=c.getMoney();
            if(c.getReason().equals("衣服"))
                money3+=c.getMoney();
            if(c.getReason().equals("话费"))
                money4+=c.getMoney();
            if(c.getReason().equals("房租"))
                money5+=c.getMoney();
        }
        float rest=(float)(1-(money1+money2+money3+money4+money5)/allmoney);
        pieChart.addPieSlice(
                new PieModel(
                        "交通",
                        (float) (money1/allmoney),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "餐饮",
                        (float) (money2/allmoney),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "衣服",
                        (float) (money3/allmoney),
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "话费",
                        (float) (money4/allmoney),
                        Color.parseColor("#29B6F6")));
        pieChart.addPieSlice(
                new PieModel(
                        "房租",
                        (float) (money5/allmoney),
                        Color.parseColor("#FF00FF")));
        pieChart.addPieSlice(
                new PieModel(
                        "其他",
                        rest,
                        Color.parseColor("#ebe437")));
        pieChart.startAnimation();
        TextView textView6=root.findViewById(R.id.c6);
        TextView textView1=root.findViewById(R.id.c1);
        TextView textView2=root.findViewById(R.id.c2);
        TextView textView3=root.findViewById(R.id.c3);
        TextView textView4=root.findViewById(R.id.c4);
        TextView textView5=root.findViewById(R.id.c5);
        textView1.setText(textView1.getText()+""+(int)(money1/allmoney*100)+"%");
        textView2.setText(textView2.getText()+""+(int)(money2/allmoney*100)+"%");
        textView3.setText(textView3.getText()+""+(int)(money3/allmoney*100)+"%");
        textView4.setText(textView4.getText()+""+(int)(money4/allmoney*100)+"%");
        textView5.setText(textView5.getText()+""+(int)(money5/allmoney*100)+"%");
        textView6.setText(textView6.getText()+""+(int)(rest*100)+"%");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
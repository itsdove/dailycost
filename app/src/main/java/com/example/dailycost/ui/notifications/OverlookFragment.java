package com.example.dailycost.ui.notifications;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.dailycost.Cost;
import com.example.dailycost.R;
import com.example.dailycost.databinding.FragmentNotificationsBinding;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.litepal.LitePal;

import java.util.List;

public class OverlookFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        PieChart pieChart=root.findViewById(R.id.piechart);
        List<Cost> l= LitePal.where("cost=?","1").find(Cost.class);
        Double allmoney=0.0;
        Double[] money=new Double[11];
        for(int i=0;i<11;i++){
            money[i]=0.0;
        }
        for(Cost c:l){
            allmoney+=c.getMoney();
            if(c.getReason().equals("交通出行"))
                money[1]+=c.getMoney();
            if(c.getReason().equals("吃喝饮食"))
                money[2]+=c.getMoney();
            if(c.getReason().equals("通讯费用"))
                money[3]+=c.getMoney();
            if(c.getReason().equals("房租水电"))
                money[4]+=c.getMoney();
            if(c.getReason().equals("消费购物"))
                money[5]+=c.getMoney();
            if(c.getReason().equals("运动健身"))
                money[6]+=c.getMoney();
            if(c.getReason().equals("旅游出行"))
                money[7]+=c.getMoney();
            if(c.getReason().equals("基金理财"))
                money[8]+=c.getMoney();
            if(c.getReason().equals("医疗用品"))
                money[9]+=c.getMoney();
            if(c.getReason().equals("日常用品"))
                money[10]+=c.getMoney();
        }
        for(Double c:money){
            money[0]+=c;
        }
        float rest=(float)(1-money[0]/allmoney);
        pieChart.addPieSlice(
                new PieModel(
                        "交通出行",
                        (float) (money[1]/allmoney),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "吃喝饮食",
                        (float) (money[2]/allmoney),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "通讯费用",
                        (float) (money[3]/allmoney),
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "房租水电",
                        (float) (money[4]/allmoney),
                        Color.parseColor("#29B6F6")));
        pieChart.addPieSlice(
                new PieModel(
                        "消费购物",
                        (float) (money[5]/allmoney),
                        Color.parseColor("#FF00FF")));

        pieChart.addPieSlice(
                new PieModel(
                        "运动健身",
                        (float) (money[6]/allmoney),
                        Color.parseColor("#ebe437")));
        pieChart.addPieSlice(
                new PieModel(
                        "旅游出行",
                        (float) (money[7]/allmoney),
                        Color.parseColor("#000080")));
        pieChart.addPieSlice(
                new PieModel(
                        "基金理财",
                        (float) (money[8]/allmoney),
                        Color.parseColor("#6A5ACD")));
        pieChart.addPieSlice(
                new PieModel(
                        "医疗用品",
                        (float) (money[9]/allmoney),
                        Color.parseColor("#8B8989")));
        pieChart.addPieSlice(
                new PieModel(
                        "日常用品",
                        (float) (money[10]/allmoney),
                        Color.parseColor("#FF0000")));
        pieChart.addPieSlice(
                new PieModel(
                        "其他",
                        rest,
                        Color.parseColor("#FFE1FF")));
        pieChart.startAnimation();
        TextView textView1=root.findViewById(R.id.c1);
        TextView textView2=root.findViewById(R.id.c2);
        TextView textView3=root.findViewById(R.id.c3);
        TextView textView4=root.findViewById(R.id.c4);
        TextView textView5=root.findViewById(R.id.c5);
        TextView textView6=root.findViewById(R.id.c6);
        TextView textView7=root.findViewById(R.id.c7);
        TextView textView8=root.findViewById(R.id.c8);
        TextView textView9=root.findViewById(R.id.c9);
        TextView textView10=root.findViewById(R.id.c10);
        TextView textView11=root.findViewById(R.id.c11);
        TextView sum=root.findViewById(R.id.sum);
        sum.setText("总支出:￥"+allmoney);
        textView1.setText(textView1.getText()+""+(int)(money[1]/allmoney*100)+"%");
        textView2.setText(textView2.getText()+""+(int)(money[2]/allmoney*100)+"%");
        textView3.setText(textView3.getText()+""+(int)(money[3]/allmoney*100)+"%");
        textView4.setText(textView4.getText()+""+(int)(money[4]/allmoney*100)+"%");
        textView5.setText(textView5.getText()+""+(int)(money[5]/allmoney*100)+"%");
        textView6.setText(textView6.getText()+""+(int)(money[6]/allmoney*100)+"%");
        textView7.setText(textView7.getText()+""+(int)(money[7]/allmoney*100)+"%");
        textView8.setText(textView8.getText()+""+(int)(money[8]/allmoney*100)+"%");
        textView9.setText(textView9.getText()+""+(int)(money[9]/allmoney*100)+"%");
        textView10.setText(textView10.getText()+""+(int)(money[10]/allmoney*100)+"%");
        textView11.setText(textView11.getText()+""+(int)(rest*100)+"%");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}